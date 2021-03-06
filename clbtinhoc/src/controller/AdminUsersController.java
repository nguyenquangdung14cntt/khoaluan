package controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ActivitiesDAO;
import dao.NewsDAO;
import dao.RolesDAO;
import dao.RulesDAO;
import dao.UsersDAO;
import entities.Activity;
import entities.News;
import entities.Rule;
import entities.User;
import utils.StringUtils;

@Controller
public class AdminUsersController {
	@Autowired
	private UsersDAO userDAO;
	@Autowired
	private RolesDAO rolesDAO;
	@Autowired
	private RulesDAO rulesDAO;
	@Autowired
	private ActivitiesDAO activityDAO;
	@Autowired
	private NewsDAO newsDAO;
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping("/admin/users")
	public String index(Principal principal,ModelMap modelMap, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="row_count", defaultValue="10")int row_count){
		int offset = (page - 1)*row_count;
		modelMap.addAttribute("listUsers",userDAO.getItems(offset,row_count));
		int total = (int)Math.ceil((float)userDAO.countItems()/row_count);
		
		modelMap.addAttribute("total", total);
		modelMap.addAttribute("user", userDAO.getItem(principal.getName()));
		return "admin.users.index";
	}
	
	@RequestMapping("/admin/users/add")
	public String add(ModelMap modelMap){
		modelMap.addAttribute("listRoles",rolesDAO.getItems());
		return "admin.users.add";
	}
	
	@RequestMapping(value="/admin/users/add", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objUser") User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "admin.users.add";
		}
		user.setFirstname(stringUtils.html2text(user.getFirstname()));
		user.setLastname(stringUtils.html2text(user.getLastname()));
		user.setEmail(stringUtils.html2text(user.getEmail()));
		user.setKlass(stringUtils.html2text(user.getKlass()));
		user.setUsername(stringUtils.html2text(user.getUsername()));
		if(userDAO.checkUserExist(user.getUsername()) > 0){
			return "redirect:/admin/users/add?msg=exist-name";
		}
		
		user.setCreated_at(new Timestamp(System.currentTimeMillis()));
		
		if(userDAO.addItem(user) > 0){
			return "redirect:/admin/users?msg=add-success";
		}
		return "redirect:/admin/users?msg=add-error";
	}
	
	@RequestMapping("/admin/users/edit/{id}")
	public String edit(Principal principal,ModelMap modelMap,@PathVariable("id")int id){
		User user = userDAO.getItem(id);
		modelMap.addAttribute("listRoles",rolesDAO.getItems());
		if(user.getRole().equals("Chủ nhiệm")){
			return "redirect:/admin/users?msg=access-denied";
		}
		modelMap.addAttribute("objUser",user);
		return "admin.users.edit";
	}
	@RequestMapping(value="/admin/users/edit/{id}",method=RequestMethod.POST)
	public String edit(@PathVariable("id")int id, @RequestParam("password") String password, @RequestParam("id_role") int id_role){
		if((password.length()>=1 && password.length()<6) || password.length()>16){
			return "redirect:/admin/users/edit/"+id+"/?msg=password-error";
		} else if(password.equals("")){
			password = userDAO.getItem(id).getPassword();
		} else {
			password = stringUtils.md5(password);
		}
		if(userDAO.editItem(id,id_role,password)>0){
			return "redirect:/admin/users?msg=edit-success";
		}
		return "redirect:/admin/users?msg=edit-error";
	}
	@RequestMapping("/admin/users/del/{id}")
	public String del(Principal principal,@PathVariable("id")int id){
		User prin = userDAO.getItem(principal.getName());
		if(prin.getId() == id){
			return "redirect:/admin/users?msg=del-error";
		}
		List<Rule> rule = rulesDAO.getItemByWriter(id);
		List<News> listNews = newsDAO.getItemsByWriter(id);
		List<Activity> listActivities = activityDAO.getItemsByJoin(id);
		if(userDAO.delItem(id)>0){
			if(listActivities.size()>0){
				for (Activity activity : listActivities) {
					activityDAO.updateJoined(activity.getId());
				}
			}
			if(rule.size()>0){
				for(Rule rules: rule){
					rulesDAO.updateWriter(rules.getId(),prin.getId());
				}
			}
			if(listNews.size()>0){
				for (News news : listNews) {
					newsDAO.updateWriter(news.getId(),prin.getId());
				}
			}
			return "redirect:/admin/users?msg=del-success";
		}
		return "redirect:/admin/users?msg=del-error";
	}
	@RequestMapping("/admin/transfer/{id}")
	public String transfer(Principal principal,@PathVariable("id") int id){
		User user = userDAO.getItem(principal.getName());
		if(userDAO.transferADMIN(user.getId_role(), id)>0){
			userDAO.transferADMIN(1, user.getId());
			return "redirect:/logout";
		}
		return "redirect:/admin/users";
	}
	@RequestMapping(value="/admin/users/search")
	public String search(Principal principal,ModelMap modelMap,@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,@RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="row_count", defaultValue="10")int row_count){
		int offset = (page - 1)*row_count;
		modelMap.addAttribute("listUsers",userDAO.search(firstname, lastname,offset,row_count));
		int total = (int)Math.ceil((float)userDAO.countSearchItems(firstname, lastname)/row_count);
		
		modelMap.addAttribute("total", total);
		modelMap.addAttribute("user", userDAO.getItem(principal.getName()));
		return "admin.users.search";
	}
}
