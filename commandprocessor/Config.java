package com.bottlesoftware.nashpati.commandprocessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;


import android.content.res.XmlResourceParser;
/**
 * 
 * 
 * @author Manish
 * @version 1.0.0
 */
class Config {
	private Map<String, String> commandMap = new HashMap<String, String>();
	//private Map<String, List<String>> commandChainMap = new HashMap<String, List<String>>();
	private static volatile Config config;
	private Map<String,String> commandViews = new HashMap<String,String>();
	private Map<String ,ConfigCommandChain> commandchainMap = new HashMap<String ,ConfigCommandChain>();
	private String exceptionView;
	private Config(){ }
	public static synchronized Config getConfig(XmlResourceParser parser)throws Exception{
		if(config == null){
			config =new Config();
			config.parse(parser);
		}
		return config;
	}
	private void parse(XmlResourceParser parser)throws Exception {
	 int eventType = parser.getEventType();
     while(!(eventType == XmlPullParser.END_DOCUMENT)){
            String tagName = parser.getName();
            if(eventType == XmlPullParser.START_TAG){
                  if(tagName.equalsIgnoreCase("command")){
                         parseCommand(parser);
                  }else if(tagName.equalsIgnoreCase("commandchain")){
                         parseCommandChain(parser);
                  }else if(tagName.equalsIgnoreCase("commandviews")){
                	  parseCommandViews(parser);
                  }else if(tagName.equalsIgnoreCase("exceptionview")){
                	  parser.next();
                	  exceptionView = parser.getText();
                  }
            }
            eventType = parser.next();
     }
	}
	private void parseCommandViews(XmlResourceParser parser) throws Exception {
		String viewName="";
		String viewClassName="";
	     String tagName = parser.getName();
	     int eventType = parser.getEventType();
	     while(!(eventType == XmlPullParser.END_TAG && tagName !=null && tagName.equalsIgnoreCase("command-view"))){
	
	     if(eventType == XmlPullParser.START_TAG){
	            tagName = parser.getName();
	            if(tagName.equalsIgnoreCase("view-name")){
	                  parser.next();
	                  viewName = parser.getText();
	            }else if(tagName.equalsIgnoreCase("view-class")){
	                  parser.next();
	                  viewClassName = parser.getText();
	            }
	            
	     }
	    
	     eventType = parser.next();
	     tagName = parser.getName();
	    
	     }
	     
	     commandViews.put(viewName, viewClassName);		
	}
	private void parseCommand(XmlResourceParser parser) throws Exception {
		String commandName="";
		String commandClassName="";
	     String tagName = parser.getName();
	     int eventType = parser.getEventType();
	     while(!(eventType == XmlPullParser.END_TAG && tagName !=null && tagName.equalsIgnoreCase("command"))){
	
	     if(eventType == XmlPullParser.START_TAG){
	            tagName = parser.getName();
	            if(tagName.equalsIgnoreCase("command-name")){
	                  parser.next();
	                  commandName = parser.getText();
	            }else if(tagName.equalsIgnoreCase("command-class")){
	                  parser.next();
	                  commandClassName = parser.getText();
	            }
	            
	     }
	    
	     eventType = parser.next();
	     tagName = parser.getName();
	    
	     }
	     
	     commandMap.put(commandName, commandClassName);
	}

	public  void parseCommandChain(XmlPullParser parser) throws Exception{
	    
	     String name = parser.getAttributeValue(null,"name");
	     ConfigCommandChain configCommandChain = new ConfigCommandChain();
	     List<String> commandNames = new ArrayList<String>();
	     int eventType = parser.next();
	     String tagName = parser.getName();
	     configCommandChain.setCommandChainName(name);
	     while(!(eventType == XmlPullParser.END_TAG && tagName !=null && tagName.equalsIgnoreCase("commandchain"))){
	            if(eventType == XmlPullParser.START_TAG  && tagName.equalsIgnoreCase("command-name")){
	                  eventType = parser.next();
	                  String commandName = parser.getText();
	                  commandNames.add(commandName);
	            }else if(eventType == XmlPullParser.START_TAG  && tagName.equalsIgnoreCase("command-view")){
	            	  eventType = parser.next();
	                  String commandView = parser.getText();
	                  String commandViewClass = commandViews.get(commandView);
	                  configCommandChain.setNextView(commandViewClass); 	
	            }
	            eventType = parser.next();
	            tagName = parser.getName();      
	     }
	           
	     configCommandChain.setCommands(commandNames);
	     commandchainMap.put(name, configCommandChain);
	}

	public Map<String, String> getCommandMap() {
		return commandMap;
	}
	public void setCommandMap(Map<String, String> commandMap) {
		this.commandMap = commandMap;
	}
	public static Config getConfig() {
		return config;
	}
	public Map<String, ConfigCommandChain> getCommandchainMap() {
		return commandchainMap;
	}
	public void setCommandchainMap(Map<String, ConfigCommandChain> commandchainMap) {
		this.commandchainMap = commandchainMap;
	}
	public String getExceptionView() {
		return exceptionView;
	}
}
