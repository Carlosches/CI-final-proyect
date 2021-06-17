package co.edu.icesi.ci.tallerfinal.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Physicalcheckup;

public interface PhysicalCheckupController {
	
	public String indexPhycheckup(Model model);

	public String addPhycheckup(Model model);
	
	public String savePhycheckup(Physicalcheckup phycheckup, BindingResult result, Model model,String action); 
	
	public String editPhycheckup(long id, Model model);
	
	public String updatePhycheckup(long id,String action, Physicalcheckup phycheckup, BindingResult bindingResult, Model model);

	public String deletePhycheckup(long id);

	public String showFromVisit(long id, Model model);

	public String showFromCheckMeasur(Model model, long id);
}
