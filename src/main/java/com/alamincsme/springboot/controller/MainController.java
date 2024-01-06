package com.alamincsme.springboot.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

	@GetMapping("/home")
	public String homePage() {
		return "home.html";
	}
	@GetMapping("/adProgrammer")
	public ModelAndView programmerPage(@RequestParam("pId") int id  , @RequestParam("pName") String name, @RequestParam("pLan") String lan,Model model ) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("programmerinfo.html");
		mv.addObject("id",id);
		mv.addObject("name",name);
		mv.addObject("language",lan);
		return mv;
	}
}
