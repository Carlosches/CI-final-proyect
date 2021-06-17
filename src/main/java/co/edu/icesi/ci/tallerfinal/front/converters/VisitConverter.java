package co.edu.icesi.ci.tallerfinal.front.converters;


import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class VisitConverter implements Converter<String, Visit> {

    @Autowired
    private BusinessDelegate bd;
    @Override
    public Visit convert(String source) {
        return bd.visitFindById(Long.parseLong(source));
    }
}
