package co.edu.icesi.ci.tallerfinal.front.bd;

import co.edu.icesi.ci.tallerfinal.front.model.classes.*;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;
import co.edu.icesi.ci.tallerfinal.front.model.wrapper.VisitList;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
public class BusinessDelegateImpl implements BusinessDelegate {

    public static final String REST_URL = "http://localhost:8080";

    private RestTemplate restTemplate;


    public BusinessDelegateImpl() {

        this.restTemplate = new RestTemplate();


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


    // ==========================
    // Institution
    // ==========================


    // ==========================
    // InstitutionCampus
    // ==========================

    public List<Institutioncampus> institutionCampusFindAll(){

        String endpoint = REST_URL + "/institution-campus/";

        Institutioncampus[] r = restTemplate.getForObject(endpoint, Institutioncampus[].class);
        List<Institutioncampus> response = Arrays.asList(r);

        return response;

    }


    // ==========================
    // Visit
    // ==========================

    //GET
    public List<Visit> visitFindAll() {
        String endpoint = REST_URL + "/visits/";

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

        String endpoint = REST_URL + "/visits";

        restTemplate.put(endpoint, visit, Visit.class);

    }

    // DELETE // TODO in REST CONTROLLER
    public void deleteVisit(long visitId){

        String endpoint = REST_URL + "/visits/" + visitId;

        restTemplate.delete(endpoint);
    }

    // ==========================
    // PhysicalCheckup
    // ==========================

    // GET // TODO in REST CONTROLLER
    public List<Physicalcheckup> physicalcheckupsFindAll(){

        String endpoint = REST_URL + "/physical-checkup";

        Physicalcheckup[] r = restTemplate.getForObject(endpoint, Physicalcheckup[].class);
        List<Physicalcheckup> response = Arrays.asList(r);

        return response;

    }

    // GET // TODO in REST CONTROLLER
    public Physicalcheckup physicalcheckupsFindById(long id){

        String endpoint = REST_URL + "/physical-checkup/" + id;

        Physicalcheckup response = restTemplate.getForObject(endpoint, Physicalcheckup.class);

        return response;
    }

    // POST // TODO in REST CONTROLLER
    public Physicalcheckup savePhysicalcheckup(Physicalcheckup pc, long persId, long visitId){

        // REST endpoint
        String endpoint = REST_URL + "/physical-checkup";

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

        String endpoint = REST_URL + "/physical-checkup";

        restTemplate.put(endpoint, pc, Physicalcheckup.class);

    }

    // DELETE // TODO in REST CONTROLLER
    public void deletePhysicalcheckup(long phycheId){

        String endpoint = REST_URL + "/physical-checkup/" + phycheId;

        restTemplate.delete(endpoint);

    }



}
