package springboot.com.ims.entity.intermediateTable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import springboot.com.ims.entity.Job;
import springboot.com.ims.entity.Skills;
import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobSkill {
    @Id
    @Column(name = "job_skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    @JsonBackReference
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    @JsonBackReference
    private Skills skills;
}

