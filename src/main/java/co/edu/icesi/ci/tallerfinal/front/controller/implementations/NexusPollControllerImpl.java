package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/front/nexuspoll")
public class NexusPollControllerImpl{

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

    @GetMapping("/edit/{id}")
    public String editNexusPoll(@PathVariable("id") long id, Model model) {

        Nexuspoll nexuspoll = bd.nexusPollFindById(id);

        model.addAttribute("poll", nexuspoll);
        model.addAttribute("institutions", bd.institutionFindAll());
        return "nexuspoll/update-nexuspoll";

    }

    @PostMapping("/edit/{id}")
    public String editPostNexusPoll(@PathVariable("id") long id,
                                    @RequestParam(value = "action", required = true) String action,
                                    @ModelAttribute("visit") @Validated({AddVisit.class}) Nexuspoll nexuspoll,
                                    BindingResult bindingResult,
                                    Model model){

        if (action != null && !action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                nexuspoll.setNexpollId(id);
                model.addAttribute("institutions", bd.institutionFindAll());
                return "nexuspoll/update-nexuspoll";
            }
            nexuspoll.setNexpollId(id);

            bd.updateNexusPoll(nexuspoll);
        }
        return "redirect:/front/nexuspoll/";


    }

    @GetMapping("/del/{id}")
    public String deleteNexusPoll(@PathVariable("id") long id) {
        Nexuspoll nexuspoll = bd.nexusPollFindById(id);
        bd.deleteNexusPoll(nexuspoll);
        return "redirect:/front/nexuspoll/";
    }

}
