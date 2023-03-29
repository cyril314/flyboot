package com.fit.core.log;

import ch.qos.logback.core.PropertyDefinerBase;
import org.springframework.boot.system.ApplicationHome;

import java.io.File;

/**
 * @Author Aim
 * @Des 日志路径
 * @DATE 2022/7/20
 */
public class LogPathDefiner extends PropertyDefinerBase {

	@Override
	public String getPropertyValue() {
		ApplicationHome h = new ApplicationHome(getClass());
		File jarF = h.getSource();
		String LogPath = jarF.getParentFile().toString() + "/logs/";
		System.out.println(" - 日志存放路径: " + LogPath);
		return LogPath;
	}
}