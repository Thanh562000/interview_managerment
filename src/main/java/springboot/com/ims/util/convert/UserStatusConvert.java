package springboot.com.ims.util.convert;

import springboot.com.ims.enums.UserStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserStatusConvert implements AttributeConverter<UserStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserStatus status) {
        return (status != null) ? status.getCode() : null;
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer code) {
        return (code != null) ? UserStatus.fromCode(code) : null;
    }
}
