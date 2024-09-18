export function joinLoginId(){
    let $inputLoginId=document.querySelector('#loginId');

    let loginReg = /^[a-zA-Z0-9_]{4,20}$/g;
    let $errorMsg = document.querySelector('#loginId-msg');

    $inputLoginId.addEventListener('change', function (){
        // 정규표현식 검사
        let checkLoginId = this.value.match(loginReg);
        console.log(checkLoginId);

        if(checkLoginId != null){
            fetch('/join/check-loginId/${loginId}', {method:'POST'})
                .then(resp=> resp.text())
                .then(text => {
                    if (text != 0){
                        // 이미 존재하는 아이디
                        $errorMsg.innerHTML = "이미 존재하는 아이디입니다. ";
                        // console.log("class name = "+$inputMsg[0].getAttribute("class"));
                        msgColorRed(0);
                        // $successJoin[0] = false;
                    }else{
                        $errorMsg.innerHTML = "사용가능한 ID입니다. ";
                        // console.log("class name = "+$inputMsg[0].getAttribute("class"));
                        // $successJoin[0] = true;
                        msgColorBlue(0)
                    }
                })
        }else{
            $errorMsg.innerText = "4 ~ 20자의 영문, 숫자와 특수문자 '_'만 사용해주세요.";
            msgColorRed(0);
            // $successJoin[0] = false;
        }

    })

}