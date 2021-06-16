package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.controller.interfaces.PhysicalCheckupController;
import co.edu.icesi.ci.tallerfinal.front.model.classes.AddPhycheckup;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/api/phycheckups")
public class PhysicalCheckupControllerImpl implements PhysicalCheckupController {

    public BusinessDelegate bd;

    @Autowired
    public PhysicalCheckupControllerImpl(BusinessDelegate bd) {
        this.bd = bd;
    }

    @Override
    @GetMapping("/")
    public String indexPhycheckup(Model model) {
        model.addAttribute("phycheckups", bd.physicalcheckupsFindAll());
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
            bd.setVisit(visit);
            bd.savePhysicalcheckup(physicalcheckup, physicalcheckup.getPerson().getPersId(), visit.getVisitId());
        }

        return "redirect:/phycheckups/";

    }

    @Override
    @GetMapping("/edit/{id}")
    public String editPhycheckup(@PathVariable("id") long id, Model model) {
        Physicalcheckup phycheckup = bd.physicalcheckupsFindById(id);

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
            bd.setPhysicalcheckup(physicalcheckup);
        }
        return "redirect:/phycheckups/";
    }

    @Override
    @GetMapping("/del/{id}")
    public String deletePhycheckup(@PathVariable("id") long id) {
        Physicalcheckup phycheckup = bd.physicalcheckupsFindById(id);
        bd.deletePhysicalcheckup(phycheckup.getPhycheId());
        return "redirect:/phycheckups/";
    }

    @Override
    @GetMapping("/showFromVisit/{id}")
    public String showFromVisit(@PathVariable("id") long id, Model model) {

        Visit visit = bd.visitFindById(id);
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
        phycheckups.add(bd.physicalcheckupsFindById(id));
        model.addAttribute("phycheckups", phycheckups);
        model.addAttribute("from", "checkMeasur");
        return "phycheckups/index";
    }


}
