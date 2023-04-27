package com.tenup.resumeIdentifier.controller;

import com.tenup.resumeIdentifier.AbstractController;
import com.tenup.resumeIdentifier.controller.dto.RequestDTO;
import com.tenup.resumeIdentifier.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController  extends AbstractController {

    @Autowired
    private EmployeeService employeeService;

    private static final String PENDING = "Pending";

    @PostMapping("/upload/resume")
    public String uploadFile(@ModelAttribute RequestDTO fileDetails) throws Exception {
        Map<String,Object> data = new HashMap<>();
        data.put("original_file_name",fileDetails.getFile().getOriginalFilename());
        data.put("file_name",fileDetails.getFile().getName());
        data.put("status",PENDING);
        employeeService.saveEmployeeDetail(data, fileDetails.getFile());
        return "";
    }

    @GetMapping("/getAll")
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
        dataTwo.put("experience", "10 years");
        dataTwo.put("skills", "java 11, Spring MVC, Spring Boot");
        employeeList.add(dataTwo);

        return employeeList;
    }
}
