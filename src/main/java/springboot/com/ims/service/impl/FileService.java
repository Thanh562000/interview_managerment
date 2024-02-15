package springboot.com.ims.service.impl;

import springboot.com.ims.dto.job.JobDto;
import springboot.com.ims.entity.Job;
import springboot.com.ims.entity.intermediateTable.JobBenefit;
import springboot.com.ims.entity.intermediateTable.JobLevel;
import springboot.com.ims.entity.intermediateTable.JobSkill;
import springboot.com.ims.helper.ExcelHelper;
import springboot.com.ims.repository.common.BenefitRepository;
import springboot.com.ims.repository.common.LevelRepository;
import springboot.com.ims.repository.common.SkillRepository;
import springboot.com.ims.repository.job.JobBenefitRepository;
import springboot.com.ims.repository.job.JobLevelRepository;
import springboot.com.ims.repository.job.JobRepository;
import springboot.com.ims.repository.job.JobSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final JobSkillRepository jobSkillRepository;
    private final BenefitRepository benefitRepository;
    private final JobBenefitRepository jobBenefitRepository;
    private final LevelRepository levelRepository;
    private final JobLevelRepository jobLevelRepository;

    public void save(MultipartFile file) {
        try {
            List<JobDto> jobDtos = ExcelHelper.excelToJobs(file.getInputStream());
            for (int i = 0; i < jobDtos.size(); i++) {
                Job job = Job.builder()
                        .title(jobDtos.get(i).getTitle())
                        .startDate(jobDtos.get(i).getStartDate())
                        .endDate(jobDtos.get(i).getEndDate())
                        .salaryFrom(jobDtos.get(i).getSalaryFrom())
                        .salaryTo(jobDtos.get(i).getSalaryTo())
                        .workingAddress(jobDtos.get(i).getWorkingAddress())
                        .description(jobDtos.get(i).getDescription())
                        .build();

                jobRepository.save(job);

                List<String> skill = jobDtos.get(i).getSkill();

                for (String skillName : skill) {
                    JobSkill jobSkill = JobSkill.builder()
                            .skills(skillRepository.findByName(skillName))
                            .job(job)
                            .build();

                    jobSkillRepository.save(jobSkill);
                }

                List<String> benefit = jobDtos.get(i).getBenefits();

                for (String benefitName : benefit) {
                    JobBenefit jobBenefit = JobBenefit.builder()
                            .benefit(benefitRepository.findByName(benefitName))
                            .job(job)
                            .build();

                    jobBenefitRepository.save(jobBenefit);
                }

                List<String> level = jobDtos.get(i).getLevel();

                for (String levelName : level) {
                    JobLevel jobLevel = JobLevel.builder()
                            .level(levelRepository.findByName(levelName))
                            .job(job)
                            .build();

                    jobLevelRepository.save(jobLevel);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
