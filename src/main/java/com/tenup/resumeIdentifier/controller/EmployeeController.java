package com.tenup.resumeIdentifier.controller;

import com.tenup.resumeIdentifier.AbstractController;
import com.tenup.resumeIdentifier.controller.dto.RequestDTO;
import com.tenup.resumeIdentifier.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController  extends AbstractController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload/resume")
    public String handleFileUpload(@ModelAttribute RequestDTO fileDetails) throws Throwable {
        Map<String,Object> data = new HashMap<>();
        data.put("original_file_name","test.jpg");
        data.put("file_name","adadadasda");
        data.put("status","Pending");
        employeeService.saveEmployeeDetail(data);
        return "";
    }

    @PostMapping("/getAll")
    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> employeeList = new ArrayList<>();

        Map<String,Object> dataOne = new HashMap<>();
        dataOne.put("name", "abc");
        dataOne.put("address", "vadodara");
        dataOne.put("experience", "5 years");
        dataOne.put("skills", "java 11, Spring MVC, Spring Boot");
        employeeList.add(dataOne);

        Map<String,Object> dataTwo = new HashMap<>();
        dataTwo.put("name", "pqr");
        dataTwo.put("address", "Ahemadabad");
        dataOne.put("experience", "10 years");
        dataTwo.put("skills", "java 11, Spring MVC, Spring Boot");
        employeeList.add(dataTwo);

        return employeeList;
    }
}
