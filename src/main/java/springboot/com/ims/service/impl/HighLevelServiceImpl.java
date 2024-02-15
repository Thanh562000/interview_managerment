package springboot.com.ims.service.impl;

import springboot.com.ims.entity.common.HighLevel;
import springboot.com.ims.repository.common.HighLevelRepository;
import springboot.com.ims.service.common.HighLevelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HighLevelServiceImpl implements HighLevelService {

    private final HighLevelRepository highLevelRepository;

    @Override
    public HighLevel findByName(String name) {
        return highLevelRepository.findByName(name);
    }

    @Override
    public List<String> findAllHighLevel() {
        return highLevelRepository.findAllHighLevel();
    }
}
