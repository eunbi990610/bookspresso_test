package com.example.bookspresso.api.member;

import com.example.bookspresso.service.admin.ManageMemberService;
import com.example.bookspresso.service.member.MailService;
import com.example.bookspresso.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {

    private final MemberService memberService;
    private final MailService mailService;
    private final ManageMemberService manageMemberService;

    // 회원가입
    @PostMapping("/join/check-loginId/{loginId}")
    public int checkLoginId(@PathVariable("loginId") String loginId){
        int loginIdCount = memberService.findLoginId(loginId);
        System.out.println("loginIdCount =@@@@=" + loginIdCount);
        return loginIdCount;

    }

    @GetMapping(value = "/join/check-nickname")
    public int checkNickname(String nickname){
        return memberService.findNickname(nickname);
    }

    @PostMapping("/join/check-email/{emailValue}")
    public int checkEmail(@PathVariable("emailValue") String emailValue){
        return memberService.findEmail(emailValue);
        // 1이면 존재 , 0 이면 아무도 사용하고 있지 않음
    }


    //로그인
    @PostMapping("/login/findMemberId/{loginId}/{password}")
    public boolean findMemberId(@PathVariable("loginId") String loginId,
                             @PathVariable("password") String password){

        try {
            memberService.findMemberId(loginId, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    //아이디 찾기
    @PostMapping("/find/email/{email}")
    public String emailCertifiedNumber(@PathVariable("email") String email){

//        System.out.println("emain@@" + email);
        int findEmail = memberService.findEmail(email);
//        System.out.println("findEmail = " + findEmail);

        if(findEmail == 1){
            String authNumber = mailService.sendEmail(email);
//            System.out.println("이메일 인증번호 발송 성공!");
            return authNumber;
        }else{
            new Exception().printStackTrace();
            return null;
        }
    }

    //비밀번호 찾기
    @PostMapping("/find/loginId/{email}")
    public String emailLoginIdEmail(@PathVariable("email") String email){
        try {
            return memberService.findLoginIdEmail(email);
        } catch (Exception e) {
            return null;
        }
    }

    // 비밀번호 변경
    @PostMapping("/update/password/{loginId}/{newPw}")
    public boolean updatePassword(@PathVariable("loginId") String loginId,
                               @PathVariable("newPw") String newPw){

        try {
            memberService.modifyPassword(loginId, newPw);
            System.out.println("loginId = " + loginId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
