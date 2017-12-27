package com.bottlesoftware.nashpati.commandprocessor;

import java.util.List;
/**
 * 
 *
 * @author Manish
 * @version 1.0.0
 */
class ConfigCommandChain {
	private String commandChainName;
	private List<String> commands;
	private String nextView;
	
	public String getCommandChainName() {
		return commandChainName;
	}
	public void setCommandChainName(String commandChainName) {
		this.commandChainName = commandChainName;
	}
	public List<String> getCommands() {
		return commands;
	}
	public void setCommands(List<String> commands) {
		this.commands = commands;
	}
	public String getNextView() {
		return nextView;
	}
	public void setNextView(String nextView) {
		this.nextView = nextView;
	}

}
