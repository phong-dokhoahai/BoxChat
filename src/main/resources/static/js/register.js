var registerForm = document.querySelector('#registerForm');

var username = document.querySelector('#username');
var password = document.querySelector('#password');
var email =document.querySelector('#email');

function submitRegisterForm(event) {
    var AccountNamePasswordDTO = {
            password: password.value,
            username: username.value,
            email:email.value,
        };
    fetch("/account/register", {
    method: "POST",
    headers: {
    "Content-Type": "application/json"
    },
     body: JSON.stringify(AccountNamePasswordDTO)
})
.then(response => {})
.catch(error => {});
}
registerForm.addEventListener('submit', submitRegisterForm, true);

