package edu.kis.powp.jobs2d.command.utils;

public class FileHelper {
    public static String getExtension(String path) {
        int dotIndex = path.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return path.substring(dotIndex + 1);
    }
}
