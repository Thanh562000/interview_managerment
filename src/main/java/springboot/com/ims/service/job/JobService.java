package springboot.com.ims.service.job;

import springboot.com.ims.dto.job.JobDto;
import springboot.com.ims.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Job findByTitle(String title);

    List<Job> getAll();

    List<String> getAllTitle();

    List<String> getAllTitleByStatus(String status) ;

    void create(Job job);

    Optional<Job> findById(Integer id);

    void update(Integer id);

    void delete(Integer id);

    Page<JobDto> searchJobList(String keyword, String status, Pageable pageable);

    List<JobDto> showAll();

    void save(JobDto jobDto, UserDetails userDetails);

    void updateJobById(Integer id, JobDto jobDto);

    void updateJobStatus();

    JobDto findJobDtoById(Integer id);
}
