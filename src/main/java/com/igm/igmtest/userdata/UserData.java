package com.igm.igmtest.userdata;

import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class UserData {
    private Long id;
    private String name;
    private String lastname;
    private String email;
}
