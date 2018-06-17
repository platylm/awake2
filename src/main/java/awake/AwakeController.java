package awake;

import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("sValue")
public class AwakeController {
	String user;
	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private WorkTimeRepository workTimeRepo;

	@Autowired
	private PaymentRepository payRepo;

	@Autowired
	private WagesRepository wagesRepo;

	@GetMapping("/index")
	public String index(Model model) {
		Iterable<Employee> empList = empRepo.findAll();
		model.addAttribute("empList", empList);
		Iterable<Payment> paymentList = payRepo.findAll();
		model.addAttribute("paymentList", paymentList);
		return "index";
	}

	@PostMapping("/checklogin")
	public String seach(@RequestParam("user") String id_emp, @RequestParam("password") String password,
			HttpServletRequest request, HttpSession session, Model model) {
		String url = "";
		String error = "user หรือ รหัสผ่านไม่ถูกต้อง";
		HttpSession Session = request.getSession();
		Employee emp = empRepo.findOne(Integer.parseInt(id_emp));
		if (password.equals(emp.getPassword())) {
			if (emp.getType().equals("Admin")) {
				Session.setAttribute("username", emp.getUsername());
				url = "redirect:user-info";
			} else {
				Session.setAttribute("username", emp.getUsername());
				Session.setAttribute("Idemp", emp.getIdEmp());
				url = "redirect:user-info";
			}
		} else {
			url = "redirect:index";
			model.addAttribute("t", error);
		}
		System.out.println(url);
		return url;
	}

	@GetMapping("/user-info")
	public String userMenu(Model model, HttpServletRequest request) {
		String url;
		Iterable<Employee> empList = empRepo.findAll();
		model.addAttribute("empList", empList);

		HttpSession session = request.getSession();
		user = (String) session.getAttribute("username");
		if (user != null) {
			System.out.println(user + "@admin");
			model.addAttribute("showsession", user);

			List<Employee> userList = empRepo.findByusername(user);
			System.out.println(user);
			model.addAttribute("userList", userList);
			url = "user-info";
		} else {
			url = "redirect:index";
		}
		return url;
	}

	@GetMapping("/staff-admin")
	public String goToStaff(Model model, HttpServletRequest request) {
		String url;
		HttpSession session = request.getSession();
		user = (String) session.getAttribute("username");

		if (user != null) {
			model.addAttribute("showsession", user);
			Iterable<Employee> empList = empRepo.findAll();
			model.addAttribute("empList", empList);
			Iterable<Employee> empTypeTestList = empRepo.findEmployeeByTypeTestTime();
			model.addAttribute("empTypeTestList", empTypeTestList);
			Iterable<Employee> empTypePartList = empRepo.findEmployeeByTypePartTime();
			model.addAttribute("empTypePartList", empTypePartList);
			System.out.println(user + "@admin");
			model.addAttribute("showsession", user);
			url = "staff-admin";
		} else {
			url = "redirect:index";
		}
		return url;
	}

	@PostMapping("/staff-add")
	public String addStaff(@RequestParam("s_id") String id_emp, @RequestParam("password") String password,
			@RequestParam("fname") String fname, @RequestParam("lname") String lname, @RequestParam("ssn") String ssn, @RequestParam("phone") String phone,
			@RequestParam("address") String address, @RequestParam("sType") String type) {
		java.util.Date today = new java.util.Date();
		Employee emp = new Employee();
		emp.setUsername(id_emp);
		emp.setPassword(password);
		emp.setFname(fname);
		emp.setLname(lname);
		emp.setSsn(ssn);
		emp.setStartDate(today);
		emp.setPhone(phone);
		emp.setAddress(address);
		emp.setType(type);
		if(type.equals("Full-Employee")){
			emp.setRate(30);
		}else{
			emp.setRate(25);
		}
		
		empRepo.save(emp);
		return "redirect:staff-admin";
	}

	@GetMapping("/staff-delete/{id_emp}")
	public String deleteStaff(@PathVariable("id_emp") Integer id_emp) {
		empRepo.delete(id_emp);
		return "redirect:/staff-admin";
	}

