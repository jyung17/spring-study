<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello Spring</title>
    <script src="/js/jquery-1.8.3.min.js"></script>
</head>
<body>
    <h1>Server-Sent Events (SSE) Example</h1>
    <input type="text" id="userId" value="test@gm.com"/>
    <button type="button" onClick="connect()">connect</button>
    <button type="button" onClick="disconnect()">disconnect</button>
    <div id="messages"></div>

<!--    <div>
    <label for="evenId2">eventId</label>
    <input type="text" id="evenId2" value=""/>
    <label for="sendData2">데이터</label>
    <input type="text" id="sendData2" value=""/>
    <button type="button" onClick="sendToJson()">send</button>
    </div>
-->
    <script type="text/javascript">

    var eventSource = "";

    function connect() {
        let userId = $("#userId").val();
        let connectUrl = "/connect/" + userId;
        // EventSource 객체로 SSE 연결
        eventSource = new EventSource(connectUrl);

        // 이벤트가 발생할 때마다 메시지를 처리하는 이벤트 리스너
        eventSource.addEventListener('message', function(event) {
            console.log(event);
            // data으로 설정한 값 받기
            const data = JSON.parse(event.data);
            console.log(data);
            var message = event.data;
            var messagesDiv = document.getElementById('messages');
            var newMessage = document.createElement('p');
            newMessage.textContent = message;
            messagesDiv.appendChild(newMessage);
        });

        // 연결 오류 처리
        eventSource.onerror = function(event) {
            console.error('SSE connection error', event);
        };
    }

    function disconnect() {
      //eventSource.close();
      console.log(eventSource);
      console.log('connection is closed');
    }

    function send() {
      let sendData = $("#sendData").val();
      console.log(sendData);
      $.ajax({
        type: 'post',
        url: '/sendAll',
        dataType : 'text',
            data : JSON.stringify({
              "eventId" : no,
              "data" : name,
            }),
      })
    }

    function sendToJson() {
          let evenId = $("#evenId2").val();
          let sendData = $("#sendData2").val();
          console.log(sendData);
          $.ajax({
            type: 'post',
            url: '/sendToJson',
            contentType: 'application/json; charset=utf-8',
            dataType : 'text',
                data : JSON.stringify({
                  "eventId" : evenId,
                  "data" : sendData,
                }),
          })
        }

    </script>
</body>
</html>
