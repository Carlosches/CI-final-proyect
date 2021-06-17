package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.MeasurementRestController;
import co.edu.icesi.ci.tallerfinal.back.service.CheckMeasurService;
import co.edu.icesi.ci.tallerfinal.back.service.MeasurementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MeasurementRestControllerImpl implements MeasurementRestController {

    private MeasurementService measurementService;
    private CheckMeasurService checkMeasurService;

    public MeasurementRestControllerImpl(MeasurementService measurementService,CheckMeasurService checkMeasurService) {
        this.measurementService = measurementService;
        this.checkMeasurService = checkMeasurService;
    }
    @Override
    @GetMapping("/measurements/")
    public Iterable<Measurement> getMeasuremets(){
        return measurementService.findAll();
    }

    @Override
    @PostMapping("/measurements/")
    public void saveMeasurement(Measurement measurement, long insitutionId){
        measurementService.addMeasurement(measurement, insitutionId);
    }

    @Override
    @PutMapping("/measurements/")
    public void updateMeasurement(Measurement measurement){
        measurementService.editMeasurement(measurement);
    }

    @Override
    @GetMapping("/measurements/{measId}")
    public Measurement findById(@PathVariable("measId") long measId) {
        return measurementService.getMeasurement(measId);
    }
    @Override
    @GetMapping("/measurements/checkmeasures/{measId}")
    public Iterable<CheckMeasur> getCheckMeasures(@PathVariable("measId") long measId) {
        return checkMeasurService.findByMeasId(measId);
    }

}
