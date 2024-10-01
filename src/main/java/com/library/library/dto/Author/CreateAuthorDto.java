package com.library.library.dto.Author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateAuthorDto {

    @NotBlank(message = "Author name is mandatory!")
    @Pattern(regexp = "^[\\p{L}][\\p{L} ]*$", message = "User name should only contain alphabetic characters.")
    private String name;

    private String bio;

}
