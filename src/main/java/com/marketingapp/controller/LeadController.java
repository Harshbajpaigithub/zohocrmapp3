package com.marketingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketingapp.dto.LeadDto;
import com.marketingapp.entities.Lead;
import com.marketingapp.service.LeadService;
import com.marketingapp.util.EmailService;

@Controller
public class LeadController {

	@Autowired
	private LeadService leadservice;
	
	@Autowired
	private EmailService emailService;

	// localhost:8080/create

	@RequestMapping("/create") // @Webservlet
	public String viewCreateLeadForm() {
		return "create_lead"; // RequestDispatcher

	}

	@RequestMapping("/saveReg")
	public String saveOneLead(@ModelAttribute Lead lead , Model model) {
		model.addAttribute("msg", "lead saved");
		leadservice.saveReg(lead);
		emailService.sendEmail(lead.getEmail(), "test", "tesing email services");
		return "create_lead";

	}
//	@RequestMapping("/saveReg") 
//	public String saveOneLead(@RequestParam("firstName") String firstname , @RequestParam("lastName") String lastname , @RequestParam("email") String email , @RequestParam("mobile") long mobile , ModelMap model) {
//		
//		Lead l = new Lead();
//		l.setFirstName(firstname);
//		l.setLastName(lastname);
//		l.setEmail(email);
//		l.setMobile(mobile);
//		
//		model.addAttribute("msg", "Lead is saved!!");
//		leadservice.saveReg(l);		
//		return"create_lead";
//
//}
//	@RequestMapping("/saveReg") 
//	public String saveOneLead(@ModelAttribute Lead lead , Model model) {
//		model.addAttribute("msg", "Lead saved");
//		leadservice.saveReg(lead);		
//		return"create_lead";
//		
//	}
//	@RequestMapping("/saveReg") 
//	public String saveOneLead(LeadDto dto , Model model) {
//	Lead lead = new Lead();
//	lead.setFirstName(dto.getFirstName());
//	lead.setLastName(dto.getLastName());
//	lead.setEmail(dto.getEmail());
//	lead.setMobile(dto.getMobile());
//	
//		model.addAttribute("msg", "Lead saved");
//		leadservice.saveReg(lead);		
//		return"create_lead";
//}

	// localhost:8080/list
	@RequestMapping("/list")
	public String getAllLeads(Model model) {

		List<Lead> leads = leadservice.findAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";

	}

	// localhost:8080/delete
	@RequestMapping("/delete")
	public String deleteLeadById(@RequestParam("id") long id, Model model) {
		leadservice.deleteLeadbyId(id);

		List<Lead> leads = leadservice.findAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
	}
	@RequestMapping("/update")
	public String updateLead(@RequestParam("id") long id, Model model) {
		Lead lead = leadservice.findLeadById(id);
		model.addAttribute("lead", lead);
		return "update_lead";
			
	}
	@RequestMapping("/editReg")
	public String editReg(LeadDto dto, Model model) {
		
		Lead lead = new Lead();
		lead.setId(dto.getId());
		lead.setFirstName(dto.getFirstName());
		lead.setLastName(dto.getLastName());
		lead.setEmail(dto.getEmail());
		lead.setMobile(dto.getMobile());
		
		leadservice.saveReg(lead);
		
		List<Lead> leads = leadservice.findAllLeads();
		model.addAttribute("leads", leads);
		return "list_leads";
		
	}

}
