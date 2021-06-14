package co.edu.icesi.ci.tallerfinal.front.model.wrapper;

import java.util.ArrayList;
import java.util.List;

public class MeasurementList {

    List<MeasurementList> measurementList;

    public MeasurementList() {
        this.measurementList = new ArrayList<>();
    }

    public List<MeasurementList> getMeasurementList() {
        return measurementList;
    }

    public void setMeasurementList(List<MeasurementList> measurementList) {
        this.measurementList = measurementList;
    }
}
