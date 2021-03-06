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

import dao.RulesDAO;
import dao.UsersDAO;
import entities.Rule;
import entities.User;

@Controller
public class AdminRulesController {
	@Autowired
	private RulesDAO rulesDAO;
	@Autowired
	private UsersDAO usersDAO;
	
	@RequestMapping("/admin/rules")
	public String index(ModelMap modelMap){
		if(rulesDAO.checkRuleExist()>0){
			Rule rule = rulesDAO.getItem();
			modelMap.addAttribute("objRule",rule);
			modelMap.addAttribute("writer", usersDAO.getItem(rule.getWrite_by()));
		}
		return "admin.rules.index";
	}
	
	@RequestMapping("/admin/rules/add")
	public String add(){
		if(rulesDAO.checkRuleExist()>0){
			return "redirect:/admin/rules?msg=rule-exist";
		}
		return "admin.rules.add";
	}
	
	@RequestMapping(value="/admin/rules/add", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objRule") Rule objRule,BindingResult bindingResult,Principal principal){
		if(bindingResult.hasErrors()){
			return "admin.rules.add";
		}
		User user = (User) usersDAO.getItem(principal.getName());
		objRule.setWrite_by(user.getId());
		if(rulesDAO.addItem(objRule)>0){
			return "redirect:/admin/rules?msg=add-success";
		}
		return "redirect:/admin/rules?msg=add-error";
	}
	
	@RequestMapping("/admin/rules/edit/{id}")
	public String edit(ModelMap modelMap, @PathVariable("id") int id){
		modelMap.addAttribute("objRule",rulesDAO.getItem(id));
		return "admin.rules.edit";
	}
	
	@RequestMapping(value="/admin/rules/edit/{id}", method=RequestMethod.POST)
	public String edit(@Valid @ModelAttribute("objRule") Rule objRule,BindingResult bindingResult,Principal principal){
		if(bindingResult.hasErrors()){
			return "admin.rules.edit";
		}
		User user = (User) usersDAO.getItem(principal.getName());
		objRule.setWrite_by(user.getId());
		if(rulesDAO.editItem(objRule)>0){
			return "redirect:/admin/rules?msg=edit-success";
		}
		return "redirect:/admin/rules?msg=edit-error";
	}
	@RequestMapping("/admin/rules/del/{id}")
	public String del(@PathVariable("id") int id){
		if(rulesDAO.delItem(id)>0){
			return "redirect:/admin/rules?msg=del-success";
		}
		return "redirect:/admin/rules?msg=del-error";
	}
	
}
