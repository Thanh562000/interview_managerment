package springboot.com.ims.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import springboot.com.ims.entity.intermediateTable.CandidateSkill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skills {
    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "skill_name")
    private String name;

    @OneToMany(mappedBy = "skill")
    @JsonIgnore
    private List<CandidateSkill> candidateSkillList = new ArrayList<>();
}
