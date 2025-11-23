package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfStart;
    String description;
    String imageUrl;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    Set<Lession> lessons = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    Set<Student> students = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "course_instructor",
            joinColumns = @JoinColumn (name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    Set<Instructor> instructors = new HashSet<>();

    public Course(String title, LocalDate dateOfStart, String description, String imageUrl) {
        this.title = title;
        this.dateOfStart = dateOfStart;
        this.description = description;
        this.imageUrl = imageUrl;
    }

}
