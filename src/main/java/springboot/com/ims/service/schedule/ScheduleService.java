package springboot.com.ims.service.schedule;

import springboot.com.ims.dto.schedule.ScheduleDto;
import springboot.com.ims.entity.Offer;
import springboot.com.ims.entity.Schedule;
import springboot.com.ims.entity.User;
import springboot.com.ims.enums.StatusSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    void deleteAllByJobId(Integer jobId);

    public void updateSchedule(ScheduleDto scheduleDto);

    void save(ScheduleDto scheduleDto);

    Schedule findById(Integer id);

    Page<ScheduleDto> searchScheduleList(String keyword, String interviewer, StatusSchedule statusSchedule, Pageable pageable);
    User findMainInterviewByScheduleId(Integer id);

    void deleteById(Integer id);

    void sendEmail(String recipientEmail, Offer offer, String link);
}
