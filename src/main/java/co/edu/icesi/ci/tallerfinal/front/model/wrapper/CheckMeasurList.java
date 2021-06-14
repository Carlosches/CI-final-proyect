package co.edu.icesi.ci.tallerfinal.front.model.wrapper;

import co.edu.icesi.ci.tallerfinal.front.model.classes.CheckMeasur;

import java.util.ArrayList;
import java.util.List;

public class CheckMeasurList {

    List<CheckMeasur> checkMeasurList;

    public CheckMeasurList() {
        this.checkMeasurList = new ArrayList<>();
    }

    public List<CheckMeasur> getCheckMeasurList() {
        return checkMeasurList;
    }

    public void setCheckMeasurList(List<CheckMeasur> checkMeasurList) {
        this.checkMeasurList = checkMeasurList;
    }
}
