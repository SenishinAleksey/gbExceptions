public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException() {
        super("Массив должен быть размером 4x4");
    }
}
