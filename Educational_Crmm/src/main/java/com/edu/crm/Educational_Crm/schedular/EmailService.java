package com.edu.crm.Educational_Crm.schedular;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.edu.crm.Educational_Crm.entity.Student;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAdmissionEmail(Student student) {
        // Create an email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(student.getEmail());  // Fetch student's email
        message.setSubject("Congratulations on Your Admission!");
        message.setText("Dear " + student.getName() + ",\n\n" +
                "We are pleased to inform you that you have been admitted to the course: " + student.getCourses() +
                ".\n\nBest regards,\nThe Uniq Admissions Team");

        // Send the email
        mailSender.send(message);
    }
}
