package edu.kis.powp.jobs2d.commons;

public class FilePath {
    public static String getAbsoluteFilePath(String relativeFilePath) {
        return new java.io.File(relativeFilePath).getAbsolutePath();
    }
}
