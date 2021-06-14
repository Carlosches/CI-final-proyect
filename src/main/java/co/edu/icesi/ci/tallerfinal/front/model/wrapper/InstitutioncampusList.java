package co.edu.icesi.ci.tallerfinal.front.model.wrapper;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Institution;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Institutioncampus;

import java.util.ArrayList;
import java.util.List;

public class InstitutioncampusList {

    List<Institutioncampus> institutioncampusList;

    public InstitutioncampusList() {
        this.institutioncampusList = new ArrayList<>();
    }

    public List<Institutioncampus> getInstitutioncampusList() {
        return institutioncampusList;
    }

    public void setInstitutioncampusList(List<Institutioncampus> institutioncampusList) {
        this.institutioncampusList = institutioncampusList;
    }
}
