package com.edu.crm.Educational_Crm.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.crm.Educational_Crm.entity.Student;
import com.edu.crm.Educational_Crm.service.StudentService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    
    @Autowired
    private JavaMailSender mailSender; 

    // http://localhost:9090/student/insert
    @PostMapping("/insert")
    public String insertStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
        
        return "redirect:http://localhost:8080/auth/student/insert"; // Redirect to the insertStudent page in the main service
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchValue") String searchValue) {
        List<Student> students = studentService.searchStudents(searchBy, searchValue);
        return ResponseEntity.ok(students);
    }
    
    @PostMapping("/uploadfile")
    public void uploadfile(@RequestBody Student student) {
        studentService.uploadfile(student);
        
        
    }
    
    @PostMapping("/update")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return ResponseEntity.ok("Student updated successfully");
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id); // Assuming you have a method like this in your service
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable Long id) {
         studentService.deleteById(id); 
         
        
        
    }
    
    @PostMapping("/api/v1/email/send")
    public ResponseEntity<String> sendCustomEmail(@RequestBody Map<String, String> emailDetails) {
        try {
            String email = emailDetails.get("email");
            String subject = emailDetails.get("subject");
            String messageContent = emailDetails.get("message");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            message.setText(messageContent);
            mailSender.send(message);

            return ResponseEntity.ok("Email sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while sending email.");
        }
    }
    
}
