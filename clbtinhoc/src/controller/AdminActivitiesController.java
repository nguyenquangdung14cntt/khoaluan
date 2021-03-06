package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import dao.AttendanceDAO;
import entities.Activity;
import entities.Activity_Users;
import entities.Attendance;
import utils.StringUtils;

@Controller
public class AdminActivitiesController {
	@Autowired
	private ActivitiesDAO activitiesDAO;
	@Autowired
	private AttendanceDAO attendanceDAO;
	@Autowired
	private StringUtils stringUtils;

	@RequestMapping("/admin/activities")
	public String index(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "row_count", defaultValue = "10") int row_count) {
		int offset = (page - 1) * row_count;
		modelMap.addAttribute("listActivities", activitiesDAO.getItems(offset, row_count));

		int total = (int) Math.ceil((float) activitiesDAO.countItems() / row_count);

		modelMap.addAttribute("total", total);

		return "admin.activities.index";
	}

	@RequestMapping("/admin/activities/add")
	public String add() {
		return "admin.activities.add";
	}

	@RequestMapping(value = "/admin/activities/add", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("objActivity") Activity activity, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin.activities.add";
		}
		activity.setTitle(stringUtils.html2text(activity.getTitle()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start = sdf.parse(activity.getStart_at());
			Date end = sdf.parse(activity.getEnd_at());
			if(end.before(start)){
				return "redirect:/admin/activities/add?msg=time-error";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (activitiesDAO.addItem(activity) > 0) {
			return "redirect:/admin/activities?msg=add-success";
		}
		return "redirect:/admin/activities?msg=add-error";
	}

	@RequestMapping("/admin/activities/edit/{id}")
	public String edit(ModelMap modelMap, @PathVariable("id") int id) {
		Activity activity = activitiesDAO.getItem(id);
		modelMap.addAttribute("objActivity", activity);
		return "admin.activities.edit";
	}

	@RequestMapping(value = "/admin/activities/edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") int id, @Valid @ModelAttribute("objActivity") Activity activity,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin.activities.edit";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date start = sdf.parse(activity.getStart_at());
			Date end = sdf.parse(activity.getEnd_at());
			if(end.before(start)){
				return "redirect:/admin/activities/edit/"+id+"?msg=time-error";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		activity.setId(id);
		activity.setTitle(stringUtils.html2text(activity.getTitle()));
		if (activitiesDAO.editItem(activity) > 0) {
			return "redirect:/admin/activities?msg=edit-success";
		}
		return "redirect:/admin/activities?msg=edit-error";
	}

	@RequestMapping("/admin/activities/del/{id}")
	public String del(@PathVariable("id") int id) {
		if (activitiesDAO.getItem(id).getStatus() == 2) {
			if (activitiesDAO.delItem(id) > 0) {
				return "redirect:/admin/activities?msg=del-success";
			}
		}
		return "redirect:/admin/activities?msg=del-error";
	}

	@RequestMapping("/admin/activities/{id}/attendance/add")
	public String addToAtt(ModelMap modelMap, @PathVariable("id") int id) {
		if (activitiesDAO.getItem(id).getStatus() == 0 && activitiesDAO.getItems(id).size() > 0) {
			modelMap.addAttribute("activity_name", activitiesDAO.getItem(id).getTitle());
			modelMap.addAttribute("times", attendanceDAO.getAttendanceTimes(id) + 1);
			modelMap.addAttribute("listUsers", activitiesDAO.getItems(id));
			return "admin.activities.addattendance";
		}
		return "redirect:/admin/activities/" + id + "/attendance?msg=activity-closed";
	}

	@RequestMapping(value = "/admin/activities/{id}/attendance/add", method = RequestMethod.POST)
	public String addToAtt(@PathVariable("id") int id, HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids");
		int times = attendanceDAO.getAttendanceTimes(id) + 1;
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created_at = sdf.format(dt);
		Attendance attendance = new Attendance(0, id, created_at, times);
		if (attendanceDAO.addAttendance(attendance) > 0) {
			Attendance attendance2 = attendanceDAO.getItem(created_at);
			List<Activity_Users> listUser = activitiesDAO.getItems(id);
			for (Activity_Users objUser : listUser) {
				for (int i = 0; i < ids.length; i++) {
					if (Arrays.asList(ids).contains(String.valueOf(objUser.getId_user()))) {
						attendanceDAO.addAttDetail(attendance2.getId(), objUser.getId_user(), 1);
					} else {
						attendanceDAO.addAttDetail(attendance2.getId(), objUser.getId_user(), 0);
					}
				}
			}
			return "redirect:/admin/activities/" + id + "/attendance?msg=attendance-success";
		}
		return "redirect:/admin/activities/" + id + "/attendance?attendance-error";
	}

	@RequestMapping("/admin/activities/{id}/attendance/{idAtt}")
	public String getAtt(ModelMap modelMap, @PathVariable("id") int id, @PathVariable("idAtt") int idAtt) {
		modelMap.addAttribute("activity_name", activitiesDAO.getItem(id).getTitle());
		modelMap.addAttribute("times", attendanceDAO.getAttTimes(idAtt));
		modelMap.addAttribute("listUsers", attendanceDAO.getAttDetail(idAtt));
		return "admin.activities.attdetail";
	}

	@RequestMapping("/admin/activities/{id}/attendance")
	public String attendance(ModelMap modelMap, @PathVariable("id") int id) {
		modelMap.addAttribute("activity_name", activitiesDAO.getItem(id).getTitle());
		modelMap.addAttribute("listAtt", attendanceDAO.getListAttendance(id));
		modelMap.addAttribute("listUser", attendanceDAO.getActivityDetail(id));
		return "admin.activities.attendance";
	}

	@RequestMapping("/admin/activities/{id}")
	public String detail(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("activity_name", activitiesDAO.getItem(id).getTitle());
		modelMap.addAttribute("listActivityUsers", activitiesDAO.getItems(id));
		return "admin.activities.detail";
	}

	@RequestMapping("/admin/activities/{id}/print")
	public String print(@PathVariable("id") int id, ModelMap modelMap) {
		modelMap.addAttribute("activity", activitiesDAO.getItem(id));
		modelMap.addAttribute("listUsers", activitiesDAO.getItems(id));
		return "admin.activities.print";
	}

	@RequestMapping(value = "/admin/activities/status", method = RequestMethod.POST)
	public void getStatus(@RequestParam("ajId") int id, @RequestParam("ajStatus") int status,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		if (status == 0) {
			activitiesDAO.setStatus(id, 1);
			response.getWriter().print(" <a href=\"javascript:void(0)\" onclick=\"return getStatus(" + id
					+ ",1)\" ><img src=" + request.getContextPath() + "/resources/admin/image/open-sign-up.jpg></a> ");
		} else {
			activitiesDAO.setStatus(id, 0);
			response.getWriter().print(" <a href=\"javascript:void(0)\" onclick=\"return getStatus(" + id
					+ ",0)\" ><img src=" + request.getContextPath() + "/resources/admin/image/close-sign-up.jpg></a> ");
		}
	}

	@RequestMapping(value = "/admin/activities/finish", method = RequestMethod.POST)
	public void finish(@RequestParam("ajId") int id, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		if (activitiesDAO.finish(id) > 0) {
			response.getWriter()
					.print("<img src=" + request.getContextPath() + "/resources/admin/image/finish-activity.jpg>");
		}
	}
}
