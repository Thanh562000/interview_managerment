package springboot.com.ims.util.convert;

import springboot.com.ims.enums.Department;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DepartmentConverter implements AttributeConverter<Department, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Department department) {
        return (department != null) ? department.getCode() : null;
    }

    @Override
    public Department convertToEntityAttribute(Integer code) {
        return (code != null) ? Department.fromCode(code) : null;
    }
}