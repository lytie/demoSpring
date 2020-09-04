package com.example.demo.controller;



import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Person;
import com.example.demo.service.IPersonDAO;



@Controller
public class mainController {

	@Autowired
    private IPersonDAO iPersonDAO;

    @GetMapping("/person")
    public String list(Model model) {
        model.addAttribute("people", iPersonDAO.findAll());
        return "index";
    }
	
    
    @GetMapping("/person/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/person";
        }

        model.addAttribute("people", iPersonDAO.search(term));
        return "index";
    }
    
    
    
    @GetMapping("/person/add")
    public String add( Model model) {
    	model.addAttribute("person", new Person());
        return "add";
    }
    
    
    @PostMapping("/person/save")
    public String save(@Valid Person person, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "add";
        }
        iPersonDAO.save(person);
        redirect.addFlashAttribute("successMessage", "Saved contact successfully!");
        return "redirect:/person";
    }
    
    
    @GetMapping("/person/delete/{id}")
    public String detele(@PathVariable int id, RedirectAttributes reAttributes) {
    	iPersonDAO.delete(id);
    	reAttributes.addFlashAttribute("successMessage", "Deleted contact succsessfully");
        return "redirect:/person";

    }
    
    @RequestMapping("/person/update/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Person> person = iPersonDAO.findOne(id);
			model.addAttribute("person", person.get());
		return "add";
	}
    
}
