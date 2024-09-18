package com.example.bookspresso.dto.admin.admin;

import lombok.*;

import javax.print.DocFlavor;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDTO {

    private Long adminId;
    private String name;
    private String adminLoginId;
    private String adminPassword;
    private String email;
    private boolean status;
    private String createdDate;
    private String approvalDate;

}
