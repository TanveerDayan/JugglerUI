package com.juggler.controller;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.juggler.constants.JugglerConstants;
import com.juggler.pojo.ActionVO;
import com.juggler.pojo.ActivityVO;
import com.juggler.pojo.SessionVO;
import com.juggler.pojo.UserCreateVO;
import com.juggler.test.beans.HelloRequestScopeData;
import com.juggler.test.beans.HelloSessionScopeData;
import com.juggler.utils.ConnectionEstablisher;
import com.juggler.utils.JugglerUtils;
import com.juggler.utils.MailSender;

@Controller()
public class JuggerUIController {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	ConnectionEstablisher connectionEstablisher;

	@Autowired
	MailSender mailSender;

	@Autowired
	SessionVO sessionVO;

	@Autowired
	private HelloRequestScopeData requestscopehellodata;

	@Autowired
	private HelloSessionScopeData sessionscopehellodata;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String loginPage() {
		return JugglerConstants.JSP_PAGE_LOGIN;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName(JugglerConstants.JSP_PAGE_LOGIN);

		return model;

	}

	@RequestMapping(value = "/masterSignUpPage", method = RequestMethod.GET)
	public String signUpMaster(ModelMap model) {
		return JugglerConstants.JSP_PAGE_SIGNUP_MASTER;
	}

	@RequestMapping(value = "/signUpPage", method = RequestMethod.GET)
	public ModelAndView signUp(ModelMap model) {

		return new ModelAndView(JugglerConstants.JSP_PAGE_SIGNUP, JugglerConstants.COMMAND, new UserCreateVO());
	}

	@RequestMapping(value = "/signUpSuccessPage", method = RequestMethod.POST)
	public String signUpSuccess(@ModelAttribute UserCreateVO userVO, ModelMap model) {
		userVO.setUserStatus("Pending");
		userVO.setActivationId("actId");
		mailSender.sendMail(userVO.getEmailId(),
				"http://localhost:8080/JugglerUI/registerPage?actId=actId&userId=" + userVO.getEmailId());

		persistUserInfo(userVO, JugglerConstants.REST_PATH_CREATE_USER);
		// send mail here
		return JugglerConstants.JSP_PAGE_SIGNUP_SUCCESS;
	}

	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public @ResponseBody ModelAndView register(@RequestParam(value = "actId") String actId,
			@RequestParam(value = "userId") String userId, ModelMap model) {
		UserCreateVO vo = new UserCreateVO();
		vo.setEmailId(userId);
		vo.setActivationId(actId);
		persistUserInfo(vo, "activateUser");
		// activate the user above
		List<String> hobbiesList = new ArrayList<String>();
		hobbiesList.add("hobbie1");
		hobbiesList.add("hobbie2");
		hobbiesList.add("hobbie3");
		model.addAttribute(JugglerConstants.JSP_DROPDOWN_HOBBIES, hobbiesList);

		return new ModelAndView(JugglerConstants.JSP_PAGE_REGISTER, JugglerConstants.COMMAND, vo);
	}

