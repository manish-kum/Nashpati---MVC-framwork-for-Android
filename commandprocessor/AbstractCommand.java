package com.bottlesoftware.nashpati.commandprocessor;

import android.content.Context;

import com.bottlesoftware.nashpati.session.Session;
/**
 * <p>All Commands must inherit this class.</p>
 * @author Manish
 *@version 1.1.0
 */
public abstract class AbstractCommand implements Command{
	protected Context context;
	protected CommandChain commandChain;
	protected CommandRequest request;
	protected Session session;

	public void setCommandChain(CommandChain commandChain) {
		this.commandChain = commandChain;
	}
	public String getCommandDescription(){
		return "Default Command";
	}
	
	/**
	 * <p>Executes next Command in series or render next Activity.</p>
	 */
	public void executeNext(){
		commandChain.execute(context, request);
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public CommandRequest getRequest() {
		return request;
	}
	public void setRequest(CommandRequest request) {
		this.request = request;
	}
	public void setSession(Session session) {
		this.session = session;
	}
}
