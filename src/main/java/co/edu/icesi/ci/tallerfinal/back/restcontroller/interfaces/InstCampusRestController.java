package co.edu.icesi.ci.tallerfinal.back.restcontroller.interfaces;

import co.edu.icesi.ci.tallerfinal.back.model.Institutioncampus;

public interface InstCampusRestController {

    public Iterable<Institutioncampus> getInstCampus();
    public Institutioncampus getInstCampus(long instId);
}
