package springboot.com.ims.service.common;

import springboot.com.ims.entity.Skills;

import java.util.List;

public interface SkillService {
    List<Skills> findAll();

    Skills findByName (String skill);

    List<String> findAllByCandidateId(Integer id);

    List<String> findAllSkill();
}
