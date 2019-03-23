<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <ul>
        <li>Application : ${application.name}</li>
        <li>Version     : ${project.version}</li>
        <li>Build on    : ${maven.build.timestamp}</li>
        <li>Server      : ${serverInfo}</li>
    </ul>
</div>

</body>
</html>
