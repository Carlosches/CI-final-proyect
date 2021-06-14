package co.edu.icesi.ci.tallerfinal.front.model.wrapper;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;

import java.util.ArrayList;
import java.util.List;

public class VisitList {

    private List<Visit> visits;

    public VisitList() {
        this.visits = new ArrayList<>();
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
