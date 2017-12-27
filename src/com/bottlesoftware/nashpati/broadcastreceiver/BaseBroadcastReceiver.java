package com.bottlesoftware.nashpati.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;

import com.bottlesoftware.nashpati.commandprocessor.CommandController;
import com.bottlesoftware.nashpati.commandprocessor.CommandRequest;
import com.bottlesoftware.nashpati.session.Session;
/**
 * 
 * @author Manish
 * @version 1.1.0
 *
 */
public  abstract class BaseBroadcastReceiver extends BroadcastReceiver {
	protected CommandController controller;
	protected Session session = Session.getSession();
	
    public Object executeCommand( Context context, CommandRequest request) {
        	if(controller==null){
        		controller = CommandController.getController(context);
        	}
    	try {
    		
			controller.handleRequest(context, request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return request.getResult();
    }
	
}
