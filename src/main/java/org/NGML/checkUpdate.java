package org.NGML;

import com.alibaba.fastjson.JSONObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class checkUpdate {
    static Logger logger= LogManager.getLogger(checkUpdate.class);
    public static JSONObject RemoteJsonConfig;
    public static void loader() throws IOException, InterruptedException {

        logger.info("获取远程配置文件");
        File c = new File("temp/config.json");
        c.delete();
    }
}