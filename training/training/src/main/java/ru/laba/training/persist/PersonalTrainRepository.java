package ru.laba.training.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.laba.training.persist.Model.PersonalProgramm;
import ru.laba.training.persist.Model.PersonalTrain;

public interface PersonalTrainRepository extends JpaRepository<PersonalTrain, Integer> {

}