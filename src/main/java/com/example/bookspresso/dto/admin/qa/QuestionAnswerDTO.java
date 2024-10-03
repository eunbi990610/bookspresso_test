package com.example.bookspresso.dto.admin.qa;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionAnswerDTO {

    Long answerId;
    Long qBoardId;
    Long adminId;
    String aContent;
    String createdDate;
    String answerModifiedDate;


}
