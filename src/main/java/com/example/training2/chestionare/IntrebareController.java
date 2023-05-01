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
    int index = 0;
    int scor = 0;
    List<Intrebare> listaIntrebariExamen = new ArrayList<>();
    List<Intrebare> listaIntrebariExamenOriginale = new ArrayList<>();
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
        model.addAttribute("pageTitle", "Adaugati noua Intrebare");
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
        Random random = new Random(16548923);
        List<Intrebare> listIntrebariSimulator = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            boolean duplicat = false;
            int generatorRandom = random.nextInt(listIntrebari.size());
            for (Intrebare intrebare : listIntrebariSimulator) {
                if (listIntrebari.get(generatorRandom).equals(intrebare)) {
                    duplicat = true;
                    break;
                }
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
        List<Intrebare> listIntrebari = service.listAll();
        ArrayList<Integer> numbers = new ArrayList<>();
        Random randomGenerator = new Random();
        while (numbers.size() <= 26) {

            int random = randomGenerator.nextInt(listIntrebari.size());
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        for (int i = 0; i < 26; i++) {
            if (listaIntrebariExamen.size() > 26) {
                listaIntrebariExamen.remove(i);
                listaIntrebariExamen.add(listIntrebari.get(numbers.get(i)));
            }
            else {
                listaIntrebariExamen.add(listIntrebari.get(numbers.get(i)));
            }
        }
        listaIntrebariExamenOriginale = listaIntrebariExamen;
        model.addAttribute("listIntrebari",listaIntrebariExamen);
        index++;
        model.addAttribute("index",index);
        model.addAttribute("scor",scor);
        return "simulatorExamen";
    }

    @PostMapping("/getTexts")
    @ResponseBody
    public List<String> getTexts () {
        List<String> texts = new ArrayList<>();
        texts.add(listaIntrebariExamen.get(index).getIntrebare());
        texts.add(listaIntrebariExamen.get(index).getOptiuneaa());
        texts.add(listaIntrebariExamen.get(index).getOptiuneab());
        texts.add(listaIntrebariExamen.get(index).getOptiuneac());
        index++;
        return texts;
    }

    @PostMapping("/submitForm")
    public String submitForm(@RequestParam(name = "myBooleanInput1", required = false) Boolean varianta1,
                           @RequestParam(name = "myBooleanInput2", required = false) Boolean varianta2,
                           @RequestParam(name = "myBooleanInput3", required = false) Boolean varianta3 ) {
        Intrebare intrebareSubmited = listaIntrebariExamen.get(index);
        List<Intrebare> intrebariTotale = service.listAll();
        Intrebare intrebareOriginala = new Intrebare();
        for (Intrebare intrebare : intrebariTotale) {
            if (intrebare.getId().equals(intrebareSubmited.getId())) {
                intrebareOriginala = intrebare;
            }
        }
        if(varianta1 == null)
            varianta1 = false;
        if(varianta2 == null)
            varianta2 = false;
        if(varianta3 == null)
            varianta3 = false;
        intrebareSubmited.setOptiuneaabool(varianta1);
        intrebareSubmited.setOptiuneabbool(varianta2);
        intrebareSubmited.setOptiuneacbool(varianta3);
        if ((intrebareSubmited.isOptiuneaabool()== intrebareOriginala.isOptiuneaabool()) && (intrebareSubmited.isOptiuneabbool()== intrebareOriginala.isOptiuneabbool()) && (intrebareSubmited.isOptiuneacbool()== intrebareOriginala.isOptiuneacbool()))
            scor++;
        if ((index - scor)>4){
            return "redirect:/rezultat";
        }
        if (index >= 26) {
            return "redirect:/rezultat";
        }
        return "redirect:/simulatorExamen";
    }

    @GetMapping("/rezultat")
    public String showRezultat(Model model) {
        model.addAttribute("scor",scor);
        scor = 0;
        index = 0;
        return "rezultat";

    }

    @GetMapping("/chestionare/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id,Model model){
        try {
            Intrebare intrebare = service.get(id);
            model.addAttribute("intrebare",intrebare);
            model.addAttribute("pageTitle","Editati Intrebare (ID: " + id + ")");

            return "intrebari_form";
        } catch (IntrebareNotFoundException e) {
            return "redirect:/chestionare";

    }
    }

    @GetMapping("/chestionare/delete/{id}")
    public String deleteIntrebare(@PathVariable("id") Integer id, Model model) {
        try {
            service.delete(id);
        } catch (IntrebareNotFoundException e) {}
            return "redirect:/chestionare";
        }
    }

