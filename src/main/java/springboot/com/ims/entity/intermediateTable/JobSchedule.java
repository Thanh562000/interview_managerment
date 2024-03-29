package springboot.com.ims.entity.intermediateTable;

import springboot.com.ims.entity.Job;
import springboot.com.ims.entity.Schedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSchedule {

    @Id
    @Column(name = "job_schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
