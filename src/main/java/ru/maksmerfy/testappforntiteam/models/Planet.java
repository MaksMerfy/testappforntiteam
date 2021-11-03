package ru.maksmerfy.testappforntiteam.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "planets")
@Setter
@Getter
public class Planet {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;
    private String name;
    @ManyToOne
    private Overlord overlord;
}
