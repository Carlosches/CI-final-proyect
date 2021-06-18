package co.edu.icesi.ci.tallerfinal.front.test;

import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegateImpl;
import co.edu.icesi.ci.tallerfinal.front.model.classes.*;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = {PersistenceContext.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DelegateTest {
    @Mock
    private static RestTemplate restTemplate;
    @InjectMocks
    private static BusinessDelegate businessDelegate;

    public static final String REST_URL = "http://localhost:8080/api";
    @BeforeAll
    public static void setup(){
        businessDelegate = new BusinessDelegateImpl();
        businessDelegate.setRestTemplate(restTemplate);
    }

        @Test
        public void personFindAllTest(){
            Person pers1 = new Person();
            Person pers2 = new Person();

            Person[] personList = {pers1,pers2};
            when(restTemplate.getForObject(REST_URL+"/persons/",Person[].class))
                    .thenReturn(personList);

            List<Person> listResponse =businessDelegate.personFindAll();
            assertNotNull(listResponse);
            assertEquals(pers1.getPersId(), listResponse.get(0).getPersId());
            assertEquals(pers2.getPersId(), listResponse.get(1).getPersId());
            verify(restTemplate).getForObject(REST_URL+"/persons/",Person[].class);
        }
        @Test
        public void personFindByIdTest(){
            Person pers1 = new Person();
            when(restTemplate.getForObject(REST_URL+"/persons/"+pers1.getPersId(),Person.class))
                    .thenReturn(pers1);
            Person person = businessDelegate.personFindById(pers1.getPersId());
            assertNotNull(person);
            assertEquals(pers1.getPersId(), person.getPersId());
            verify(restTemplate).getForObject(REST_URL+"/persons/"+pers1.getPersId(),Person.class);
        }

        @Test
        public void institutionFindAllTest(){
            Institution inst1 = new Institution();
            Institution inst2 = new Institution();

            Institution[] instList = {inst1,inst2};
            when(restTemplate.getForObject(REST_URL+"/institutions/",Institution[].class))
                    .thenReturn(instList);

            List<Institution> listResponse =businessDelegate.institutionFindAll();
            assertNotNull(listResponse);
            assertEquals(inst1.getInstId(), listResponse.get(0).getInstId());
            assertEquals(inst2.getInstId(), listResponse.get(1).getInstId());
            verify(restTemplate).getForObject(REST_URL+"/institutions/",Institution[].class);
        }
        @Test
        public void institutioncampusFindByIdTest(){
            Institutioncampus inst1 = new Institutioncampus();
            when(restTemplate.getForObject(REST_URL+"/institutioncampus/"+inst1.getInstcamId(),Institutioncampus.class))
                    .thenReturn(inst1);
            Institutioncampus inst = businessDelegate.institutioncampusFindById(inst1.getInstcamId());
            assertNotNull(inst);
            assertEquals(inst1.getInstcamId(), inst.getInstcamId());
            verify(restTemplate).getForObject(REST_URL+"/institutioncampus/"+inst1.getInstcamId(),Institutioncampus.class);
        }
        @Test
        public void institutionCampusFindAll(){
            Institutioncampus inst1 = new Institutioncampus();
            Institutioncampus inst2 = new Institutioncampus();

            Institutioncampus[] instList = {inst1,inst2};
            when(restTemplate.getForObject(REST_URL+"/institutioncampus/",Institutioncampus[].class))
                    .thenReturn(instList);

            List<Institutioncampus> listResponse =businessDelegate.institutionCampusFindAll();
            assertNotNull(listResponse);
            assertEquals(inst1.getInstcamId(), listResponse.get(0).getInstcamId());
            assertEquals(inst2.getInstcamId(), listResponse.get(1).getInstcamId());
            verify(restTemplate).getForObject(REST_URL+"/institutioncampus/",Institutioncampus[].class);
        }

        @Test
        public void visitFindAllTest(){
            Visit visist1 = new Visit();
            Visit visist2 = new Visit();

            Visit[] visitList = {visist1,visist2};
            when(restTemplate.getForObject(REST_URL+"/visits/",Visit[].class))
                    .thenReturn(visitList);

            List<Visit> listResponse =businessDelegate.visitFindAll();
            assertNotNull(listResponse);
            assertEquals(visist1.getVisitId(), listResponse.get(0).getVisitId());
            assertEquals(visist2.getVisitId(), listResponse.get(1).getVisitId());
            verify(restTemplate).getForObject(REST_URL+"/visits/",Visit[].class);
        }

        @Test
        public void visitFindByIdTest(){
            Visit visit1 = new Visit();
            when(restTemplate.getForObject(REST_URL+"/visits/"+visit1.getVisitId(),Visit.class))
                    .thenReturn(visit1);
            Visit visit = businessDelegate.visitFindById(visit1.getVisitId());
            assertNotNull(visit);
            assertEquals(visit1.getVisitId(), visit.getVisitId());
            verify(restTemplate).getForObject(REST_URL+"/visits/"+visit1.getVisitId(),Visit.class);
        }
        @Test
        public void saveVisitTest(){
            Visit visit1 = new Visit();
            // REST endpoint
            String endpoint = REST_URL + "/visits/";
            Person person = new Person();
            Institutioncampus inst = new Institutioncampus();
            visit1.setPerson(person);
            visit1.setInstitutioncampus(inst);
            // Add query parameters to URL
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                    .queryParam("personId", person.getPersId())
                    .queryParam("campusId", inst.getInstcamId());
            endpoint = builder.toUriString();


            // Add Entity
            HttpEntity<Visit> request = new HttpEntity<>(visit1);
            when(restTemplate.postForObject(endpoint, request, Visit.class))
                    .thenReturn(visit1);

            Visit visitResponse = businessDelegate.saveVisit(visit1,person.getPersId(), inst.getInstcamId());
            assertNotNull(visitResponse);
            assertEquals(visit1.getVisitId(), visitResponse.getVisitId());
            assertEquals(visit1.getPerson().getPersId(), visitResponse.getPerson().getPersId());
            assertEquals(visit1.getInstitutioncampus().getInstcamId(), visitResponse.getInstitutioncampus().getInstcamId());
            verify(restTemplate).postForObject(endpoint, request, Visit.class);

        }
        @Test
        public void setVisitTest(){
            Visit visit1 = new Visit();
            visit1.setVisitDetail("Detail 1");
            String endpoint = REST_URL + "/visits/" + visit1.getVisitId();

            when(restTemplate.getForObject(endpoint, Visit.class)).thenReturn(visit1);

            Visit visitToUpdate = businessDelegate.visitFindById(visit1.getVisitId());
            visitToUpdate.setVisitDetail("Detail 2");
            businessDelegate.setVisit(visitToUpdate);
            verify(restTemplate).getForObject(endpoint, Visit.class);
            verify(restTemplate).put(REST_URL + "/visits/", visitToUpdate, Visit.class);
            assertNotNull(visitToUpdate);
        }
        @Test
        public void measurementFindAllTest(){
            Measurement meas1 = new Measurement();
            Measurement meas2 = new Measurement();

            Measurement[] measList = {meas1,meas2};
            when(restTemplate.getForObject(REST_URL+"/measurements/",Measurement[].class))
                    .thenReturn(measList);

            List<Measurement> listResponse =businessDelegate.measurementFindAll();
            assertNotNull(listResponse);
            assertEquals(meas1.getMeasId(), listResponse.get(0).getMeasId());
            assertEquals(meas2.getMeasId(), listResponse.get(1).getMeasId());
            verify(restTemplate).getForObject(REST_URL+"/measurements/",Measurement[].class);
        }
        @Test
        public void measurementFindByIdTest(){
            Measurement meas1 = new Measurement();
            when(restTemplate.getForObject(REST_URL+"/measurements/"+meas1.getMeasId(),Measurement.class))
                    .thenReturn(meas1);
            Measurement measResponse = businessDelegate.measurementFindById(meas1.getMeasId());
            assertNotNull(measResponse);
            assertEquals(meas1.getMeasId(), measResponse.getMeasId());
            verify(restTemplate).getForObject(REST_URL+"/measurements/"+meas1.getMeasId(),Measurement.class);
        }
        @Test
        public void saveMeasurementTest(){
            Measurement meas1 = new Measurement();
            // REST endpoint
            String endpoint = REST_URL + "/measurements/";
            Institution inst = new Institution();
            meas1.setInstitution(inst);
            // Add query parameters to URL
            // Add query parameters to URL
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                    .queryParam("instId", inst.getInstId());
            endpoint = builder.toUriString();

            when(restTemplate.postForObject(endpoint, meas1, Measurement.class))
                    .thenReturn(meas1);

            Measurement measResponse = businessDelegate.saveMeasurement(meas1, inst.getInstId());
            assertNotNull(measResponse);
            assertEquals(meas1.getMeasId(), measResponse.getMeasId());
            assertEquals(meas1.getInstitution().getInstId(), measResponse.getInstitution().getInstId());
            verify(restTemplate).postForObject(endpoint, meas1, Measurement.class);

        }
        @Test
        public void setMeasurement(){
            Measurement meas1 = new Measurement();
            meas1.setMeasName("meas 1");
            String endpoint = REST_URL+"/measurements/"+meas1.getMeasId();

            when(restTemplate.getForObject(endpoint,Measurement.class)).thenReturn(meas1);

            Measurement measToUpdate = businessDelegate.measurementFindById(meas1.getMeasId());
            measToUpdate.setMeasName("measAAA");
            businessDelegate.setMeasurement(measToUpdate);
            verify(restTemplate).getForObject(endpoint, Measurement.class);
            verify(restTemplate).put(REST_URL + "/measurements/", measToUpdate, Measurement.class);
            assertNotNull(measToUpdate);
        }

        @Test
        public void checkMeasurFindAllTest(){
            Measurement measurement = new Measurement();
            Measurement measurement2 = new Measurement();
            Physicalcheckup pyche = new Physicalcheckup();
            Physicalcheckup pyche2 = new Physicalcheckup();

            CheckMeasur measur1 = new CheckMeasur();
            measur1.setMeasurement(measurement);
            measur1.setPhysicalcheckup(pyche);
            CheckMeasur measur2 = new CheckMeasur();
            measur2.setMeasurement(measurement2);
            measur2.setPhysicalcheckup(pyche2);

            CheckMeasur[] measurList = {measur1,measur2};
            when(restTemplate.getForObject(REST_URL+"/checkmeasures/",CheckMeasur[].class))
                    .thenReturn(measurList);

            List<CheckMeasur> listResponse =businessDelegate.checkMeasurFindAll();
            assertNotNull(listResponse);
            assertEquals(measur1.getMeasurement().getMeasId(), listResponse.get(0).getMeasurement().getMeasId());
            assertEquals(measur2.getPhysicalcheckup().getPhycheId(), listResponse.get(0).getPhysicalcheckup().getPhycheId());
            verify(restTemplate).getForObject(REST_URL+"/checkmeasures/",CheckMeasur[].class);
        }
        @Test
        public void checkMeasurFindByIdTest(){
            Measurement measurement = new Measurement();
            Physicalcheckup pyche = new Physicalcheckup();
            CheckMeasur measur = new CheckMeasur();
            CheckMeasurPK measurPK = new CheckMeasurPK();
            measurPK.setMeasMeasId(measurement.getMeasId());
            measurPK.setPhychePhycheId(pyche.getPhycheId());
            measur.setId(measurPK);
            when(restTemplate.getForObject(REST_URL+"/checkmeasures/"+pyche.getPhycheId()+"/"+measurement.getMeasId(),CheckMeasur.class))
                    .thenReturn(measur);
            CheckMeasur measResponse = businessDelegate.checkMeasurFindById(measur.getId().getMeasMeasId(), measur.getId().getPhychePhycheId());
            assertNotNull(measResponse);
            assertEquals(measResponse.getId().getMeasMeasId(), measur.getId().getMeasMeasId());
            assertEquals(measResponse.getId().getPhychePhycheId(), measur.getId().getPhychePhycheId());

            verify(restTemplate).getForObject(REST_URL+"/checkmeasures/"+pyche.getPhycheId()+"/"+measurement.getMeasId(),CheckMeasur.class);
        }
        @Test
        public void saveCheckmeasurTest(){
            CheckMeasur measur = new CheckMeasur();
            // REST endpoint
            String endpoint = REST_URL + "/checkmeasures/";
            Measurement meas = new Measurement();
            Physicalcheckup pyche = new Physicalcheckup();
            measur.setMeasurement(meas);
            measur.setPhysicalcheckup(pyche);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                    .queryParam("measId", meas.getMeasId())
                    .queryParam("phycheId", pyche.getPhycheId());
            endpoint = builder.toUriString();


            when(restTemplate.postForObject(endpoint, measur, CheckMeasur.class))
                    .thenReturn(measur);

            CheckMeasur measResponse = businessDelegate.saveCheckMeasur(measur, meas.getMeasId(), pyche.getPhycheId());
            assertNotNull(measResponse);
            assertEquals(measur.getMeasurement().getMeasId(), measResponse.getMeasurement().getMeasId());
            assertEquals(measur.getPhysicalcheckup().getPhycheId(), measResponse.getPhysicalcheckup().getPhycheId());
            verify(restTemplate).postForObject(endpoint, measur, CheckMeasur.class);

        }
        @Test
        public void setCheckMeasurTest(){
            Measurement measurement = new Measurement();
            Physicalcheckup pyche = new Physicalcheckup();
            CheckMeasur measur = new CheckMeasur();
            CheckMeasurPK measurPK = new CheckMeasurPK();
            measurPK.setMeasMeasId(measurement.getMeasId());
            measurPK.setPhychePhycheId(pyche.getPhycheId());
            measur.setId(measurPK);

            String endpoint = REST_URL+"/checkmeasures/"+measurement.getMeasId()+"/"+pyche.getPhycheId();

            when(restTemplate.getForObject(endpoint,CheckMeasur.class)).thenReturn(measur);

            CheckMeasur measurToUpdate = businessDelegate.checkMeasurFindById(measur.getId().getMeasMeasId(), measur.getId().getPhychePhycheId());
            businessDelegate.setCheckMeasur(measurToUpdate);
            verify(restTemplate).getForObject(endpoint,CheckMeasur.class);
            verify(restTemplate).put(REST_URL + "/checkmeasures/", measur, CheckMeasur.class);
            assertNotNull(measurToUpdate);
        }
        @Test
        public void physicalcheckupsFindAllTest(){
            Physicalcheckup phyche1 = new Physicalcheckup();
            Physicalcheckup phyche2 = new Physicalcheckup();

            Physicalcheckup[] phycheList = {phyche1,phyche2};
            when(restTemplate.getForObject(REST_URL+"/phycheckups/",Physicalcheckup[].class))
                    .thenReturn(phycheList);

            List<Physicalcheckup> listResponse =businessDelegate.physicalcheckupsFindAll();
            assertNotNull(listResponse);
            assertEquals(phyche1.getPhycheId(), listResponse.get(0).getPhycheId());
            assertEquals(phyche1.getPhycheId(), listResponse.get(1).getPhycheId());
            verify(restTemplate).getForObject(REST_URL+"/phycheckups/",Physicalcheckup[].class);
        }
        @Test
        public void physicalcheckupsFindByIdTest(){
            Physicalcheckup pyche1 = new Physicalcheckup();
            when(restTemplate.getForObject(REST_URL+"/phycheckups/"+pyche1.getPhycheId(),Physicalcheckup.class))
                    .thenReturn(pyche1);
            Physicalcheckup pyche = businessDelegate.physicalcheckupsFindById(pyche1.getPhycheId());
            assertNotNull(pyche);
            assertEquals(pyche1.getPhycheId(), pyche.getPhycheId());
            verify(restTemplate).getForObject(REST_URL+"/phycheckups/"+pyche1.getPhycheId(),Physicalcheckup.class);
        }
        @Test
        public void savePhysicalcheckupTest(){
            Physicalcheckup pyche1 = new Physicalcheckup();
            // REST endpoint
            String endpoint = REST_URL + "/phycheckups/";
            Person person = new Person();
            Visit visit = new Visit();
            pyche1.setPerson(person);
            pyche1.setVisit(visit);
            // Add query parameters to URL
            // Add query parameters to URL
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endpoint)
                    .queryParam("persId", person.getPersId())
                    .queryParam("visitId", visit.getVisitId());
            endpoint = builder.toUriString();


            // Add Entity
            HttpEntity<Physicalcheckup> request = new HttpEntity<>(pyche1);

            when(restTemplate.postForObject(endpoint, request, Physicalcheckup.class))
                    .thenReturn(pyche1);

            Physicalcheckup pycheResponse = businessDelegate.savePhysicalcheckup(pyche1, person.getPersId(), visit.getVisitId());
            assertNotNull(pycheResponse);
            assertEquals(pyche1.getPhycheId(), pycheResponse.getPhycheId());
            assertEquals(pyche1.getPerson().getPersId(), pycheResponse.getPerson().getPersId());
            assertEquals(pyche1.getVisit().getVisitId(), pycheResponse.getVisit().getVisitId());
            verify(restTemplate).postForObject(endpoint, request, Physicalcheckup.class);

        }
        @Test
        public void setPhysicalcheckup(){
            Physicalcheckup pyche1 = new Physicalcheckup();
            pyche1.setPhycheDate(new Date());
            String endpoint = REST_URL+"/phycheckups/"+pyche1.getPhycheId();

            when(restTemplate.getForObject(endpoint,Physicalcheckup.class)).thenReturn(pyche1);

            Physicalcheckup pycheToUpdate = businessDelegate.physicalcheckupsFindById(pyche1.getPhycheId());
            pycheToUpdate.setPhycheDate(new Date());
            businessDelegate.setPhysicalcheckup(pycheToUpdate);
            verify(restTemplate).getForObject(endpoint, Physicalcheckup.class);
            verify(restTemplate).put(REST_URL + "/phycheckups/", pycheToUpdate, Physicalcheckup.class);
            assertNotNull(pycheToUpdate);
        }
        @Test
        public void nexusPollFindallTest(){
            Nexuspoll poll1 = new Nexuspoll();
            Nexuspoll poll2 = new Nexuspoll();

            Nexuspoll[] nexusList = {poll1,poll2};
            when(restTemplate.getForObject(REST_URL+"/nexus-poll/",Nexuspoll[].class))
                    .thenReturn(nexusList);

            List<Nexuspoll> listResponse =businessDelegate.nexusPollFindAll();
            assertNotNull(listResponse);
            assertEquals(poll1.getNexpollId(), listResponse.get(0).getNexpollId());
            assertEquals(poll2.getNexpollId(), listResponse.get(1).getNexpollId());
            verify(restTemplate).getForObject(REST_URL+"/nexus-poll/",Nexuspoll[].class);
        }
        @Test
        public void nexusPollFindById(){
            Nexuspoll poll1 = new Nexuspoll();
            when(restTemplate.getForObject(REST_URL+"/nexus-poll/"+poll1.getNexpollId(),Nexuspoll.class))
                    .thenReturn(poll1);
            Nexuspoll poll = businessDelegate.nexusPollFindById(poll1.getNexpollId());
            assertNotNull(poll);
            assertEquals(poll1.getNexpollId(), poll.getNexpollId());
            verify(restTemplate).getForObject(REST_URL+"/nexus-poll/"+poll1.getNexpollId(),Nexuspoll.class);
        }
        @Test
        public void saveNexPollTest(){
            Nexuspoll poll1 = new Nexuspoll();
            // REST endpoint
            String endpoint = REST_URL + "/nexus-poll/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Nexuspoll> request = new HttpEntity<>(poll1, headers);


            when(restTemplate.postForObject(endpoint, request, Nexuspoll.class))
                    .thenReturn(poll1);

            Nexuspoll poll = businessDelegate.addNexusPoll(poll1);
            assertNotNull(poll);
            assertEquals(poll1.getNexpollId(), poll.getNexpollId());
            verify(restTemplate).postForObject(endpoint, request, Nexuspoll.class);

        }
        @Test
        public void updateNexusPollTest(){
            Nexuspoll nex = new Nexuspoll();
            nex.setNexpollName("poll1");
            String endpoint = REST_URL+"/nexus-poll/"+nex.getNexpollId();

            when(restTemplate.getForObject(endpoint, Nexuspoll.class)).thenReturn(nex);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            Nexuspoll pollToUpdate = businessDelegate.nexusPollFindById(nex.getNexpollId());
            HttpEntity<Nexuspoll> request = new HttpEntity<>(pollToUpdate, headers);
            pollToUpdate.setNexpollName("name2");
            businessDelegate.updateNexusPoll(nex);
            verify(restTemplate).getForObject(endpoint, Nexuspoll.class);
            verify(restTemplate).put(REST_URL + "/nexus-poll/", request, Nexuspoll.class);
        }

        @Test
        public void nexusQuestFindallTest(){
            Nexusquestion quest1 = new Nexusquestion();
            Nexusquestion quest2= new Nexusquestion();

            Nexusquestion[] nexList = {quest1,quest2};
            when(restTemplate.getForObject(REST_URL+"/nexus-quest/",Nexusquestion[].class))
                    .thenReturn(nexList);

            List<Nexusquestion> listResponse =businessDelegate.nexusQuestionsFindAll();
            assertNotNull(listResponse);
            assertEquals(quest1.getNexquesId(), listResponse.get(0).getNexquesId());
            assertEquals(quest2.getNexquesId(), listResponse.get(1).getNexquesId());
            verify(restTemplate).getForObject(REST_URL+"/nexus-quest/",Nexusquestion[].class);
        }
        @Test
        public void nexusQuestFindById(){
            Nexusquestion quest = new Nexusquestion();
            when(restTemplate.getForObject(REST_URL+"/nexus-quest/"+quest.getNexquesId(),Nexusquestion.class))
                    .thenReturn(quest);
            Nexusquestion questResponse = businessDelegate.nexusPollQuestionFindById(quest.getNexquesId());
            assertNotNull(questResponse);
            assertEquals(quest.getNexquesId(), questResponse.getNexquesId());
            verify(restTemplate).getForObject(REST_URL+"/nexus-quest/"+quest.getNexquesId(),Nexusquestion.class);
        }
        @Test
        public void saveNexusQuestTest(){
            Nexusquestion nex = new Nexusquestion();
            // REST endpoint
            String endpoint = REST_URL + "/nexus-quest/";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Nexusquestion> request = new HttpEntity<>(nex, headers);

            when(restTemplate.postForObject(endpoint, request, Nexusquestion.class))
                    .thenReturn(nex);

            Nexusquestion quest = businessDelegate.addNexusPollQuestion(nex);
            assertNotNull(nex);
            assertEquals(nex.getNexquesId(), quest.getNexquesId());
            verify(restTemplate).postForObject(endpoint, request, Nexusquestion.class);

        }
        @Test
        public void updateNexusquestTest(){
            Nexusquestion nex = new Nexusquestion();
            nex.setNexquesName("quest1");
            nex.setNexquesId(1);
            String endpoint = REST_URL + "/nexus-quest/"+nex.getNexquesId();
            when(restTemplate.getForObject(endpoint, Nexusquestion.class)).thenReturn(nex);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Nexusquestion> request = new HttpEntity<>(nex, headers);

            Nexusquestion quesToUpdate = businessDelegate.nexusPollQuestionFindById(nex.getNexquesId());
            businessDelegate.updateNexusPollQuestion(nex);
            verify(restTemplate).getForObject(endpoint, Nexusquestion.class);
            verify(restTemplate).put(REST_URL + "/nexus-quest/", request, Nexusquestion.class);
        }

}
