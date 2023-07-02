package org.gimeast.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.gimeast.springex.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello............");
    }

    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("ex1................");
        log.info("name: {}", name);
        log.info("age: {}", age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "AAA") String name, @RequestParam(name = "age", defaultValue = "20") int age) {
        log.info("ex1................");
        log.info("name: {}", name);
        log.info("age: {}", age);
    }

    @GetMapping("ex3")
    public void ex3(LocalDate dueDate) {//LocalDate로 받기위해서는 Formatter 구현과 servlet-context.xml에 conversionService 설정을 해주어야 한다.
        log.info("ex3...........");
        log.info("dueDate: {}", dueDate);
    }

    @GetMapping("ex4")
    public void ex4(Model model) {
        log.info("---------------------_");
        model.addAttribute("message", "<br/><br/><h1>hihi</h1>");
    }

    @GetMapping("/ex4_1")
    public void ex4Extra(@ModelAttribute("dto") TodoDTO todoDTO, Model model) {
        log.info(todoDTO);
    }

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("name", "abc");
        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/ex6";
    }

    @GetMapping("/ex6")
    public void ex6() {

    }

    @GetMapping("/ex7")
    public void ex7(String p1, int p2) {
        log.info("p1..............."+p1);
        log.info("p2..............."+p2);
    }

}
