package springboot.com.ims.repository.schedule;

import springboot.com.ims.entity.Schedule;
import springboot.com.ims.entity.User;
import springboot.com.ims.enums.StatusSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(value = "select s FROM Schedule s WHERE s.id = :id")
    Schedule findByScheduleId(@Param("id") Integer id);

    @Modifying
    @Transactional
    void deleteByJobId(Integer jobId);

    @Query("""
            SELECT s FROM Schedule s 
                    left JOIN User u ON u.userId = s.mainInterviewer.userId 
                    WHERE (:keyword IS NULL 
                            OR LOWER(s.title) LIKE LOWER(CONCAT('%', :keyword, '%')))
                        AND (:interviewer IS NULL OR u.userName = :interviewer)
                        AND (:status IS NULL OR s.statusSchedule = :status)
                    ORDER BY 
                        CASE s.statusSchedule
                            WHEN springboot.com.ims.enums.StatusSchedule.NEW THEN 0 
                            WHEN springboot.com.ims.enums.StatusSchedule.INVITED THEN 1 
                            WHEN springboot.com.ims.enums.StatusSchedule.INTERVIEWED THEN 2
                            WHEN springboot.com.ims.enums.StatusSchedule.CANCELLED THEN 3 
                        END ASC ,
                        s.scheduleDate DESC 
            """
    )
    Page<Schedule> searchScheduleList (String keyword, String interviewer, StatusSchedule status, Pageable pageable);

    @Query(value = "select sch.mainInterviewer from Schedule sch where sch.id = :scheduleId ")
    User findMainInterviewByScheduleId (@Param("scheduleId") Integer id);

}
