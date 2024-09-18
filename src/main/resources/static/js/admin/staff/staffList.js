import{allCheckBtn} from "../modul/debateModul.js";

let $allCheckBtn = document.querySelector('#allCheck');
let $deleteBtn = document.querySelector('#deleteBtn');
let $checkBoxes = document.querySelectorAll('.check-boxes');
// let $changeStatusBtn = document.querySelector('#change-status');
// 최상단 checkBox 선택시 리스트의 모든 checkbox 선택되는 기능
{
    $allCheckBtn.addEventListener('change',()=>{
        allCheckBtn();
    })
}

//관리자 권한 해제
{
    $deleteBtn.addEventListener('click', ()=>{
        for (let i = 0; i < $checkBoxes.length; i++) {
            let $revokeList = [];

            if ($checkBoxes[i].checked) {
                revokeList.push($checkBoxes[i].value);
            }
        }
        console.log("deleteList = " + revokeList);

        fetch(`/staff/revoke/${revokeList}`,{
            method : "post"
        }).then(resp => resp.text())
            .then(text=>{
                console.log(text);
            })

        location.reload();


    })
}

