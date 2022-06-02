package _04.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "diagnoses")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 30)
    private String name;

    @Column(length = 100)
    private String comments;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<Patient> patients;

public  Diagnose() {}

    public Diagnose(String name, String comments) {
        this.name = name;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

