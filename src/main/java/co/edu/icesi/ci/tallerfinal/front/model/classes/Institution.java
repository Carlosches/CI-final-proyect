package co.edu.icesi.ci.tallerfinal.front.model.classes;

import javax.persistence.*;
import java.util.List;

public class Institution {

    private static final long serialVersionUID = 1L;

    private long instId;

    private String instAcademicserverurl;

    private String instAcadextradataurl;

    private String instAcadloginpassword;

    private String instAcadloginurl;

    private String instAcadloginusername;

    private String instAcadpersoninfodocurl;

    private String instAcadpersoninfoidurl;

    private String instAcadphysicalspacesurl;

    private String instAcadprogrammedcoursesurl;

    private String instLdapbasedn;

    private String instLdappassword;

    private String instLdapurl;

    private String instLdapusername;

    private String instLdapusersearchbase;

    private String instLdapusersearchfilter;

    private String instName;

    private List<Institutioncampus> institutioncampuses;

    // bi-directional many-to-one association to Measurement
    @OneToMany(mappedBy = "institution")
    private List<Measurement> measurements;

    // bi-directional many-to-one association to Person
    @OneToMany(mappedBy = "institution")
    private List<Person> persons;

    public Institution() {
    }

    public long getInstId() {
        return this.instId;
    }

    public void setInstId(long instId) {
        this.instId = instId;
    }

    public String getInstAcademicserverurl() {
        return this.instAcademicserverurl;
    }

    public void setInstAcademicserverurl(String instAcademicserverurl) {
        this.instAcademicserverurl = instAcademicserverurl;
    }

    public String getInstAcadextradataurl() {
        return this.instAcadextradataurl;
    }

    public void setInstAcadextradataurl(String instAcadextradataurl) {
        this.instAcadextradataurl = instAcadextradataurl;
    }

    public String getInstAcadloginpassword() {
        return this.instAcadloginpassword;
    }

    public void setInstAcadloginpassword(String instAcadloginpassword) {
        this.instAcadloginpassword = instAcadloginpassword;
    }

    public String getInstAcadloginurl() {
        return this.instAcadloginurl;
    }

    public void setInstAcadloginurl(String instAcadloginurl) {
        this.instAcadloginurl = instAcadloginurl;
    }

    public String getInstAcadloginusername() {
        return this.instAcadloginusername;
    }

    public void setInstAcadloginusername(String instAcadloginusername) {
        this.instAcadloginusername = instAcadloginusername;
    }

    public String getInstAcadpersoninfodocurl() {
        return this.instAcadpersoninfodocurl;
    }

    public void setInstAcadpersoninfodocurl(String instAcadpersoninfodocurl) {
        this.instAcadpersoninfodocurl = instAcadpersoninfodocurl;
    }

    public String getInstAcadpersoninfoidurl() {
        return this.instAcadpersoninfoidurl;
    }

    public void setInstAcadpersoninfoidurl(String instAcadpersoninfoidurl) {
        this.instAcadpersoninfoidurl = instAcadpersoninfoidurl;
    }

    public String getInstAcadphysicalspacesurl() {
        return this.instAcadphysicalspacesurl;
    }

    public void setInstAcadphysicalspacesurl(String instAcadphysicalspacesurl) {
        this.instAcadphysicalspacesurl = instAcadphysicalspacesurl;
    }

    public String getInstAcadprogrammedcoursesurl() {
        return this.instAcadprogrammedcoursesurl;
    }

    public void setInstAcadprogrammedcoursesurl(String instAcadprogrammedcoursesurl) {
        this.instAcadprogrammedcoursesurl = instAcadprogrammedcoursesurl;
    }

    public String getInstLdapbasedn() {
        return this.instLdapbasedn;
    }

    public void setInstLdapbasedn(String instLdapbasedn) {
        this.instLdapbasedn = instLdapbasedn;
    }

    public String getInstLdappassword() {
        return this.instLdappassword;
    }

    public void setInstLdappassword(String instLdappassword) {
        this.instLdappassword = instLdappassword;
    }

    public String getInstLdapurl() {
        return this.instLdapurl;
    }

    public void setInstLdapurl(String instLdapurl) {
        this.instLdapurl = instLdapurl;
    }

    public String getInstLdapusername() {
        return this.instLdapusername;
    }

    public void setInstLdapusername(String instLdapusername) {
        this.instLdapusername = instLdapusername;
    }

    public String getInstLdapusersearchbase() {
        return this.instLdapusersearchbase;
    }

    public void setInstLdapusersearchbase(String instLdapusersearchbase) {
        this.instLdapusersearchbase = instLdapusersearchbase;
    }

    public String getInstLdapusersearchfilter() {
        return this.instLdapusersearchfilter;
    }

    public void setInstLdapusersearchfilter(String instLdapusersearchfilter) {
        this.instLdapusersearchfilter = instLdapusersearchfilter;
    }

    public String getInstName() {
        return this.instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public List<Institutioncampus> getInstitutioncampuses() {
        return this.institutioncampuses;
    }

    public void setInstitutioncampuses(List<Institutioncampus> institutioncampuses) {
        this.institutioncampuses = institutioncampuses;
    }

    public Institutioncampus addInstitutioncampus(Institutioncampus institutioncampus) {
        getInstitutioncampuses().add(institutioncampus);
        institutioncampus.setInstitution(this);

        return institutioncampus;
    }

    public Institutioncampus removeInstitutioncampus(Institutioncampus institutioncampus) {
        getInstitutioncampuses().remove(institutioncampus);
        institutioncampus.setInstitution(null);

        return institutioncampus;
    }

    public List<Measurement> getMeasurements() {
        return this.measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public Measurement addMeasurement(Measurement measurement) {
        getMeasurements().add(measurement);
        measurement.setInstitution(this);

        return measurement;
    }

    public Measurement removeMeasurement(Measurement measurement) {
        getMeasurements().remove(measurement);
        measurement.setInstitution(null);

        return measurement;
    }

    public List<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Person addPerson(Person person) {
        getPersons().add(person);
        person.setInstitution(this);

        return person;
    }

    public Person removePerson(Person person) {
        getPersons().remove(person);
        person.setInstitution(null);

        return person;
    }

}
