import java.io.IOException;

public class Counter implements AutoCloseable {

    private Integer count;

    public Counter() {
        count = 0;
    }

    public void add() throws IOException {
        if (count == null) {
            throw new IOException();
        }
        count++;
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }

    @Override
    public void close() throws Exception {
        count = null;
    }
}
