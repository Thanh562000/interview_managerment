package springboot.com.ims.entity.intermediateTable;

import springboot.com.ims.entity.Job;
import springboot.com.ims.entity.common.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Table
@Entity
@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobLevel {
    @Id
    @Column(name = "job_level_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    @JsonBackReference
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    @JsonBackReference
    private Level level;
}

