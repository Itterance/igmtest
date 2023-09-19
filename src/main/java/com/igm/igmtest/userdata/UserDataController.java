package com.igm.igmtest.userdata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/userdata")

public class UserDataController {

    @GetMapping
    public List<UserData> getAllData() {
        List<UserData> usersData = Arrays.asList(
                new UserData(
                        1L,
                        "Jhon",
                        "Doe",
                        "jdoe@igmtech.com"),
                new UserData(
                        2L,
                        "Eve",
                        "Smith",
                        "esmith@igmtech.com")

        );
        return usersData;
    }
}
