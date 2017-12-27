package com.bottlesoftware.nashpati.commandprocessor;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import android.app.Activity;
import android.content.Context;
import android.content.res.XmlResourceParser;

import com.bottlesoftware.nashpati.session.Session;
/**
 * <p>MVC Controller.</p>
 * 
 * @author Manish
 * @version 1.1.0
 */
public class CommandController {
	
	private static volatile CommandController controller;
	private Config config;
	private Session session;
	
	private CommandController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static synchronized CommandController getController(Context context, int id) throws Exception{
		if(controller ==null){
			controller = new CommandController();
			XmlResourceParser xmlResourceParser =  context.getResources().getXml(id);
			Config config = Config.getConfig(xmlResourceParser);
			controller.setConfig(config);
			controller.setSession(Session.getSession());
			return controller;
		}
		return controller;
	}
	
	public static CommandController getController(Context context){
		if(controller ==null){
		 ClassLoader defaultLoder = context.getClassLoader();
		   String defaultPackage = context.getPackageName();
		try {
			Class defaultResourceClass = defaultLoder.loadClass(defaultPackage+".R$xml");
			int commandsXML = defaultResourceClass.getDeclaredField("commands").getInt(null);
			 controller = getController(context, commandsXML);
		
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return controller;
	}
	
	private void setConfig(Config config) {
		this.config = config;
	}
	public boolean handleRequest( Context context,CommandRequest request ) throws Exception{
		serveRequest(context, request);
		return true;
	}
	
	public boolean serveRequest(Context context, CommandRequest request) throws Exception{
		
		ConfigCommandChain configCommandChain = config.getCommandchainMap().get(request.getCommandName());
		
		List<String> commandList = configCommandChain.getCommands();
		String nextActivityClass = configCommandChain.getNextView();
		String exceptionActivityClass = config.getExceptionView();
		Activity nextActivity = (Activity) Class.forName(nextActivityClass).newInstance();
		Activity exceptionActivity = (Activity) Class.forName(exceptionActivityClass).newInstance();
		Map<String, String> commandMap = config.getCommandMap();
		Queue<Command> commands = new ArrayBlockingQueue<Command>(10);
		CommandChain commandChain = new CommandChain(commands,nextActivity, exceptionActivity);
		Command command;
		for(String commandName:commandList)
		{
			String commandClass = commandMap.get(commandName);
			command = (Command)Class.forName(commandClass).newInstance();
			command.setContext(context);
			command.setCommandChain(commandChain);
			command.setSession(session);
			command.setRequest(request);
			commands.add(command);
		}
		commandChain.execute(context, request);
		
		return true;

	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
