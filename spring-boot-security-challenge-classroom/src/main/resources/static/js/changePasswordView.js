document.addEventListener("DOMContentLoaded", function(event) {

    tokenVerification();

    var form = document.getElementById("myform");
    form.addEventListener("submit", function(e) {
        e.preventDefault();
        return validateForm();
    });
});

function tokenVerification() {

    if (typeof Cookies.get('token') == 'undefined') {
        console.log("Cookie detected");
        document.location.href="out.html";
    }
}

function validateForm() {
    try {
        var inputValue1 = document.getElementById("input1").value;
        var inputValue2 = document.getElementById("input2").value;
        var inputValue3 = document.getElementById("input3").value;
        const data = { currentPassword: inputValue1, newPassword: inputValue2, newPassword2: inputValue3 };
        const address = '/api/users/update-password';
        fetch(address, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + Cookies.get('token')
            },
            body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                if(data.message != "OK"){
                    let conf = document.getElementById("mensaje");
                    conf.style.color = "red";
                    conf.textContent = "Las contraseñas nuevas no son iguales";
                }else{

                    console.log("Contraseña cambiada correctamente");
                    document.location.href="index.html";
                }
                console.log(data);
            });

    } catch (err) {
        console.error(err.message);

    }
    return false;
}