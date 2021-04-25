package am.itspace.taskmaster.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name is Required")
    private String name;
    @NotEmpty(message = "Surname is Required")
    private String surname;
    @NotEmpty(message = "Email can not be null")
    @Email(message = "Email is not valid")
    private String email;
    @Size(min = 4, message = "Password should be between 4 ")
    private String password;
    @Enumerated(EnumType.STRING)
    private Type type = Type.EMPLOYER;

    @ManyToMany
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id")
    )
    List<Task> tasks;

}
