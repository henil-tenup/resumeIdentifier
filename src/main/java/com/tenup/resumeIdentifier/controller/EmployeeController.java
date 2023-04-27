package com.tenup.resumeIdentifier.controller;

import com.tenup.resumeIdentifier.AbstractController;
import com.tenup.resumeIdentifier.controller.dto.RequestDTO;
import com.tenup.resumeIdentifier.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
}
