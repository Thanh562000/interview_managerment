package springboot.com.ims.service.impl;

import springboot.com.ims.dto.candidate.CandidateDto;
import springboot.com.ims.entity.Candidate;
import springboot.com.ims.entity.intermediateTable.CandidateSkill;
import springboot.com.ims.entity.Skills;
import springboot.com.ims.enums.Gender;
import springboot.com.ims.enums.Position;
import springboot.com.ims.enums.Status;
import springboot.com.ims.repository.candidate.CandidateRepository;
import springboot.com.ims.repository.candidate.CandidateSkillRepository;
import springboot.com.ims.repository.common.HighLevelRepository;
import springboot.com.ims.repository.common.SkillRepository;
import springboot.com.ims.repository.user.UserRepository;
import springboot.com.ims.service.candidate.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    private final UserRepository userRepository;

    private final HighLevelRepository highLevelRepository;

    private final CandidateSkillRepository candidateSkillRepository;

    private final SkillRepository skillRepository;
    @Override
    public Page<CandidateDto> searchCandidateList(String keyword, String status, Pageable pageable) {
        Status enumStatus = (status != null) ? Status.valueOf(status) : null;
        String search = (keyword != null) ? keyword.toLowerCase() : null;
        return candidateRepository.getSearchList(search, enumStatus, pageable);
    }

    @Override
    public Optional<Candidate> findById(Integer id) {
        return candidateRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateCandidate(CandidateDto candidateDto, UserDetails userDetails) {
        Optional<Candidate> oCandidate = candidateRepository.findById(candidateDto.getCandidateId());
        if (oCandidate.isPresent()) {
            Candidate candidate = oCandidate.get();

            BeanUtils.copyProperties(candidateDto, candidate);

    //        candidate.setUser(userRepository.findByFullNameAndRole(candidateDto.getUser()));
            candidate.setHighLevel(highLevelRepository.findByName(candidateDto.getHighLevel()));
            candidate.setGender(Gender.fromValue(candidateDto.getGender()));
            candidate.setPosition(Position.fromValue(candidateDto.getPosition()));
            candidate.setStatus(Status.fromValue(candidateDto.getStatus()));
            candidate.setLastModifiedBy(userDetails.getUsername());
            candidate.setLastModifiedDate(LocalDateTime.now());

            candidateSkillRepository.deleteAllByCandidate(candidate.getCandidateId());
            for (String skillName : candidateDto.getSkill()) {
                Skills skill = skillRepository.findByName(skillName);
                CandidateSkill candidateSkill = new CandidateSkill();
                candidateSkill.setCandidate(candidate);
                candidateSkill.setSkill(skill);
                candidateSkillRepository.save(candidateSkill);

            }
            candidateRepository.save(candidate);
        }
    }


    @Override
    public List<Candidate> getAll() {
        return this.candidateRepository.findAll();
    }


    @Override
    public void deleteById(Integer id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public void save(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return candidateRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return candidateRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByPhoneNumberAndCandidateId(String phoneNumber, Integer candidateId) {
        return candidateRepository.existsByPhoneNumberAndCandidateId(phoneNumber, candidateId);
    }

    @Override
    public Boolean existsByEmailAndCandidateId(String email, Integer id) {
        return candidateRepository.existsByEmailAndCandidateId(email, id);
    }

    @Override
    public List<Candidate> getAllNoSchedule () {
        return candidateRepository.getAllNoSchedule();
    };

    @Override
    public Candidate findByFullName(String name) {
        return candidateRepository.findByFullName(name);
    }

}
