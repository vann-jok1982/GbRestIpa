package com.example.Web_Koleso.controllers;

import com.example.Web_Koleso.repositories.TireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class KolesoController {
    @Autowired
    private TireRepository tireRepository;

    @GetMapping("/tire")
    public String SpisokAll(Model model) {
        model.addAttribute("tire",tireRepository.findAll());
        return "tire-spisok";
    }
    @GetMapping("/home")
    public String Home() {
        return "home";
    }
    @PostMapping("/submit-article")
    public String handleArticleRequest(@RequestParam("article") String article) {
        ModelAndView modelAndView = new ModelAndView("articlePage");

        // Добавьте логику для обработки артикула
        modelAndView.addObject("article", article);

        return "redirect:/home";
    }

    @PostMapping("/submit-name")
    public ModelAndView handleNameRequest(@RequestParam("name") String name) {
        ModelAndView modelAndView = new ModelAndView("namePage");

        // Добавьте логику для обработки наименования
        modelAndView.addObject("name", name);

        return modelAndView;
    }

    @GetMapping("/save")   // Добавление задачи
    public String showForm(Model model) {
        //       model.addAttribute("formData", new Koleso());
        return "koleso-save"; //
    }

  //  @PostMapping("/save")   // пост метод для добавления задачи
 //   public String submitForm(@ModelAttribute Koleso koleso) {
 //       kolesoRepository.save(koleso);
 //       return "redirect:/Koleso"; // имя шаблона для отображения результата
  //  }

}