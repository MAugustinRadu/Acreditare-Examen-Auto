package com.example.training2.chestionare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class IntrebareController {
    @Autowired private IntrebareService service;

    @GetMapping("/chestionare")
    public String showUserList(Model model){
        List<Intrebare> listIntrebari = service.listAll();
        model.addAttribute("listIntrebari",listIntrebari);

        return "chestionare";
    }

    @GetMapping("/chestionare/new")
    public String showNewForm(Model model) {
        model.addAttribute("intrebare",new Intrebare());
        return "intrebari_form";
    }

    @PostMapping("/chestionare/save")
    public String saveIntrebare(Intrebare intrebare) {
        service.save(intrebare);

        return "redirect:/chestionare";
    }

}
