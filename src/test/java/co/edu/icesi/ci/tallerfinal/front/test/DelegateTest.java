package co.edu.icesi.ci.tallerfinal.front.test;

import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DelegateTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private BusinessDelegate businessDelegate;

    public static final String REST_URL = "http://localhost:8080/api";

    @Nested
    @DisplayName("Person and institution test")
    class PersonAndInsitution{

        @Test
        public void personFindAllTest(){
            /*List<Person> personList = new ArrayList<>();
            when(restTemplate.getForObject(REST_URL+"/persons/",Person[].class))
                    .thenReturn(new ResponseEntity(personList, HttpStatus.OK));*/
        }
    }
}
