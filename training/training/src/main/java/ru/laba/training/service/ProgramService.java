package ru.laba.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.laba.training.persist.Model.Programm;
import ru.laba.training.persist.ProgrammRepository;

import java.util.List;

public class ProgramService implements IProgramService{

    @Autowired
    private ProgrammRepository programmRepository;
    @Override
    public List<Programm> getAll() {
        return programmRepository.findAll();
    }
}
