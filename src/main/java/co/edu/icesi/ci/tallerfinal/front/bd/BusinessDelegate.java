package co.edu.icesi.ci.tallerfinal.front.bd;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;

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
public class BusinessDelegate {

    public static final String REST_URL = "http://localhost:8080";

    private RestTemplate restTemplate;


    public BusinessDelegate() {

        this.restTemplate = new RestTemplate();


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

    // ==========================
    // PhysicalCheckup
    // ==========================


}
