package com.example.elearningplatform.user.user.dto;

import java.time.LocalDateTime;

import com.example.elearningplatform.user.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;
    private String imageUrl;

    private String phoneNumber;

    private Boolean enabled;

    private LocalDateTime registrationDate;
    private String about;

    private LocalDateTime lastLogin;

    private Integer age;

    // private AddressDto address;

    public ProfileDto(User user) {

        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.imageUrl = "https://via.placeholder.com/300x150";
        this.phoneNumber = user.getPhoneNumber();
        this.enabled = user.isEnabled();
        this.registrationDate = user.getRegistrationDate();
        this.about = user.getAbout();
        this.lastLogin = user.getLastLogin();
        this.age = user.getAge();
        // this.address = new AddressDto(user.getAddress());

    }

}