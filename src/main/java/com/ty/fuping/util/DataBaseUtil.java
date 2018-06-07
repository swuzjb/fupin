package com.ty.fuping.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;

/**
 * 数据库备份与恢复
 *
 * @author ZhanJingbo
 * @version 1.0.0
 * Created on 2018/5/29
 */
public class DataBaseUtil {
    private static final Logger log = LoggerFactory.getLogger(DataBaseUtil.class);

    /**
     * 备份命令
     */
    private static final String EXPORT_COMMAND = "mysqldump -h%s -P%d -u%s -p%s --default-character-set=utf8 --result-file=%s --add-drop-table %s";

    /**
     * 恢复命令
     */
    private static final String IMPORT_COMMAND = "mysql -h%s -P%d -u%s -p%s --default-character-set=utf8 %s";


    public static boolean exportDatabase(String host, Integer port, String user, String password, String database, String filePath) {
        String command = String.format(EXPORT_COMMAND, host, port, user, password, filePath, database);
        System.out.println(command);
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(command);
            if (process.waitFor() == 0) {
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void importDatabase(String host, Integer port, String user, String password, String database, String filePath) {
        try {
            String command = String.format(IMPORT_COMMAND, host, port, user, password, database);
            log.info("command:{}", command);
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);
            OutputStream outputStream = process.getOutputStream();
            outputStream.write(String.format("source %s", filePath).getBytes());
            outputStream.flush();
            outputStream.close();
            Scanner scanner = new Scanner(process.getInputStream());
            while (scanner.hasNextLine()) {
                log.info("响应:{}", scanner.nextLine());
            }
            scanner.close();
            scanner = new Scanner(process.getErrorStream());
            while (scanner.hasNextLine()) {
                log.error(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
