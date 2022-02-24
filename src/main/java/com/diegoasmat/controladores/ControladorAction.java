package com.diegoasmat.controladores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diegoasmat.modelos.Action;

@Controller
@RequestMapping("/gold")
public class ControladorAction {
	
	public static ArrayList<Action> actions = new ArrayList<Action>();
	
	@GetMapping("")
	public String renderIndex(HttpSession session, Model model) {
		if(session.getAttribute("oro")==null) {
			session.setAttribute("oro", 0);
		}
		else if((int)session.getAttribute("oro")<-200){
			return "redirect:/gold/prison";
		}
		model.addAttribute("acciones", actions);
		return "index.jsp";
	}
	
	@PostMapping("/process")
	public String processAction(@RequestParam("accion")String realizedAction, HttpSession session) {
		int value = (int) session.getAttribute("oro");
		int incremento = doAction(realizedAction);
		session.setAttribute("oro", value+incremento);
		return "redirect:/gold";
	}
	
	@GetMapping("/reset")
	public String resetGold(HttpSession session, Model model) {
		session.setAttribute("oro", 0);
		actions.clear();
		return "redirect:/gold";
	}
	
	
	@GetMapping("/prison")
	public String renderPrison(HttpSession session) {
		if(session.getAttribute("oro")!=null) {
			return "prison.jsp";
			
		}
		return "redirect:/gold";
	}
	
	public static int doAction(String accion) {
		Random ranGen = new Random();
		int value = 0;
		String message = "";
		Date fecha = new Date();
		DateFormat format = new SimpleDateFormat("MMMMMM d yyyy h:mm a");
		String fechaEnString = format.format(fecha);
        
		if(accion.equals("farm")) {
			value = ranGen.nextInt(10, 21);
			message = String.format("You entered a farm and earned %d gold. (%s)", value, fechaEnString);
		}
		else if(accion.equals("cave")) {
			value = ranGen.nextInt(5, 11);
			message = String.format("You entered a cave and earned %d gold. (%s)", value, fechaEnString);
		}
		else if(accion.equals("house")) {
			value = ranGen.nextInt(2, 6);
			message = String.format("You entered a house and earned %d gold. (%s)", value, fechaEnString);
		}
		else if(accion.equals("casino")) {
			value = ranGen.nextInt(-50, 51);
			if(value<0) {
				message = String.format("You entered a casino and lost %d gold. Ouch. (%s)", value, fechaEnString);
			}
			else {
				message = String.format("You entered a casino and earned %d gold. (%s)", value, fechaEnString);
			}
		}
		else if(accion.equals("spa")) {
			value = ranGen.nextInt(-20, -4);
			message = String.format("You entered a spa and lost %d gold. How relaxing! (%s)", value, fechaEnString);
		}
		else {
			value = 0;
			message = "UPS! Something went wrong!";
		}
		
		
		Action action = new Action(message, value);
		
		actions.add(action);
		
		return value;
	}
}
