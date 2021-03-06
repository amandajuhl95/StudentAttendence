package models;

import lombok.Data;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name="Student.findAll", query="SELECT s FROM Student s"),
        @NamedQuery(name="Student.deleteAll", query="DELETE FROM Student s")
})
public class Student {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String phonenumber;
    private String address;
    private String city;
    private String zipcode;
    private String birthdate;

    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;

    @Transient
    private SimpleDateFormat formatter;

    public Student(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.subjects = new ArrayList();
    }

    public Student(String name, String email, String phonenumber, String address, String city, String zipcode, Date birthdate) {
        this.formatter = new SimpleDateFormat("dd.MM.yyyy");

        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.city = city;
        this.zipcode = zipcode;
        this.birthdate = this.formatter.format(birthdate);
    }

    public Student() {
    }

    public void addSubject(Subject subject) {
        if(!this.subjects.contains(subject)) {
            this.subjects.add(subject);
            subject.addStudent(this);
        }

    }



}
