package co.edu.icesi.ci.tallerfinal.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import co.edu.icesi.ci.tallerfinal.back.model.Measurement;

public interface MeasureController {
	
	public String indexMeasure(Model model);

	public String addMeasurement(Model model);
	public String saveMeasurement( String action, Measurement measure, BindingResult result, Model model );
	
	public String editMeasurement(long id, Model model);
	
	public String updateMeasurement(long id,  String action, Measurement measure, BindingResult bindingResult, Model model);

	public String deleteMeasurement(long id);

	public String showFromCheckMeasur(Model model, long id);
}
