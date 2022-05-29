package com.common.util;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    /**
     * 解压zip,
     *
     * @param zipFullName 　压缩文件名
     * @param targetPath  　保存目录
     * @throws Exception
     */
    public static void unzipFile(String zipFullName, String targetPath) throws Exception {
            ZipFile zipFile = new ZipFile(zipFullName);
            Enumeration emu = zipFile.entries();
            while (emu.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) emu.nextElement();
                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
                IOUtils.copy(bis,  new FileOutputStream(new File(targetPath + entry.getName())));
                IOUtils.closeQuietly(bis);
            }
            zipFile.close();
    }

    /**
     * 压缩文件
     * @param fileFullName 要压缩的文件全路径名
     */
    public static void zipFile(String fileFullName) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(fileFullName+".zip"));
        ZipEntry entry = new ZipEntry(fileFullName.substring(fileFullName.lastIndexOf("/")+1));
        out.putNextEntry(entry);
        IOUtils.copy( new FileInputStream(fileFullName), out);
        IOUtils.closeQuietly(out);
    }

}
