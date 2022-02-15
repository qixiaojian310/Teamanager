package com.jian.controller.sampleproject.controller;

import com.jian.controller.sampleproject.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.PUT})
@Controller
@RequestMapping(value = "/json")
public class JsonRequestController {

	@RequestMapping(value = "/postEmployee", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	Employee postEmployeeData(@RequestBody Employee employee) {

		double newSalary = employee.getSalary() + 1000;
		employee.setSalary(newSalary);

		return employee;
	}
	
	@RequestMapping(value = "/addEmployee")
	public String showEmployeePage(){
		return "addEmployee";
	}
			
}
