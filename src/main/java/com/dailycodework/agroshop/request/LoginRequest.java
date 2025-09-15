package com.dailycodework.agroshop.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest{
    @NotBlank(message="Campo obrigatório")
    private String email;
    @NotBlank(message="Campo obrigatório")
    private String senha;
}

