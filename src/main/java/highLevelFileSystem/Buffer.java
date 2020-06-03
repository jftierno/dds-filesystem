package highLevelFileSystem;

public class Buffer {
    private byte[] bufferBytes;
    private int bufferStart;
    private int bufferEnd;

    public Buffer(int size){
        this.bufferBytes = new byte[size];
        /* Podemos abstraer al usuario de ingresar manualmente el bufferStart y el bufferEnd,
        ya que se trata de un vector con posiciones de 0 a size-1 */
        this.bufferStart = 0;
        this.bufferEnd = size-1;
    }

    public byte[] getBufferBytes() {
        return bufferBytes;
    }
    public int getBufferEnd() {
        return bufferEnd;
    }
    public int getBufferStart() {
        return bufferStart;
    }
}
