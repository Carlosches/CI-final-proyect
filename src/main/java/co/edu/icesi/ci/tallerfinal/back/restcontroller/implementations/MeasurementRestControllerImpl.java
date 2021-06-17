package co.edu.icesi.ci.tallerfinal.back.restcontroller.implementations;

import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces.MeasurementRestController;
import co.edu.icesi.ci.tallerfinal.back.service.MeasurementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MeasurementRestControllerImpl implements MeasurementRestController {

    private MeasurementService measurementService;

    public MeasurementRestControllerImpl(MeasurementService measurementService) {
        this.measurementService = measurementService;
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
}
