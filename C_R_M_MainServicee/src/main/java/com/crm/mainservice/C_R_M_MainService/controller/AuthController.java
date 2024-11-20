package com.crm.mainservice.C_R_M_MainService.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.mainservice.C_R_M_MainService.entity.Student;
import com.crm.mainservice.C_R_M_MainService.entity.User;
import com.crm.mainservice.C_R_M_MainService.service.UserService;


@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "signin"; // Ensure this maps to the signin.jsp view
    }

    @RequestMapping(value = "/educationalcrm", method = RequestMethod.GET)
    public String redirect() {
        return "educationalcrm"; 
    }

    
    @PostMapping("/signin")
    public String userRegister(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        if (user != null) {
            if (user.getUsername().equals("praveen") && user.getPassword().equals("k123") && user.getCrmType().equals("Educational CRM")) {
                redirectAttributes.addFlashAttribute("warning", "Welcome to Educational CRM");
                return "educationalcrm";
            } else {
                redirectAttributes.addFlashAttribute("danger", "Something Went Bad");
            }
        } else {
            redirectAttributes.addFlashAttribute("danger", "Something Went Bad");
        }
        return "redirect:/auth"; // Redirect to the appropriate URL
    }
    
    @PostMapping("/student/insert")
    public String insertStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes)
    {
    	 userService.insertStudent(student);
    	 redirectAttributes.addFlashAttribute("successMessage", "Student details inserted successfully");
    	 return "inserteduser";
    }
    
    
    @GetMapping("/student/search")
    public String searchStudents(
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchValue") String searchValue,
            Model model) {

        List<Student> students = userService.searchStudents(searchBy, searchValue);

        // Add search results and search parameters to the model
        model.addAttribute("students", students);
        model.addAttribute("searchBy", searchBy);
        model.addAttribute("searchValue", searchValue);

        return "studentResults";
    }

    @GetMapping("/downloadFilteredStudents")
    public ResponseEntity<InputStreamResource> downloadFilteredStudents(
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchValue") String searchValue) throws IOException {

        List<Student> students = userService.searchStudents(searchBy, searchValue);
        ByteArrayInputStream in = exportStudentsToExcel(students);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=filtered_students.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    private ByteArrayInputStream exportStudentsToExcel(List<Student> students) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Students");

            // Create header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Name");
            header.createCell(2).setCellValue("Phone");
            header.createCell(3).setCellValue("Courses");
            header.createCell(4).setCellValue("HR Name");
            header.createCell(5).setCellValue("Stage");
            header.createCell(6).setCellValue("Email");

            // Create data rows
            int rowNum = 1;
            for (Student student : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(student.getId());
                row.createCell(1).setCellValue(student.getName());
                row.createCell(2).setCellValue(student.getPhone());
                row.createCell(3).setCellValue(student.getCourses());
                row.createCell(4).setCellValue(student.getHr());
                row.createCell(5).setCellValue(student.getStage());
                row.createCell(6).setCellValue(student.getEmail());
            }

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return new ByteArrayInputStream(outputStream.toByteArray());
            }
        }
    }
   
    
    @PostMapping("/student/uploadExcel")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file, Model model) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            boolean hasErrors = false;
            int rowCount = 0;  // Count rows processed successfully
            int errorCount = 0; // Track number of errors

            // Log total number of rows for debugging
            System.out.println("Total rows: " + sheet.getPhysicalNumberOfRows());

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                try {
                    // Log each row being processed
                    System.out.println("Processing row: " + row.getRowNum());

                    // Check if any cell is null and handle gracefully
                    if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null ||
                        row.getCell(3) == null || row.getCell(4) == null || row.getCell(5) == null || row.getCell(6) == null) {
                        System.out.println("Skipping row due to missing data: " + row.getRowNum());
                        continue;  // Skip this row if any cell is missing
                    }

                    // Read and validate each cell value
                    String name = row.getCell(0).getStringCellValue();
                    String degree = row.getCell(1).getStringCellValue();
                    long phone = (long) row.getCell(2).getNumericCellValue();
                    String email = row.getCell(3).getStringCellValue();
                    String courses = row.getCell(4).getStringCellValue();
                    String stage = row.getCell(5).getStringCellValue();
                    String hr = row.getCell(6).getStringCellValue();

                    // Log the data being saved
                    System.out.println("Saving student: " + name + ", " + degree + ", " + phone + ", " + email + ", " + courses + ", " + stage + ", " + hr);

                    // Save the student in the database
                    userService.saveStudent(name, degree, phone, email, courses, hr, stage);
                   
                } catch (Exception e) {
                   
                   
                    continue;  // Continue with the next row instead of breaking the loop
                }
            }

            
            if (!hasErrors) {
                model.addAttribute("successMessage", "Excel data uploaded and saved successfully. Rows processed: " + rowCount + ", Errors: " + errorCount);
            }
        } catch (Exception e) {
           
            model.addAttribute("errorMessage", "Error uploading Excel file: " + e.getMessage());
        }
        return "fileupload"; // Return to the same JSP page
    }

    @GetMapping("/student/update")
    public String showUpdateForm(@RequestParam("id") Long id , Model model) {
        Student student = userService.getStudentById(id);
        model.addAttribute("student", student);
       return "updateStudent"; // Ensure this maps to the updateStudent.jsp view
    }

    @PostMapping("/student/update")
    public String updateStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        userService.updateStudent(student);
        redirectAttributes.addFlashAttribute("successMessage", "Student details updated successfully");
        return "Updated";
   }
   
    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.GET)
    public String deleteUsers(@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
     userService.deleteUser(id);
     redirectAttributes.addFlashAttribute("successMessage", "Student details updated successfully");
     System.out.println("Auth Controller");
             return "deleted";
    }


    @GetMapping("/emailPage")
    public ModelAndView emailPage(@RequestParam String email) {
        ModelAndView mav = new ModelAndView("emailPage");  // Loads emailPage.jsp
        mav.addObject("email", email);
        return mav;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam String email, 
                                            @RequestParam String subject, 
                                            @RequestParam String message) {
        boolean isSent = userService.sendCustomEmailToStudent(email, subject, message);
        if (isSent) {
            return ResponseEntity.ok("Email sent successfully to " + email + ". <a href='/auth/educationalcrm'>Go to Homepage</a>");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email to " + email + ". <a href='/auth/educationalcrm'>Go to Homepage</a>");
        }
    }
    
    
}
