package com.juggler.constants;

public interface JugglerConstants {
	public final String COMMAND = "command";
	public final String UNDERSCORE = "_";

	public final String REST_INPUT_USERJSON = "userJson";
	public final String REST_INPUT_ACTIVITYJSON = "activityJson";
	public final String REST_INPUT_LOGIN_JSON = "loginJson";
	public final String REST_INPUT_VALIDATE_USER_ID = "userId";
	public final String REST_INPUT_VALIDATE_FRIEND_ID = "friendId";
	public final String REST_INPUT_RETRIEVAL_TYPE = "retrievalType";
	public final String REST_INPUT_RECIEVED_FRIEND_REQUESTS = "recievedFriendRequests";
	public final String REST_INPUT_SENT_FRIEND_REQUESTS = "sentFriendRequests";
	public final String REST_INPUT_FRIENDS = "friends";
	public final String REST_INPUT_ACTIVITYID = "activityId";
	public final String REST_INPUT_ACTION = "action";

	public final String REST_PATH_GET_USER_DETAIL = "getUserDetail";
	public final String REST_PATH_CREATE_USER = "createUser";
	public final String REST_PATH_UPDATE_USER = "updateUser";
	public final String REST_PATH_VALIDATE_USER = "validateUser";
	public final String REST_PATH_ADD_ACTIVITY = "addActivity";
	public final String REST_PATH_FRIEND_SUGGESTION = "friendSuggestion";
	public final String REST_PATH_ADD_FRIEND = "addFriend";
	public final String REST_PATH_ACCEPT_FRIEND = "acceptFriend";
	public final String REST_PATH_CANCEL_FRIEND = "cancelFriend";
	public final String REST_PATH_GET_USER_FRIEND_DETAILS = "getUserFriendDetails";
	public final String REST_PATH_GET_ACTION_DETAILS = "getActionDetails";
	public final String REST_PATH_GET_SUGGESTED_ACTIVITY = "getSuggestedActivity";
	public final String REST_PATH_GET_ACTIVITY = "getActivity";
	public final String REST_PATH_PERSIST_ACTION = "persistAction";
	
	

	public final String JSP_PAGE_SIGNUP = "SignUp";
	public final String JSP_PAGE_SIGNUP_SUCCESS = "SignUpSuccess";
	public final String JSP_PAGE_REGISTER = "Register";
	public final String JSP_PAGE_WELCOME = "Welcome";
	public final String JSP_PAGE_LOGIN = "Login";
	public final String JSP_PAGE_SIGNUP_MASTER = "SignUpMaster";
	public final String JSP_PAGE_CREATE_ACTIVITY = "CreateActivity";
	public final String JSP_PAGE_FRIENDS_ACTIVITY = "FriendsActivity";
	public final String JSP_PAGE_FRIEND_SUGGESTION = "FriendSuggestion";
	public final String JSP_PAGE_ACTIVITY_MAIN = "ActivityMainPage";
	public final String JSP_PAGE_FRIENDS_MAIN = "FriendsMainPage";
	public final String JSP_PAGE_ERROR = "Error";
	public final String JSP_PAGE_SENT_FRIEND_REQUEST = "SentFriendRequest";
	public final String JSP_PAGE_RECIEVED_FRIEND_REQUEST = "RecievedFriendRequest";
	public final String JSP_PAGE_MY_FRIENDS = "MyFriends";

	public final String JSP_DROPDOWN_HOBBIES = "hobbiesDropDown";
	public final String JSP_USER_NAME = "userName";
	public final String JSP_FRIEND_SUGGESTION = "friendSuggestion";
	public final String JSP_SENT_FRIEND_REQUEST = "sentFriendRequest";
	public final String JSP_RECIEVED_FRIEND_REQUEST = "recievedFriendRequest";
	public final String JSP_MY_FRIENDS = "myfriends";
	public final String JSON_PASSWORD = "password";
	public final String JSON_EMAILID = "emailId";
	public final String JSON_ROLENAME = "roleName";

	// ACTION TYPES
	public final String ACTION_SUGGESTS = "Suggests";
	public final String ACTION_PARTICIPATE = "Participated in";
}
