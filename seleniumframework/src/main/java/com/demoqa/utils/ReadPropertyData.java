package com.demoqa.utils;

public interface ReadPropertyData {
	public String readProperty(String key);

	public Boolean isRunningDebug();

	public String getResultsFolderPath();

	public String getPlatformVersion();

	public boolean isPrintLogsToConsole();
}
