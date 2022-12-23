import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
//        try {
//            doSomething();
//        } catch (IOException e) {
//            System.out.println(e.getClass());
//            System.out.println(e.getMessage());
//            System.exit(1);
//        }
//        System.out.println(123);

//        try (Counter counter = new Counter()) {
//            System.out.println(counter);
//            counter.add();
//            System.out.println(counter);
//        }
//
//        Counter counter = new Counter();
//        counter.add();
//        System.out.println(counter);
//        counter.close();
//        counter.add();

//        int num1 = 6;
//        try {
//            int num = num1 / 0;
//        } catch (ArithmeticException e) {
//            throw new DevidedZeroException(num1);
//        }


        String[][] nums = new String[][]{{"1", "asdasd", "3", "4"},{"1", "2", "3", "4"},{"1", "2", "3", "4"}};
        try {
            System.out.println(arraySum(nums));
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println(e.getMessage());
        }



    }

    public void rwLine(Path pathRead, Path pathWrite) throws IOException {

        try (BufferedReader in = Files.newBufferedReader(pathRead);
             BufferedWriter out = Files.newBufferedWriter(pathWrite)) {
            out.write(in.readLine());
        }
    }

    public static void doSomething() throws IOException {
        throw new IOException("dsasdasdasdas");
    }

    public static int arraySum(String[][] nums) {
        int sum = 0;
        if (nums.length != 4 || nums[0].length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                try {
                    sum += Integer.parseInt(nums[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }

}
