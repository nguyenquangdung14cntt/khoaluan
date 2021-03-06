package controller;

import java.security.Principal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ActivitiesDAO;
import dao.CategoriesDAO;
import dao.UsersDAO;
import entities.Activity;
import entities.User;
import utils.SlugUtils;

@Controller
public class PublicActivitiesController {
	@Autowired
	private ActivitiesDAO activityDAO;
	@Autowired
	private CategoriesDAO catDAO;
	@Autowired
	private UsersDAO userDAO;
	@Autowired
	private SlugUtils slugUtils;
	@ModelAttribute
	public void addCommon(ModelMap modelMap,Principal principal){
		modelMap.addAttribute("lCat",catDAO.getItems());
		modelMap.addAttribute("slug",slugUtils);
		modelMap.addAttribute("chunhiem",userDAO.getAdmin());
		modelMap.addAttribute("newActivities",activityDAO.getNewItems());
		if(principal != null){
			modelMap.addAttribute("logged",userDAO.getItem(principal.getName()));
		}
	}
	
	@RequestMapping("/danh-sach-hoat-dong")
	public String index(ModelMap modelMap,@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="row_count", defaultValue="5")int row_count) {
		int offset = (page - 1) * row_count;
		modelMap.addAttribute("listActivities",activityDAO.getNewItems(offset,row_count));
		int total = (int)Math.ceil((float)activityDAO.countNewItems()/row_count);
		modelMap.addAttribute("total",total);
		return "public.activities.index";
	}
	@RequestMapping("/hoat-dong/{slug}/{id}")
	public String detail(ModelMap modelMap,@PathVariable("id") int id){
		Activity activity = activityDAO.getItem(id);
		modelMap.addAttribute("objActivity",activity);
		return "public.activities.detail";
	}
	@RequestMapping(value="/hoat-dong/{slug}/{id}",method=RequestMethod.POST)
	public String add(@PathVariable("id") int id,Principal principal){
		User user = userDAO.getItem(principal.getName());
		Activity activity = activityDAO.getItem(id);
		if(activityDAO.checkRegisted(id,user.getId())>0){
			return "redirect:/danh-sach-hoat-dong?msg=registed";
		}
		if(activity.getJoined()<activity.getLimited()){
			Timestamp joined_at = new Timestamp(System.currentTimeMillis());
			if(activityDAO.registerEvent(id,user.getId(),joined_at)>0){
				if(activityDAO.updateJoined(1,id)>0){
					return "redirect:/lich-su-tham-gia?msg=add-success";
				}
				return "redirect:/danh-sach-hoat-dong?msg=add-error";
			}
			return "redirect:/danh-sach-hoat-dong?msg=add-error";
		}
		return "redirect:/danh-sach-hoat-dong?msg=full";
	}
	@RequestMapping("/lich-su-tham-gia")
	public String history(ModelMap modelMap,Principal principal){
		modelMap.addAttribute("listActivities",activityDAO.getUserItems(userDAO.getItem(principal.getName()).getId()));
		return "public.activities.history";
	}
	
	@RequestMapping("/huy-tham-gia/{id}")
	public String delete(Principal principal,@PathVariable("id") int id){
		User user = userDAO.getItem(principal.getName());
		Activity activity = activityDAO.getItem(id);
		if(activity.getStatus() == 1){
			if(activityDAO.deleteItem(id,user.getId())>0){
				activityDAO.updateJoined(id);
				return "redirect:/lich-su-tham-gia?msg=del-success";
			}
		}
		return "redirect:/lich-su-tham-gia?msg=del-error";
	}
	
	@RequestMapping("/tim-kiem-hoat-dong")
	public String search(ModelMap modelMap, @RequestParam("search") String search,@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="row_count", defaultValue="10")int row_count){
		modelMap.addAttribute("search",search.toLowerCase());
		int offset = (page - 1) * row_count;
		modelMap.addAttribute("listActivities",activityDAO.search(search.toLowerCase(),offset,row_count));
		int total = (int)Math.ceil((float)activityDAO.countSearchItems(search.toLowerCase())/row_count);
		modelMap.addAttribute("total", total);
		return "public.activities.search";
	}
}
