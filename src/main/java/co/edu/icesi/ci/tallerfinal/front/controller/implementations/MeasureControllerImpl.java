package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import java.util.ArrayList;

import co.edu.icesi.ci.tallerfinal.back.repositories.InstitutionRepository;
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

import co.edu.icesi.ci.tallerfinal.front.controller.interfaces.MeasureController;
import co.edu.icesi.ci.tallerfinal.back.groups.AddMeasurement;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.service.MeasurementService;

@Controller
@RequestMapping("/measurements")
public class MeasureControllerImpl implements MeasureController {
	public MeasurementService measureService;
	public InstitutionRepository institutionRepository;

	@Autowired
	public MeasureControllerImpl( MeasurementService measureService,InstitutionRepository institutionRepository) {
		this.measureService = measureService;
		this.institutionRepository=institutionRepository;
	}

	@Override
	@GetMapping("/")
	public String indexMeasure(Model model) {
		model.addAttribute("measures", measureService.findAll());
		return "measurements/index";
	}
	@Override
	@GetMapping("/add/")
	public String addMeasurement(Model model) {
		model.addAttribute("measurement", new Measurement());
		model.addAttribute("institutions", institutionRepository.findAll());
		return "measurements/add-measure";
	}
	@Override
	@PostMapping("/add/")
	public String saveMeasurement(@RequestParam(value = "action", required = true) String action, @Validated({AddMeasurement.class}) Measurement measurement, BindingResult result, Model model ) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("institutions", institutionRepository.findAll());
				return "measurements/add-measure";
			}
			measureService.addMeasurement(measurement, measurement.getInstitution().getInstId());
		}

		return "redirect:/measurements/";

	} 
	@Override
	@GetMapping("/edit/{id}")
	public String editMeasurement(@PathVariable("id") long id, Model model) {
		Measurement measurement = measureService.getMeasurement(id);
		
		model.addAttribute("measurement", measurement);
		model.addAttribute("institutions", institutionRepository.findAll());
		return "measurements/update-measure";
	}
	@Override
	@PostMapping("/edit/{id}")
	public String updateMeasurement(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			@ModelAttribute("measurement") @Validated({AddMeasurement.class}) Measurement measurement, BindingResult bindingResult, Model model) {
		
		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {

				measurement.setMeasId(id);
				model.addAttribute("institutions", institutionRepository.findAll());
				
				return "measurements/update-measure";
			}
			measurement.setMeasId(id);
			measureService.editMeasurement(measurement);
		}
		return "redirect:/measurements/";
	}

	@Override
	@GetMapping("/del/{id}")
	public String deleteMeasurement(@PathVariable("id") long id) {
		Measurement measurement = measureService.getMeasurement(id);
		measureService.delete(measurement);
		return "redirect:/measurements/";
	}
	
	@Override
	@GetMapping("/showFromCheckMeasur/{id}")
	public String showFromCheckMeasur(Model model, @PathVariable("id") long id) {
		ArrayList<Measurement> measures = new ArrayList<>();
		measures.add(measureService.getMeasurement(id));
		model.addAttribute("measures",measures);
		
		return "measurements/show-measurements";
	}
}
