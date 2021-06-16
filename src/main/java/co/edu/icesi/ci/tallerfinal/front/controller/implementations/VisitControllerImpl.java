package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import java.util.ArrayList;
import java.util.List;

import co.edu.icesi.ci.tallerfinal.front.model.classes.AddVisit;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;
import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
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

import co.edu.icesi.ci.tallerfinal.front.controller.interfaces.VisitController;


@Controller
@RequestMapping("/api/visits")
public class VisitControllerImpl implements VisitController {

	public BusinessDelegate bd;

	@Autowired
	public VisitControllerImpl(BusinessDelegate bd) {
		this.bd = bd;
	}

	@Override
	@GetMapping("/")
	public String indexVisit(Model model) {
		model.addAttribute("visits", bd.visitFindAll());
		return "/visits/index";
	}

	@Override
	@GetMapping("/add/")
	public String addVisit(Model model) {
		model.addAttribute("visit", new Visit());
		model.addAttribute("institutions", bd.institutionCampusFindAll());
		model.addAttribute("persons", bd.personFindAll());
		return "visits/add-visit";
	}


	@Override
	@PostMapping("/add/")
	public String saveVisit(@Validated(AddVisit.class) Visit visit, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("institutions", bd.institutionCampusFindAll());
				model.addAttribute("persons", bd.personFindAll());
				return "/visits/add-visit";
			}
			bd.saveVisit(visit, visit.getPerson().getPersId(), visit.getInstitutioncampus().getInstcamId());
		}

		return "redirect:/visits/";

	}
	@Override
	@GetMapping("/edit/{id}")
	public String editVisit(@PathVariable("id") long id, Model model) {
		Visit visit = bd.visitFindById(id);

		model.addAttribute("visit", visit);
		model.addAttribute("institutions", bd.institutionCampusFindAll());
		model.addAttribute("persons", bd.personFindAll());
		return "visits/update-visit";
	}

	@Override
	@PostMapping("/edit/{id}")
	public String updateVisit(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("visit") @Validated({AddVisit.class}) Visit visit, BindingResult bindingResult, Model model) {

		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				visit.setVisitId(id);
				model.addAttribute("institutions", bd.institutionCampusFindAll());
				model.addAttribute("persons", bd.personFindAll());
				return "visits/update-visit";
			}
			visit.setVisitId(id);
			bd.setVisit(visit);
		}
		return "redirect:/visits/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deleteVisist(@PathVariable("id") long id) {
		bd.deleteVisit(id);
		return "redirect:/visits/";
	}
	@Override
	@GetMapping("/showFromPhycheckup/{id}")
	public String showFromPhycheckup(Model model, @PathVariable("id") long id) {
		List<Visit> visits = new ArrayList<>();
		Visit visit = bd.visitFindById(id);
		visits.add(visit);
		model.addAttribute("visits", visits);
		return "/visits/index";
	}

}
