package com.juggler.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.juggler.constants.JugglerConstants;
import com.juggler.pojo.ActionVO;
import com.juggler.pojo.ActivityVO;
import com.juggler.pojo.UserCreateVO;

public class JugglerUtils {

	public static UserCreateVO convertToUserObjectFromJsonString(
			String jsonObject) throws JsonParseException, JsonMappingException,
			IOException {
		UserCreateVO userCreateVO = null;
		userCreateVO = new ObjectMapper().readValue(jsonObject,
				UserCreateVO.class);
		return userCreateVO;
	}

	public static ActionVO convertToActionObjectFromJsonString(String jsonObject)
			throws JsonParseException, JsonMappingException, IOException {
		ActionVO actionVO = null;
		actionVO = new ObjectMapper().readValue(jsonObject, ActionVO.class);
		return actionVO;
	}

	public static ActivityVO convertToActivityObjectFromJsonString(
			String jsonObject) throws JsonParseException, JsonMappingException,
			IOException {
		ActivityVO activityVO = null;
		activityVO = new ObjectMapper().readValue(jsonObject, ActivityVO.class);
		return activityVO;
	}

	public static ActionVO convertFromActivityVOToActionVO(ActivityVO activityVO) {
		ActionVO actionVO = new ActionVO();
		actionVO.setAction(JugglerConstants.ACTION_SUGGESTS);
		actionVO.setActivityId(activityVO.getId());
		actionVO.setUserId(activityVO.getActivityOwnerId());
		actionVO.setTimeStamp(Long.parseLong(activityVO.getId().split(
				JugglerConstants.UNDERSCORE)[1]));
		return actionVO;
	}

	public static ActionVO convertToActionObjectFromActivityObject(
			ActivityVO activityVO) {
		ActionVO actionVO = null;
		return actionVO;
	}
}
