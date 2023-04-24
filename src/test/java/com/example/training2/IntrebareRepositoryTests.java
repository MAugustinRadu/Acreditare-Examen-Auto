package com.example.training2;

import com.example.training2.chestionare.IntrebareRepository;
import com.example.training2.chestionare.Intrebare;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

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
        Assertions.assertEquals(0,(intrebari).s;
    }
}
