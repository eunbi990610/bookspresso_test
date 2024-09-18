package com.example.bookspresso.mapper.admin;

import com.example.bookspresso.dto.admin.page.AdminPageRequestDTO;
import com.example.bookspresso.dto.admin.qa.ManageQuestionListDTO;
import com.example.bookspresso.dto.admin.qa.ModifyAnswer;
import com.example.bookspresso.dto.admin.qa.QuestionAnswerDTO;
import com.example.bookspresso.dto.question.board.QuestionDetailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionManageMapperTest {

    @Autowired QuestionManageMapper questionManageMapper;
    AdminPageRequestDTO adminPageRequestDTO;
    QuestionAnswerDTO questionAnswerDTO;
    QuestionDetailDTO questionDetailDTO;

    @BeforeEach
    void setUp() {
        adminPageRequestDTO = new AdminPageRequestDTO();

        questionAnswerDTO = QuestionAnswerDTO.builder()
                .adminId(1L)
                .qBoardId(103L)
                .aContent("test 답변입니다!! ################")
                .build();
    }

    @Test
    void selectQuestionList() {
        questionManageMapper.selectQuestionList(adminPageRequestDTO);
    }

    @Test
    void selectQuestionCount() {
        questionManageMapper.selectQuestionCount();

    }

    @Test
    void selectFinishQuestionList(){
        List<ManageQuestionListDTO> manageQuestionListDTOS = questionManageMapper.selectFinishQuestionList(adminPageRequestDTO);
        int count = questionManageMapper.selectFinishQuestionCount();
        System.out.println("manageQuestionListDTOS: " + manageQuestionListDTOS);
        System.out.println("count = " + count);

    }

    @Test
    void insertAnswer(){
        questionManageMapper.insertAnswer(questionAnswerDTO);

        System.out.println("insert $$ = " + questionAnswerDTO);

    }





//    @Test
//    void insertAnswer(){
//        questionManageMapper.insertAnswer(questionAnswerDTO);
//
//        System.out.println(" ### dto = " + questionAnswerDTO);
//
//    }

    @Test
    void updateAnswerStatus(){
        questionManageMapper.updateAnswerStatus(37L);
        System.out.println("$$$$="+ questionDetailDTO.isAStatus());

    }
    @Test
    void updateAnswer(){

        questionManageMapper.updateAnswer(questionAnswerDTO);

        System.out.println(" ### dto = " + questionAnswerDTO);


    }
}