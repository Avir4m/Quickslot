package com.quickslot.website.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class userDTO {
    private int id;
    private String username;
    private String email;
    private String password;
}

