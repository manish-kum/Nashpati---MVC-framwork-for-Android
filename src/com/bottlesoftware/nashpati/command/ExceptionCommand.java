package com.bottlesoftware.nashpati.command;

import android.app.Activity;
import android.content.Intent;

import com.bottlesoftware.nashpati.commandprocessor.AbstractCommand;

/**
 * <p>This Command renders Exception Activityin case of Exception occers.</p>
 * @author Manish
 *@version 1.0.0
 */
 public class ExceptionCommand extends AbstractCommand {

	public boolean execute() {
		Activity activity = commandChain.getExceptionActivity();
		Intent intent = new Intent(context,activity.getClass());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("request", request);
		context.startActivity(intent);
		return true;
	}
}
