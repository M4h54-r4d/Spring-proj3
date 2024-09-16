package ir.freeland.springboot.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/registerationForm")
public class RegisterController {
  

    @GetMapping("/view-Register")
    public String viewRegForm(Model model) {
        model.addAttribute("UserName", "");
        model.addAttribute("FirstName", "");
        model.addAttribute("LastName", "");
        model.addAttribute("DateOfBirth", "");
       
        return "view-Register";
    }

    @PostMapping("/showResult")
    public String showResult(@RequestParam String User_Name,
    		@RequestParam String First_Name,
    		@RequestParam String Last_Name,
    		@RequestParam String Date_Of_Birth, Model model) 
    {
    String errorMessage = null;
    String namePattern = "^[a-zA-Z]+$";
    
    
    if (User_Name == null || User_Name.trim().isEmpty()) {
        errorMessage = "You must Fill User Name.";
    } else if (First_Name == null || First_Name.trim().isEmpty()) {
        errorMessage = "You must Fill First Name.";
    } else if (!First_Name.matches(namePattern)) {
        errorMessage = "First Name can only contain letters.";
    } else if (Last_Name == null || Last_Name.trim().isEmpty()) {
        errorMessage = "You must Fill Last Name.";
    } else if (!Last_Name.matches(namePattern)) {
        errorMessage = "Last Name can only contain letters.";
    } else if (Date_Of_Birth == null || Date_Of_Birth.trim().isEmpty()) {
        errorMessage = "You must Fill Date of Birth.";
    } else {
        // Validate date of birth
        try {
            LocalDate dateOfBirth = LocalDate.parse(Date_Of_Birth, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate today = LocalDate.now();

            if (dateOfBirth.isAfter(today)) {
                errorMessage = "Date of Birth cannot be in the future.";
            }
        } catch (DateTimeParseException e) {
            errorMessage = "Date of Birth must be in the format yyyy-MM-dd.";
        }
    }
    
    if (errorMessage != null) {
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("UserName", User_Name);
        model.addAttribute("firstName", First_Name);
        model.addAttribute("lastName", Last_Name);
        model.addAttribute("DateOfBirth", Date_Of_Birth);
        return "view-Register"; 
    }
    model.addAttribute("UserName", User_Name);
    model.addAttribute("firstName", First_Name);
    model.addAttribute("lastName", Last_Name);
    model.addAttribute("DateOfBirth", Date_Of_Birth);
    return "showResult"; 
}
}   