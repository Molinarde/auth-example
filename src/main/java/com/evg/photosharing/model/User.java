package com.evg.photosharing.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Document(collection = "user")
@Data
public class User {

    @Id
    private String id;
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 10, max = 50)
    private String password;
    @NotEmpty
    private Set<Role> role;

}
