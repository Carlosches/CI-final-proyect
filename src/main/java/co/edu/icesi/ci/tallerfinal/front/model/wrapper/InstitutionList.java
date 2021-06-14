package co.edu.icesi.ci.tallerfinal.front.model.wrapper;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Institution;

import java.util.ArrayList;
import java.util.List;

public class InstitutionList {

    List<Institution> institutionList;

    public InstitutionList() {
        this.institutionList = new ArrayList<>();
    }

    public List<Institution> getInstitutionList() {
        return institutionList;
    }

    public void setInstitutionList(List<Institution> institutionList) {
        this.institutionList = institutionList;
    }
}
