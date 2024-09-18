import{allCheckBtn} from "../modul/debateModul.js";

let $allCheckBtn = document.querySelector('#allCheck');
let $changeStatusBtn = document.querySelector('#change-status');
let $checkBoxes = document.querySelectorAll('.check-boxes');
// 최상단 checkBox 선택시 리스트의 모든 checkbox 선택되는 기능
{
    $allCheckBtn.addEventListener('change',()=>{
        allCheckBtn();
    })
}

// 승인 대기 중인 관리자 명단 리스트 중 관리자로 권한 승인해주는 기능
{
    $changeStatusBtn.addEventListener('click',()=>{
        let preList = [];

        for (let i = 0; i < $checkBoxes.length; i++) {
            if ($checkBoxes[i].checked){
                preList.push($checkBoxes[i].value);
            }
        }
        console.log('list = ' + preList);

        fetch(`/change/staff/${preList}`, {
            method : "post"
        }).then(resp=>resp.text())
            .then(text =>{
                console.log("resp = "+text);
            })

        location.reload();

    })

}