package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.CheckMeasurRestController;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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
    @PostMapping("/checkmeasures/data")
    public void saveCheckMeasur(@RequestBody CheckMeasur checkMeasur,
                                @RequestParam(value = "phycheId", required = true) long phycheId,
                                @RequestParam(value = "measId", required = true) long measId){
        checkMeasurService.addCheckMeasur(checkMeasur,measId, phycheId);
    }
    @Override
    @PutMapping("/checkmeasures/")
    public void updateCheckMeasur(CheckMeasur checkMeasur){
        checkMeasurService.editCheckMeasur(checkMeasur);
    }

    @Override
    @GetMapping("checkmeasures/{pycheId}/{measId}")
    public CheckMeasur findById(@PathVariable("pycheId") long phycheId,
                         @PathVariable("measId") long measId){
        CheckMeasurPK checkMeasurPK = new CheckMeasurPK();
        checkMeasurPK.setMeasMeasId(measId);
        checkMeasurPK.setPhychePhycheId(phycheId);
        return checkMeasurService.findById(checkMeasurPK);

    }
}
