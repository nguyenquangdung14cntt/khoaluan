package controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.CategoriesDAO;
import dao.NewsDAO;
import dao.UsersDAO;
import entities.User;
import entities.User_Update;
import utils.SlugUtils;
import utils.StringUtils;

@Controller
public class PublicUsersController {
	@Autowired
	private NewsDAO newsDAO;
	@Autowired
	private CategoriesDAO catDAO;
	@Autowired
	private UsersDAO userDAO;
	@Autowired
	private SlugUtils slugUtils;
	@Autowired
	private StringUtils stringUtils;
	@ModelAttribute
	public void addCommon(ModelMap modelMap,Principal principal){
		modelMap.addAttribute("lCat",catDAO.getItems());
		modelMap.addAttribute("slug",slugUtils);
		modelMap.addAttribute("chunhiem",userDAO.getAdmin());
		modelMap.addAttribute("newList",newsDAO.getNewItems());
		if(principal != null){
			modelMap.addAttribute("logged",userDAO.getItem(principal.getName()));
		}
	}
	
	@RequestMapping("/thong-tin-ca-nhan")
	public String index(ModelMap modelMap,Principal principal) {
		User objUser = userDAO.getItem(principal.getName());
		modelMap.addAttribute("objUser",objUser);
		return "public.users.index";
	}
	
	@RequestMapping("/cap-nhap-thong-tin")
	public String edit(ModelMap modelMap,Principal principal) {
		User objUser = userDAO.getItem(principal.getName());
		modelMap.addAttribute("objUser",objUser);
		return "public.users.edit";
	}
	
	@RequestMapping(value="/cap-nhap-thong-tin",method=RequestMethod.POST)
	public String edit(HttpServletResponse response,@Valid @ModelAttribute("objUser") User_Update objUser,BindingResult bindingResult,Principal principal) throws IOException {
		if(bindingResult.hasErrors()){
			return "public.users.edit";
		}
		if(userDAO.updateItem(objUser)>0){
			return "redirect:/thong-tin-ca-nhan?msg=update-success";
		}
		return "redirect:/thong-tin-ca-nhan?msg=update-error";
	}
	
	
	@RequestMapping("/thay-doi-mat-khau")
	public String password(ModelMap modelMap,Principal principal) {
		User objUser = userDAO.getItem(principal.getName());
		modelMap.addAttribute("objUser",objUser);
		return "public.users.password";
	}
	
	@RequestMapping(value="/thay-doi-mat-khau",method=RequestMethod.POST)
	public String password(@RequestParam("password") String password, @RequestParam("password_confirm") String password_confirm,Principal principal) {
		User objUser = userDAO.getItem(principal.getName());
		if((password.length()>=1 && password.length()<6) || password.length()>16){
			return "redirect:/thay-doi-mat-khau?msg=password-length";
		} else if(password.equals("")){
			return "redirect:/thay-doi-mat-khau?msg=password-length";
		} else if(!password.equals(password_confirm)){
			return "redirect:/thay-doi-mat-khau?msg=password-confirm";
		} else {
			password = stringUtils.md5(password);
		}
		if(userDAO.updatePassword(objUser.getId(),password)>0){
			return "redirect:/thong-tin-ca-nhan?msg=password-success";
		}
		
		return "redirect:/thong-tin-ca-nhan?msg=password-error";
	}
}