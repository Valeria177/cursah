package ru.laba.training.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.laba.training.persist.Model.Exercise;

public interface ExerciserRepository extends JpaRepository<Exercise, Integer> {
    //List<Programm> findByUserUsername(String username);
}
