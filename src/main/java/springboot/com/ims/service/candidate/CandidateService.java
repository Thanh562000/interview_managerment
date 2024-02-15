package springboot.com.ims.service.candidate;

import springboot.com.ims.dto.candidate.CandidateDto;
import springboot.com.ims.entity.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface CandidateService {

    Page<CandidateDto> searchCandidateList(String keyword, String enumStatus, Pageable pageable);

    Optional<Candidate> findById(Integer id);

    void save(Candidate candidate);

    void updateCandidate(CandidateDto candidateDto, UserDetails userDetails);

    void deleteById(Integer id);

    List<Candidate> getAll();

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumberAndCandidateId(String phoneNumber, Integer candidateId);

    Boolean existsByEmailAndCandidateId(String email, Integer id);

    List<Candidate> getAllNoSchedule();

    Candidate findByFullName(String name);

}
