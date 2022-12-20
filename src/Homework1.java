import java.util.Arrays;
import java.util.Stack;

public class Homework1 {


    public static void main(String[] args) {
        /* ArrayIndexOutOfBoundsException */
//        except1();
        /* ArithmeticException */
//        except2();
        /* EmptyStackException */
//        except3();

        /* NullPointerException */
//        String[][] arr = new String[][]{null};
        /* ArrayIndexOutOfBoundsException */
//        String[][] arr = new String[][]{{"1", "2"},{"1", "2"}};
        /* NumberFormatException */
//        String[][] arr = new String[][]{{"as", "2"}, {"1", "2"}};
//        int sum = sum2d(arr);

//        System.out.println(Arrays.toString(arraysDiff(new int[]{4, 5}, new int[]{1, 2, 3})));

//        System.out.println(Arrays.toString(arraysDivision(new float[]{4, 0}, new float[]{1, 2, 3})));
    }

    public static void except1() {
        int[] arr = new int[]{1, 2};
        System.out.println(arr[2]);
    }

    public static void except2() {
        int num = 5 / 0;
    }

    public static void except3() {
        Stack stack = new Stack();
        stack.pop();
    }

    public static int sum2d(String[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 5; j++) {
                int val = Integer.parseInt(arr[i][j]);
                sum += val;
            }
        }
        return sum;
    }

    public static int[] arraysDiff(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new RuntimeException("Массив не задан");
        }
        if (arr1.length != arr2.length) {
            throw new RuntimeException("Длины массивов не равны");
        }
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            res[i] = arr1[i] - arr2[i];
        }
        return res;
    }


    public static float[] arraysDivision(float[] arr1, float[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new RuntimeException("Массив не задан");
        }
        if (arr1.length != arr2.length) {
            throw new RuntimeException("Длины массивов не равны");
        }
        float[] res = new float[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            if (arr2[i] == 0) {
                throw new RuntimeException("На ноль делить нельзя");
            }
            res[i] = arr1[i] / arr2[i];
        }
        return res;
    }
}
