package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Physicalcheckup;
import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.controller.interfaces.VisitController;
import co.edu.icesi.ci.tallerfinal.front.model.classes.AddVisit;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/front/visits")
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
                //System.out.println("insti id: " + visit.getInstitutioncampus().getInstcamId());
                model.addAttribute("institutions", bd.institutionCampusFindAll());
                model.addAttribute("persons", bd.personFindAll());
                return "/visits/add-visit";
            }

            bd.saveVisit(visit, visit.getPerson().getPersId(), visit.getInstitutioncampus().getInstcamId());
        }

        return "redirect:/front/visits/";

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
        return "redirect:/front/visits/";
    }

    @Override
    @GetMapping("/del/{id}")
    public String deleteVisist(@PathVariable("id") long id) {
        bd.deleteVisit(id);
        return "redirect:/front/visits/";
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
    @Override
    @GetMapping("/phycheckups/{visitId}")
    public String getPychesFromVisit(Model model, @PathVariable("visitId") long visitId){
        List<Physicalcheckup> phycheckups = bd.getPychesFromVisit(visitId);
        model.addAttribute("phycheckups", phycheckups);
        model.addAttribute("idVisit", visitId);
        model.addAttribute("from", "visit");
        return  "phycheckups/index";
    }
    @GetMapping("/bydate/")
    public String getVisitsByDate(Model model,
                                @RequestParam(value = "entranceDate", required = false) String entranceDate,
                                @RequestParam(value="exitDate", required = false) String exitDate){
        System.out.println("Exitdate: " + exitDate);
        model.addAttribute("visits", bd.visitByExitDate(exitDate));
        return"visits/index";
    }
    @GetMapping("/visitsbydate/")
    public String showConsultOption(Model model){
        model.addAttribute("visit", new Visit());
        return "/visits/consult-visit";
    }
}
