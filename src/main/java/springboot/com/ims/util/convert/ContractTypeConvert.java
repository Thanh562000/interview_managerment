package springboot.com.ims.util.convert;

import springboot.com.ims.enums.ContractType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ContractTypeConvert implements AttributeConverter<ContractType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ContractType contractType) {
        return (contractType != null) ? contractType.getCode() : null;
    }

    @Override
    public ContractType convertToEntityAttribute(Integer code) {
        return (code != null) ? ContractType.fromCode(code) : null;
    }
}
