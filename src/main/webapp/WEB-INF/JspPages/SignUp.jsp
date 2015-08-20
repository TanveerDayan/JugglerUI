<html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.11.1.min.js"></script>
<title>JUGGLER</title>

<body>
	<div>
		<h3 align="center">Sign Up</h3>
		<form:form method="POST" action="/JugglerUI/signUpSuccessPage">
			<table align="center">
				<tr>
					<td><form:label path="emailId">EmailId</form:label></td>
					<td><form:input path="emailId" /></td>
				</tr>
				<tr>
					<td><form:label path="password">Password</form:label></td>
					<td><form:input path="password" /></td>
				</tr>
				<tr>
					<td><form:label path="userName">Name</form:label></td>
					<td><form:input path="userName" /></td>
				</tr>

				<tr>
					<td><form:label path="dateOfBirth">Date Of Birth</form:label>
					</td>
					<td><form:input path="dateOfBirth" /></td>
				</tr>
				<tr>
					<td><form:label path="city">City</form:label></td>
					<td><form:input path="city" /></td>
				</tr>
				<tr>
					<td><form:label path="locality">Locality</form:label></td>
					<td><form:input path="locality" /></td>
				</tr>
				<tr>
					<td><form:label path="roleName">RoleName</form:label></td>
					<td><form:input path="roleName" /></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="submit" value="Next" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
