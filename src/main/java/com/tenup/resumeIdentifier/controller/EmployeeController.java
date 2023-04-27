package com.tenup.resumeIdentifier.controller;

import com.tenup.resumeIdentifier.AbstractController;
import com.tenup.resumeIdentifier.controller.dto.EmployeeDTO;
import com.tenup.resumeIdentifier.controller.dto.RequestDTO;
import com.tenup.resumeIdentifier.controller.dto.SkillDTO;
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
    public List<EmployeeDTO> getAll() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setName("abc");
        employeeDto.setAddress("Gujarat");
        employeeDto.setExperience(5);

        List<String> skillList = new ArrayList<>();
        skillList.add("skills");
        skillList.add("java 11");
        skillList.add("Spring MVC");
        employeeDto.setSkills(skillList);
        employeeDTOList.add(employeeDto);

        return employeeDTOList;
    }
}