	@GetMapping("/user-check-time")
	public String userCheckTime(Model model, HttpServletRequest request) {
		String url;
		HttpSession session = request.getSession();
		user = (String) session.getAttribute("username");
		Integer s = (Integer) session.getAttribute("Idemp");
		Integer worktimeList = workTimeRepo.getLastCheckIng(s);
		System.out.println(worktimeList);
		if (user != null) {
			model.addAttribute("showsession", user);
			List<Employee> userList = empRepo.findByusername(user);
			System.out.println(user);
			model.addAttribute("userList", userList);

			Iterable<WorkTime> workList = workTimeRepo.findAllworkTimeAndEmployee();
			model.addAttribute("workList", workList);
			Iterable<Employee> empWorkTimeList = empRepo.findEmployeeByWorkTime();
			model.addAttribute("empWorkTimeList", empWorkTimeList);
			
			model.addAttribute("checktimestatus", worktimeList);
			url = "user-check-time";
		} else {
			url = "redirect:index";
		}
		return url;
	}

	////////////////////// Edit_Employee ///////////////////////////////////////
	Integer idea;
	@GetMapping("/editchecktime/{id_emp}")
	public String editcheck(@PathVariable("id_emp") Integer id, Model model, HttpServletRequest request) {
		String url;
		HttpSession session = request.getSession();
		user = (String) session.getAttribute("username");
		idea = id;
		System.out.println(idea);
		WorkTime work = workTimeRepo.findOne(id);

		if (user != null && user.equals("admin")) {
			model.addAttribute("Ework", work);
			url = "editchecktime";
		} else {
			url = "redirect:/index";
		}
		return url;
	}

	@PostMapping("/update")
	public String edit(@ModelAttribute WorkTime Ework, Model model,HttpSession session) throws Exception {
		//workTimeRepo.save(Ework);
		String emp = (String) session.getAttribute("username");
		Integer workList = workTimeRepo.getLastCheckIn(idea.toString());
		System.out.println(idea+"  session" + workList);
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		int rate = empRepo.findRate(Ework.getEmployee().getUsername());
		java.util.Date today = new java.util.Date();
		System.out.println(rate+" username "+ Ework.getEmployee().getUsername());
		Time tis = new java.sql.Time(today.getTime());
		String tt = Ework.getCheckOut().toString();
		WorkTime findOneForSet = workTimeRepo.findOne(idea);
		System.out.println(tis);
		
		String ss = Ework.getCheckIn().toString();
		Date s1 = format.parse(tt);
		Date s2 = format.parse(ss);
		long h = s1.getTime() - s2.getTime();
		System.out.println("time "+ss+" "+tt);
		int total = rate * (int) (h / 3600000);

		System.out.println("----------------------------------");
		System.out.println(h);
		System.out.println(total);
		String mess = "บันทึกเวลาสำเร็จ";
		model.addAttribute("mess", mess);
		findOneForSet.setCheckIn(Ework.getCheckIn());
		findOneForSet.setStatus(0);
		findOneForSet.setCheckOut(Ework.getCheckOut());
		findOneForSet.setWage(total);
		workTimeRepo.save(findOneForSet);		
		
		return "redirect:/user-check-time";
	}

	@GetMapping("/checktimeinfo/{id_emp}")
	public String checktimeinfo(@PathVariable("id_emp") Integer id, Model model, HttpServletRequest request) {
		String url;
		HttpSession session = request.getSession();
		user = (String) session.getAttribute("username");
		int sum1 = 0;
		List<WorkTime> workList = workTimeRepo.findByworkTime(id);
		for (int i = 0; i < workList.size(); i++) {
			WorkTime wag = workList.get(i);
			sum1 += wag.getWage();
		}
		if (user != null) {
			model.addAttribute("workList", workList);
			url = "checktimeinfo";
		} else {
			url = "redirect:index";
		}
		model.addAttribute("sum", sum1);
		return url;
	}
	//////////////////////ประวัติการทำงานของพนักงาน ///////////////////////////////////////
	@GetMapping("/history")
	public String showhistory(Model model, HttpServletRequest request) {
		String url;
		HttpSession session = request.getSession();
		user = (String) session.getAttribute("username");
		
		List<WorkTime> showDate = workTimeRepo.findBydateWork();
		if (user != null) {
			model.addAttribute("showDate", showDate);
			url = "showInDay";
		} else {
			url = "redirect:index";
		}
		return url;
	}

