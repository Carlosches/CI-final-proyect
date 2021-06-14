package co.edu.icesi.ci.tallerfinal.front.model.wrapper;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Physicalcheckup;

import java.util.ArrayList;
import java.util.List;

public class PhysicalcheckupList {

    List<Physicalcheckup> physicalcheckupList;

    public PhysicalcheckupList() {
        this.physicalcheckupList = new ArrayList<>();
    }

    public List<Physicalcheckup> getPhysicalcheckupList() {
        return physicalcheckupList;
    }

    public void setPhysicalcheckupList(List<Physicalcheckup> physicalcheckupList) {
        this.physicalcheckupList = physicalcheckupList;
    }
}
