let index = {
    init:function (){
        document.querySelector("#btn-save").addEventListener("click", function () {
            index.save();
        });
        },

    save:function (){
        let data = {
            username:document.querySelector("#username").value,
            password:document.querySelector("#password").value,
            email:document.querySelector("#email").value,
        }

        // console.log(data);
    }
}

index.init();