package co.edu.icesi.ci.tallerfinal.front.converters;


import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Nexuspoll;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Nexusquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class NexusPollConverter implements Converter<String, Nexuspoll> {

    @Autowired
    private BusinessDelegate bd;
    @Override
    public Nexuspoll convert(String source) {
        return bd.nexusPollFindById(Long.parseLong(source));
    }
}
