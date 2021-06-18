package co.edu.icesi.ci.tallerfinal.front.converters;


import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Institutioncampus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class InstCampusConverter implements Converter<String, Institutioncampus> {

    @Autowired
    private BusinessDelegate bd;
    @Override
    public Institutioncampus convert(String source) {
        return bd.institutioncampusFindById(Long.parseLong(source));
    }
}
