package com.liulu.sell.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2017/8/31
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class FileUploadController {

    public static void filecopy() throws Exception{
        File file = new File("C:\\Users\\lenovo\\Desktop\\IMG_20170829_102036.jpg");
        FileInputStream in = new FileInputStream(file);
        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\lenovo\\Desktop\\IMG.txt"));

        long time=new Date().getTime();
        int length=2097152;
        byte[] buffer=new byte[length];
        while(true){
            int ins=in.read(buffer);
            if(ins==-1){
                in.close();
                out.flush();
                out.close();
            }else
                out.write(buffer,0,ins);
        }
    }

    public static void main(String[] args) throws Exception{
        filecopy();
    }
}
