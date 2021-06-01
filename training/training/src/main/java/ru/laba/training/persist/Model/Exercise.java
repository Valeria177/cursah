package ru.laba.training.persist.Model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;



import javax.persistence.*;


@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID", nullable = false)
    private int ID;

    @Column(name = "description")
    private  String description;
    @Column(name = "countOFrepetishions")
    private  int countOFrepetishions;
    @Column(name = "href")
    private  String href;
    //@Column(name = "id_train")
    //private  int id_train;
    @ManyToOne(fetch = FetchType.EAGER  , cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_train", nullable = false)
    private Train train;

    public Exercise(int id, String description, int countOFrepetishions, String href, Train t) {
        setID(id);
        setDescription(description);
        setCountOFrepetishions(countOFrepetishions);
        setHref(href);
        setTrain(t);
    }

    public Exercise() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCountOFrepetishions() {
        return countOFrepetishions;
    }

    public void setCountOFrepetishions(int countOFrepetishions) {
        this.countOFrepetishions = countOFrepetishions;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
}
