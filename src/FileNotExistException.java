import java.io.IOException;

public class FileNotExistException extends IOException {

    public FileNotExistException() {
        super("Файл не существует");
    }
}
