package ma.xproce.task_manager.dao.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private Date creationDate;
    private Date lastUpdateDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Deadline;
    private boolean isDeadlinePassed;

    private boolean isCompleted;
    private Date completionDate;

    private String priorityLevel;



    @ManyToOne
    @JoinColumn(name = "task_list_id")//, nullable = false
    private TaskList taskList;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<SubTask> subtasks;




}