	private void persistUserInfo(UserCreateVO userVO, String path) {
		StringWriter writer = new StringWriter();
		try {
			Map<String, String> parameterMap = new HashMap<String, String>();
			mapper.writeValue(writer, userVO);
			parameterMap.put(JugglerConstants.REST_INPUT_USERJSON, writer.toString());
			connectionEstablisher.connectRest(path, parameterMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute UserCreateVO userVO, ModelMap model) {
		persistUserInfo(userVO, JugglerConstants.REST_PATH_UPDATE_USER);
		return JugglerConstants.JSP_PAGE_LOGIN;
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcome(ModelMap model) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			sessionVO.setUserId(auth.getName());
			Map<String, String> parameterMap = new HashMap<String, String>();
			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
			String output = connectionEstablisher.connectRest(JugglerConstants.REST_PATH_GET_USER_DETAIL, parameterMap);
			JSONObject object = new JSONObject(output);
			model.addAttribute(JugglerConstants.JSP_USER_NAME, object.get(JugglerConstants.JSP_USER_NAME));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JugglerConstants.JSP_PAGE_WELCOME;

	}

	// -----------------------------------ACTIVITY-------------------------------------------//

	@RequestMapping(value = { "/activityMainPage" }, method = RequestMethod.GET)
	public String activityMainPage() {
		return JugglerConstants.JSP_PAGE_ACTIVITY_MAIN;
	}

	@RequestMapping(value = "/activityPage", method = RequestMethod.GET)
	public ModelAndView activityPage(ModelMap model) {
		List<String> hobbiesList = new ArrayList<String>();
		hobbiesList.add("hobbie1");
		hobbiesList.add("hobbie2");
		hobbiesList.add("hobbie3");
		model.addAttribute(JugglerConstants.JSP_DROPDOWN_HOBBIES, hobbiesList);
		return new ModelAndView(JugglerConstants.JSP_PAGE_CREATE_ACTIVITY, JugglerConstants.COMMAND, new ActivityVO());
	}

	@RequestMapping(value = "/createActivity", method = RequestMethod.POST)
	public ModelAndView createActivity(@ModelAttribute ActivityVO activityVO, ModelMap model) {
		StringWriter writer = new StringWriter();
		try {
			activityVO.setActivityOwnerId(sessionVO.getUserId());
			Map<String, String> parameterMap = new HashMap<String, String>();
			mapper.writeValue(writer, activityVO);
			parameterMap.put(JugglerConstants.REST_INPUT_ACTIVITYJSON, writer.toString());
			connectionEstablisher.connectRest(JugglerConstants.REST_PATH_ADD_ACTIVITY, parameterMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(JugglerConstants.JSP_PAGE_CREATE_ACTIVITY, JugglerConstants.COMMAND, new ActivityVO());
	}

	@RequestMapping(value = "/showFriendsActivity", method = RequestMethod.GET)
	public ModelAndView showFriendsActivity(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView(JugglerConstants.JSP_PAGE_FRIENDS_ACTIVITY);
		Map<String, String> parameterMap = new HashMap<String, String>();
		List<ActionVO> actionVOList = new ArrayList<ActionVO>();
		parameterMap.put(JugglerConstants.REST_INPUT_RETRIEVAL_TYPE, "friends");
		parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
		try {
			String output = connectionEstablisher.connectRest(JugglerConstants.REST_PATH_GET_ACTION_DETAILS,
					parameterMap);
			JSONArray array = new JSONArray(output);
			for (int i = 0; i < array.length(); i++) {
				ActionVO actionVO = JugglerUtils.convertToActionObjectFromJsonString(array.getString(i));
				ActivityVO activityVO = getActivityVO(actionVO.getActivityId());
				actionVO.setActivityVO(activityVO);

				if (activityVO != null && activityVO.getParticipants() != null
						&& activityVO.getParticipants().contains(sessionVO.getUserId())) {
					actionVO.setParticipatedFlag(true);
				}
				actionVOList.add(actionVO);
			}
			modelAndView.addObject("friendsActions", actionVOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/showMyActivity", method = RequestMethod.GET)
	public ModelAndView showMyActivity(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("MyActivity");
		Map<String, String> parameterMap = new HashMap<String, String>();
		List<ActionVO> actionVOList = new ArrayList<ActionVO>();
		parameterMap.put(JugglerConstants.REST_INPUT_RETRIEVAL_TYPE, "userId");
		parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
		try {
			String output = connectionEstablisher.connectRest(JugglerConstants.REST_PATH_GET_ACTION_DETAILS,
					parameterMap);
			JSONArray array = new JSONArray(output);
			for (int i = 0; i < array.length(); i++) {
				ActionVO actionVO = JugglerUtils.convertToActionObjectFromJsonString(array.getString(i));
				ActivityVO activityVO = getActivityVO(actionVO.getActivityId());
				actionVO.setActivityVO(activityVO);
				actionVOList.add(actionVO);
			}
			modelAndView.addObject("myActions", actionVOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	private ActivityVO getActivityVO(String activityId) {
		ActivityVO activityVO = null;
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put(JugglerConstants.REST_INPUT_ACTIVITYID, activityId);
		try {
			String output = connectionEstablisher.connectRest(JugglerConstants.REST_PATH_GET_ACTIVITY, parameterMap);

			activityVO = JugglerUtils.convertToActivityObjectFromJsonString(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activityVO;
	}

	@RequestMapping(value = "/showSuggestedActivity", method = RequestMethod.GET)
	public ModelAndView showSuggestedActivity(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView("ActivitySuggestion");
		Map<String, String> parameterMap = new HashMap<String, String>();
		List<ActionVO> actionVOList = new ArrayList<ActionVO>();
		parameterMap.put(JugglerConstants.REST_INPUT_RETRIEVAL_TYPE, "hobbies");
		parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
		try {
			String output = connectionEstablisher.connectRest(JugglerConstants.REST_PATH_GET_SUGGESTED_ACTIVITY,
					parameterMap);
			JSONArray array = new JSONArray(output);
			for (int i = 0; i < array.length(); i++) {

				ActivityVO activityVO = JugglerUtils.convertToActivityObjectFromJsonString(array.getString(i));

				ActionVO actionVO = JugglerUtils.convertFromActivityVOToActionVO(activityVO);
				actionVO.setActivityVO(activityVO);

				actionVOList.add(actionVO);
			}
			modelAndView.addObject("activitySuggestion", actionVOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = { "/participateAction" }, method = RequestMethod.POST)
	public @ResponseBody void participateAction(@RequestParam(value = "activityId") String activityId) {
		try {
			Map<String, String> parameterMap = new HashMap<String, String>();
			parameterMap.put(JugglerConstants.REST_INPUT_ACTIVITYID, activityId);
			parameterMap.put(JugglerConstants.REST_INPUT_ACTION, JugglerConstants.ACTION_PARTICIPATE);
			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
			connectionEstablisher.connectRest(JugglerConstants.REST_PATH_PERSIST_ACTION, parameterMap);

			Map<String, String> parameterMap2 = new HashMap<String, String>();
			parameterMap2.put("activityId", activityId);
			parameterMap2.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
			connectionEstablisher.connectRest("addParticipant", parameterMap2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -----------------------------------FRIENDS-------------------------------------------//

	@RequestMapping(value = { "/friendsMainPage" }, method = RequestMethod.GET)
	public String friendsMainPage() {
		return JugglerConstants.JSP_PAGE_FRIENDS_MAIN;
	}

	@RequestMapping(value = "/showFriendSuggestion", method = RequestMethod.GET)
	public ModelAndView showFriendSuggestion(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView(JugglerConstants.JSP_PAGE_FRIEND_SUGGESTION);
		List<UserCreateVO> userCreateVOList = new ArrayList<UserCreateVO>();
		try {

			Map<String, String> parameterMap = new HashMap<String, String>();
			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
			String output = connectionEstablisher.connectRest(JugglerConstants.REST_PATH_FRIEND_SUGGESTION,
					parameterMap);
			JSONArray array = new JSONArray(output);
			for (int i = 0; i < array.length(); i++) {
				userCreateVOList.add(JugglerUtils.convertToUserObjectFromJsonString(array.getString(i)));
			}
			modelAndView.addObject(JugglerConstants.JSP_FRIEND_SUGGESTION, userCreateVOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = { "/addFriend" }, method = RequestMethod.POST)
	public @ResponseBody String addFriend(@RequestParam(value = "friendId") String friendId) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		try {

			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_FRIEND_ID, friendId);
			connectionEstablisher.connectRest(JugglerConstants.REST_PATH_ADD_FRIEND, parameterMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friendId;
	}

	@RequestMapping(value = { "/removeFriend" }, method = RequestMethod.POST)
	public @ResponseBody String removeFriend(@RequestParam(value = "friendId") String friendId) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		try {

			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_FRIEND_ID, friendId);
			connectionEstablisher.connectRest("removeFriend", parameterMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friendId;
	}

	@RequestMapping(value = { "/acceptFriend" }, method = RequestMethod.POST)
	public @ResponseBody String acceptFriend(@RequestParam(value = "friendId") String friendId) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		try {

			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_FRIEND_ID, friendId);
			connectionEstablisher.connectRest(JugglerConstants.REST_PATH_ACCEPT_FRIEND, parameterMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friendId;
	}

	@RequestMapping(value = { "/cancelFriend" }, method = RequestMethod.POST)
	public @ResponseBody String cancelFriend(@RequestParam(value = "friendId") String friendId) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		try {

			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
			parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_FRIEND_ID, friendId);
			connectionEstablisher.connectRest(JugglerConstants.REST_PATH_CANCEL_FRIEND, parameterMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friendId;
	}

	@RequestMapping(value = "/showSentFriendRequest", method = RequestMethod.GET)
	public ModelAndView showSentFriendRequest(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView(JugglerConstants.JSP_PAGE_SENT_FRIEND_REQUEST);
		Map<String, String> parameterMap = new HashMap<String, String>();
		List<UserCreateVO> userCreateVOList = new ArrayList<UserCreateVO>();
		parameterMap.put(JugglerConstants.REST_INPUT_RETRIEVAL_TYPE,
				JugglerConstants.REST_INPUT_RECIEVED_FRIEND_REQUESTS);
		parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
		try {
			String output = connectionEstablisher.connectRest(JugglerConstants.REST_PATH_GET_USER_FRIEND_DETAILS,
					parameterMap);
			JSONArray array = new JSONArray(output);
			for (int i = 0; i < array.length(); i++) {
				userCreateVOList.add(JugglerUtils.convertToUserObjectFromJsonString(array.getString(i)));
			}
			modelAndView.addObject(JugglerConstants.JSP_SENT_FRIEND_REQUEST, userCreateVOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/showRecievedFriendRequest", method = RequestMethod.GET)
	public ModelAndView showRecievedFriendRequest(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView(JugglerConstants.JSP_PAGE_RECIEVED_FRIEND_REQUEST);
		Map<String, String> parameterMap = new HashMap<String, String>();
		List<UserCreateVO> userCreateVOList = new ArrayList<UserCreateVO>();
		parameterMap.put(JugglerConstants.REST_INPUT_RETRIEVAL_TYPE, JugglerConstants.REST_INPUT_SENT_FRIEND_REQUESTS);
		parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
		try {
			String output = connectionEstablisher.connectRest(JugglerConstants.REST_PATH_GET_USER_FRIEND_DETAILS,
					parameterMap);
			JSONArray array = new JSONArray(output);
			for (int i = 0; i < array.length(); i++) {
				userCreateVOList.add(JugglerUtils.convertToUserObjectFromJsonString(array.getString(i)));
			}
			modelAndView.addObject(JugglerConstants.JSP_RECIEVED_FRIEND_REQUEST, userCreateVOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/showMyFriends", method = RequestMethod.GET)
	public ModelAndView showMyFriends(ModelMap model) {
		ModelAndView modelAndView = new ModelAndView(JugglerConstants.JSP_PAGE_MY_FRIENDS);
		Map<String, String> parameterMap = new HashMap<String, String>();
		List<UserCreateVO> userCreateVOList = new ArrayList<UserCreateVO>();
		parameterMap.put(JugglerConstants.REST_INPUT_RETRIEVAL_TYPE, JugglerConstants.REST_INPUT_FRIENDS);
		parameterMap.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, sessionVO.getUserId());
		try {
			String output = connectionEstablisher.connectRest(JugglerConstants.REST_PATH_GET_USER_FRIEND_DETAILS,
					parameterMap);
			JSONArray array = new JSONArray(output);
			for (int i = 0; i < array.length(); i++) {
				userCreateVOList.add(JugglerUtils.convertToUserObjectFromJsonString(array.getString(i)));
			}
			modelAndView.addObject(JugglerConstants.JSP_MY_FRIENDS, userCreateVOList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(ModelMap model) {
		model.addAttribute("requestscopedate", requestscopehellodata.getDate());
		model.addAttribute("sessionscopedate", sessionscopehellodata.getDate());
		return "Test";
	}

}