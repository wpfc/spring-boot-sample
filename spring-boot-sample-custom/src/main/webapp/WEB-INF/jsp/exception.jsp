<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>统一异常处理</title>
</head>
<body>
	<h1> 系统异常 </h1>
	<h1>${url == null ? '未捕捉到URL' : url}</h1>
	<h1>${exception == null ? '暂无异常信息' : exception.message}</h1>
</body>
</html>