<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="ui feed">
	<c:forEach var="action" items="${friendsActions}">
		<div class="event">
			<div class="label">
				<img src="<c:url value="/dist/images.jpg" />">
			</div>
			<div class="content">
				<div class="date">3 days ago</div>
				<div class="summary">
					<a>${action.userId}</a> ${action.action}
					${action.activityVO.activityName}
				</div>
				<div class="extra images">
					<a><img src="<c:url value="/dist/images2.jpg" />"></a><a><img
						src="<c:url value="/dist/images2.jpg" />"> </a>
				</div>
				<div class="extra text">Hey! Lets Do this Activity!</div>
				<br />
				<div id='${action.timeStamp}_retreat' class="juggler-float-right"
					style="display: none;">
					<input type="submit" value="Retreat"
						class="tiny ui button ui red button" />
				</div>
				<c:choose>
					<c:when test="${action.participatedFlag==false}">
						<div id='${action.timeStamp}_participateButton'
							class="juggler-float-right">
							<input type="submit" value="Participate"
								class="tiny ui button ui green button"
								onclick="createAction('${action.activityId}','${action.timeStamp}', 'participateAction')" />
						</div>
						<div id='${action.timeStamp}_view' class="juggler-float-right">
							<input type="submit" value="View Details"
								class="tiny ui button ui Grey button" />
						</div>
					</c:when>

					<c:otherwise>

						<div id='${action.timeStamp}_retreat2' class="juggler-float-right">
							<input type="submit" value="Retreat"
								class="tiny ui button ui red button" />
						</div>
						<div id='${action.timeStamp}_view' class="juggler-float-right">
							<input type="submit" value="View Details"
								class="tiny ui button ui Grey button" />
						</div>
					</c:otherwise>

				</c:choose>




			</div>
		</div>
	</c:forEach>
</div>