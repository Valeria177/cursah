package ru.laba.training.persist.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.laba.training.persist.TrainProgram;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
    @Table(name="users")
    public class User implements UserDetails {

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



    @ManyToOne
    private Role role;


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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
            this.username = username;
        }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(role) ;
        return roles ;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
