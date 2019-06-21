
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        $(function () {
           $.ajax({
               method : "post",
               url : "/getData.do",
               data:null,
               success :function (data) {
                   console.log(data);
               }
           })
        });
    </script>
</head>
<body>
123123
</body>
</html>
