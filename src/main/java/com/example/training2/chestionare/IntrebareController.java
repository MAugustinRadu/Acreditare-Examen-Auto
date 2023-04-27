package com.example.training2.chestionare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public RedirectView saveIntrebare(@RequestBody Intrebare intrebare) {
        service.save(intrebare);

        return new RedirectView("/chestionare");
    }

    @GetMapping("/intrebarisimulator")
    public String intrebariSimulator(Model model) {
        List<Intrebare> listIntrebari = service.listAll();
        Random random = new Random();
        List<Intrebare> listIntrebariSimulator = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            Boolean duplicat = false;
            int generatorRandom = random.nextInt(listIntrebari.size());
            for (int j = 0; j < listIntrebariSimulator.size(); j++) {
                if(listIntrebari.get(i).equals(listIntrebariSimulator.get(j)))
                    duplicat=true;
            }
            if (!duplicat) {
                listIntrebariSimulator.add(listIntrebari.get(i));
            }
        }
        model.addAttribute("listIntrebari",listIntrebariSimulator);
        return "intrebarisimulator";
    }
    @GetMapping("/simulatorExamen")
    public String simulatorExamen(Model model) {
        return "simulatorExamen";
    }

}
