package com.example.acreditareExamenAuto.chestionare;

import org.springframework.data.repository.CrudRepository;

public interface IntrebareRepository extends CrudRepository <Intrebare, Integer> {
        public long countById(Integer id);
}
