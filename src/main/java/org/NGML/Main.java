package org.NGML;

import org.to2mbn.jmccc.auth.yggdrasil.YggdrasilAuthenticator;
import org.to2mbn.jmccc.launch.LaunchException;
import org.to2mbn.jmccc.launch.Launcher;
import org.to2mbn.jmccc.launch.LauncherBuilder;
import org.to2mbn.jmccc.mcdownloader.MinecraftDownloader;
import org.to2mbn.jmccc.mcdownloader.MinecraftDownloaderBuilder;
import org.to2mbn.jmccc.mcdownloader.download.concurrent.CallbackAdapter;
import org.to2mbn.jmccc.mcdownloader.download.concurrent.DownloadCallback;
import org.to2mbn.jmccc.mcdownloader.download.tasks.DownloadTask;
import org.to2mbn.jmccc.option.LaunchOption;
import org.to2mbn.jmccc.option.MinecraftDirectory;
import org.to2mbn.jmccc.version.Version;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class Main {
    public static String projectName = "New Generation Minecraft Launcher";
    public static String Version = Metadata.M2_VERSION;
    public static String getTitle() {
        return projectName + ' ' + Version;
    }
    private static final Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());
    public static String WorkingDir = System.getProperty("user.dir");
    private static void setupSystemProperties() {
        System.setProperty("org.NGML.version", Metadata.M2_VERSION);
        System.setProperty("NGML.readPluginToMem", "true");
        System.setProperty("NGML.forciblyExit", "true");
        System.setProperty("NGML.overwriteSystemPlugins", "true");
        System.setProperty("NGML.hackCss", "true");
        System.setProperty("NGML.windowShadow", "true");
        System.setProperty("org.ehcache.sizeof.AgentSizeOf.bypass", "true");
    }
    private static void addWorkingDir() throws IOException {
        File NGMLDir = new File(".NGML");
        if (NGMLDir.exists()) {
            if (NGMLDir.isFile()) {
                JOptionPane.showMessageDialog(null, "请将当前目录下的.NGML运行文件删除后再启动", Metadata.NGML_NAME, JOptionPane.ERROR_MESSAGE);
                throw new IOException("当前目录下存在.NGML文件,而不是目录");
            } else {
                if (!NGMLDir.mkdirs()) {
                    throw new IOException("无法建立.NGML目录!");
                }
            }
        }
    }
    private static void DownloadingMinecraft() {
        MinecraftDirectory dir = new MinecraftDirectory(WorkingDir + ".NGML/.minecraft");
        MinecraftDownloader downloader = MinecraftDownloaderBuilder.buildDefault();
        downloader.downloadIncrementally(dir, "1.16.5", new CallbackAdapter<>() {
            @Override
            public void failed(Throwable e) {
                // when the task fails
            }

            @Override
            public void done(Version result) {
                // when the task finishes
            }

            @Override
            public void cancelled() {
                // when the task cancels
            }

            @Override
            public <R> DownloadCallback<R> taskStart(DownloadTask<R> task) {
                // when a new sub download task starts
                // return a DownloadCallback to listen the status of the task
                return new CallbackAdapter<>() {
                    @Override
                    public void done(R result) {
                        // when the sub download task finishes
                    }

                    @Override
                    public void failed(Throwable e) {
                        // when the sub download task fails
                    }

                    @Override
                    public void cancelled() {
                        // when the sub download task cancels
                    }

                    @Override
                    public void updateProgress(long done, long total) {
                        // when the progress of the sub download task has updated
                    }

                    @Override
                    public void retry(Throwable e, int current, int max) {
                        // when the sub download task fails, and the downloader decides to retry the task
                        // in this case, failed() won't be called
                    }
                };
            }
        });
    }
    private static void LaunchingMinecraft() {
        MinecraftDirectory dir = new MinecraftDirectory(WorkingDir + ".NGML/.minecraft");
        Launcher launcher = LauncherBuilder.buildDefault();
        try {
            launcher.launch(new LaunchOption("1.16.5", YggdrasilAuthenticator.password("<email>", "<password>"), dir));
        } catch (LaunchException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        try{
            org.NGML.Metadata.initMetadata();
            setupSystemProperties();
            addWorkingDir();
            DownloadingMinecraft();
            LaunchingMinecraft();
        } catch (Throwable e) {
            System.exit(1);
        }
    }
}