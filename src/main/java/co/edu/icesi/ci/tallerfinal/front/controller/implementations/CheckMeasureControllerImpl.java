package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import java.util.List;

import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.*;
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


@Controller
@RequestMapping("/front/checkmeasures")
public class CheckMeasureControllerImpl implements CheckMeasureController {

	public BusinessDelegate bd;
	
	@Autowired
	public CheckMeasureControllerImpl(BusinessDelegate bd) {
		this.bd = bd;
	}
	@Override
	@GetMapping("/")
	public String indexCheckmeasure(Model model) {
		model.addAttribute("checkmeasures", bd.checkMeasurFindAll());
		return "/checkmeasures/index";
	}

	@Override
	@GetMapping("/add/")
	public String addCheckmeasure(Model model) {
		model.addAttribute("checkMeasur", new CheckMeasur());
		model.addAttribute("measures", bd.measurementFindAll());
		model.addAttribute("phycheckups", bd.physicalcheckupsFindAll());
		model.addAttribute("from","get");
		return "checkmeasures/add-checkmeasure";
	}
	@Override
	@PostMapping("/add/")
	public String saveCheckmeasure(@Validated(AddCheckMeasure.class) CheckMeasur checkmeasure, BindingResult result, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (result.hasErrors()) {
				model.addAttribute("checkMeasur", checkmeasure);
				model.addAttribute("measures", bd.measurementFindAll());
				model.addAttribute("phycheckups", bd.physicalcheckupsFindAll());
				model.addAttribute("from","error");
				return "checkmeasures/add-checkmeasure";
			}
			CheckMeasurPK checkMeasurePK = new CheckMeasurPK();
			checkMeasurePK.setMeasMeasId(checkmeasure.getMeasurement().getMeasId());
			checkMeasurePK.setPhychePhycheId(checkmeasure.getPhysicalcheckup().getPhycheId());
			checkmeasure.setId(checkMeasurePK);
			if(bd.checkMeasurExistById(checkMeasurePK)==true) {
				model.addAttribute("checkMeasur", checkmeasure);
				model.addAttribute("measures", bd.measurementFindAll());
				model.addAttribute("phycheckups", bd.physicalcheckupsFindAll());
				model.addAttribute("from","sameId");
				return "checkmeasures/add-checkmeasure";
			}
			bd.saveCheckMeasur(checkmeasure, checkmeasure.getMeasurement().getMeasId(), checkmeasure.getPhysicalcheckup().getPhycheId());
			Physicalcheckup phy = checkmeasure.getPhysicalcheckup();
			phy.addCheckMeasur(checkmeasure);
			bd.setPhysicalcheckup(phy);
			Measurement meas = checkmeasure.getMeasurement();
			meas.addCheckMeasur(checkmeasure);
			bd.setMeasurement(meas);
		}

		return "redirect:/front/checkmeasures/";

	} 
	@Override
	@GetMapping("/edit/{phycheId}/{measId}")
	public String editCheckmeasure(@PathVariable("phycheId") long phycheId,@PathVariable("measId") long measId, Model model) {
		CheckMeasur checkMeasur = bd.checkMeasurFindById(phycheId,measId);
		
		model.addAttribute("checkMeasur", checkMeasur);
		model.addAttribute("measures", bd.measurementFindAll());
		model.addAttribute("phycheckups", bd.physicalcheckupsFindAll());
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
				model.addAttribute("measures", bd.measurementFindAll());
				model.addAttribute("phycheckups", bd.physicalcheckupsFindAll());
				model.addAttribute("from","error");
				return "checkmeasures/update-checkmeasure";
			}
			checkMeasur.setId(chPK);
			bd.setCheckMeasur(checkMeasur);
		}
		return "redirect:/front/checkmeasures/";
	}

	@Override
	@GetMapping("/del/{phycheId}/{measId}")
	public String deleteCheckmeasure(@PathVariable("phycheId") long phycheId, @PathVariable("measId") long measId) {
		CheckMeasur check = bd.checkMeasurFindById(phycheId,measId);
		bd.deleteCheckMeasur(check);
		return "redirect:/front/checkmeasures/";
	}

}
