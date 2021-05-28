document.addEventListener("DOMContentLoaded", function(event) {

    tokenVerification();

    var form = document.getElementById("form");
    form.addEventListener("submit", function(e) {
        e.preventDefault();
        return validateReservation();
    });
    var form2 = document.getElementById("verReservas");
        form2.addEventListener("submit", function(e) {
            e.preventDefault();
            return getReservations();
        });

});

function tokenVerification() {

    if (typeof Cookies.get('token') !== 'undefined') {
        console.log("Cookie detected");

    }else{
        alert("Para reservar debes haber iniciado sesión");
        document.location.href="index-3.html";
    }
}

function validateReservation() {
    try {
        var inputValue1 = document.getElementById("dia").value;
        var inputValue2 = document.getElementById("hora").value;
        var inputValue3 = document.getElementById("personas").value;
        const data = { dia: inputValue1, hora: inputValue2, personas: inputValue3};
        const address = '/api/reserva';
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
                let conf = document.getElementById("mensaje");
                if(data.message == "OK"){
                    conf.style.color = "green";
                    conf.textContent = "Reserva completada";
                    let message="Reserva completada";
                }else{
                    conf.style.color = "red";
                    conf.textContent = "Fecha no disponible";
                    let message="Fecha no disponible";
                }
                console.log(data.message);
            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}

function aumentar(){
      let html = message;
      document.getElementById("mensaje").innerHTML = html;
}

function getReservations() {
    try {
        const address = '/api/reserva';
        fetch(address, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + Cookies.get('token')
            }
            })
            .then(response => response.json())
            .then(data => {
                console.log(data.message);
                if(data.message != ""){
                    let html = data.message
                    document.getElementById("Reservas").innerHTML = html;
                }else{
                    document.getElementById("Reservas").innerHTML = "No hay reservas en tu nombre";
                }

            });

    } catch (err) {
        console.error(err.message);
    }
    return false;
}

function reply_click(id_reserva){
    try{
        let cadena = document.getElementById(id_reserva)
        const address = '/api/reserva/'+id_reserva;
        fetch(address, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + Cookies.get('token')
            }
        })
        .then(response => response.json())
        .then(data=>{
            let conf = document.getElementById("estado");
            if(data.message == "OK"){
                conf.style.color = "green";
                conf.textContent = "Reserva eliminada";
                cadena.remove();
            }
            console.log(data.message);
        });

    }catch(err){
        console.error(err.message);
    }
    return false;
}