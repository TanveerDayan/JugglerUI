<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui feed">
	<c:forEach var="activity" items="${activitySuggestion}">
		<div class="event">
			<div class="label">
				<img src="<c:url value="/dist/images.jpg" />">
			</div>
			<div class="content">
				<div class="date">3 days ago</div>
				<div class="summary">
					<a>${activity.userId}</a> ${activity.action} ${activity.activityVO.activityName}
				</div>
				<div class="extra images">
					<a><img src="<c:url value="/dist/images2.jpg" />"> </a> <a><img
						src="<c:url value="/dist/images2.jpg" />"> </a>
				</div>
				<div class="extra text">Hey! Lets Do this Activity!</div>
			</div>
		</div>
	</c:forEach>
</div>