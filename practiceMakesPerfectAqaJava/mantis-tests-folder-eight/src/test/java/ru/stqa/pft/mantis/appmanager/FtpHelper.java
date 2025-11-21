package ru.stqa.pft.mantis.appmanager;


import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

//    public void upload(File file, String target, String backup) throws IOException {
//       // ftp.connect(app.getProperty("ftp.host"));
//        ftp.connect(app.getProperty("ftp.host"), Integer.parseInt(app.getProperty("ftp.port")));
//        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
//        ftp.deleteFile(backup);
//        ftp.rename(target, backup);
//        ftp.enterLocalPassiveMode();
//        ftp.storeFile(target, new FileInputStream(file));
//        ftp.disconnect();
//    }

public void upload(File file, String target, String backup) throws IOException {
    System.out.println("=== FTP DEBUG ===");
    System.out.println("Connecting to: " + app.getProperty("ftp.host") + ":" + app.getProperty("ftp.port"));

    try {
        ftp.connect(app.getProperty("ftp.host"), Integer.parseInt(app.getProperty("ftp.port")));
        System.out.println("Connected: " + ftp.isConnected());

        boolean login = ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        System.out.println("Login: " + login);
        System.out.println("FTP Reply: " + ftp.getReplyString());

        ftp.deleteFile(backup);
        System.out.println("Backup deleted");

        ftp.rename(target, backup);
        System.out.println("Original renamed to backup");

        ftp.enterLocalPassiveMode();

        boolean stored = ftp.storeFile(target, new FileInputStream(file));
        System.out.println("File stored: " + stored);
        System.out.println("Final Reply: " + ftp.getReplyString());

        ftp.disconnect();

    } catch (Exception e) {
        System.out.println("FTP Error: " + e.getMessage());
        throw e;
    }
}

    public void restore(String backup, String target) throws IOException {
        //ftp.connect(app.getProperty("ftp.host"));
        ftp.connect(app.getProperty("ftp.host"), Integer.parseInt(app.getProperty("ftp.port")));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }
}
