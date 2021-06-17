package co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;
import co.edu.icesi.ci.tallerfinal.back.model.Measurement;
import org.springframework.web.bind.annotation.PathVariable;

public interface MeasurementRestController {
    public Iterable<Measurement> getMeasuremets();
    public void saveMeasurement(Measurement measurement, long insitutionId);
    public void updateMeasurement(Measurement measurement);
    public Measurement findById(long measId);
    public Iterable<CheckMeasur> getCheckMeasures(@PathVariable("pycheId") long pycheId);
}
