<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui cards">
	<h3 class="ui dividing header">My Friends</h3>
	<c:forEach var="friend" items="${myfriends}">
		<div class="card" style="width: 110px; height: 195px"
			id="<c:out value="${friend.id}"/>">
			<div class="image" style="height: 110px; width: 110px">
				<img src="<c:url value="/dist/images.jpg" />"
					style="height: 100%; width: 100%">
			</div>
			<div class="content">
				<div class="header" style="font-size: 100%">${friend.userName}</div>
			</div>
			<div class="extra content">
				<a class="friends"
					onclick="friendAction('<c:out value="${friend.id}"/>','removeFriend')">
					<i class="user icon"></i> Remove </a>
			</div>
		</div>
	</c:forEach>
</div>
