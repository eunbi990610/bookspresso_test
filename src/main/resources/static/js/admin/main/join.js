let $InputBox = document.querySelectorAll('.typoBox');
let $inputMsg = document.querySelectorAll('.inputMsg');
let $joinForm = document.querySelector("#join-form");
let $joinBtn = document.querySelector("#join-btn");

let $checkLoignId = document.getElementsByName("loginId");
let $successJoin = [];
/**
 * 아이디 중복 검사 후 에러 메세지 출력
 * 정규표현식 테스트 _> 아이디 이상함 
  */
{
    // let $inputLoginId = $InputBox[0];
        //유효성 검사
    let loginReg = /^[a-zA-Z0-9_]{4,20}$/g;

    printBasicMsg(0)

    $InputBox[0].addEventListener('change', function (){
        //정규표현식 검사
        let loginId = this.value.match(loginReg);
        // console.log(this.value);
        // console.log(loginId);

        if (loginId != null){
            fetch(`/join/check-loginId/${loginId}`, {method: 'POST'})
                .then(resp=> resp.text())
                .then(text => {
                    if (text != 0){
                        // 이미 존재하는 아이디
                        $inputMsg[0].innerHTML = "이미 존재하는 아이디입니다. ";
                        // console.log("class name = "+$inputMsg[0].getAttribute("class"));
                        msgColorRed(0);
                        $successJoin[0] = false;
                    }else{
                        $inputMsg[0].innerHTML = "사용가능한 ID입니다. ";
                        // console.log("class name = "+$inputMsg[0].getAttribute("class"));
                        $successJoin[0] = true;
                        msgColorBlue(0)
                    }
                })
        }else{
            $inputMsg[0].innerText = "4 ~ 20자의 영문, 숫자와 특수문자 '_'만 사용해주세요.";
            msgColorRed(0);
            $successJoin[0] = false;
        }



    })

}
/**
 * 비밀번호 유효성 검사
 * 8-16 자리 영문, 숫자, 특수문자 조합
 */
{
    printBasicMsg(1);
    let pwReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/

    $InputBox[1].addEventListener('change', function () {
        let pw = this.value;

        console.log(pwReg.test(pw));
        if (pwReg.test(pw)){
            $inputMsg[1].innerText= "사용 가능한 비밀번호예요!";
            msgColorBlue(1);
            $successJoin[1] = true;
        }
        else{
            // console.dir($inputMsg[1])
            $inputMsg[1].innerText =document.querySelector("#passwordInvalid").innerText;
            // console.log("sfsf#### = " + $inputMsg[1].innerText);
            msgColorRed(1);
            $successJoin[1] = false;
        }
    })

    icon.addEventListener('click', function (){
        let currentType = $InputBox[1].getAttribute('type');
        if (currentType === 'password'){
            $InputBox[1].setAttribute('type','text');
            // 아이콘 눈 감고 있는 걸로 변경 .textContent
        }else{
            $InputBox[1].setAttribute('type','password');
            // 아이콘 눈 뜨고 있는 걸로 변경 .textContent

        }
    })



}

/**
 *  닉네임 중복 검사 후 에러 메세지
 */
{
    printBasicMsg(2);
    $InputBox[2].addEventListener('change', function(){
        let name = this.value;
        // console.log("nickname = " + nickname);

        // if (name != null){
        //     $successJoin[2] = true;
        // }else{
        //     $successJoin[2] = false;
        // }
        name!=null?$successJoin[2] = true:$successJoin[2] = false;
    })
}

/**
 *  이메일 유효성 검사 (정규표현식)
 */
{
    printBasicMsg(3);
    let emailReg = /^[A-Z0-9._]+@[A-Z0-9.-]+\.[A-Z]{2,3}$/i;

    $InputBox[3].addEventListener('change', function (){

        let emailValue = this.value.match(emailReg);
        console.log(this.value);
        console.log(emailValue);

        if (emailValue != null){
            fetch(`/join/check-email/${emailValue}`, {method:'POST'})
                .then(resp=>resp.text())
                .then(result =>{
                    if (result != 0) {
                        $inputMsg[3].innerHTML = "이미 존재하는 회원입니다.";
                        msgColorRed(3);
                        $successJoin[3] = false;
                    }else{
                        $successJoin[3] = true;
                        msgColorBlue(3);
                        console.log("이메일 입력 성공! == " + $successJoin);
                        $inputMsg[3].innerHTML = "";
                    }
                })
        }else{
            $inputMsg[3].innerHTML="잘못된 이메일 주소입니다. 이메일 주소를 정확하게 입력해주세요.";
            console.log($inputMsg[3].innerHTML);
            msgColorRed(3);
            $successJoin[3] = false;
        }
    })
}



// 안내 문자
// 아이디, 비밀번호
/**
 * 회원가입 정보 작성 데이터 중 아이디와 비밀번호를 입력하기 위해 input창을 클릭 시 자동으로 나오는
 * 아이디와 비밀번호의 작성 기준의 대한 메세지 출력 코드
 */

// 함수로 만들어서  개별 칸에 사용
// {
//     for (let i = 0; i < $InputBox.length; i++) {
//
//         $InputBox[i].addEventListener('focus', function (){
//             let inputName = this.getAttribute("name");
//             console.log("inputName ::: "+inputName);
//
//             let className = $inputMsg[i].getAttribute("class");
//             console.log("class = " + className);
//
//             let basicMsg = document.getElementById(inputName+"Msg").innerText;
//             console.log('basicMsg',basicMsg);
//
//             $inputMsg[].innerHTML = basicMsg;
//
//             if(className == "inputMsg basicMsg error" || className == "inputMsg error"){
//                 $inputMsg[i].classList.remove("error");
//                 console.log("class = " + className);
//             }
//         })
//     }}

 // 함수
// input 칸에 blur일 때 나오는 기본 에러메서지 출력 함수
    function printBasicMsg(index){
        $InputBox[index].addEventListener('blur', function (){
            let inputName = this.getAttribute("name");
            console.log("focusOut ::: "+inputName);
            if (!this.value){
                let errorMsg = document.getElementById(inputName+"Invalid").innerHTML;
                console.log('errorMsg', errorMsg);
                msgColorRed(index);
                $inputMsg[index].innerHTML = errorMsg;
                $successJoin[index] = false;
            }
        })
    }



    function msgColorBlue(index){
        let inputClass = $inputMsg[index].getAttribute("class");
        if(inputClass.includes('error')){
            $inputMsg[index].classList.replace("error", "pass");
            console.log("class = " + inputClass);
            // $inputMsg[i].
        }else{
            $inputMsg[index].classList.add("pass");
            console.log("class = " + inputClass);
        }
    }
// 색 이 없거나 빨간 파랑
    function msgColorRed(index){
        let $inputMsgBox = $inputMsg[index];
        let inputClass = $inputMsgBox.getAttribute("class");
        if(inputClass.includes('pass')){
            $inputMsgBox.classList.replace('pass', 'error');
            console.log("class = " + inputClass);
        }else{
            $inputMsg[index].classList.add("error");
            console.log("class = " + inputClass);
        }
    }




/**
 * 모든 input 에 데이터를 입력해야만 submit 버튼이 활성화 되게 하는 코드
 */
{


     $joinBtn.addEventListener("click", function (){
         let joinResult = $successJoin.every(result => result==true);

         console.log("성공여부 = " + joinResult);

         if (joinResult){
             $joinForm.submit()
         }
     })
}






















