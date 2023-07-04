var loginform = document.querySelector('#loginform');

var username = document.querySelector('#username');
var password = document.querySelector('#password');

function submitLoginForm(event) {
    var LoginRequest = {
        password: password.value,
        username: username.value,
    };
    fetch("/auth/login", {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(LoginRequest)
    })
        .then(function (response) {
            if (response.ok) {
                return response.json();
            }
        })
        .then(function (data) {
            localStorage.setItem('token', data.token);
            localStorage.setItem('username', data.username);
            localStorage.setItem('id', data.id);
        })
        .catch(error => { });
}

loginform.addEventListener('submit', submitLoginForm, true);
