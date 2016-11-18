package com.demoqa.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class FilePathUtils {
  static Logger logger = Logger.getLogger(FilePathUtils.class);

  public static String getFullPathOfFileFromClassPath(final String browserImplExeFileName) {
    return FilePathUtils.class.getClassLoader().getResource(browserImplExeFileName).getFile();
  }

  public static String getDriverFullPath(final String browserName) {
    String driverPath = getDriverFromJar(browserName, true);
    if (driverPath != null) {
      return driverPath;
    }
    logger.warn("there is no matching binary for our system architecture");
    System.out.println("there is no matching binary for our system architecture");
    driverPath = getDriverFromJar(browserName, false);
    return driverPath;

  }

  private static String getDriverFromJar(final String browserName, final boolean withoutArchOps) {
    SystemArchitecture systemArc = SystemArchitecture.getCurrentSystemArcitecture();
    OperatingSystem ops = OperatingSystem.getCurrentOperatingSystem();
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    Resource[] resources = null;
    try {
      resources = resolver.getResources("/binaries/**");
    } catch (IOException e) {
      e.printStackTrace();
    }
    String filePath = null;
    for (Resource resource : resources) {
      try {
        filePath = resource.getURL().getPath();
        if (withoutArchOps) {
          if (filePath.contains(".") & filePath.contains(browserName)
              & filePath.contains(ops.getOperatingSystemType())
              & filePath.contains(systemArc.getSystemArchitectureType()))
            return filePath;
        } else if (filePath.contains(".") & filePath.contains(browserName))
          return filePath;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return filePath;
  }

  public static String getScreenShotFullPath(final String fileName) {
    StringBuffer stringTotalName = new StringBuffer();
    stringTotalName.append(System.getProperty("user.dir"));
    stringTotalName.append("/" + fileName);
    return stringTotalName.toString();

  }

  public static String getScreenCastFolderPath(final String folderName) {
    StringBuffer stringTotalName = new StringBuffer();
    stringTotalName.append(System.getProperty("user.dir"));
    stringTotalName.append("\\ScreenCast\\");
    stringTotalName.append("\\" + folderName);
    return stringTotalName.toString();

  }

  public static void main(final String[] args) {
    System.out.println("main method " + getDriverFullPath("geckodriver.exe"));
  }

}
