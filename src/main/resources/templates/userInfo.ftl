<!DOCTYPE html>
<html xmlns:c="http://www.w3.org/1999/html">
<head lang="en">
    <title>Spring Boot Demo - FreeMarker</title>
    <link href="/css/index.css" rel="stylesheet"/>
</head>
<body>
<div>国家：${user.country}</div>
<div>省份：${user.province}</div>
<div>城市：${user.city}</div>
<div>openid：${user.openid}</div>
<div>性别：${user.sex}</div>
<div>昵称：${user.nickname}</div>
<div>头像：<img src="${user.headimgurl}"></img></div>
</body>
</html>
