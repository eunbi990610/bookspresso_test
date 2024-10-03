package com.example.bookspresso.controller.admin;

import com.example.bookspresso.dto.admin.page.AdminPageRequestDTO;
import com.example.bookspresso.dto.admin.page.AdminPageSetDTO;
import com.example.bookspresso.dto.admin.qa.ManageQuestionListDTO;
import com.example.bookspresso.dto.admin.qa.ModifyAnswer;
import com.example.bookspresso.dto.admin.qa.QuestionAnswerDTO;
import com.example.bookspresso.dto.question.board.QuestionDetailDTO;
import com.example.bookspresso.service.admin.ManageQuestionService;
import com.example.bookspresso.service.question.QuestionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.jar.Attributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/question")
public class QandAController {

    private final ManageQuestionService manageQuestionService;
    private final QuestionService questionService;

    @GetMapping("/list")
    public String question(AdminPageRequestDTO adminPageRequestDTO,
                           Model model){

        List<ManageQuestionListDTO> list = manageQuestionService.findQuestionList(adminPageRequestDTO);
        int total = manageQuestionService.findQuestionCount();

        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);

        model.addAttribute("total",total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO",adminPageSetDTO);

        return "admin/qa/question";
    }

    @GetMapping("/finishList")
    public String finishList(AdminPageRequestDTO adminPageRequestDTO,
                             Model model){
        List<ManageQuestionListDTO> list = manageQuestionService.findFinishQuestionList(adminPageRequestDTO);
        int total = manageQuestionService.findFinishQuestionCount();

        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);

        model.addAttribute("total",total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO",adminPageSetDTO);
        return "admin/qa/finishList";
    }

    @GetMapping("/popup")
    public String answer(Long qBoardId, Model model, HttpSession session){
//        return "redirect:/qa/detail";
        QuestionDetailDTO list = questionService.findQuestion(qBoardId);
        System.out.println("findList###### = " + list);
        model.addAttribute("list", list);

        Long adminId = (Long)session.getAttribute("adminId");
        System.out.println("###adminId = " + adminId);
        return "admin/qa/toAnswer";
    }

    @PostMapping("/popup")
    public String answer(QuestionAnswerDTO questionAnswerDTO,
                        HttpSession session){

        questionAnswerDTO.setAdminId((Long)session.getAttribute("adminId"));
        manageQuestionService.addAnswer(questionAnswerDTO);
        System.out.println("@@@adminId =" + questionAnswerDTO.getAdminId());


        return "redirect:/admin/question/list";
    }

    @GetMapping("/modify")
    public String modify(Long qBoardId, Model model){

        QuestionDetailDTO list = questionService.findQuestion(qBoardId);
        model.addAttribute("list", list);

        return "admin/qa/modifyAnswer";
    }
//
    @PostMapping("/modify")
    public String modify(QuestionAnswerDTO questionAnswerDTO,
                         HttpSession session,
                         RedirectAttributes redirectAttributes){

        questionAnswerDTO.setAdminId((Long)session.getAttribute("adminId"));
        manageQuestionService.updateAnswerContent(questionAnswerDTO);

        redirectAttributes.addAttribute("qBoardId", questionAnswerDTO.getQBoardId());

        return "redirect:/admin/question/modify";
    }



}
