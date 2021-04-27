package io;

public interface IO {
    void print(String msg);
    String nextLine(String msg);
    int nextInt(String msg);
    void error(String msg);
    String trimTags(String msg);
}
