package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front/nexuspoll")
public class NexusPollControllerImpl {

    public BusinessDelegate bd;

    @Autowired
    public NexusPollControllerImpl(BusinessDelegate bd){
        this.bd = bd;
    }

    @GetMapping("/")
    public String indexVisit(Model model) {
        model.addAttribute("polls", bd.nexusPollFindAll()); // TODO
        return "/nexuspoll/index";
    }


    @Override
    @GetMapping("/edit/{id}")
    public String editVisit(@PathVariable("id") long id, Model model) {
//        Visit visit = bd.visitFindById(id);
//
//        model.addAttribute("visit", visit);
//        model.addAttribute("institutions", bd.institutionCampusFindAll());
//        model.addAttribute("persons", bd.personFindAll());
//        return "visits/update-visit";
        return null;
    }





}
