package com.example.examspring.controller;

import com.example.examspring.dto.EmployeeDto;
import com.example.examspring.dto.PageDto;
import com.example.examspring.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class EmployeeController extends BaseController {
    @Autowired
    private EmployeeService service;

    //View
    @GetMapping(value = "/employees")
    public ModelAndView gets(@RequestParam(value = "nameEmployee", required = false) String name,
                             @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize,
                             HttpServletRequest request) {
        EmployeeDto criteria = new EmployeeDto();
//        criteria.setPageSize(commonProperties.getPageSize());
//        criteria.setPageNumber(commonProperties.getPageNumber());
//        ModelAndView view = new ModelAndView("employee/list");
//        List<EmployeeDto> employees = service.gets(criteria);
//        view.addObject("employees", employees);

        if (StringUtils.isNotEmpty(name)) {
            criteria.setName(name);
        }

        if (pageNumber == null || pageNumber < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        } else {
            criteria.setPageNumber(pageNumber);
        }
        if (pageSize == null || pageSize <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        } else {
            criteria.setPageSize(pageSize);
        }
        ModelAndView view = new ModelAndView("employee/list");
        PageDto<EmployeeDto> employeeDtoPageDto = service.gets(criteria);

        view.addObject("employees", employeeDtoPageDto.getList());
        view.addObject("pages", employeeDtoPageDto);
        return view;
    }

    @GetMapping("/employee")
    public ModelAndView get(@RequestParam(required = false) Long id, HttpServletRequest request) {

        ModelAndView view = new ModelAndView("employee/detail");
        EmployeeDto employeeDto = service.getById(id);
        view.addObject("employee", employeeDto);
        return view;
    }

    @GetMapping(value = "/employee/form")
    public ModelAndView form(@RequestParam(required = false) Long id, HttpServletRequest request) {
        EmployeeDto employeeDto = new EmployeeDto();
        if (!Objects.isNull(id)) {
            employeeDto = service.getById(id);
        }
        ModelAndView view = new ModelAndView("employee/form");
        view.addObject("employee", employeeDto);
        return view;
    }

    @PostMapping(value = "/employee/save")
    public ModelAndView save(@ModelAttribute() EmployeeDto employeeDto, HttpServletRequest request) {
        EmployeeDto employee = service.save(employeeDto);
        if (Objects.isNull(employee)) {
            return null;
        }
        return new ModelAndView("redirect:/employee?id=" + employee.getId());
    }

//    @GetMapping(value = "/employee/delete")
//    public ModelAndView delete(@RequestParam(required = false) Long id, RedirectAttributes redirectAttributes) {
//        boolean result = service.delete(id);
//        if (result) {
//            redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully!");
//        } else {
//            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete employee!");
//        }
//        return new ModelAndView("redirect:/employees");
//    }

    @GetMapping(value = "/employee/delete")
    public ModelAndView delete(@RequestParam(required = false) Long id,
                               @RequestParam(required = false) Integer pageNumber,
                               @RequestParam(required = false) Integer pageSize,
                               RedirectAttributes redirectAttributes) {
        boolean result = service.delete(id);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete employee!");
        }

        StringBuilder redirectUrl = new StringBuilder("redirect:/employees");
        if (pageNumber != null && pageSize != null) {
            redirectUrl.append("?pageNumber=").append(pageNumber).append("&pageSize=").append(pageSize);
        }

        return new ModelAndView(redirectUrl.toString());
    }
}
