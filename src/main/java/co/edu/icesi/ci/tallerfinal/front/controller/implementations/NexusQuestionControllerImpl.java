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
@RequestMapping("/front/nexusquest")
public class NexusQuestionControllerImpl {

    public BusinessDelegate bd;

    @Autowired
    public NexusQuestionControllerImpl(BusinessDelegate bd){
        this.bd = bd;
    }

    @GetMapping("/")
    public String indexVisit(Model model) {
        model.addAttribute("quests", bd.nexusQuestionsFindAll()); // TODO
        return "nexusquest/index";
    }

    @GetMapping("/add/")
    public String addNexusPollQuestion(Model model){
        model.addAttribute("quest", new Nexusquestion());
        model.addAttribute("polls", bd.nexusPollFindAll());
        return "nexusquest/add-nexusquest";

    }

    @PostMapping("/add/")
    public String saveNexusPollQuestion(@Validated(AddNexusQuestion.class)Nexusquestion nexusquestion,
                                BindingResult result,
                                Model model,
                                @RequestParam(value = "action", required = true) String action){

        if (!action.equals("Cancel")) {
            if (result.hasErrors()) {
                model.addAttribute("institutions", bd.institutionFindAll());
                return "nexuspoll/add-nexusquest";
            }

            bd.addNexusPollQuestion(nexusquestion);
        }

        return "redirect:/front/nexusquest/";
    }

    @GetMapping("/del/{id}")
    public String deleteNexusPollQuestion(@PathVariable("id") long id) {
        Nexusquestion nexusquestion = bd.nexusPollQuestionFindById(id);
        bd.deleteNexusQuestion(nexusquestion);
        return "redirect:/front/nexusquest/";
    }

    @GetMapping("/edit/{id}")
    public String editNexusPollQuestion(@PathVariable("id") long id, Model model) {

        Nexusquestion nexuspollQuestion = bd.nexusPollQuestionFindById(id);

        model.addAttribute("quest", nexuspollQuestion);
        model.addAttribute("polls", bd.nexusPollFindAll());
        return "nexuspoll/update-nexusquest";

    }

    @PostMapping("/edit/{id}")
    public String editPostNexusPoll(@PathVariable("id") long id,
                                    @RequestParam(value = "action", required = true) String action,
                                    @ModelAttribute("visit") @Validated({AddVisit.class}) Nexusquestion nexuspollQuestion,
                                    BindingResult bindingResult,
                                    Model model){

        if (action != null && !action.equals("Cancel")) {
            if (bindingResult.hasErrors()) {
                nexuspollQuestion.setNexquesId(id);
                model.addAttribute("polls", bd.nexusPollFindAll());
                return "nexuspoll/update-nexusquest";
            }
            nexuspollQuestion.setNexquesId(id);

            bd.updateNexusPollQuestion(nexuspollQuestion);
        }
        return "redirect:/front/nexusquest/";


    }





}
