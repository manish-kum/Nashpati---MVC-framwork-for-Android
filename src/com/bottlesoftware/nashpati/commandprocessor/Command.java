package com.bottlesoftware.nashpati.commandprocessor;

import com.bottlesoftware.nashpati.session.Session;

import android.content.Context;
/**
 * 
 * @author Manish
 * @version 1.1.0
 */
interface Command {
	/**
	 * <p>All commands must override this method.This method encapsulates the business logic.</p>
	 */
	public boolean execute() throws Exception;
	public String getCommandDescription();
	public void setContext(Context context);
	public void setRequest(CommandRequest request);
	public void setCommandChain(CommandChain commandChain);
	public void setSession(Session session);

}
