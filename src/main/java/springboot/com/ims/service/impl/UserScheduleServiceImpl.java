package springboot.com.ims.service.impl;

import springboot.com.ims.entity.User;
import springboot.com.ims.repository.user.UserScheduleRepository;
import springboot.com.ims.service.schedule.UserScheduleSevice;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserScheduleServiceImpl implements UserScheduleSevice {
    private final UserScheduleRepository userScheduleRepository;
    @Override
    public List<User> getUserByScheduleId(Integer id) {
        return userScheduleRepository.findByScheduleId(id);
    }

    @Override
    public void deleteAllUserScheduleById(Integer id) {
        userScheduleRepository.deleteAllById(id);
    }
}
