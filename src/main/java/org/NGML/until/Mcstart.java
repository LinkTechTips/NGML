package org.NGML.until;


import org.NGML.INIT;
import org.NGML.Info;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Mcstart {
    static Logger logger = LogManager.getLogger(Mcstart.class);
    public static void startMC() throws IOException {
        logger.info("开始启动游戏");

        String java;
        if (INIT.clientList.getFirst().getJava().equals("auto"))
        {
            java="\""+System.getProperty("java.home") + "\\bin\\javaw.exe\"";
        }else{
            java="\""+INIT.clientList.getFirst().getJava()+"\"";
        }java=java.replace("\\","\\\\");

        logger.info("尝试启动..");
        try{
            if (INIT.clientList.getFirst().getClientName().isEmpty())
            {
                Info.info(3000, "当前客户端不支持启动", false);
                logger.info("当前客户端不支持启动");
            }else
            {
                init();
                Info.info(3000,"游戏已启动请等待运行,启动器将自动退出",false);
                logger.info("C:/Windows/System32/cmd.exe /k gml-windows.exe -run "+INIT.clientList.getFirst().getClientName()+" -email "+INIT.userList.getFirst().getUsername()
                        +" -password "+INIT.userList.getFirst().getPassword()+" -yggdrasil ddns.xiaomckedou233.top:34002 -ram "+INIT.clientList.getFirst().getRAM()+" -javapath "+java);
                Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe /k gml-windows.exe -run "+INIT.clientList.getFirst().getClientName()+" -email "+INIT.userList.getFirst().getUsername()
                        +" -password "+INIT.userList.getFirst().getPassword()+" -yggdrasil ddns.xiaomckedou233.top:34002 -ram "+INIT.clientList.getFirst().getRAM()+" -javapath "+java);

                Thread t = new Thread(new Runnable(){
                    public void run(){
                        // run方法具体重写
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.exit(1);
                    }});
                t.start();
            }
        }catch (NullPointerException e)
        {
            Info.info(3000, "当前客户端不支持启动", false);
            logger.info("当前客户端不支持启动");
        }

    }
    public static void init() throws IOException {

        File gml=new File("gml-windows.exe");
        if (!gml.exists())
        {
            logger.info("gml不存在开始下载");

            logger.info("gml下载完成");
        }
    }

}