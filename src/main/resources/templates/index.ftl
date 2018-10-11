<!DOCTYPE html>
<html xmlns:c="http://www.w3.org/1999/html">
<head lang="en">
    <title>Spring Boot Demo - FreeMarker</title>
    <link href="/css/index.css" rel="stylesheet"/>
</head>
<body>
<center>
    <img src="/images/logo.png"/>
    <h1 id="title">${title}</h1>
<#list tbUser as user>
    <tr>
        <h1 id="title">${user.id}</h1>
        <h1 id="title">${user.username}</h1>
        <h1 id="title">${user.password}</h1>
        <h1 id="title">${user.phone}</h1>
        <h1 id="title">${user.created?string('dd.MM.yyyy HH:mm:ss')}</h1>
        <h1 id="title">${user.updated?string('dd.MM.yyyy HH:mm:ss')}</h1>
    </tr>
</#list>
</center>
<script type="text/javascript" src="/webjars/jquery/2.1.4/jquery.min.js"></script>
<script>
    $(function () {
        $('#title').click(function () {
            alert('点击了');
        });
    })
</script>
</body>
</html>
