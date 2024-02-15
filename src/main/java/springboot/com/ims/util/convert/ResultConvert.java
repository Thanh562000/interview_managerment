package springboot.com.ims.util.convert;

import springboot.com.ims.enums.Result;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ResultConvert implements AttributeConverter<Result, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Result result) {
        return (result != null) ? result.getCode() : null;
    }

    @Override
    public Result convertToEntityAttribute(Integer code) {
        return (code != null) ? Result.fromCode(code) : null;
    }
}
