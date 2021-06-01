package ru.laba.training.persist.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="personaltrain")
public class PersonalTrain {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", nullable = false)
    private  int ID;
    @Column(name = "status")
    private String status;
    @ManyToOne
    private Train train;
    @ManyToOne
    private PersonalProgramm personalprogramm;


    public PersonalTrain(int ID, Train train, PersonalProgramm personalProgramm, String status) {
        this.ID = ID;
        this.train = train;
        this.personalprogramm = personalProgramm;
        this.status = status;
    }

    public PersonalTrain() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public PersonalProgramm getPersonalprogramm() {
        return personalprogramm;
    }

    public void setPersonalprogramm(PersonalProgramm personalprogramm) {
        this.personalprogramm = personalprogramm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}