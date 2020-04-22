package com.cheapflights.tickets.domain.dto;

import com.cheapflights.tickets.config.security.AuthorityConstants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserDTO {

    private Long id;

    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @NotNull
    @Size(min = 1, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 4, max = 128)
    private String password;

    @NotNull
    private AuthorityConstants role;

    public String toString() {
        return "UserDTO(id=" + this.getId() + ", username=" + this.getUsername() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", role=" + this.getRole() + ")";
    }
}