//send message
var sendMessageArea = document.getElementById("send-message-area");
var stompClient = null;
document.getElementById("send-message-button").addEventListener('click', sendMessage);
document.addEventListener("DOMContentLoaded", connect);

//var testResponse = document.querySelector('#testResponse');
//testResponse.addEventListener('click', connect);
//testResponse.addEventListener('click', test1);
//function test1 (){
//      document.getElementById("demo").innerHTML = localStorage.getItem('token')
//}

function sendMessage(event, conversationId) {
     conversationId = 1;

    var ChatMessage = {
    content:sendMessageArea.value.trim(),
    token:localStorage.getItem('token'),
    }
    stompClient.send("/chat/content/send/"+conversationId,
        {},
        JSON.stringify(ChatMessage)
    )
    sendMessageArea.value = '';
}

function connect(event) {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected);
    event.preventDefault();
}
function onConnected(conversationId) {
    var conversationId = 1
    stompClient.subscribe("/conversation/"+conversationId, onMessageReceived);
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log(message);
    var chatMessages = document.getElementById("chatMessages");

    var messageItem = document.createElement("li");
    messageItem.classList.add("clearfix");

    var messageData = document.createElement("div");
    messageData.classList.add("message-data");

    var messageDataTime = document.createElement("span");
    messageDataTime.classList.add("message-data-time");
    messageDataTime.textContent = new Date(message.createDate)

    messageData.appendChild(messageDataTime);

    var content = document.createElement("div");
    content.classList.add("message");
    content.textContent = message.content;

    var nickname = document.createElement("div");
    nickname.classList.add("message");
    nickname.textContent = message.nickName;

    messageItem.classList.add("my-message");
    messageItem.appendChild(messageData);
    messageItem.appendChild(nickname);
    messageItem.appendChild(content);

    chatMessages.appendChild(messageItem);
}
