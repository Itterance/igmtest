package com.igm.igmtest.endpoint_3a;

import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class UserData {
    private Integer id;
    private String name;
    private String email;
}
