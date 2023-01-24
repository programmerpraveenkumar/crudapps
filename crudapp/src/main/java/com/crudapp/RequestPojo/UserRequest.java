package com.crudapp.RequestPojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {
    Long id;
    String name, address,email,mobile;
    LocalDate dob;
}
