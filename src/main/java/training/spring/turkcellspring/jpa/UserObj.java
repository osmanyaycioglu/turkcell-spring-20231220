package training.spring.turkcellspring.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "security_user")
public class UserObj {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long   userId;
    @NotEmpty
    @NotBlank
    @Size(min = 6,max = 12)
    @Column(unique = true)
    private String username;
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ERole  role;
}
