package com.bottlesoftware.nashpati.session;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.bottlesoftware.nashpati.commandprocessor.CommandController;

/**
 * 
 * @author Manish
 * @version 1.1.0
 */
public class Session {

	private Map<String,Object> sessionMap = new HashMap<String,Object>();
	private static volatile Session session;	
	private Session(){}
	
	public static synchronized Session getSession(){
		if(session == null){
			session = new Session();			
		}
		return session;
	}
	
	public void setAttribute(String key, Object object) {
		sessionMap.put(key, object);
	}

	public Object getAttribute(String key) {
		// TODO Auto-generated method stub
		return sessionMap.get(key);
	}
}
