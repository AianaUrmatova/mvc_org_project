package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "lessons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate publisherDate;
    String description;
    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    public Lesson(String title, LocalDate publisherDate, String description) {
        this.title = title;
        this.publisherDate = publisherDate;
        this.description = description;
    }
}