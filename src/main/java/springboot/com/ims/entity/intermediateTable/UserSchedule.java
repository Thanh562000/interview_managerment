package springboot.com.ims.entity.intermediateTable;

import springboot.com.ims.entity.Schedule;
import springboot.com.ims.entity.User;
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
public class UserSchedule {

    @Id
    @Column(name = "user_schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}