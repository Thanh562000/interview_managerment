package springboot.com.ims.service.schedule;

import springboot.com.ims.entity.User;

import java.util.List;

public interface UserScheduleSevice {
    List<User> getUserByScheduleId (Integer id);
    void deleteAllUserScheduleById(Integer id);
}
