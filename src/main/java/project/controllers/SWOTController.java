package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.entity.Company;
import project.entity.CompanyData;
import project.entity.SWOT;
import project.entity.User;
import project.repository.CompanyDataRepository;
import project.repository.CompanyRepository;
import project.repository.UserRepository;
import project.service.CompanyDataService;
import project.service.CompanyService;
import project.service.SWOTService;
import project.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class SWOTController {
    @Autowired
    SWOTService swotService;
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyDataService companyDataService;

    @GetMapping("/swot/{id}") //Источник: CNews Analytics, 2023
    public String swot(Model model, @PathVariable("id") long id_company){
        CompanyData companyData = companyDataService.find(id_company);
        Company company = companyService.get(id_company);
        User user = company.getUser();
        SWOT analysis = swotService.SWOTAnalysis(companyData.getRevenue(), companyData.getEmployees(), companyData.getCompany());
        model.addAttribute("user", user);
        model.addAttribute("company", company);
        model.addAttribute("swot", analysis);
        return "swot";
    }

}