// Gọi API để lấy nội dung tin nhắn từ server
var sendMessageArea = document.getElementById("send-message-area");
function getChatHistory(conversationId) {
  var conversationId = 1;
  fetch("/content/chat-history/" + conversationId)
    .then(response => {
      if (response.ok) {
        return response.json();
      }
      throw new Error('Network response was not OK');
    })
    .then(data => {
      var chatMessages = document.getElementById("chatMessages");
      // Hiển thị các tin nhắn trong danh sách
      data.forEach(function (contentDto) {
        console.log(contentDto)
        var messageItem = document.createElement("li");
        messageItem.classList.add("clearfix");

        var messageData = document.createElement("div");
        messageData.classList.add("message-data");

        var messageDataTime = document.createElement("span");
        messageDataTime.classList.add("message-data-time");
        messageDataTime.textContent = new Date(contentDto.createDate);

        messageData.appendChild(messageDataTime);

        var message = document.createElement("div");
        message.classList.add("message");
        message.textContent = contentDto.content;

        var nickname = document.createElement("div");
        nickname.classList.add("message");
        nickname.textContent = contentDto.nickName;

        messageItem.classList.add("my-message");
        messageItem.appendChild(messageData);
        messageItem.appendChild(nickname);
        messageItem.appendChild(message);

        //                 Kiểm tra nếu tin nhắn là của người gửi
        //                if (contentDto.nickname === "Aiden") {
        //                } else {
        //                    var messageDataTextRight = document.createElement("div");
        //                    messageDataTextRight.classList.add("message-data", "text-right");
        //
        //                    var messageDataTimeTextRight = document.createElement("span");
        //                    messageDataTimeTextRight.classList.add("message-data-time");
        //                    messageDataTimeTextRight.textContent = new Date(contentDto.createDate).toLocaleTimeString();
        //
        //                    var avatar = document.createElement("img");
        //                    avatar.src = "https://bootdey.com/img/Content/avatar/avatar7.png";
        //                    avatar.alt = "avatar";
        //
        //                    messageDataTextRight.appendChild(messageDataTimeTextRight);
        //                    messageDataTextRight.appendChild(avatar);
        //
        //                    messageItem.classList.add("other-message");
        //                    messageItem.appendChild(messageDataTextRight);
        //                    messageItem.appendChild(message);
        //                }
      chatMessages.appendChild(messageItem);
      });
    })
    .catch(function (error) {
      console.log(error);
    });
}
document.addEventListener("DOMContentLoaded", getChatHistory);

// event search-input
var inputSearch = document.getElementById('search-input');
var chatList = document.getElementById('friend-list');

inputSearch.addEventListener('click', function () {
  chatList.style.display = 'none'; // Ẩn thẻ ul
});

inputSearch.addEventListener('blur', function () {
  // Kiểm tra xem ô input có nội dung hay không
  if (inputSearch.value === '') {
    chatList.style.display = 'block'; // Hiển thị lại thẻ ul nếu ô input rỗng
  }
});

// show-hidden user information
document.getElementById("avatar").addEventListener('click',showUserInfor)
function showUserInfor() {
   document.getElementById("user-information").style.display = "flex";   
}
document.getElementById("cancel0").addEventListener('click', cancelForm0)
function cancelForm0() {
  var form = document.getElementById("user-information");
  form.style.display = "none";
}

// show-hidden enter user form
 document.getElementById("editButton").addEventListener('click', showForm)
function showForm() {
  var form = document.getElementById("user-information-input");
  form.style.display = "block";
}
document.getElementById("cancel1").addEventListener('click', cancelForm1)
function cancelForm1() {
  document.getElementById("user-information-input").style.display="none";

}


// submit user form

var submitButtonUserForm = document.getElementById("submit-userForm").addEventListener('submit', submitForm);

function submitForm() {
  var firstName = document.getElementById("firstName").value;
  var lastName = document.getElementById("lastName").value;
  var dateOfBirth = document.getElementById("dateOfBirth").value;
  var accountNote = document.getElementById("accountNote").value;
  var phoneNumber = document.getElementById("phoneNumber").value;
  var nickName = document.getElementById("nickName").value;
  var gender = document.getElementById("gender").value === "true";
  var email = document.getElementById("email").value;

var accountId = localStorage.getItem('id');
  var accountDto = {
    id:accountId,
    firstName: firstName,
    lastName: lastName,
    dateOfBirth: dateOfBirth,
    accountNote: accountNote,
    phoneNumber: phoneNumber,
    nickName: nickName,
    gender: gender,
    email: email
  };
  console.log(accountDto)
  sendFormData(accountDto);
}

function sendFormData(accountDto) {

  fetch('/account/information/edit', {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(accountDto)
  })
    .then(response => {
      if (response.ok) {
        console.log('Thông tin người dùng đã được cập nhật thành công!');

      } else {
        console.error('Có lỗi xảy ra khi cập nhật thông tin người dùng.');
      }
    })
    .catch(error => {
      console.error('Lỗi mạng:', error);
    });
  console.log(data);
}

//// logout
//document.getElementById("logout").addEventListener('click', logout());
//function logout() {
//  fetch('/logout', {
//    method: 'post',
//  })
//    .then(response => {
//      if (response.ok) {
//        console.log(' thành công!');
//
//      } else {
//        console.error('Có lỗi xảy ra');
//      }
//    })
//    .catch(error => {
//      console.error('Lỗi mạng:', error);
//    });
//}

// show all conversation
document.addEventListener('DOMContentLoaded',showAllConversation)
function showAllConversation(){
  var username =localStorage.getItem('username');
  fetch('/conversation/account/all?username='+username)
        .then(response => response.json())
        .then(conversations => {
            const friendList = document.getElementById('friend-list');

            // Lặp qua danh sách cuộc trò chuyện và tạo các phần tử <li> tương ứng
            conversations.forEach(ConversationDto => {
                const li = document.createElement('li');
                li.classList.add('clearfix');

                const img = document.createElement('img');
                img.src = '/image/defaultAVT.jpg';
                img.alt = 'avatar';

                const divAbout = document.createElement('div');
                divAbout.classList.add('about');

                const divId = document.createElement('div');
                divId.classList.add('conversationId');
                divId.textContent = ConversationDto.id;

                const divName = document.createElement('div');
                divName.classList.add('name');
                divName.textContent = ConversationDto.conversationName;

                const divStatus = document.createElement('div');
                divStatus.classList.add('status');
                divStatus.innerHTML = '<i class="fa fa-circle online"></i> online';

                divAbout.appendChild(divId);
                divAbout.appendChild(divName);
                divAbout.appendChild(divStatus);

                li.appendChild(img);
                li.appendChild(divAbout);

                friendList.appendChild(li);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


