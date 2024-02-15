package springboot.com.ims.util.convert;

import springboot.com.ims.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getCode();
    }

    @Override
    public Status convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Status.fromCode(code);
    }
}