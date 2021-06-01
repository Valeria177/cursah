package ru.laba.training.persist.Model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", nullable = false)
    private  int ID;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "muscleGroup")
    private String muscleGroup;
    @Column(name = "purpose")
    private String purpose;
   // @Column(name = "id_programm")
  //  private int id_programm;
    @ManyToOne(fetch = FetchType.LAZY   , cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_programm", nullable = false)
    private Programm programm;
    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "train")
    private List<Exercise> exercises;
    @OneToMany(
            mappedBy="train",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<PersonalTrain> personalTrains;


    public Train(int id, String name, String description, String muscleGroup, String purpose, Programm p, List<Exercise> exercises, List<PersonalTrain> personalTrains) {
        setID(id);
        setName(name);
        setDescription(description);
        setMuscleGroup(muscleGroup);
        setPurpose(purpose);
        setExercises(exercises);
        setProgramm(p);
        setPersonalTrains(personalTrains);
    }

    public Train() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }


    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Programm getProgramm() {
        return programm;
    }

    public void setProgramm(Programm programm) {
        this.programm = programm;
    }

    public List<PersonalTrain> getPersonalTrains() {
        return personalTrains;
    }

    public void setPersonalTrains(List<PersonalTrain> personalTrains) {
        this.personalTrains = personalTrains;
    }
}

