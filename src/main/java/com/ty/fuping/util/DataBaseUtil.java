package com.ty.fuping.util;

import java.io.*;

/**
 * 数据库备份与恢复
 *
 * @author ZhanJingbo
 * @version 1.0.0
 * Created on 2018/5/29
 */
public class DataBaseUtil {

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

    public static void importDatabase(String host, Integer port, String user, String password, String database, InputStream sqlInputStream) {
        try {
            String command = String.format(IMPORT_COMMAND, host, port, user, password, database);
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);
            OutputStream outputStream = process.getOutputStream();
            StringBuffer sql = new StringBuffer();
            String s = "";
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(sqlInputStream));
            while ((s = fileReader.readLine()) != null) {
                sql.append(s);
            }
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8");
            writer.write(sql.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
