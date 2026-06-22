package org.ats.dto;

import lombok.*;

@NoArgsConstructor@AllArgsConstructor
@Builder
@Setter@Getter
public class UserRequest {

    private String email;
    private String passwordHash;

}
