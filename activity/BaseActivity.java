package com.bottlesoftware.nashpati.activity;

import android.app.Activity;
import android.content.Context;

import com.bottlesoftware.nashpati.commandprocessor.CommandController;
import com.bottlesoftware.nashpati.commandprocessor.CommandRequest;
import com.bottlesoftware.nashpati.session.Session;

/**
 *<p> Super Class for all Activity. </p>
 *
 * @author Manish
 * @version 1.0.0
 */
public abstract class BaseActivity extends Activity{
protected  CommandController controller;
protected Session session = Session.getSession();


/**
 * <p>Executes command and return result </p>
 * @param request
 * @return Object
 * @throws Exception
 */
public Object executeCommand( CommandRequest request) throws Exception{
	Context context = getApplicationContext();
	if(controller ==null){
		controller = CommandController.getController(context);
	}
    controller.handleRequest(context, request);
    return request.getResult();
}










}