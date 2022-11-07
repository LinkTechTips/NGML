package org.NGML;

import javafx.application.Application;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public final class Main {
    public static String projectName = "New Generation Minecraft Launcher";
    public static String Version = Metadata.M2_VERSION;
    public static String getTitle() {
        return projectName + ' ' + Version;
    }
    static Logger logger= LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("开始初始化…");
        Application.launch(INIT.class);
    }
}