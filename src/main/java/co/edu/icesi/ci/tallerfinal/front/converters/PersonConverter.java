package co.edu.icesi.ci.tallerfinal.front.converters;


import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class PersonConverter implements Converter<String, Person> {

    @Autowired
    private BusinessDelegate bd;
    @Override
    public Person convert(String source) {
        return bd.personFindById(Long.parseLong(source));
    }
}
