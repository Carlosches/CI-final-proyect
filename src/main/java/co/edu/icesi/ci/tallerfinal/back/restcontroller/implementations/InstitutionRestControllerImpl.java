package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Institution;
import co.edu.icesi.ci.tallerfinal.back.model.Institutioncampus;
import co.edu.icesi.ci.tallerfinal.back.repositories.CampusRepository;
import co.edu.icesi.ci.tallerfinal.back.repositories.InstitutionRepository;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.InstitutionRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/institutions")
public class InstitutionRestControllerImpl implements InstitutionRestController {



    private InstitutionRepository institutionRepository;

    public InstitutionRestControllerImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }
    @Override
    @GetMapping("/")
    public Iterable<Institution> getInstitution(){
        return institutionRepository.findAll();
    }
}
