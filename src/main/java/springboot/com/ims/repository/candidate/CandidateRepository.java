package springboot.com.ims.repository.candidate;

import springboot.com.ims.dto.candidate.CandidateDto;
import springboot.com.ims.entity.Candidate;
import springboot.com.ims.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    @Query("""
            SELECT NEW springboot.com.ims.dto.candidate.CandidateDto(c.candidateId, c.fullName,c.email,c.phoneNumber,c.status, c.position, c.user) FROM Candidate c 
            WHERE 
               (:keyword IS NULL OR 
                   LOWER(c.fullName) LIKE CONCAT('%', :keyword, '%') OR 
                   LOWER(c.email) LIKE CONCAT('%', :keyword, '%') OR 
                   c.phoneNumber LIKE CONCAT('%', :keyword, '%') OR 
                   LOWER(c.user.fullName) LIKE CONCAT('%', :keyword, '%') OR 
                   LOWER(c.position) LIKE CONCAT('%', :keyword, '%')
               ) 
               AND (:status IS NULL OR c.status = :status) 
            ORDER BY 
               CASE c.status 
                   WHEN springboot.com.ims.enums.Status.WAITING_FOR_INTERVIEW THEN 0 
                   WHEN springboot.com.ims.enums.Status.WAITING_FOR_APPROVAL THEN 1 
                   WHEN springboot.com.ims.enums.Status.WAITING_FOR_RESPONSE THEN 2 
                   WHEN springboot.com.ims.enums.Status.OPEN THEN 3 
                   WHEN springboot.com.ims.enums.Status.PASSED_INTERVIEW THEN 4 
                   WHEN springboot.com.ims.enums.Status.APPROVED_OFFER THEN 5 
                   WHEN springboot.com.ims.enums.Status.REJECTED_OFFER THEN 6 
                   WHEN springboot.com.ims.enums.Status.ACCEPTED_OFFER THEN 7 
                   WHEN springboot.com.ims.enums.Status.DECLINED_OFFER THEN 8 
                   WHEN springboot.com.ims.enums.Status.CANCELLED_OFFER THEN 9 
                   WHEN springboot.com.ims.enums.Status.FAILED_INTERVIEW THEN 10 
                   WHEN springboot.com.ims.enums.Status.CANCELLED_INTERVIEW THEN 11 
                   WHEN springboot.com.ims.enums.Status.BANNED THEN 12 
                   ELSE 31 
               END ASC, 
               c.createdDate DESC
               """)
    Page<CandidateDto> getSearchList(String keyword, Status status, Pageable pageable);

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumberAndCandidateId(String phoneNumber, Integer candidateId);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndCandidateId(String email, Integer id);

    @Query(value = "select c from Candidate c")
    List<Candidate> findAllCandidate();

    @Query("SELECT c FROM Candidate c " +
            "LEFT OUTER JOIN c.schedule s " +
            "WHERE s.candidate IS NULL")
    List<Candidate> findAllCandidateNoSchedule();

    @Query(value = "select c from Candidate c where c.email = :email")
    Candidate findByCandidateEmail(String email);

    @Query("SELECT c FROM Candidate c " +
            "LEFT OUTER JOIN c.schedule s " +
            "WHERE s.candidate IS NULL")
    List<Candidate> getAllNoSchedule ();

    Candidate findByFullName (String name);
}
