package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.AddNexusPoll;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return "nexuspoll/index";
    }

    @GetMapping("/add/")
    public String addNexusPoll(Model model){
        model.addAttribute("poll", new Nexuspoll());
        model.addAttribute("institutions", bd.institutionFindAll());
        return "nexuspoll/add-nexuspoll";

    }

    @PostMapping("/add/")
    public String saveNexusPoll(@Validated(AddNexusPoll.class)Nexuspoll nexuspoll,
                                BindingResult result,
                                Model model,
                                @RequestParam(value = "action", required = true) String action){

        if (!action.equals("Cancel")) {
            if (result.hasErrors()) {
                model.addAttribute("institutions", bd.institutionFindAll());
                return "nexuspoll/add-nexuspoll";
            }

            bd.addNexusPoll(nexuspoll);
        }

        return "redirect:/front/nexuspoll/";
    }

    @GetMapping("/del/{id}")
    public String deleteNexuspoll(@PathVariable("id") long id) {
        // TODO LLAMAR AL BD
        return "redirect:/front/nexuspoll";
    }

    @GetMapping("/edit/{id}")
    public String editVisit(@PathVariable("id") long id, Model model) {

        Nexuspoll nexuspoll = bd.nexusPollFindById(id);

        model.addAttribute("poll", nexuspoll);
        model.addAttribute("institutions", bd.institutionFindAll());
        return "nexuspoll/update-nexuspoll";

    }





}
