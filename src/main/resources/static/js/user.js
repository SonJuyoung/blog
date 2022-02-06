let index = {
    init:function (){
        if(document.querySelector("#btn-save")) {
            document.querySelector("#btn-save").addEventListener("click", function () { //function을 쓸 때와 화살표 함수를 쓸 때 this가 가리키는 것이 달라진다. function의 this는 window, 화살표의 this는 index
            index.save();
        })};
        // if(document.querySelector("#btn-login")) {document.querySelector("#btn-login").addEventListener("click", function () { //function을 쓸 때와 화살표 함수를 쓸 때 this가 가리키는 것이 달라진다. function의 this는 window, 화살표의 this는 index
        //     index.login();
        // })}
        },


    save:function (){
        let data = {
            username:document.querySelector("#username").value,
            password:document.querySelector("#password").value,
            email:document.querySelector("#email").value,
        };

        console.log(data);
        //ajax 비동기 호출, 3개의 데이터를 json으로 변경해서 insert, ajax가 통신을 성공하고 서버가 json을 리턴하면 자동으로 자바 오브젝트로 변환
    fetch('/auth/joinProc', {
        method : 'POST',
        headers : {
            'Content-Type': 'application/json' //body데이터가 어떤 타입인지
        },
        body : JSON.stringify(data), //http body데이터, 서버로 요청을 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면 자바스크립트 오브젝트로 변경)
    }).then((res) => {
        console.log(res);
        alert("회원가입이 완료되었습니다.");
        location.href="/";
        return res.json();
    }).then((data) => {
        console.log(data)
    }).catch((e) => {
        alert("회원가입에 실패했습니다.");
        console.log(JSON.stringify(e));
    })

    },
}

index.init();



// login:function (){
//     let data = {
//         username:document.querySelector("#username").value,
//         password:document.querySelector("#password").value
//     };
//
//     fetch('/api/user/login', {
//         'method' : 'POST',
//         'headers' : {
//             'Content-Type': 'application/json' //body데이터가 어떤 타입인지
//         },
//         'body' : JSON.stringify(data), //http body데이터, 서버로 요청을 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면 자바스크립트 오브젝트로 변경)
//     }).then((res) => {
//         console.log(res);
//         alert("로그인이 완료되었습니다.");
//         location.href="/";
//         return res.json();
//     }).then((data) => {
//         console.log(data)
//     }).catch((e) => {
//         alert("회원가입에 실패했습니다.");
//         console.log(JSON.stringify(e));
//     })
//
// }
