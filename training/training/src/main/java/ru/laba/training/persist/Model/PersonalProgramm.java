package ru.laba.training.persist.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="personalprogramm")
public class PersonalProgramm {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", nullable = false)
    private  int ID;
    @ManyToOne
    private User user;
    @ManyToOne
    private Programm programm;
    @OneToMany(
            mappedBy="personalprogramm",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<PersonalTrain> personalTrains;



    public PersonalProgramm(int ID, User user, Programm programm, List<PersonalTrain> personalTrains  ) {
        this.ID = ID;
        this.user = user;
        this.programm = programm;
        this.personalTrains = personalTrains;
    }

    public PersonalProgramm() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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