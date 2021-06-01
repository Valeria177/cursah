package ru.laba.training.persist.Model;

import ru.laba.training.persist.TrainProgram;

import javax.persistence.*;
import java.util.List;

@Entity
    @Table(name="users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true,nullable = false)
        private String username;

        @Column(nullable = false)
        private String password;

    @OneToMany(
            mappedBy="user",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<Programm> programms;

    @OneToMany(
            mappedBy="user",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<PersonalProgramm> personalProgramms;


        public User() {
        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<Programm> getProgramms() {
            return programms;
        }

        public void setProgramms(List<Programm> programms) {
            this.programms = programms;
        }

        public List<PersonalProgramm> getPersonalProgramms() {
            return personalProgramms;
        }

        public void setPersonalProgramms(List<PersonalProgramm> personalProgramms) {
            this.personalProgramms = personalProgramms;
        }
}
