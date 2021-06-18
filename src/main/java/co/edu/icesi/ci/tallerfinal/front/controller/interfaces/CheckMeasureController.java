package co.edu.icesi.ci.tallerfinal.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import co.edu.icesi.ci.tallerfinal.front.model.classes.CheckMeasur;

public interface CheckMeasureController {


	public String deleteCheckmeasure(long id, long id2);

	public String updateCheckmeasure(long id1, long id2, String action, CheckMeasur checkMeasur, BindingResult bindingResult,
			Model model);

	public String editCheckmeasure(long id, long id2, Model model);

	public String saveCheckmeasure(CheckMeasur checkmeasure, BindingResult result, Model model, String action);

	public String addCheckmeasure(Model model);

	public String indexCheckmeasure(Model model);

}
