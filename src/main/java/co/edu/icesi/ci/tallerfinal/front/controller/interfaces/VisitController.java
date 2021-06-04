package co.edu.icesi.ci.tallerfinal.front.controller.interfaces;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import co.edu.icesi.ci.tallerfinal.back.model.Visit;

public interface VisitController {

	public String indexVisit(Model model);

	public String addVisit(Model model);
	public String saveVisit(Visit visit, BindingResult result, Model model,String action);

	public String editVisit(long id, Model model);
	
	public String updateVisit(long id,String action,Visit visit, BindingResult bindingResult, Model model);

	public String deleteVisist(long id);

	public String showFromPhycheckup(Model model, long id);
}
