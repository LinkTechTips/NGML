package org.NGML.until;

import javafx.application.Platform;
import org.NGML.INIT;
import org.NGML.Info;
import org.NGML.checkUpdate;
import org.NGML.dataStorage.Client;
import org.NGML.zip;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class McClientDownload implements Runnable{
    static Logger logger = LogManager.getLogger(McClientDownload.class);
    static boolean f=false;
    public  McClientDownload() throws IOException {

    }
    public McClientDownload(boolean f)
    {
        this.f =f;
    }
    @Override
    public void run() {
        if (f){
            Platform.runLater(() -> {
                Info.info(1500,"未找到更新包，开始完整下载",false);
                try {
                    Thread.sleep(1600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        File client = new File(System.getProperty("user.dir")+"//.minecraft");
        if (client.exists())
        {
            //System.out.println(client);
            logger.info("正在删除旧客户端");
            Platform.runLater(() -> {
                Info.info(0,"正在删除旧客户端",true);
            });
            File data=new File("data");
            for (File file : Objects.requireNonNull(data.listFiles())) {

            }
            if (!INIT.clientList.isEmpty())
                INIT.clientList.remove();
            //Info msg=new Info("正在删除旧客户端");
            //new Thread(msg).start();
            deleteAll(client);
            logger.info("客户端删除完成");
            Platform.runLater(Info::infoClose);


            //deleteFile(client);
        }


        try {
            logger.info("开始下载最新客户端:"+getClientVersion()+".zip");

            logger.info("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            logger.info("开始解压客户端");
            zip.decompressZip(System.getProperty("user.dir") + "/" + getClientVersion() + ".zip",System.getProperty("user.dir")+"/");
            logger.info("解压客户端成功");
        } catch (IOException | ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        logger.info("开始存储客户端配置文件");
        try {
            Client c=new Client(getClientName(),getClientVersion());
            c.saveToFile();
            INIT.clientList.add(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("存储客户端配置文件完成");
    }
    public static String getClientVersion() throws IOException {
        return checkUpdate.RemoteJsonConfig.getString("ClientVersion");
    }
    public static String getClientName() throws IOException {
        return checkUpdate.RemoteJsonConfig.getString("ClientName");
    }
    public static void deleteAll(File file) {

        if (file.isFile() || Objects.requireNonNull(file.list()).length == 0) {
            if (!file.delete())
                logger.info(file+"文件删除失败");
        } else {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                deleteAll(f); // 递归删除每一个文件
            }
            if (!file.delete())
                logger.info(file+"文件夹删除失败");
        }
    }
}