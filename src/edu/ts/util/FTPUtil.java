package edu.ts.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jose on 2017/3/2.
 */
public class FTPUtil {
    /**
     * 想ftp服务器上传文件
     *
     * @param url ftp服务器hostname
     * @param port ftp服务器端口
     * @param loginName ftp登录账号
     * @param password ftp登录密码
     * @param path ftp服务器保存目录
     * @param fileName ftp服务器
     * @param input 上传服务器
     * @return
     */
    public static boolean uploadFile(
            String fileName,
            InputStream input
    ){
        String url = "118.89.31.153";
        //int port = 21;
        String loginName = "crm";
        String path = "/usr/local/tomcat2/webapps/ROOT/pic";
        String password = "zyj163710";
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("utf-8");
        try {
            int reply;
            ftp.connect(url);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(loginName,password);
            reply = ftp.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)){
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            //todo 判断是否有该文件夹
            boolean flag=ftp.changeWorkingDirectory(path);
            ftp.storeFile(fileName,input);
            input.close();
            ftp.logout();
            success = true;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

}
