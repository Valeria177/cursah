package ru.laba.training.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.laba.training.persist.Model.PersonalProgramm;
import ru.laba.training.persist.Model.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PersonalProgrammRepository extends JpaRepository<PersonalProgramm, Integer> {

}