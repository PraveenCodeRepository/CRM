package com.crm.mainservice.C_R_M_MainService.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import com.crm.mainservice.C_R_M_MainService.entity.Student;


@Service
public class UserService {
	
	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<String>insertStudent(Student student) {
		
		
		
		HttpEntity<Student> requestEntity = new HttpEntity<>(student);
		
		 return restTemplate.exchange(
			    "http://localhost:9090/student/insert",
	            HttpMethod.POST,
	            requestEntity,
	            String.class
	            
	        );
	}

	public List<Student> searchStudents(String searchBy, String searchValue) {
        String url = "http://localhost:9090/student/search?searchBy=" + searchBy + "&searchValue=" + searchValue;
        ResponseEntity<List<Student>> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Student>>() {}
        );
        return responseEntity.getBody();
    }

	@Transactional
    public void saveStudent(String name, String degree, long phone, String email, String courses, String hrName, String stage) {
        Student student = new Student();
        student.setName(name);
        student.setDegree(degree);
        student.setPhone(phone);
        student.setEmail(email);
        student.setCourses(courses);
        student.setHr(hrName);
        student.setStage(stage);


		HttpEntity<Student> requestEntity = new HttpEntity<>(student);
		
		  restTemplate.exchange(
			    "http://localhost:9090/student/uploadfile",
	            HttpMethod.POST,
	            requestEntity,
	            String.class
	            
	        );
    }
	
	
	public Student getStudentById(Long id) {
        String url = "http://localhost:9090/student/" + id;
        ResponseEntity<Student> responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            Student.class
        );
        return responseEntity.getBody();
    }

    public void updateStudent(Student student) {
        HttpEntity<Student> requestEntity = new HttpEntity<>(student);
        restTemplate.exchange(
            "http://localhost:9090/student/update",
            HttpMethod.POST,
            requestEntity,
            String.class
        );
    }

    public void deleteUser(Long id) {
        String url = "http://localhost:9090/student/delete/" + id;
        
        try {
            // Use HttpMethod.DELETE and Void as the response type
            restTemplate.exchange(
                url,
                HttpMethod.GET,  // Use DELETE instead of GET
                null,  // No request body for delete
                Void.class  // Void indicates that we don't expect a response body
            );
            
            System.out.println("User Service - Deleted student with ID: " + id);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
            // Handle the error as per your application logic
        }
    }


    private final String secondServiceUrl = "http://localhost:9090/student/api/v1/email/send";

    public boolean sendCustomEmailToStudent(String email, String subject, String message) {
        try {
            // Create email payload
            Map<String, String> emailPayload = new HashMap<>();
            emailPayload.put("email", email);
            emailPayload.put("subject", subject);
            emailPayload.put("message", message);

            // Call the second service to send an email
            ResponseEntity<String> response = restTemplate.postForEntity(secondServiceUrl, emailPayload, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
       
		
	}

