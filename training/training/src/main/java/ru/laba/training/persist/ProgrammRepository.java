package ru.laba.training.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.laba.training.persist.Model.Programm;

import java.util.List;

@Repository
public interface ProgrammRepository extends JpaRepository<Programm, Integer> {
    //List<Programm> findByUserUsername(String username);
}
