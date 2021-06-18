package co.edu.icesi.ci.tallerfinal.front.bd;

import co.edu.icesi.ci.tallerfinal.front.model.classes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
public class BusinessDelegateImpl implements BusinessDelegate {

    public static final String REST_URL = "http://localhost:8080/api";

    private RestTemplate restTemplate;

    public BusinessDelegateImpl() {
       //  this.restTemplate = restTemplate;
        this.restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);

    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
// ==========================
    // Person
    // ==========================

    public List<Person> personFindAll(){

        String endpoint = REST_URL + "/persons/";

        Person[] r = restTemplate.getForObject(endpoint, Person[].class);
        List<Person> response = Arrays.asList(r);

        return response;
    }
    public Person personFindById(long persId){
        String endpoint = REST_URL+"/persons/"+persId;
        Person r = restTemplate.getForObject(endpoint, Person.class);
        return r;
    }

    // ==========================
    // Institution
    // ==========================

    // TODO in REST CONTROLLER
    public List<Institution> institutionFindAll(){

        String endpoint = REST_URL + "/institutions/";

        Institution[] r = restTemplate.getForObject(endpoint, Institution[].class);
        List<Institution> response = Arrays.asList(r);

        return  response;
    }


    // ==========================
    // InstitutionCampus
    // ==========================

    public List<Institutioncampus> institutionCampusFindAll(){

        String endpoint = REST_URL + "/institutioncampus/";

        Institutioncampus[] r = restTemplate.getForObject(endpoint, Institutioncampus[].class);
        List<Institutioncampus> response = Arrays.asList(r);

        return response;

    }
    public Institutioncampus institutioncampusFindById(long instId){
        String endpoint = REST_URL + "/institutioncampus/"+instId;
        Institutioncampus r = restTemplate.getForObject(endpoint, Institutioncampus.class);
        return r;
    }

    // ==========================
    // Visit
    // ==========================

    //GET
    public List<Visit> visitFindAll() {
        String endpoint = REST_URL + "/visits/";
      //  System.out.println(endpoint);
        Visit[] r = restTemplate.getForObject(endpoint, Visit[].class);
        List<Visit> response = Arrays.asList(r);

        return response;
    }

    //GET
    public Visit visitFindById(long persId){

        // REST endpoint
        String endpoint = REST_URL + "/visits/" + persId;

        Visit response = restTemplate.getForObject(endpoint, Visit.class);
        return response; // TODO
    }

    //POST
    public Visit saveVisit(Visit visit, long personId, long campusId){

        // REST endpoint
        String endpoint = REST_URL + "/visits";

        // Add query parameters to URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("personId", personId)
                .queryParam("campusId", campusId);
        endpoint = builder.toUriString();


        // Add Entity
        HttpEntity<Visit> request = new HttpEntity<>(visit);

        // Perform REST CALL
        Visit response = restTemplate.postForObject(endpoint, request, Visit.class);

        return response;

    }

    //PUT
    public void setVisit(Visit visit){

        String endpoint = REST_URL + "/visits/";
        restTemplate.put(endpoint, visit, Visit.class);

    }

    // DELETE // TODO in REST CONTROLLER
    public void deleteVisit(long visitId){

        String endpoint = REST_URL + "/visits/" + visitId;

        restTemplate.delete(endpoint);
    }
    public List<Physicalcheckup> getPychesFromVisit(long visitId){
        String endpoint = REST_URL + "/visits/phycheckups/"+visitId;
        Physicalcheckup[] r = restTemplate.getForObject(endpoint, Physicalcheckup[].class);
        List<Physicalcheckup> response = Arrays.asList(r);
        return response;
    }
    // ==========================
    // PHYSICAL CHECKUP
    // ==========================

    // GET // TODO in REST CONTROLLER
    public List<Physicalcheckup> physicalcheckupsFindAll(){

        String endpoint = REST_URL + "/phycheckups/";

        Physicalcheckup[] r = restTemplate.getForObject(endpoint, Physicalcheckup[].class);
        List<Physicalcheckup> response = Arrays.asList(r);

        return response;

    }

    // GET // TODO in REST CONTROLLER
    public Physicalcheckup physicalcheckupsFindById(long id){

        String endpoint = REST_URL + "/phycheckups/" + id;

        Physicalcheckup response = restTemplate.getForObject(endpoint, Physicalcheckup.class);

        return response;
    }

    // POST // TODO in REST CONTROLLER
    public Physicalcheckup savePhysicalcheckup(Physicalcheckup pc, long persId, long visitId){

        // REST endpoint
        String endpoint = REST_URL + "/phycheckups";

        // Add query parameters to URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("persId", persId)
                .queryParam("visitId", visitId);
        endpoint = builder.toUriString();


        // Add Entity
        HttpEntity<Physicalcheckup> request = new HttpEntity<>(pc);

        // Perform REST CALL
        Physicalcheckup response = restTemplate.postForObject(endpoint, request, Physicalcheckup.class);

        return response;

    }

