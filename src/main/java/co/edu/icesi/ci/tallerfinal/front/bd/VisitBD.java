package co.edu.icesi.ci.tallerfinal.front.bd;

import co.edu.icesi.ci.tallerfinal.front.model.Visit;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class VisitBD {



    // ==========================
    // Visit
    // ==========================

    public List<Visit> visitFindAll() {

        List<Visit> visits = restTemplate
                .getForObject(fooResourceUrl + "/1", Foo.class);
        assertThat(foo.getName(), notNullValue());
        assertThat(foo.getId(), is(1L));

        return null;
    }
}
