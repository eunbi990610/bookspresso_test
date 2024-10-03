let $idForm = document.querySelector('#idForm');
let $pwForm =document.querySelector('#pwForm');
let loginBtn = document.querySelector('#loginBtn');
let $loginForm = document.querySelector('#login-form');
{

    loginBtn.addEventListener('click', function () {
        let loginId = $idForm.value;
        let password = $pwForm.value;
        // 단순 값이 들어오면 - 값이 널이 아니라면 submit 실행
        console.log("id : " + $idForm.value);
        console.log("pw : " + $pwForm.value);

        fetch(`/login/findAdminId/${loginId}/${password}`, {method: 'POST'})
            .then(resp => resp.text())
            .then(text => {
                if (text=='true') {
                    console.log("존재하는 회원!!!");
                    fetch(`/login/check/${loginId}`, {method:'POST'})
                        .then(resp=> resp.text())
                        .then(text => {
                            if (text=='true') {
                                console.log('관리자 확인 성공');
                                $loginForm.submit();
                            } else {
                                console.log("관리자 확인 실패 ");
                                failedLoginModal("관리자 권한이 필요합니다. 승인을 기다려주세요.");
                            }
                        })
                    // $loginForm.submit();
                } else {
                    console.log("로그인 실패!!!");
                    failedLoginModal("아이디 또는 비밀번호가 일치하지 않습니다.");
                }
            })


    })


// 로그인 실패 모달 창 실행 함수
    {
        function failedLoginModal(msg) {
            let $closeBtn = document.querySelector('#close-modal_btn');
            let $failModal = document.querySelector('.modal-layered');
            let $failMsg = document.querySelector('#modalFailedMsg');

            $failMsg.innerText = msg;

            $failModal.style.display = 'block';

            $closeBtn.addEventListener('click', () => {
                // $failModal.classList.add("off");
                $failModal.style.display = 'none';

            })
        }

    }
}