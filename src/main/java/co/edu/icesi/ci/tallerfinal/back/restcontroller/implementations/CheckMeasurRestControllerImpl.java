package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasurPK;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.CheckMeasurRestController;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurService;
import co.edu.icesi.ci.tallerfinal.back.service.MeasurementService;
import co.edu.icesi.ci.tallerfinal.back.service.PhysicalcheckupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CheckMeasurRestControllerImpl implements CheckMeasurRestController {

    private CheckMeasurService checkMeasurService;
    private MeasurementService measurementService;
    private PhysicalcheckupService physicalcheckupService;

    public CheckMeasurRestControllerImpl(CheckMeasurService checkMeasurService, MeasurementService measurementService, PhysicalcheckupService physicalcheckupService) {
        this.checkMeasurService = checkMeasurService;
        this.measurementService = measurementService;
        this.physicalcheckupService = physicalcheckupService;
    }


    @Override
    @GetMapping("/checkmeasures/")
    public Iterable<CheckMeasur> getChekMeasur(){
        return checkMeasurService.findAll();
    }

    @Override
    @PostMapping("/checkmeasures/")
    public void saveCheckMeasur(@RequestBody CheckMeasur checkMeasur,
                                @RequestParam(value = "phycheId", required = true) long phycheId,
                                @RequestParam(value = "measId", required = true) long measId){
        CheckMeasurPK checkMeasurPK = new CheckMeasurPK();
        checkMeasurPK.setMeasMeasId(measId);
        checkMeasurPK.setPhychePhycheId(phycheId);
        checkMeasur.setId(checkMeasurPK);
        checkMeasurService.addCheckMeasur(checkMeasur,measId, phycheId);
    }
    @Override
    @PutMapping("/checkmeasures/")
    public void updateCheckMeasur(@RequestBody CheckMeasur checkMeasur){
        checkMeasur.setMeasurement(measurementService.getMeasurement(checkMeasur.getId().getMeasMeasId()));
        checkMeasur.setPhysicalcheckup(physicalcheckupService.getPhysicalcheckup(checkMeasur.getId().getPhychePhycheId()));

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
