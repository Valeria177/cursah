package ru.laba.training.persist.Model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="programm")
public class Programm {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", nullable = false)
    private  int ID;
    @Column(name = "name")
    private String name;
    @Column (name = "description")
    private String description;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "type")
    private String type;
    @Column(name = "countOFsubscribers")
    private int countOFsubscribers;
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "programm")
    private List<Train> trains;
    @ManyToOne
    private User user;

    @OneToMany(
            mappedBy="user",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<PersonalProgramm> personalProgramms;

    public Programm(int id, String name, String description, String purpose, String type, int countOFsubscribers, List<Train> trains, User user) {
        setID(id);
        setName(name);
        setDescription(description);
        setPurpose(purpose);
        setType(type);
        setCountOFsubscribers(countOFsubscribers);
        setTrains(trains);
        setUser(user);
    }

    public Programm() {
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCountOFsubscribers() {
        return countOFsubscribers;
    }

    public void setCountOFsubscribers(int countOFsubscribers) {
        this.countOFsubscribers = countOFsubscribers;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PersonalProgramm> getPersonalProgramms() {
        return personalProgramms;
    }

    public void setPersonalProgramms(List<PersonalProgramm> personalProgramms) {
        this.personalProgramms = personalProgramms;
    }
}