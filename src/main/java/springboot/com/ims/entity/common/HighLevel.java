package springboot.com.ims.entity.common;

import springboot.com.ims.entity.Candidate;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HighLevel {
    @Id
    @Column(name = "high_level_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "high_level_name")
    private String name;

    @OneToMany(mappedBy = "highLevel")
    private List<Candidate> candidateList = new ArrayList<>();

    @Override
    public String toString() {
        return "HighLevel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