    //PUT // TODO in REST CONTROLLER
    public void setPhysicalcheckup(Physicalcheckup pc){

        String endpoint = REST_URL + "/phycheckups/";

        restTemplate.put(endpoint, pc, Physicalcheckup.class);

    }

    // DELETE // TODO in REST CONTROLLER
    public void deletePhysicalcheckup(long phycheId){

        String endpoint = REST_URL + "/phycheckups/" + phycheId;

        restTemplate.delete(endpoint);

    }
    public List<CheckMeasur> getCheckMeasureFromPyche(long pycheId){
        String endpoint = REST_URL + "/phycheckups/checkmeasures/" + pycheId;
        CheckMeasur[] r = restTemplate.getForObject(endpoint, CheckMeasur[].class);
        List<CheckMeasur> response = Arrays.asList(r);
        return response;
    }
    // ==========================
    // MEASUREMENT
    // ==========================

    // GET // TODO in REST CONTROLLER
    public List<Measurement> measurementFindAll(){

        String endpoint = REST_URL + "/measurements/";

        Measurement[] r = restTemplate.getForObject(endpoint, Measurement[].class);
        List<Measurement> response = Arrays.asList(r);

        return response;
    }

    // GET // TODO in REST CONTROLLER
    public Measurement measurementFindById(long measId){

        String endpoint = REST_URL + "/measurements/" + measId;

        Measurement response = restTemplate.getForObject(endpoint, Measurement.class);

        return response;

    }

    // POST // TODO in REST CONTROLLER
    public Measurement saveMeasurement(Measurement measurement, long instId){
        // REST endpoint
        String endpoint = REST_URL + "/measurements";

        // Add query parameters to URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("instId", instId);
        endpoint = builder.toUriString();

        // Perform REST CALL
        Measurement response = restTemplate.postForObject(endpoint, measurement, Measurement.class);

        return response;
    }

    // PUT // TODO in REST CONTROLLER
    public void setMeasurement(Measurement measurement){
        String endpoint = REST_URL + "/measurements/";

        restTemplate.put(endpoint, measurement, Measurement.class);
    }

    // DELETE  // TODO in REST CONTROLLER
    public void deleteMeasurement(long measId){

        String endpoint = REST_URL + "/measurements/" + measId;

        restTemplate.delete(endpoint);

    }
    public List<CheckMeasur> getCheckMeasureFromMeas(long measId){
        String endpoint = REST_URL + "/measurements/checkmeasures/" + measId;
        CheckMeasur[] r = restTemplate.getForObject(endpoint, CheckMeasur[].class);
        List<CheckMeasur> response = Arrays.asList(r);
        return response;
    }
    // ==========================
    // CHECK MEASURES
    // ==========================

    // GET // TODO in REST CONTROLLER
    public List<CheckMeasur> checkMeasurFindAll(){
        String endpoint = REST_URL + "/checkmeasures/";

        CheckMeasur[] r = restTemplate.getForObject(endpoint, CheckMeasur[].class);
        List<CheckMeasur> response = Arrays.asList(r);

        return response;
    }

    // GET // TODO in REST CONTROLLER
    public CheckMeasur checkMeasurFindById(long phycheId, long measId){
        String endpoint = REST_URL + "/checkmeasures/"+phycheId + "/" + measId;
        CheckMeasur response = restTemplate.getForObject(endpoint, CheckMeasur.class);

        return response;

    }

    // POST // TODO in REST CONTROLLER
    public boolean checkMeasurExistById(CheckMeasurPK checkMeasurePK){
        String endpoint = REST_URL + "/checkmeasures/fk";

        Boolean response = restTemplate.postForObject(endpoint, checkMeasurePK, Boolean.class);

        return response.booleanValue();

    }

    // POST // TODO in REST CONTROLLER
    public CheckMeasur saveCheckMeasur(CheckMeasur checkMeasur, long measId, long phycheId){

        String endpoint = REST_URL + "/checkmeasures/data";

        // Add query parameters to URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("measId", measId)
                .queryParam("phycheId", phycheId);
        endpoint = builder.toUriString();

        CheckMeasur cm = restTemplate.postForObject(endpoint, checkMeasur, CheckMeasur.class);

        return cm;
    }

    // PUT // TODO in REST CONTROLLER
    public void setCheckMeasur(CheckMeasur checkMeasur){
        String endpoint = REST_URL + "/checkmeasures/data";

        restTemplate.put(endpoint, checkMeasur, CheckMeasur.class);

    }

    // DELETE // TODO in REST CONTROLLER
    public void deleteCheckMeasur(CheckMeasur checkMeasur){
        String endpoint = REST_URL + "/checkmeasures/data";

        restTemplate.delete(endpoint, checkMeasur, CheckMeasur.class);

    }

}
