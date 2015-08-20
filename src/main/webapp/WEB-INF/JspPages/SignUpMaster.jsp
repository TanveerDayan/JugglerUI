<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/dist/semantic.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/dist/semantic.min.css" />">
	
<script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script src="<c:url value="/dist/semantic.js" />"></script>
<script src="<c:url value="/dist/semantic.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		pageLoader('signUp', 'childDiv');
	});
	function pageLoader(action, div) {
		var path = null;
		if (action == 'signUp') {
			path = '/JugglerUI/signUpPage';
		} else if (action == 'createActivity') {
			path = '/JugglerUI/register';
		}
		$("#" + div).load(path);
	}
</script>
<style type="text/css">
.register-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}

.hidden-rows {
	display: none;
}
</style>
</head>
<body>
	<div id="childDiv" class="register-box"></div>
</body>
</html>