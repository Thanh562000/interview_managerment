package springboot.com.ims.dto.user;

import springboot.com.ims.enums.Department;
import springboot.com.ims.enums.Gender;
import springboot.com.ims.enums.UserStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ListUserDto {
    private Integer userId;
    private String phoneNumber;
    private String status;
    private String email;
    private String userName;
    private String role;
    private String fullName;
    private LocalDate dateOfBirth;
    private String address;
    private String gender;
    private String department;
    private String note;

    public ListUserDto(Integer userId, String phoneNumber, UserStatus status, String email, String userName, String role, String fullName, LocalDate dateOfBirth, String address, Gender gender, Department department, String note) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.status = status.getName();
        this.email = email;
        this.userName = userName;
        this.role = role;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.gender = gender.getName();
        this.department = department.getName();
        this.note = note;
    }

    public ListUserDto(Integer userId, String userName, String email, String phoneNumber, UserStatus status, String role) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.status = status.getName();
        this.email = email;
        this.role = role;
        this.userName = userName;
    }
}
