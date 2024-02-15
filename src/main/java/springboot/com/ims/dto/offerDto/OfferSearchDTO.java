package springboot.com.ims.dto.offerDto;

import springboot.com.ims.enums.Department;
import springboot.com.ims.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferSearchDTO {
    private Integer offerDtoId;
    private String candidateName;
    private String emailCandidate;
    private String approver;
    private String department;
    private String note;
    private String status;
    public OfferSearchDTO(Integer offerDtoId, String candidateName, String emailCandidate, String user,
                          Department department, String note, Status status) {
        this.offerDtoId = offerDtoId;
        this.candidateName = candidateName;
        this.emailCandidate = emailCandidate;
        this.approver = user;
        this.department = department.name();
        this.note = note;
        this.status = status.getName();
    }
}
