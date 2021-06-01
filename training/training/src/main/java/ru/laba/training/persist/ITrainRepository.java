package ru.laba.training.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.laba.training.persist.Model.Train;

public interface ITrainRepository extends JpaRepository<Train, Integer> {
    //List<Programm> findByUserUsername(String username);
}
