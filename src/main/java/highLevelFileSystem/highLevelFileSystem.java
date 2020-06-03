package highLevelFileSystem;

public interface highLevelFileSystem
{
    File openFile(String fileDirectory);
    void closeFile();
    void syncReadFile(Buffer buffer);
    void syncWriteFile(Buffer buffer);
    void asyncReadFile(Buffer buffer);
}
