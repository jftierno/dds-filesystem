package highLevelFileSystem;

import lowLevelFileSystem.lowLevelFileSystem;
import exceptions.*;

public class File implements highLevelFileSystem{
    private String fileDirectory;
    private int fileDescriptor;
    private lowLevelFileSystem lowLevelFileSystem;

    public File(String fileDirectory, lowLevelFileSystem lowLevelFileSystem) {
        this.fileDirectory = fileDirectory;
        this.lowLevelFileSystem = lowLevelFileSystem;
    }

    public File openFile(String fileDirectory) {
        /*Las funciones de lectura y escritura asumen un fileDescriptor de un archivo ya abierto,
        por lo que hay que abrirlo antes de pasarlo (para un correcto manejo de errores)*/
        this.fileDescriptor = lowLevelFileSystem.openFile(fileDirectory);
        // Se chequea si es correcta la apertura del archivo
        checkFileOpening(this.fileDescriptor);
        // Se retorna un archivo correctamente abierto
        return new File(fileDirectory, this.lowLevelFileSystem);
    }

    public void closeFile(){
        lowLevelFileSystem.closeFile(this.fileDescriptor);
    }

    public void syncReadFile(Buffer buffer){
        int readBytes = lowLevelFileSystem.syncReadFile(fileDescriptor,
                buffer.getBufferBytes(),
                buffer.getBufferStart(),
                buffer.getBufferEnd()
        );
        checkReadBytes(readBytes);
    }

    public void syncWriteFile(Buffer buffer){
        lowLevelFileSystem.syncWriteFile(fileDescriptor,
                buffer.getBufferBytes(),
                buffer.getBufferStart(),
                buffer.getBufferEnd()
        );
    }
    public void asyncReadFile(Buffer buffer)
    {
        lowLevelFileSystem.asyncReadFile(fileDescriptor,
                buffer.getBufferBytes(),
                buffer.getBufferStart(),
                buffer.getBufferEnd(),
                readBytes -> { /*?*/}
        );
    }

    public void checkFileOpening(int fileDescriptor) {
        if(fileDescriptor < 0)
            throw new fileErrorException("An error has occurred trying to open the file.");
    }
    public void checkReadBytes(int readBytes){
        if(readBytes < 0)
            throw new readBytesException("An error has occurred trying to read the file.");
    }

    public int getFileDescriptor(){return this.fileDescriptor;}
    public void setFileDescriptor(int fileDescriptor){this.fileDescriptor = fileDescriptor;}
    public String getFileDirectory() {return this.fileDirectory;}
    public void setFileDirectory(String fileDirectory) {this.fileDirectory = fileDirectory;}
}
