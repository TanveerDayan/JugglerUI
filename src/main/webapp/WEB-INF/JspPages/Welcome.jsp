<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>JUGGLER</title>
<script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script src="<c:url value="/dist/semantic.js" />"></script>

<script src="<c:url value="/dist/semantic.min.js" />"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/dist/semantic.css" />">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/dist/semantic.min.css" />">
<script type="text/javascript">
	function pageLoader(action, div) {
		var path;
		if (action == 'activityMainPage') {
			path = '/JugglerUI/activityMainPage';
		} else if (action == 'createActivity') {
			path = '/JugglerUI/activityPage';
		} else if (action == 'friendsActivity') {
			path = '/JugglerUI/showFriendsActivity';
		} else if (action == 'myActivity') {
			path = '/JugglerUI/showMyActivity';
		} else if (action == 'friendsMainPage') {
			path = '/JugglerUI/friendsMainPage';
		} else if (action == 'showFriendSuggestion') {
			path = '/JugglerUI/showFriendSuggestion';
		} else if (action == 'showSentFriendRequest') {
			path = '/JugglerUI/showSentFriendRequest';
		} else if (action == 'showRecievedFriendRequest') {
			path = '/JugglerUI/showRecievedFriendRequest';
		} else if (action == 'showMyFriends') {
			path = '/JugglerUI/showMyFriends';
		} else if (action == 'suggestedActivity') {
			path = '/JugglerUI/showSuggestedActivity';
		}

		$("#" + div).load(path);
	}

	function friendAction(friendId, actionType) {
		var url = "/JugglerUI/" + actionType;
		$.ajax({
			type : "POST",
			url : url,
			data : {
				"friendId" : friendId
			},
			success : function(data) {
				document.getElementById(data).style.display = 'none';
			}
		});
	}

	function createAction(activityId, actionId, path) {
		var url = "/JugglerUI/" + path;
		$
				.ajax({
					type : "POST",
					url : url,
					data : {
						"activityId" : activityId
					},
					success : function() {
						document
								.getElementById(actionId + "_participateButton").style.display = 'none';
						document
						.getElementById(actionId + "_retreat").style.display = 'block';
					}
				});
	}

	$(document).ready(function() {
		$('.ui.accordion').accordion();
	});
</script>

<style type="text/css">
.juggler-body {
	overflow: auto;
	min-width: 100%;
}

.juggler-header-icon {
	width: 200px;
}

.juggler-header-logout {
	margin-left: 1170px;
	margin-top: -90px;
}

.juggler-accordion {
	width: 200px;
	height: 100px;
	margin-top: 50px;
}

.juggler-child-div {
	width: 500px;
	margin-left: 390px;
	margin-top: -95px;
}

.juggler-float-right {
	float: right;
}

/*  .content > .header
{
	border:2px solid green !important;
} */
</style>
</head>
<body class="juggler-body">
	<div>
		<div class="juggler-header-icon">
			<span class="ui icon header"> <i class="settings icon"></i>JUGGLER
			</span>
		</div>
		<div class="juggler-header-logout">
			<a href="<c:url value="/j_spring_security_logout" />"> <span
				class="ui primary button">LogOut</span>
			</a>
		</div>
	</div>
	<div class="juggler-accordion">
		<div class="ui styled accordion">
			<div class="title">
				<i class="dropdown icon"></i>Activity
			</div>
			<div class="content">
				<div class="ui link list">
					<a class="item" onclick="pageLoader('createActivity','childDiv')">Create
						Activity</a> <a class="item"
						onclick="pageLoader('friendsActivity','childDiv')">Friends
						Activities</a> <a class="item"
						onclick="pageLoader('myActivity','childDiv')">My Activities</a> <a
						class="item" onclick="pageLoader('suggestedActivity','childDiv')">Suggested
						Activities</a>
				</div>
			</div>
			<div class="title">
				<i class="dropdown icon"></i>Friends
			</div>
			<div class="content">
				<div class="ui link list">
					<a class="item"
						onclick="pageLoader('showFriendSuggestion','childDiv')">Friend
						Suggestion</a> <a class="item"
						onclick="pageLoader('showSentFriendRequest','childDiv')">Sent
						Friend Requests</a> <a class="item"
						onclick="pageLoader('showRecievedFriendRequest','childDiv')">Recieved
						Friend Requests</a><a class="item"
						onclick="pageLoader('showMyFriends','childDiv')">My Friends</a>
				</div>
			</div>
		</div>
	</div>
	<div id="childDiv" class="juggler-child-div"></div>
</body>
</html>