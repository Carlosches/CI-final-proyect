package co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces;

import co.edu.icesi.ci.tallerfinal.back.model.CheckMeasur;

import java.util.List;

public interface CheckMeasurRestController {
    public Iterable<CheckMeasur> getChekMeasur();
    public void saveCheckMeasur(CheckMeasur checkMeasur, long measurementId, long phycheId);
    public void updateCheckMeasur(CheckMeasur checkMeasur);
}
