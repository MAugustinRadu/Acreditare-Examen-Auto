package com.example.acreditareExamenAuto;

import com.example.acreditareExamenAuto.chestionare.IntrebareRepository;
import com.example.acreditareExamenAuto.chestionare.Intrebare;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class IntrebareRepositoryTests {
    @Autowired
    private IntrebareRepository repo;

    @Test
    public void testAddNew() {
        Intrebare intrebaretest = new Intrebare();
        intrebaretest.setIntrebare("testing111111");
        intrebaretest.setCategoria("testing222222");
        intrebaretest.setOptiuneaa("testing333333");
        intrebaretest.setOptiuneab("testing444444");
        intrebaretest.setOptiuneac("testing555555");
        intrebaretest.setOptiuneaabool(false);
        intrebaretest.setOptiuneabbool(false);
        intrebaretest.setOptiuneacbool(false);

        Intrebare intrebareSalvata = repo.save(intrebaretest);
        Assertions.assertNotEquals(null,intrebareSalvata);
        Assertions.assertNotEquals(0,intrebareSalvata.getId());

    }
    @Test
    public void testListAll() {
        Iterable<Intrebare> intrebari = repo.findAll();
        for (Intrebare intrebare: intrebari) {
            System.out.println(intrebare);
        }
    }

    @Test
    public void testUpdate(){
        Integer intrebareId = 10;
        Optional<Intrebare> intrebareOptionala = repo.findById(intrebareId);
        Intrebare intrebare = intrebareOptionala.get();
        intrebare.setCategoria("testing");
        repo.save(intrebare);

        Intrebare intrebareUpdated = repo.findById(intrebareId).get();
        Assertions.assertTrue((intrebareUpdated.getCategoria()).equals("testing"));
    }

    @Test
    public void testGet(){
        Integer intrebareid = 10;
        Optional<Intrebare> intrebareOptional = repo.findById(intrebareid);
        Assertions.assertTrue((intrebareOptional).isPresent());
        System.out.println(intrebareOptional.get());
    }

    @Test
    public void testDelete(){
        Integer intrebareid = 10;
        repo.deleteById(intrebareid);

        Optional<Intrebare> intrebareOptional = repo.findById(intrebareid);
        Assertions.assertTrue((intrebareOptional).isEmpty());
    }
}
