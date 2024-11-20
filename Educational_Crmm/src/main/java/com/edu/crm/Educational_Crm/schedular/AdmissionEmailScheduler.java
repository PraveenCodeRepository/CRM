package com.edu.crm.Educational_Crm.schedular;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.edu.crm.Educational_Crm.entity.Student;
import com.edu.crm.Educational_Crm.repo.StudentRepository;
import com.edu.crm.Educational_Crm.schedular.EmailLogRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

@Configuration
@EnableScheduling
public class AdmissionEmailScheduler {

    private static final Logger logger = LogManager.getLogger(AdmissionEmailScheduler.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailLogRepository emailLogRepository;

    @Scheduled(cron = "0 * * * * ?")
    public void sendAdmissionEmails() {
    	
    	System.out.println("Scheduled task is running at " + java.time.LocalDateTime.now());
        List<Student> students = studentRepository.findByStage("Admission");

        for (Student student : students) {
            if (!emailLogRepository.findByStudentIdAndEmailType(student.getId(), "Admission").isPresent()) {
                try {
                    emailService.sendAdmissionEmail(student);  // Sending email
                    EmailLog emailLog = new EmailLog();
                    emailLog.setStudentId(student.getId());
                    emailLog.setEmailType("Admission");
                    emailLog.setSent(true);
                    emailLogRepository.save(emailLog);
                    logger.info("Admission email sent to: " + student.getEmail());
                } catch (Exception e) {
                    logger.error("Failed to send email to: " + student.getEmail(), e);
                }
            }
        }
    }
}
