package com.bottlesoftware.nashpati.commandprocessor;

import java.util.Queue;

import android.app.Activity;
import android.content.Context;

import com.bottlesoftware.nashpati.command.ExceptionCommand;
import com.bottlesoftware.nashpati.command.RenderAcivityCommand;
/**
 * 
 * 
 * @author Manish
 * @version 1.0.0
 */
public class CommandChain{
	private Queue<Command> commands;
	private Activity nextActivity;
	private Activity exceptionActivity;
    CommandChain(Queue<Command> commands, Activity nextActivity, Activity exceptionActivity){
	                this.commands = commands;
	                this.nextActivity = nextActivity;
	                this.exceptionActivity = exceptionActivity;
	}
	public boolean execute(Context context, CommandRequest request){
		if(!commands.isEmpty()){
		try {
			commands.poll().execute();
			} catch (Exception e) {
				executeExceptionCommand(context,request,e);	
		}
		}else{
			Command c = new RenderAcivityCommand();
			c.setContext(context);
			c.setRequest(request);
			c.setCommandChain(this);
			try {
				c.execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				executeExceptionCommand(context,request,e);	
			}
		}
	       return true;
	}
	private void executeExceptionCommand( Context context, CommandRequest request, Exception e){
		Command exceptionCommand = new ExceptionCommand();
		request.setAttribute("exception", e);
		exceptionCommand.setContext(context);
		exceptionCommand.setRequest(request);
		
		try {
			exceptionCommand.execute();
		} catch (Exception e1) {
		e1.printStackTrace();
	}
	}
	
	public Activity getNextActivity() {
		return nextActivity;
	}
	public Activity getExceptionActivity() {
		return exceptionActivity;
	}

}
