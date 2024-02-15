package springboot.com.ims.service.impl;

import springboot.com.ims.entity.common.Level;
import springboot.com.ims.repository.common.LevelRepository;
import springboot.com.ims.service.common.LevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Override
    public List<String> findAllLevels() {
        return levelRepository.findAllLevels();
    }
    @Override
    public Level findLevelByName(String name) {
        return levelRepository.findByName(name);
    }
}
