package controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ActivitiesDAO;
import dao.CategoriesDAO;
import dao.NewsDAO;
import dao.UsersDAO;
import utils.SlugUtils;

@Controller
public class PublicActivitiesController {
	@Autowired
	private ActivitiesDAO activityDAO;
	@Autowired
	private NewsDAO newsDAO;
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
		if(principal != null){
			modelMap.addAttribute("logged",userDAO.getItem(principal.getName()));
		}
	}
	
	@RequestMapping("/danh-sach-hoat-dong")
	public String index(ModelMap modelMap,@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="row_count", defaultValue="5")int row_count) {
		int offset = (page - 1) * row_count;
		modelMap.addAttribute("listActivities",activityDAO.getItems(offset,row_count));
		int total = (int)Math.ceil((float)activityDAO.countItems()/row_count);
		modelMap.addAttribute("total",total);
		modelMap.addAttribute("newList",newsDAO.getNewItems());
		return "public.activities.index";
	}
}