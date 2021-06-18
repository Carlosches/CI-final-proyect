package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Institutioncampus;
import co.edu.icesi.ci.tallerfinal.back.model.Visit;
import co.edu.icesi.ci.tallerfinal.back.repositories.CampusRepository;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.InstCampusRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InstCampusRestControllerImpl implements InstCampusRestController {

    private CampusRepository campusRepository;

    @Autowired
    public InstCampusRestControllerImpl(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @Override
    @GetMapping("/institutioncampus/")
    public Iterable<Institutioncampus> getInstCampus(){
        return campusRepository.findAll();
    }

    @Override
    @GetMapping("/institutioncampus/{instId}")
    public Institutioncampus getInstCampus(@PathVariable("instId") long instId){
        return campusRepository.findById(instId).get();
    }


}
