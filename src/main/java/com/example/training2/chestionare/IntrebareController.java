package com.example.training2.chestionare;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
        listaIntrebariExamen.clear();
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
        List<Intrebare> listIntrebari = service.listAll();
        listaIntrebariExamen.clear();
        Random random = new Random();
        for (int i = 0; i < 26; i++) {
            Boolean duplicat = false;
            int generatorRandom = random.nextInt(listIntrebari.size());
            for (int j = 0; j < listaIntrebariExamen.size(); j++) {
                if(listIntrebari.get(i).equals(listaIntrebariExamen.get(j)))
                    duplicat=true;
            }
            if (!duplicat) {
                listaIntrebariExamen.add(listIntrebari.get(i));
            }
        }
        listaIntrebariExamenOriginale = listaIntrebariExamen;
        model.addAttribute("listIntrebari",listaIntrebariExamen);
        index++;
        model.addAttribute("index",index);
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
        // Your code here to process the three boolean inputs
        Intrebare intrebareSubmited = listaIntrebariExamen.get(index);
        Intrebare intrebareOriginala =listaIntrebariExamenOriginale.get(index);

        Boolean var1 = varianta1 != null ? varianta1.booleanValue() : false;
        Boolean var2 = varianta2 != null ? varianta2.booleanValue() : false;
        Boolean var3 = varianta3 != null ? varianta3.booleanValue() : false;
        if (var1) {
            // checkbox was checked
            intrebareSubmited.setOptiuneaabool(true);
        } else {
            // checkbox was not checked
            intrebareSubmited.setOptiuneaabool(false);
        }
        if (var2) {
            // checkbox was checked
            intrebareSubmited.setOptiuneabbool(true);
        } else {
            // checkbox was not checked
            intrebareSubmited.setOptiuneabbool(false);
        }
        if (var3) {
            // checkbox was checked
            intrebareSubmited.setOptiuneacbool(true);
        } else {
            // checkbox was not checked
            intrebareSubmited.setOptiuneacbool(false);
        }
        if ((intrebareSubmited.isOptiuneaabool()== intrebareOriginala.isOptiuneaabool()) && (intrebareSubmited.isOptiuneabbool()== intrebareOriginala.isOptiuneabbool()) && (intrebareSubmited.isOptiuneacbool()== intrebareOriginala.isOptiuneacbool()))
            scor++;
        if (index >= 26) {
            return "redirect:/rezultat";
        }
        return "redirect:/simulatorExamen";
    }

    @GetMapping("/rezultat")
    public String showRezultat(Model model) {
        model.addAttribute("scor",scor);
        return "rezultat";
    }
    }
