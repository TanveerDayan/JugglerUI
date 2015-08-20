package com.juggler.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.juggler.constants.JugglerConstants;
import com.juggler.utils.ConnectionEstablisher;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	ConnectionEstablisher connectionEstablisher;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails userDetail = null;
		
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap
				.put(JugglerConstants.REST_INPUT_VALIDATE_USER_ID, username);
		try {
			String output = (connectionEstablisher.connectRest(
					JugglerConstants.REST_PATH_VALIDATE_USER, parameterMap));
			JSONObject jsonObject = new JSONObject(output);
			if (jsonObject.has(JugglerConstants.JSON_EMAILID)
					&& jsonObject.has(JugglerConstants.JSON_PASSWORD)
					&& jsonObject.has(JugglerConstants.JSON_ROLENAME)) {
				GrantedAuthority authority = new GrantedAuthorityImpl(
						jsonObject.getString(JugglerConstants.JSON_ROLENAME));
				Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>();
				authoritySet.add(authority);
				userDetail = new UserDetailsImpl(
						jsonObject.getString(JugglerConstants.JSON_EMAILID),
						jsonObject.getString(JugglerConstants.JSON_PASSWORD),
						authoritySet);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetail;
	}

}
