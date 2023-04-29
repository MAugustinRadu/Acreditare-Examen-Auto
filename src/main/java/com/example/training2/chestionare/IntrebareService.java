package com.example.training2.chestionare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IntrebareService {
    @Autowired private IntrebareRepository repo;

    public List<Intrebare> listAll() {
        return (List<Intrebare>) repo.findAll();
    }

    public void save(Intrebare intrebare) {
        Intrebare intrebareDeSalvat = intrebare;
        repo.save(intrebareDeSalvat);
    }

    public int find(Intrebare intrebare) {
        List<Intrebare> intrebari = listAll();
        int index = intrebari.indexOf(intrebare);
        return index;
    }
}
