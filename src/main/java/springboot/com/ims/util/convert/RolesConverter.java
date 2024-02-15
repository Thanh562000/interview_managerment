package springboot.com.ims.util.convert;

import springboot.com.ims.enums.Roles;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RolesConverter implements AttributeConverter<Roles, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Roles roles) {
        if (roles == null) {
            return null;
        }
        return roles.getCode();
    }

    @Override
    public Roles convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Roles.fromCode(code);
    }
}
