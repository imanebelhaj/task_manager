package ma.xproce.task_manager.dao.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
    private Date creationDate;
    private Date lastUpdateDate;
    private String imageUrl;
    //profile picture


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskList> taskLists = new ArrayList<>();


}
