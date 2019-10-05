package ru.marsel.flat_rent.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.marsel.flat_rent.domain.Views;

@Data
@AllArgsConstructor
@JsonView(Views.UserInfo.class)
public class UserDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
}
