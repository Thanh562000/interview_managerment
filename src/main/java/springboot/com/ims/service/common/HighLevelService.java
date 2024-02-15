package springboot.com.ims.service.common;

import springboot.com.ims.entity.common.HighLevel;

import java.util.List;

public interface HighLevelService {

    HighLevel findByName(String name);

    List<String> findAllHighLevel();
}
