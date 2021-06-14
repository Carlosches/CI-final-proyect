package co.edu.icesi.ci.tallerfinal.front.bd;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;

import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;
import co.edu.icesi.ci.tallerfinal.front.model.wrapper.VisitList;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class BusinessDelegate {

    public static final String REST_URL = "http://localhost:8080/";

    private RestTemplate restTemplate;


    public BusinessDelegate() {

        this.restTemplate = new RestTemplate();


    }

    // ==========================
    // Visit
    // ==========================

    //GET
    public List<Visit> visitFindAll() {
        VisitList response = restTemplate.getForObject(REST_URL, VisitList.class);
        return response.getVisits();
    }

    //POST
    public void saveVisit(Visit visit, long personId, long campusId){
        // HttpEntity<Visit> request = new HttpEntity<>()
    }

    //PUT
    public void setVisit(){

    }

}
