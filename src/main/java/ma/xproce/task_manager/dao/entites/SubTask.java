package ma.xproce.task_manager.dao.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "subtasks")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private Date creationDate;
    private Date lastUpdateDate;
    private boolean isCompleted;
    private Date completionDate;

    private String priorityLevel;



    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