	@GetMapping("/allhistory")
	public String showAllHistory(Model model, HttpServletRequest request) {
		String url;
		HttpSession session = request.getSession();
		user = (String) session.getAttribute("username");
		
		List<WorkTime> showAll = workTimeRepo.findAllworkTime();
		if (user != null) {
			model.addAttribute("showAll", showAll);
			url = "showAllCheck";
		} else {
			url = "redirect:index";
		}
		return url;
	}

//////////////////////ดูค่าจ้างของพนักงาน ///////////////////////////////////////
	@GetMapping("/ViewTotalEmp")
	public String listEmp(Model model, HttpServletRequest request) {
		String url;
		HttpSession session = request.getSession();
		user = (String) session.getAttribute("username");
		
		Iterable<WorkTime> payList = workTimeRepo.findAll();
		if (user != null) {
			model.addAttribute("payList", payList);
			url = "ViewTotalEmp";
		} else {
			url = "redirect:index";
		}
		return url;
	}

	@GetMapping("/wages")
	public String Wages(Model model, HttpServletRequest request) {
		String url;
		HttpSession session = request.getSession();
		user = (String) session.getAttribute("username");
		if (user != null) {
			url = "wages";
		} else {
			url = "redirect:index";
		}
		return url;
	}

	@PostMapping("/findWages")
	public String findWages(@RequestParam("month") String month, Model model) {
		Employee emp;
		Integer id = null;
		int sum = 0;
		List<Wages> wasList = wagesRepo.findWages(month);
		model.addAttribute("wasList", wasList);
		for (int i = 0; i < wasList.size(); i++) {
			Wages wag = wasList.get(i);
			emp = wag.getEmployee();
			id = emp.getIdEmp();
			sum += wag.getTotalWage();
		}

		List<Employee> userList = empRepo.findById(id);
		System.out.println(id);
		model.addAttribute("userList", userList);
		model.addAttribute("sum", sum);
		return "wages";
	}
	/* *********** Ceck Time ****************/
	@GetMapping("/checkIn/{idemp}")
	public String checkIn(@PathVariable("idemp") Integer Id, Model model) {
		WorkTime workTime = new WorkTime();
		Employee addWork = empRepo.findId(Id);
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		System.out.println(new java.sql.Time(today.getTime()));
		workTime.setCheckIn(new java.sql.Time(today.getTime()));
		workTime.setDateWork(date);
		workTime.setStatus(-1);
		workTime.setWage(0);
		workTime.setEmployee(addWork);
		workTimeRepo.save(workTime);
		return "redirect:Salary";

	}

	@GetMapping("/getCheckIn")
	public String insertChecktime(HttpSession session, Model model) {
		String emp = (String) session.getAttribute("username");
		Employee em = empRepo.findByUsername(emp);
		WorkTime workTime = new WorkTime();
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		System.out.println(new java.sql.Time(today.getTime()));

		workTime.setCheckIn(new java.sql.Time(today.getTime()));
		workTime.setDateWork(date);
		workTime.setStatus(-1);
		workTime.setWage(0);
		workTime.setEmployee(em);
		workTimeRepo.save(workTime);
		String mess = "บันทึกเวลาสำเร็จ";
		model.addAttribute("mess", mess);
		return "redirect:/checktimeinfo/" + em.getIdEmp();
	}

	@GetMapping("/getCheckOut")
	public String updateCheckTime(HttpSession session, Model model) throws Exception {
		String emp = (String) session.getAttribute("username");
		Integer workList = workTimeRepo.getLastCheckIn(emp);
		Employee em = empRepo.findByUsername(emp);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		System.out.println(emp);
		int rate = empRepo.findRate(emp);
		java.util.Date today = new java.util.Date();
		System.out.println(new java.sql.Time(today.getTime()));
		Time tis = new java.sql.Time(today.getTime());
		String tt = tis.toString();
		WorkTime findOneForSet = workTimeRepo.findOne(workList);
		System.out.println(tis);
		String ss = findOneForSet.getCheckIn().toString();
		Date s1 = format.parse(tt);
		Date s2 = format.parse(ss);
		long h = s1.getTime() - s2.getTime();
		int total = rate * (int) (h / 3600000);

		System.out.println("----------------------------------");
		System.out.println(h);
		System.out.println(total);
		String mess = "บันทึกเวลาสำเร็จ";
		model.addAttribute("mess", mess);

		findOneForSet.setStatus(0);
		findOneForSet.setCheckOut(new java.sql.Time(today.getTime()));
		findOneForSet.setWage(total);
		workTimeRepo.save(findOneForSet);
		return "redirect:checktimeinfo/" + em.getIdEmp();
	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}
}
