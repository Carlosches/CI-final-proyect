package co.edu.icesi.ci.tallerfinal.front.converters;


import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Institution;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Nexusquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class InstitutionConverter implements Converter<String, Institution> {

    @Autowired
    private BusinessDelegate bd;
    @Override
    public Institution convert(String source) {
        return bd.institutionFindById(Long.parseLong(source));
    }
}
