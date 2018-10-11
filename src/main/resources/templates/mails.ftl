<!DOCTYPE html>
<html>
<head lang="en">
    <title>Spring Boot Demo - FreeMarker</title>
    <link href="../static/css/index.css" rel="stylesheet"/>
</head>
<body>
<center>
    <input type="text" name="email" id="email"/>
    <button id="send">发送邮件</button>
</center>
<form method="POST" enctype="multipart/form-data" action="/file/upload">
    文件：<input type="file" name="roncooFile"/>
    <input type="submit" value="上传"/>
</form>
<script type="text/javascript" src="../static/js/jquery.min.js"></script>
<script>
    $(function () {
        alert("1");
        $('#send').click(function () {
            alert("1");
            var email = $('#email').val();
            $.ajax({
                url: '/api/mail',
                type: 'post',
                data: {'email': email},
                success: function (msg) {
                    alert(msg);
                }
            });
        });
    })
</script>
</body>
</html>