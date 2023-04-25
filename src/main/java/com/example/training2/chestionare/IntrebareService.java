package com.example.training2.chestionare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntrebareService {
    @Autowired private IntrebareRepository repo;

    public List<Intrebare> listAll() {
        return (List<Intrebare>) repo.findAll();
    }

    public void save(Intrebare intrebare) {
        repo.save(intrebare);
    }
}
