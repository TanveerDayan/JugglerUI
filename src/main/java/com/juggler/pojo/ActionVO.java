package com.juggler.pojo;

import java.util.List;

public class ActionVO {

	private long timeStamp;
	private String activityId;
	private String userId;
	private String action;
	private List<String> friends;
	private ActivityVO activityVO;
	private boolean participatedFlag;

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	public ActivityVO getActivityVO() {
		return activityVO;
	}

	public void setActivityVO(ActivityVO activityVO) {
		this.activityVO = activityVO;
	}

	public boolean isParticipatedFlag() {
		return participatedFlag;
	}

	public void setParticipatedFlag(boolean participatedFlag) {
		this.participatedFlag = participatedFlag;
	}

	@Override
	public String toString() {
		return "ActionVO [timeStamp=" + timeStamp + ", activityId="
				+ activityId + ", userId=" + userId + ", action=" + action
				+ ", friends=" + friends + ", activityVO=" + activityVO
				+ ", participatedFlag=" + participatedFlag + "]";
	}

}
