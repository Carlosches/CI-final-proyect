package co.edu.icesi.ci.tallerfinal.front.converters;


import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Physicalcheckup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class PhysicalcheckupConverter implements Converter<String, Physicalcheckup> {

    @Autowired
    private BusinessDelegate bd;
    @Override
    public Physicalcheckup convert(String source) {
        return bd.physicalcheckupsFindById(Long.parseLong(source));
    }
}
