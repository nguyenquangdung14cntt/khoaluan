package controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.CategoriesDAO;
import dao.ContactsDAO;
import dao.UsersDAO;
import entities.Contact;
import utils.SlugUtils;

@Controller
public class PublicContactsController {
	@Autowired
	private ContactsDAO contactsDAO;
	@Autowired
	private UsersDAO userDAO;
	@Autowired
	private CategoriesDAO catDAO;
	@Autowired
	private SlugUtils slugUtils;

	@ModelAttribute
	public void addCommon(ModelMap modelMap,Principal principal){
		modelMap.addAttribute("lCat",catDAO.getItems());
		modelMap.addAttribute("slug",slugUtils);
		if(principal != null){
			modelMap.addAttribute("logged",userDAO.getItem(principal.getName()));
		}
	}
	
	@RequestMapping("/y-kien")
	public String index(){
		return "public.contacts.index";
	}
	@RequestMapping(value="/y-kien",method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objContact") Contact objContact, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "public.contacts.index";
		}
		if(contactsDAO.checkSpam(objContact)>2){
			return "redirect:/y-kien?msg=spam";
		}
		if(contactsDAO.addItem(objContact)>0){
			return "redirect:/y-kien?msg=add-success";
		}
		return "redirect:/y-kien?msg=add-error";
	}
}
