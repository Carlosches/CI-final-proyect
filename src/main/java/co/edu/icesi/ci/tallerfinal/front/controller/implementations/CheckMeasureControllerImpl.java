package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import java.util.List;

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

import co.edu.icesi.ci.tallerfinal.front.controller.interfaces.CheckMeasureController;
import co.edu.icesi.ci.tallerfinal.back.groups.AddCheckMeasure;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.model.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurService;
import co.edu.icesi.ci.tallerfinal.back.service.MeasurementService;
import co.edu.icesi.ci.tallerfinal.back.service.PhysicalcheckupService;



@Controller
@RequestMapping("/checkmeasures")
public class CheckMeasureControllerImpl implements CheckMeasureController {
	
	private CheckMeasurService checkMeasureService;
	private MeasurementService measurementService;
	private PhysicalcheckupService phychechupService;
	
	@Autowired
	public CheckMeasureControllerImpl(CheckMeasurService checkMeasureService, MeasurementService measurementService,
			PhysicalcheckupService phychechupService) {
		this.checkMeasureService = checkMeasureService;
		this.measurementService = measurementService;
		this.phychechupService = phychechupService;
	}
	@Override
	@GetMapping("/")
	public String indexCheckmeasure(Model model) {
		model.addAttribute("checkmeasures", checkMeasureService.findAll());
		return "/checkmeasures/index";
	}

	@Override
	@GetMapping("/add/")
	public String addCheckmeasure(Model model) {
		model.addAttribute("checkMeasur", new CheckMeasur());
		model.addAttribute("measures", measurementService.findAll());
		model.addAttribute("phycheckups", phychechupService.findAll());
		model.addAttribute("from","get");
		return "checkmeasures/add-checkmeasure";
	}
	@Override
	@PostMapping("/add/")
	public String saveCheckmeasure(@Validated(AddCheckMeasure.class) CheckMeasur checkmeasure, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("checkMeasur", checkmeasure);
				model.addAttribute("measures", measurementService.findAll());
				model.addAttribute("phycheckups", phychechupService.findAll());
				model.addAttribute("from","error");
				return "checkmeasures/add-checkmeasure";
			}
			CheckMeasurPK checkMeasurePK = new CheckMeasurPK();
			checkMeasurePK.setMeasMeasId(checkmeasure.getMeasurement().getMeasId());
			checkMeasurePK.setPhychePhycheId(checkmeasure.getPhysicalcheckup().getPhycheId());
			checkmeasure.setId(checkMeasurePK);
			if(checkMeasureService.existById(checkMeasurePK)==true) {
				model.addAttribute("checkMeasur", checkmeasure);
				model.addAttribute("measures", measurementService.findAll());
				model.addAttribute("phycheckups", phychechupService.findAll());
				model.addAttribute("from","sameId");
				return "checkmeasures/add-checkmeasure";
			}
			checkMeasureService.addCheckMeasur(checkmeasure, checkmeasure.getMeasurement().getMeasId(), checkmeasure.getPhysicalcheckup().getPhycheId());
			Physicalcheckup phy = checkmeasure.getPhysicalcheckup();
			phy.addCheckMeasur(checkmeasure);
			phychechupService.editPhysicalcheckup(phy);
			Measurement meas = checkmeasure.getMeasurement();
			meas.addCheckMeasur(checkmeasure);
			measurementService.editMeasurement(meas);
		}

		return "redirect:/checkmeasures/";

	} 
	@Override
	@GetMapping("/edit/{id}/{id2}")
	public String editCheckmeasure(@PathVariable("id") long id,@PathVariable("id2") long id2, Model model) {
		CheckMeasurPK chPK = new CheckMeasurPK();
		chPK.setPhychePhycheId(id);
		chPK.setMeasMeasId(id2);
		CheckMeasur checkMeasur = checkMeasureService.getCheckMeasur(chPK);
		
		model.addAttribute("checkMeasur", checkMeasur);
		model.addAttribute("measures", measurementService.findAll());
		model.addAttribute("phycheckups", phychechupService.findAll());
		model.addAttribute("from","get");
		return "checkmeasures/update-checkmeasure";
	}
	@Override
	@PostMapping("/edit/{id1}/{id2}")
	public String updateCheckmeasure(@PathVariable("id1") long id1,@PathVariable("id2") long id2, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("checkMeasur") @Validated(AddCheckMeasure.class) CheckMeasur checkMeasur, BindingResult bindingResult, Model model) {
		CheckMeasurPK chPK = new CheckMeasurPK();
		chPK.setPhychePhycheId(id1);
		chPK.setMeasMeasId(id2);
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				checkMeasur.setId(chPK);
				model.addAttribute("measures", measurementService.findAll());
				model.addAttribute("phycheckups", phychechupService.findAll());
				model.addAttribute("from","error");
				return "checkmeasures/update-checkmeasure";
			}
			checkMeasur.setId(chPK);
			checkMeasureService.editCheckMeasur(checkMeasur);
		}
		return "redirect:/checkmeasures/";
	}

	@Override
	@GetMapping("/del/{id}/{id2}")
	public String deleteCheckmeasure(@PathVariable("id") long id,@PathVariable("id2") long id2) {
		CheckMeasurPK chPK = new CheckMeasurPK();
		chPK.setPhychePhycheId(id);
		chPK.setMeasMeasId(id2);
		CheckMeasur check = checkMeasureService.getCheckMeasur(chPK);
		checkMeasureService.deleted(check);
		return "redirect:/checkmeasures/";
	}
	@Override
	@GetMapping("/showFromPhycheckup/{id}")
	public String showFromPhycheckup(@PathVariable("id") long id, Model model) {
		Physicalcheckup phy = phychechupService.getPhysicalcheckup(id);
		List<CheckMeasur> ch = (List<CheckMeasur>) phy.getCheckMeasurs();
		model.addAttribute("checkmeasures", ch);
		return "/checkmeasures/index";
	}
	@Override
	@GetMapping("/showFromMeasures/{id}")
	public String showFromMeasures(@PathVariable("id") long id, Model model) {
		Measurement measurement = measurementService.getMeasurement(id);
		List<CheckMeasur> ch = (List<CheckMeasur>) measurement.getCheckMeasurs();
		model.addAttribute("checkmeasures", ch);
		return "/checkmeasures/show-checkmeasures";
	}
}
