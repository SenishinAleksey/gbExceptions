public class DevidedZeroException extends ArithmeticException {
    public DevidedZeroException(int num) {
        super("На ноль делить нельзя, даже " + num);
    }
}
