package springboot.com.ims.entity.intermediateTable;

import springboot.com.ims.entity.Candidate;
import springboot.com.ims.entity.Skills;
import jakarta.persistence.*;
import lombok.*;

@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"candidate_id", "skill_id"})})
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_skill_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skills skill;
}

