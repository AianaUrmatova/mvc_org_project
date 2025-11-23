package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Instructor {
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

    @ManyToMany(mappedBy = "instructors")
    List<Course> courses = new ArrayList<>();

    public Instructor(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


}
