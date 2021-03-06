package controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CategoriesDAO;
import entities.Category;
import utils.StringUtils;

@Controller
public class AdminCategoriesController {
	@Autowired
	private CategoriesDAO catDAO;
	@Autowired
	private StringUtils stringUtil;
	@RequestMapping("admin/categories")
	public String index(ModelMap modelMap){
		modelMap.addAttribute("listCat",catDAO.getItems());
		return "admin.categories.index";
	}
	@RequestMapping("admin/categories/add")
	public String add(){
		return "admin.categories.add";
	}
	@RequestMapping(value="admin/categories/add",method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objCat") Category objCat, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "admin.categories.add";
		}
		objCat.setName_cat(stringUtil.html2text(objCat.getName_cat()));
		if(catDAO.checkName(objCat.getName_cat())>0){
			return "redirect:/admin/categories/add?msg=exist-name";
		}
		if(catDAO.addItem(objCat)>0){
			return "redirect:/admin/categories?msg=add-success";
		}
		return "redirect:/admin/categories?msg=add-error";
	}
	@RequestMapping("admin/categories/edit/{id}")
	public String edit(ModelMap modelMap,@PathVariable("id") int id){
		modelMap.addAttribute("objCat",catDAO.getItem(id));
		return "admin.categories.edit";
	}
	@RequestMapping(value="admin/categories/edit/{id}",method=RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("objCat") Category objCat,@PathVariable("id") int id, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "admin.categories.edit";
		}
		objCat.setName_cat(stringUtil.html2text(objCat.getName_cat()));
		if(catDAO.checkName(objCat.getName_cat())>0){
			return "redirect:/admin/categories/edit/"+id+"?msg=exist-name";
		}
		if(catDAO.editItem(objCat)>0){
			return "redirect:/admin/categories?msg=edit-success";
		}
		return "redirect:/admin/categories?msg=edit-error";
	}
	@RequestMapping("admin/categories/del/{id}")
	public String del(@PathVariable("id") int id){
		if(catDAO.delItem(id)>0){
			return "redirect:/admin/categories?msg=del-success";
		}
		return "redirect:/admin/categories?msg=del-error";
	}
}
