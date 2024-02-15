package springboot.com.ims.controller.job;

import springboot.com.ims.dto.candidate.FileDto;
import springboot.com.ims.dto.job.JobDto;
import springboot.com.ims.entity.Job;
import fa.training.fjb04.ims.entity.intermediateTable.*;
import springboot.com.ims.repository.candidate.CandidateJobRepository;
import springboot.com.ims.repository.common.BenefitRepository;
import springboot.com.ims.repository.common.LevelRepository;
import springboot.com.ims.repository.common.SkillRepository;
import springboot.com.ims.repository.job.JobBenefitRepository;
import springboot.com.ims.repository.job.JobLevelRepository;
import springboot.com.ims.repository.job.JobSkillRepository;
import springboot.com.ims.repository.user.UserScheduleRepository;
import springboot.com.ims.service.common.BenefitService;
import springboot.com.ims.service.common.LevelService;
import springboot.com.ims.service.common.SkillService;
import springboot.com.ims.service.job.JobService;
import springboot.com.ims.service.schedule.ScheduleService;
import springboot.com.ims.util.constant.AppConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(MockitoExtension.class)
class JobControllerTest {
    @InjectMocks
    private JobController jobController;

    @Mock
    private JobService jobService;

    @Mock
    private SkillService skillService;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private LevelService levelService;

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private BenefitService benefitService;

    @Mock
    private BenefitRepository benefitRepository;

    @Mock
    private CandidateJobRepository candidateJobRepository;
    @Mock
    private JobBenefitRepository jobBenefitRepository;
    @Mock
    private JobLevelRepository jobLevelRepository;
    @Mock
    private JobSkillRepository jobSkillRepository;
    @Mock
    private UserScheduleRepository userScheduleRepository;
    @Mock
    private ScheduleService scheduleService;

    private MockMvc mockMvc;

    private void buildMockMvc(final Object controller) {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @BeforeEach
    public void setup() {
        buildMockMvc(jobController);
    }

    @Test
    void getAll() throws Exception {
        JobDto jobDto = new JobDto();
        jobDto.setDescription("abc");
        List<JobDto> jobDtoList = new ArrayList<>();
        jobDtoList.add(jobDto);
        Mockito.when(jobService.showAll()).thenReturn(jobDtoList);

        ResultActions resultActions = mockMvc.perform(get("/job"))
                .andExpect(model().attribute("jobDtoList", jobDtoList))
                .andExpect(model().attribute("fileDto", new FileDto()))
                .andExpect(model().attribute("pageSizeCurrent", AppConstants.DEFAULT_PAGE_SIZE));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.forwardedUrl("jobs/list_job"));
    }

    @Test
    void searchCandidateList() throws Exception {
        int pageIndex = 0;
        int pageSize = 3;
        String keyword = "HR";
        String status = "Open";

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        JobDto jobDto = new JobDto();
        jobDto.setStatus(status);
        jobDto.setTitle(keyword);
        List<JobDto> jobDtoList = new ArrayList<>();
        jobDtoList.add(jobDto);
        Mockito.when(jobService.searchJobList(keyword, status, pageable)).thenReturn(new PageImpl<>(jobDtoList, pageable, jobDtoList.size()));

        ResultActions resultActions = mockMvc.perform(get("/job/api/v1")
                .param("pageIndex", String.valueOf(pageIndex))
                .param("pageSize", String.valueOf(pageSize))
                .param("keyword", keyword)
                .param("status", status));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title").value("HR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].status").value("Open"));
    }

    @Test
    void getFormAddJob() throws Exception {
        Mockito.when(skillService.findAllSkill()).thenReturn(Collections.emptyList());
        Mockito.when(levelService.findAllLevels()).thenReturn(Collections.emptyList());
        Mockito.when(benefitService.findAllBenefit()).thenReturn(Collections.emptyList());

        ResultActions resultActions = mockMvc.perform(get("/job/add"))
                .andExpect(model().attribute("addJob", new JobDto()))
                .andExpect(model().attribute("skills", skillService.findAllSkill()))
                .andExpect(model().attribute("levels", levelService.findAllLevels()))
                .andExpect(model().attribute("benefits", benefitService.findAllBenefit()));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.forwardedUrl("jobs/add_job"));
    }

//    @Test
//    void addJob() throws Exception {
//        ResultActions resultActions = mockMvc.perform(post("/job/add")
//                .param("title", "Some title")
//                .param("salaryFrom", "1000")
//                .param("salaryTo", "2000")
//                .param("workingAddress", "Some workingAddress")
//                .param("startDate", "2024-01-15")
//                .param("endDate", "2024-02-15")
//                .param("skillsSet", "1", "2", "3")
//                .param("benefitSet", "1", "2", "3")
//                .param("levelsSet", "1", "2", "3"));
//
//        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
//        resultActions.andExpect(MockMvcResultMatchers.forwardedUrl("jobs/add_job"));
//    }

    @Test
    void getEditJobForm() throws Exception {
        JobDto jobDto = new JobDto();
        jobDto.setJobId(1);
//        jobService.save(jobDto);

        Mockito.when(jobService.findJobDtoById(1)).thenReturn(jobDto);
        Mockito.when(skillService.findAllSkill()).thenReturn(Collections.emptyList());
        Mockito.when(levelService.findAllLevels()).thenReturn(Collections.emptyList());
        Mockito.when(benefitService.findAllBenefit()).thenReturn(Collections.emptyList());

        ResultActions resultActions = mockMvc.perform(get("/job/update/1"))
                .andExpect(model().attribute("jobDto", jobDto))
                .andExpect(model().attribute("benefitNames", benefitService.findAllBenefit()))
                .andExpect(model().attribute("levelNames", levelService.findAllLevels()))
                .andExpect(model().attribute("skillNames", skillService.findAllSkill()));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.forwardedUrl("jobs/edit_job"));
    }

    @Test
    void submitUpdateJob() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/job/update/1")
                .param("title", "Some title")
                .param("salaryFrom", "1000")
                .param("salaryTo", "2000")
                .param("workingAddress", "Some workingAddress")
                .param("startDate", "2024-01-15")
                .param("endDate", "2024-02-15")
                .param("skillsSet", "1", "2", "3")
                .param("benefitSet", "1", "2", "3")
                .param("levelsSet", "1", "2", "3"));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.forwardedUrl("jobs/edit_job"));
    }

    @Test
    void getViewJobDetail() throws Exception {
        JobDto jobDto = new JobDto();
        jobDto.setJobId(1);
//        jobService.save(jobDto);

        Job job = new Job();
        job.setCreatedDate(LocalDateTime.now());
        BeanUtils.copyProperties(job, jobDto);
//        jobService.save(jobDto);

        Mockito.when(jobService.findById(1)).thenReturn(Optional.of(job));

        boolean showTodayInfo = job.getCreatedDate().isEqual(LocalDateTime.now());

        boolean showOtherDayInfo = !showTodayInfo;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatterDate = job.getCreatedDate().format(formatter);

        ResultActions resultActions = mockMvc.perform(get("/job/viewDetail/1"))
                .andExpect(model().attribute("jobDetail", job))
                .andExpect(model().attribute("showTodayInfo", showTodayInfo))
                .andExpect(model().attribute("showOtherDayInfo", showOtherDayInfo))
                .andExpect(model().attribute("formatterDate", formatterDate));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andExpect(MockMvcResultMatchers.forwardedUrl("jobs/detail_job"));
    }
}