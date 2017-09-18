package com.liulu.sell.util;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2017/9/18
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
public class FtpUpload {

    public static void main(String[] args) throws Exception{

        //File file = new File("/opt/mfs/bills/toDayGoApp_20170917.csv");
        //File file = new File("/opt/mfs/bills/toDayGoApp_20170917.csv")
        File file = new File("D:\\test\\toDayGoApp_20170917.csv");
        InputStream in = new FileInputStream(file);
        uploadFile("116.214.35.82",2122,"partnertest","hxmkl2017","Homefax/1001000001/20170917/","toDayGoApp_20170917.csv",in);

    }

    /**
     * Description: 向FTP服务器上传文件
     * @Version      1.0
     * @param url FTP服务器hostname
     * @param port  FTP服务器端口
     * @param username FTP登录账号
     * @param password  FTP登录密码
     * @param path  FTP服务器保存目录
     * @param filename  上传到FTP服务器上的文件名
     * @param input   输入流
     * @return 成功返回true，否则返回false *
     */

    public static boolean uploadFile(String url,// FTP服务器hostname
                                     int port,// FTP服务器端口
                                     String username, // FTP登录账号
                                     String password, // FTP登录密码
                                     String path, // FTP服务器保存目录
                                     String filename, // 上传到FTP服务器上的文件名
                                     InputStream input // 输入流
    ){
        boolean success = false;
        FTPClient ftp = new FTPClient();
        //ftp.setControlEncoding("GBK");
        //ftp.setControlEncoding("UTF-8");
        try {
            int reply;
            ftp.connect(url, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            System.out.println(reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.enterLocalPassiveMode();
            //ftp.enterLocalActiveMode();
            //ftp.enterRemotePassiveMode();
            ftp.enterRemoteActiveMode(InetAddress.getByName("116.214.35.82"), 2122);
            boolean result = ftp.makeDirectory(path);
            System.out.println(result);
            boolean result1 = ftp.changeWorkingDirectory(path);
            System.out.println(result1);
            // ftp.setFileType(FTP.BINARY_FILE_TYPE);
            System.out.println(ftp.printWorkingDirectory());
            success = ftp.storeFile(filename, input);
            System.out.println(success);
            input.close();
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return success;
    }
}
