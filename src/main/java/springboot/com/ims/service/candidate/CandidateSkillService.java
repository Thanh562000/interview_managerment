package springboot.com.ims.service.candidate;

import springboot.com.ims.entity.intermediateTable.CandidateSkill;

import java.util.List;

public interface CandidateSkillService {
    void saveAll(List<CandidateSkill> candidateSkillList);

    void deleteByCandidateId(Integer id);
}
