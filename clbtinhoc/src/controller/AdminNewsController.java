package controller;

import java.security.Principal;

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

import dao.CategoriesDAO;
import dao.NewsDAO;
import dao.UsersDAO;
import entities.News;
import entities.User;

@Controller
public class AdminNewsController {
	@Autowired
	private NewsDAO newsDAO;
	@Autowired
	private CategoriesDAO catDAO;
	@Autowired
	private UsersDAO usersDAO;
	
	@RequestMapping("/admin/news")
	public String index(ModelMap modelMap, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="row_count", defaultValue="10")int row_count){
		int offset = (page - 1)*row_count;
		modelMap.addAttribute("listNews",newsDAO.getItems(offset, row_count));
		int total = (int)Math.ceil((float)newsDAO.countItems()/row_count);
		modelMap.addAttribute("total", total);
		return "admin.news.index";
	}
	
	@RequestMapping("/admin/news/no-cat")
	public String news(ModelMap modelMap, @RequestParam(value="page", defaultValue="1") int page, @RequestParam(value="row_count", defaultValue="10")int row_count){
		int offset = (page - 1)*row_count;
		modelMap.addAttribute("listNews",newsDAO.getNullItems(offset, row_count));
		int total = (int)Math.ceil((float)newsDAO.countItems()/row_count);
		modelMap.addAttribute("total", total);
		return "admin.news.nocat";
	}
	
	@RequestMapping("/admin/news/add")
	public String add(ModelMap modelMap){
		modelMap.addAttribute("listCat", catDAO.getItems());
		return "admin.news.add";
	}
	
	@RequestMapping(value="/admin/news/add", method=RequestMethod.POST)
	public String add(ModelMap modelMap,@Valid @ModelAttribute("objNews") News objNews,BindingResult bindingResult,Principal principal){
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("listCat", catDAO.getItems());
			return "admin.news.add";
		}
		System.out.println(objNews.toString());
		User user = (User) usersDAO.getItem(principal.getName());
		objNews.setWrite_by(user.getId());
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objNews.setCreated_at(sdf.format(dt));
		objNews.setUpdated_at(objNews.getCreated_at());
		if(newsDAO.addItem(objNews)>0){
			return "redirect:/admin/news?msg=add-success";
		}
		return "redirect:/admin/news?msg=add-error";
	}
	
	@RequestMapping("/admin/news/edit/{id}")
	public String edit(ModelMap modelMap, @PathVariable("id") int id){
		modelMap.addAttribute("objNews",newsDAO.getItem(id));
		modelMap.addAttribute("listCat", catDAO.getItems());
		return "admin.news.edit";
	}
	
	@RequestMapping(value="/admin/news/edit/{id}", method=RequestMethod.POST)
	public String edit(ModelMap modelMap,@PathVariable("id")int id,@Valid @ModelAttribute("objNews") News objNews,BindingResult bindingResult,Principal principal){
		if(bindingResult.hasErrors()){
			modelMap.addAttribute("listCat", catDAO.getItems());
			return "admin.news.edit";
		}
		objNews.setId(id);
		User user = (User) usersDAO.getItem(principal.getName());
		objNews.setWrite_by(user.getId());
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objNews.setUpdated_at(sdf.format(dt));
		if(newsDAO.editItem(objNews)>0){
			return "redirect:/admin/news?msg=edit-success";
		}
		return "redirect:/admin/news?msg=edit-error";
	}
	@RequestMapping("/admin/news/del/{id}")
	public String del(@PathVariable("id") int id){
		if(newsDAO.delItem(id)>0){
			return "redirect:/admin/news?msg=del-success";
		}
		return "redirect:/admin/news?msg=del-error";
	}
	
}
