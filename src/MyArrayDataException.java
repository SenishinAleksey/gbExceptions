public class MyArrayDataException extends RuntimeException{

    public MyArrayDataException(int i, int j) {
        super("В ячейке [" + i + "][" + j + "] не число!");
    }
}
