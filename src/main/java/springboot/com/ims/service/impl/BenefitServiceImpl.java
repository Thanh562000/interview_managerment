package springboot.com.ims.service.impl;

import springboot.com.ims.repository.common.BenefitRepository;
import springboot.com.ims.service.common.BenefitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BenefitServiceImpl implements BenefitService {

    private final BenefitRepository benefitRepository;

    @Override
    public List<String> findAllBenefit() {
        return benefitRepository.findAllBenefits();
    }
}
