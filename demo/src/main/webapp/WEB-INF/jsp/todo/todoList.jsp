<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello Spring</title>
    <script src="<c:url value='/static/js/jquery-1.8.3.min.js'/>"></script>
</head>
<body>
    <input type="text" id="inputId"></input>

    <h1>Server-Sent Events (SSE) Example</h1>
    <div id="messages"></div>

    <script type="text/javascript">
        // EventSource 객체로 SSE 연결
        var eventSource = new EventSource('/sse');

        // 이벤트가 발생할 때마다 메시지를 처리하는 이벤트 리스너
        eventSource.addEventListener('message', function(event) {
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
    </script>
</body>
</html>
