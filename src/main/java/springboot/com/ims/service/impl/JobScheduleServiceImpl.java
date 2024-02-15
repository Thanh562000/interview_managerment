package springboot.com.ims.service.impl;

import springboot.com.ims.repository.schedule.JobScheduleRepository;
import springboot.com.ims.service.schedule.JobScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JobScheduleServiceImpl implements JobScheduleService {
    private final JobScheduleRepository jobScheduleRepository;
    @Override
    public void deleteAllJobScheduleServiceById(Integer id) {
        jobScheduleRepository.deleteAllById(id);
    }
}
