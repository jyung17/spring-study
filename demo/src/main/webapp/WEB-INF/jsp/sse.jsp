<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="<c:url value='/static/js/jquery-1.8.3.min.js'/>"></script>
    <title>SSE with Spring MVC</title>
</head>
<body>
    <h1>Server-Sent Events (SSE) Example</h1>
    <div id="events"></div>

    <script>
        // Create a new EventSource instance to listen for events from the server
        const eventSource = new EventSource('/sse');

        // Listen for events
        eventSource.onmessage = function(event) {
            // When an event is received, display it on the page
            const eventDiv = document.createElement("div");
            eventDiv.textContent = "Received: " + event.data;
            document.getElementById("events").appendChild(eventDiv);
        };

        // Handle errors in case the connection is lost
        eventSource.onerror = function(error) {
            console.error("Error occurred: ", error);
            eventSource.close();  // Optionally close the connection
        };
    </script>
</body>
</html>