package com.example.bookspresso.dto.admin.admin;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaitingAdminDTO {

    private Long adminId;
    private String name;
    private String adminLoginId;
    private String email;
    private boolean status;
    private String createdDate;

}
