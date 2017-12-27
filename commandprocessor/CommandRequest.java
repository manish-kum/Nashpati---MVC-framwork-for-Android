package com.bottlesoftware.nashpati.commandprocessor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * <p>This acts as model of framework.</p>
 * @author Manish
 * @version 1.0.0
 */
public class CommandRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 100000L;
	private String commandName;
	private Map <String,Object> attributes;
	
	public CommandRequest(){
		attributes = new HashMap<String,Object>();
	}
	
	public String getCommandName() {
		return commandName;
	}
	/**
	 * <p>Set Command to be Executed.</p>
	 * @author Manish
	 *
	 */
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	/**
	 * <p>Bind value with corresponding key.</p>
	 * @param key
	 * @param value
	 */
	public void setAttribute(String key,Object value){
		attributes.put(key,value);
	}
	/**
	 * <p>Get value with corresponding key. Returns null, if key is not found.</p>
	 * @param key
	 * @return Object 
	 */
	public Object getAttribute(String key){
		return attributes.get(key);

	}
	/**
	 * <p>Get result of the execution of Command. If not set, Return null. To make Result Available, Resultant Object must be bound with key "result" </p>
	 * @return Object
	 */
	public Object getResult() {
		return attributes.get("result");
	}
}
