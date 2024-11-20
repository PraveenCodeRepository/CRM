package com.edu.crm.Educational_Crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.crm.Educational_Crm.entity.Student;
import com.edu.crm.Educational_Crm.repo.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;

	 public String insertStudent(Student student) {
	        studentRepository.save(student);
	        return "Student data inserted successfully";
	    }
	  
	 public List<Student> searchStudents(String searchBy, String searchValue) {
	        switch (searchBy) {
	            case "name":
	                return studentRepository.findStudents(searchValue, null, null, null, null);
	            case "phone":
	                return studentRepository.findStudents(null, Long.parseLong(searchValue), null, null, null);
	            case "course":
	                return studentRepository.findStudents(null, null, searchValue, null, null);
	            case "hr":
	                return studentRepository.findStudents(null, null, null, searchValue, null);
	            case "stage":
	                return studentRepository.findStudents(null, null, null, null, searchValue);
	            default:
	                return List.of(); // Return empty list if no valid search criteria is given
	        }
	    }

	public void uploadfile(Student student) {
		studentRepository.save(student);
		
	}

	public void updateStudent(Student student) {
		
		studentRepository.save(student);
	}
	
	public Optional<Student> findById(Long id) {
	    return studentRepository.findById(id); 
	}

	
	 @Transactional
	public void deleteById(Long id) {
		studentRepository.deleteById(id);
		System.out.println("Student Service");
		
	}
	 
	 


}
