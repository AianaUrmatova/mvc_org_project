package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    @Column(nullable = false)
    String email;
    @Column(
            name = "phone_number",
            nullable = false,
            length = 13,
            columnDefinition = "varchar(13) check (phone_number LIKE '+996%' AND length(phone_number) = 13)"
    )
    String phoneNumber;

    @ManyToMany(mappedBy = "students")
    List<Course> courses = new ArrayList<>();

    public Student(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}