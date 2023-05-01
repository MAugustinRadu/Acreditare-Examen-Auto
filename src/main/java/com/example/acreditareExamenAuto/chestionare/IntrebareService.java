package com.example.acreditareExamenAuto.chestionare;

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

    public Intrebare get(Integer id) throws IntrebareNotFoundException {
        Optional<Intrebare> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new IntrebareNotFoundException("Nu s-a putut gasi intrebarea cu ID:" + id);
    }

    public void delete(Integer id) throws IntrebareNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new IntrebareNotFoundException("Nu s-a putut gasi intrebarea cu ID:" + id);
        }
        repo.deleteById(id);
    }
}
