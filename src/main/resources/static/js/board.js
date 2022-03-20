let index = {
    init:function (){
        if(document.querySelector("#btn-save")) {
            document.querySelector("#btn-save").addEventListener("click", function () { //function을 쓸 때와 화살표 함수를 쓸 때 this가 가리키는 것이 달라진다. function의 this는 window, 화살표의 this는 index
            index.save();
        })};
        if(document.querySelector("#btn-delete")) {
            document.querySelector("#btn-delete").addEventListener("click", function () { //function을 쓸 때와 화살표 함수를 쓸 때 this가 가리키는 것이 달라진다. function의 this는 window, 화살표의 this는 index
                index.deleteById();
            })};
        // if(document.querySelector("#btn-login")) {document.querySelector("#btn-login").addEventListener("click", function () { //function을 쓸 때와 화살표 함수를 쓸 때 this가 가리키는 것이 달라진다. function의 this는 window, 화살표의 this는 index
        //     index.login();
        // })}
        if(document.querySelector("#btn-update")) {
            document.querySelector("#btn-update").addEventListener("click", function () { //function을 쓸 때와 화살표 함수를 쓸 때 this가 가리키는 것이 달라진다. function의 this는 window, 화살표의 this는 index
                index.update();
            })};
        if(document.querySelector("#btn-reply-save")) {
            document.querySelector("#btn-reply-save").addEventListener("click", function () { //function을 쓸 때와 화살표 함수를 쓸 때 this가 가리키는 것이 달라진다. function의 this는 window, 화살표의 this는 index
                index.replySave();
            })};
        },


    save:function (){
        let data = {
            title:document.querySelector("#title").value,
            content:document.querySelector("#content").value
        };

        console.log(data);
        //ajax 비동기 호출, 3개의 데이터를 json으로 변경해서 insert, ajax가 통신을 성공하고 서버가 json을 리턴하면 자동으로 자바 오브젝트로 변환
    fetch('/api/board', {
        method : 'POST',
        headers : {
            'Content-Type': 'application/json' //body데이터가 어떤 타입인지
        },
        body : JSON.stringify(data), //http body데이터, 서버로 요청을 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면 자바스크립트 오브젝트로 변경)
    }).then((res) => {
        console.log(res);
        alert("글쓰기가 완료되었습니다.");
        location.href="/";
        return res.json();
    }).then((data) => {
        console.log(data)
    }).catch((e) => {
        alert("글쓰기에 실패했습니다.");
        console.log(JSON.stringify(e));
    })

    },

    deleteById:function (){
        let id = document.querySelector("#id").textContent;
        //ajax 비동기 호출, 3개의 데이터를 json으로 변경해서 insert, ajax가 통신을 성공하고 서버가 json을 리턴하면 자동으로 자바 오브젝트로 변환
        fetch('/api/board/' + id, {
            method : 'DELETE'
             }).then((res) => {
            alert("삭제가 완료되었습니다.");
            console.log(id);
            location.href="/";
            return res.json();
        }).then((data) => {
            console.log(data)
        }).catch((e) => {
            alert("삭제에 실패했습니다.");
            console.log(JSON.stringify(e));
        })

    },

    update:function (){
        let id = document.querySelector("#id").value;


        let data = {
            title:document.querySelector("#title").value,
            content:document.querySelector("#content").value
        };

        console.log(data);
        //ajax 비동기 호출, 3개의 데이터를 json으로 변경해서 insert, ajax가 통신을 성공하고 서버가 json을 리턴하면 자동으로 자바 오브젝트로 변환
        fetch('/api/board'+id, {
            method : 'PUT',
            headers : {
                'Content-Type': 'application/json' //body데이터가 어떤 타입인지
            },
            body : JSON.stringify(data), //http body데이터, 서버로 요청을 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면 자바스크립트 오브젝트로 변경)
        }).then((res) => {
            console.log(res);
            alert("글수정이 완료되었습니다.");
            location.href="/";
            return res.json();
        }).then((data) => {
            console.log(data)
        }).catch((e) => {
            alert("글수정에 실패했습니다.");
            console.log(JSON.stringify(e));
        })

    },
    replySave:function (){
        let data = {
            userId:document.querySelector("#userId").value,
            boardId:document.querySelector("#boardId").value,
            content:document.querySelector("#reply-content").value
        };

        //ajax 비동기 호출, 3개의 데이터를 json으로 변경해서 insert, ajax가 통신을 성공하고 서버가 json을 리턴하면 자동으로 자바 오브젝트로 변환
        fetch(`/api/board/${data.boardId}/reply`, {
            method : 'POST',
            headers : {
                'Content-Type': 'application/json' //body데이터가 어떤 타입인지
            },
            body : JSON.stringify(data), //http body데이터, 서버로 요청을 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면 자바스크립트 오브젝트로 변경)
        }).then((res) => {
            console.log(res);
            alert("댓글 작성이 완료되었습니다.");
            location.href=`/board/${data.boardId}`;
            return res.json();
        }).then((data) => {
            console.log(data)
        }).catch((e) => {
            alert("댓글 작성에 실패했습니다.");
            console.log(JSON.stringify(e));
        })

    },
    replyDelete:function (boardId, replyId){
        fetch(`/api/board/${boardId}/reply/${replyId}`, {
            method : 'DELETE',
         }).then((res) => {
            console.log(res);
            alert("댓글 삭제 성공.");
            location.href=`/board/${boardId}`;
            return res.json();
        }).then((data) => {
            console.log(data)
        }).catch((e) => {
            alert("댓글 삭제를 실패했습니다.");
            console.log(JSON.stringify(e));
        })

    }
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
