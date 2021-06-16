package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import java.util.ArrayList;
import java.util.List;

import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.tallerfinal.front.controller.interfaces.PhysicalCheckupController;
import co.edu.icesi.ci.tallerfinal.back.groups.AddPhycheckup;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;


@Controller
@RequestMapping("/api/phycheckups")
public class PhysicalCheckupControllerImpl implements PhysicalCheckupController{

	public BusinessDelegate bd;

	@Autowired
	public PhysicalCheckupControllerImpl(BusinessDelegate bd) {
		this.bd = bd;
	}
	@Override
	@GetMapping("/")
	public String indexPhycheckup(Model model) {
		model.addAttribute("phycheckups", phycheckupService.findAll());
		return "/phycheckups/index";
	}

	@Override
	@GetMapping("/add/")
	public String addPhycheckup(Model model) {
		model.addAttribute("physicalcheckup", new Physicalcheckup());
		model.addAttribute("visits", bd.visitFindAll());
		model.addAttribute("persons", bd.personFindAll());
		return "phycheckups/add-phycheckup";
	}
	@Override
	@PostMapping("/add/")
	public String savePhycheckup(@Validated(AddPhycheckup.class) Physicalcheckup physicalcheckup, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("physicalcheckup", physicalcheckup);
				model.addAttribute("visits", bd.visitFindAll());
				model.addAttribute("persons", bd.personFindAll());
				return "phycheckups/add-phycheckup";
			}
			Visit visit = physicalcheckup.getVisit();
			visit.addPhysicalcheckup(physicalcheckup);
			visitService.editVisit(visit);
			phycheckupService.addPhysicalcheckup(physicalcheckup, physicalcheckup.getPerson().getPersId(), visit.getVisitId());
		}

		return "redirect:/phycheckups/";

	} 
	@Override
	@GetMapping("/edit/{id}")
	public String editPhycheckup(@PathVariable("id") long id, Model model) {
		 Physicalcheckup phycheckup = phycheckupService.getPhysicalcheckup(id);
		
		model.addAttribute("physicalcheckup", phycheckup);
		model.addAttribute("visits", bd.visitFindAll());
		model.addAttribute("persons", bd.personFindAll());
		return "phycheckups/update-phycheckup";
	}
	@Override
	@PostMapping("/edit/{id}")
	public String updatePhycheckup(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("physicalcheckup") @Validated(AddPhycheckup.class) Physicalcheckup physicalcheckup, BindingResult bindingResult, Model model) {
		
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {

				physicalcheckup.setPhycheId(id);
				model.addAttribute("visits", bd.visitFindAll());
				model.addAttribute("persons", bd.personFindAll());
				return "phycheckups/update-phycheckup";
			}
			physicalcheckup.setPhycheId(id);
			phycheckupService.editPhysicalcheckup(physicalcheckup);
		}
		return "redirect:/phycheckups/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deletePhycheckup(@PathVariable("id") long id) {
		Physicalcheckup phycheckup = phycheckupService.getPhysicalcheckup(id);
		phycheckupService.delete(phycheckup);
		return "redirect:/phycheckups/";
	}
	
	@Override
	@GetMapping("/showFromVisit/{id}")
	public String showFromVisit(@PathVariable("id") long id, Model model) {
		
		Visit visit = visitService.getVisit(id);
		List<Physicalcheckup> phycheckups = visit.getPhysicalcheckups();
		model.addAttribute("phycheckups", phycheckups);
		model.addAttribute("idVisit", id);
		model.addAttribute("from", "visit");
		return "phycheckups/index";
	}
	
	@Override
	@GetMapping("/showFromCheckMeasur/{id}")
	public String showFromCheckMeasur(Model model, @PathVariable("id") long id) {
		ArrayList<Physicalcheckup> phycheckups = new ArrayList<>();
		phycheckups.add(phycheckupService.getPhysicalcheckup(id));
		model.addAttribute("phycheckups", phycheckups);
		model.addAttribute("from", "checkMeasur");
		return "phycheckups/index";
	}


}
