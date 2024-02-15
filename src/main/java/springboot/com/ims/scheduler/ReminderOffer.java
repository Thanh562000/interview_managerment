package springboot.com.ims.scheduler;


import springboot.com.ims.entity.Candidate;
import springboot.com.ims.entity.Offer;
import springboot.com.ims.service.offer.OfferService;
import springboot.com.ims.service.schedule.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class ReminderOffer {

    private final OfferService offerService;
    private final ScheduleService scheduleService;
    @Scheduled(cron = " 0 0 8 * * *")
    public void sendEmailReminderAuto(){

        try{
            LocalDate dueDate = LocalDate.now();
            List<Offer> offerList = offerService.findByDueDate(dueDate);

            for (Offer offer : offerList) {
                Candidate candidate = offer.getCandidate();
                String email = candidate.getEmail();
                String link = "http://localhost:8080/offer/view/" + offer.getId();
                scheduleService.sendEmail(email, offer, link);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
