package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.CheckMeasurRestController;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckMeasurRestControllerImpl implements CheckMeasurRestController {

    public CheckMeasurService checkMeasurService;

    public CheckMeasurRestControllerImpl(CheckMeasurService checkMeasurService) {
        this.checkMeasurService = checkMeasurService;
    }

    @Override
    @GetMapping("/checkmeasures/")
    public Iterable<CheckMeasur> getChekMeasur(){
        return checkMeasurService.findAll();
    }

    @Override
    @PostMapping("/checkmeasures/")
    public void saveCheckMeasur(CheckMeasur checkMeasur, long measurementId, long phycheId){
        checkMeasurService.addCheckMeasur(checkMeasur,measurementId, phycheId);
    }
    @Override
    @PutMapping("/checkmeasures/")
    public void updateCheckMeasur(CheckMeasur checkMeasur){
        checkMeasurService.editCheckMeasur(checkMeasur);
    }
}
