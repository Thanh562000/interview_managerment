package springboot.com.ims.service.email;

import springboot.com.ims.dto.request.MailComplexRequest;
import springboot.com.ims.dto.request.MailReminderRequest;

public interface EmailService {
    void sendMailWithTemplateThymeleaf(MailComplexRequest request);
    void sendMailChangEmail(MailComplexRequest request);

    void sendMailRemindWithTemplateThymeleaf(MailReminderRequest request);
}
