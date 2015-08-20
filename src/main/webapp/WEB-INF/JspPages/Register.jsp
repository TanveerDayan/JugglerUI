<html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.11.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="register-box">
		<h3 align="center">Register</h3>
		<form:form method="POST" action="/JugglerUI/registerUser">
			<table align="center">
				<tr>
					<td><form:label path="hobbies">Hobbies</form:label></td>
					<td><form:select multiple="multiple" path="hobbies"
							items="${hobbiesDropDown}" /></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="submit" value="Register" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>