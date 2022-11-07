package org.NGML;

import javafx.application.Platform;
import org.NGML.until.Ui;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author MakesYT
 */
public class Download {
    public static long contentLength;
    public static long alreadyDownloadLength;
    public static long speed;
    static Logger logger = LogManager.getLogger(Download.class);
    public static void downloadFile(String fileURL,String savePath) throws IOException {
        logger.info("开始下载:"+fileURL+"的文件,保存到:"+savePath);
        Thread thread = new Thread(() -> {
            while (true)
            {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.setName("下载进度条");



        Platform.runLater(() -> {
            Ui.progress.setText("");
        });


        logger.info("下载完成");
    }

    /*
     */
}