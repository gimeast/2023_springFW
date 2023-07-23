package org.gimeast.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gimeast.springex.dto.PageRequestDTO;
import org.gimeast.springex.dto.PageResponseDTO;
import org.gimeast.springex.dto.TodoDTO;
import org.gimeast.springex.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

//    @RequestMapping("/list")
    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult , Model model) {
        log.info("todo list..........");

//        List<TodoDTO> todoDTOList = todoService.getAll();
//        model.addAttribute("dtoList", todoDTOList);

        log.info("pageRequestDTO: {}", pageRequestDTO);

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public void register() {
        log.info("todo register.................");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("POST todo register....................");

        if (bindingResult.hasErrors()) {
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/todo/register";
        }

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(PageRequestDTO pageRequestDTO, Long tno, Model model) {
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info("todoDTO: {}", todoDTO);

        model.addAttribute("dto", todoDTO);
    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO) {
        log.info("-------------remove-----------------");
        log.info("tno: " + tno);

        todoService.remove(tno);

        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/todo/list?" + pageRequestDTO.getLink();
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO) {
        log.info("todoDTO: {}", todoDTO);
        redirectAttributes.addAttribute("tno", todoDTO.getTno());

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/todo/modify?" + pageRequestDTO.getLink();
        }

        todoService.modify(todoDTO);

        return "redirect:/todo/read";
    }



}
