package ru.laba.training.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.laba.training.persist.*;
import ru.laba.training.persist.Model.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainingController {

    private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

    private ProgrammRepository repository;

    private final UserRepository userRepository;

    private ITrainRepository trainRepository;

    private ExerciserRepository exerciserRepository;

    private PersonalProgrammRepository personalProgrammRepository;

    private PersonalTrainRepository personalTrainRepository;

    @Autowired
    public TrainingController(ProgrammRepository repository, UserRepository userRepository,
                              ITrainRepository trainRepository, ExerciserRepository exerciserRepository,
                              PersonalProgrammRepository personalProgrammRepository, PersonalTrainRepository personalTrainRepository) {
        this.repository=repository;
        this.userRepository = userRepository;
        this.trainRepository= trainRepository;
        this.exerciserRepository = exerciserRepository;
        this.personalProgrammRepository = personalProgrammRepository;
        this.personalTrainRepository = personalTrainRepository;
    }

    //PROGRAMMS
    @GetMapping
    public String indexPage(Model model, Principal principal) {
        logger.info("User name: {}",principal.getName());

        model.addAttribute("programms", repository.findAll());
        return "index";
    }

    /*@PostMapping
    public String newTrainProgram(TrainProgram trainProgram, Principal principal) {
        logger.info("User name: {}",principal.getName());

        User user = userRepository.findByUsername(principal.getName()).get();
        trainProgram.setUser(user);
        //repository.save(trainProgram);
        return "redirect:/";
    }*/



    @GetMapping("/{id}/details")
    public String Detailprogramm(@PathVariable("id") int id, Model model){
        model.addAttribute("programm",repository.getOne(id));
        return "Detailprogramm";
    }

    @GetMapping("/create")
    public String createProgramm(Model model){
        model.addAttribute("programm", new Programm());
        return "CreateProgramm";
    }

    @PostMapping("/createProgramm")
    public String createProgramm(@ModelAttribute("programm") Programm programm, Principal principal){
        programm.setCountOFsubscribers(0);  //TO BL
        programm.setUser(userRepository.findByUsername(principal.getName()).get());
        repository.save(programm);
        return "redirect:/";
    }


    //Personal programms
    @PostMapping ("/{id}/subscribe")
    public String SubTOprogramm(@PathVariable("id") int id, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        for (PersonalProgramm p:
                user.getPersonalProgramms()) {
            if(p.getProgramm().getID()==id)
                return "redirect:/myprgogramms";
        }

        PersonalProgramm personalProgramm = new PersonalProgramm();
        Programm programm = repository.getOne(id);
        personalProgramm.setProgramm(programm);
        programm.setCountOFsubscribers(programm.getCountOFsubscribers()+1); // TO BL
        personalProgramm.setUser(user);
        personalProgramm.setPersonalTrains(new ArrayList<PersonalTrain>());
        for (Train t:
                programm.getTrains()) {
            PersonalTrain personalTrain = new PersonalTrain();
            personalTrain.setTrain(t);
            personalTrain.setStatus("unstarted");
            personalTrain.setPersonalprogramm(personalProgramm);
            personalProgramm.getPersonalTrains().add(personalTrain);
            personalProgrammRepository.save(personalProgramm);
            personalTrainRepository.save(personalTrain);
        }
        //personalProgrammRepository.save(personalProgramm);
        repository.save(programm);
        return "redirect:/myprgogramms";
    }

    @PostMapping ("/{id}/unsubscribe")// --count
    public String UnSubTOprogramm(@PathVariable("id") int id, Principal principal){
        Programm p = personalProgrammRepository.getOne(id).getProgramm();
        p.setCountOFsubscribers(p.getCountOFsubscribers()-1);
        repository.save(p);
        personalProgrammRepository.deleteById(id);
        return "redirect:/myprgogramms";
    }

    @GetMapping("/myprgogramms")
    public String PersonalProgramms(Model model, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).get();
        List<PersonalProgramm> personalProgramms = user.getPersonalProgramms();
        model.addAttribute("programms", personalProgramms);
        return "PersonalProgramms";
    }

    //TRAINS
    @GetMapping("/{id}")
    public String TrainsOFprogramm(@PathVariable("id") int id, Model model){
        model.addAttribute("trains", repository.getOne(id).getTrains());
        model.addAttribute("id_prog", id);
        return "Train";
    }

    @GetMapping("/{id_program}/create")
    public String createTrain(@PathVariable("id_program") int id_program, Model model){
        model.addAttribute("train", new Train());
        model.addAttribute("id_program", id_program);
        return "CreateTrain";
    }

    @PostMapping("/createTrain")
    public String createTrain(@ModelAttribute("train") Train train, @ModelAttribute("id_program") int id_program){
        train.setProgramm(repository.getOne(id_program));
        trainRepository.save(train);
        return "redirect:/" + id_program;
    }


    //PersonalTrains
    @GetMapping("/{id}/mytrains")
    public String PERSONALTrainsOFprogramm(@PathVariable("id") int id, Model model){
        model.addAttribute("trains", personalProgrammRepository.getOne(id).getPersonalTrains());
        model.addAttribute("id_prog", id);
        return "PersonalTrains";
    }

    @GetMapping("/{idP}/{idT}/start")
    public String startTrain(@PathVariable("idP") int idP, @PathVariable("idT") int idT, Model model) {
        PersonalTrain personalTrain = personalTrainRepository.getOne(idT);
        personalTrain.setStatus("In process");
        personalTrainRepository.save(personalTrain);
        model.addAttribute("exercises", personalTrain.getTrain().getExercises());
        model.addAttribute("idP", idP);
        model.addAttribute("idT",idT);
        return  "StartedTrain";
    }

    @GetMapping("/{idP}/{idT}/finish")
    public String finishTrain(@PathVariable("idP") int idP, @PathVariable("idT") int idT, Model model) {
        PersonalTrain personalTrain = personalTrainRepository.getOne(idT);
        personalTrain.setStatus("finished");
        personalTrainRepository.save(personalTrain);
        return "redirect:/" +idP  + "/"+ "mytrains";
    }
        //EXERCISES
    @GetMapping("/{id_programm}/{id_train}")
    public String ExercisesOFTrain(@PathVariable("id_programm") int id_programm, @PathVariable("id_train") int id_train, Model model){
        model.addAttribute("exercises",trainRepository.getOne(id_train).getExercises());
        model.addAttribute("idP", id_programm);
        model.addAttribute("idT", id_train);
        return "Exercises";
    }



    @GetMapping("/{idP}/{idT}/create")
    public String createTrain(@PathVariable("idP") int id_programm, @PathVariable("idT") int id_train, Model model){
        model.addAttribute("exercise", new Exercise());
        model.addAttribute("idP", id_programm);
        model.addAttribute("idT", id_train);
        return "CreateExercise";
    }

    @PostMapping("/createExercise")
    public String createEsercise(@ModelAttribute("exercise") Exercise exercise, @ModelAttribute("idP") int idP , @ModelAttribute("idT") int idT){
        exercise.setTrain(trainRepository.getOne(idT));
        exerciserRepository.save(exercise);
        return "redirect:/" + idP + "/" + idT;
    }

}
