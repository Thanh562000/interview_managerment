package springboot.com.ims.service.common;

import springboot.com.ims.entity.common.Level;

import java.util.List;

public interface LevelService {

    List<String> findAllLevels();
    Level findLevelByName (String name);
}
