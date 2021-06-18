package co.edu.icesi.ci.tallerfinal.front.converters;


import co.edu.icesi.ci.tallerfinal.front.bd.BusinessDelegate;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Measurement;
import co.edu.icesi.ci.tallerfinal.front.model.classes.Physicalcheckup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@WritingConverter
@Component
public class MeasurementConverter implements Converter<String, Measurement> {

    @Autowired
    private BusinessDelegate bd;
    @Override
    public Measurement convert(String source) {
        return bd.measurementFindById(Long.parseLong(source));
    }
}
