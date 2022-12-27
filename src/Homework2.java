import java.util.Scanner;

public class Homework2 {
    public static void main(String[] args) {

        // #1
        System.out.println(requestFloat());


        // #2
        int[] intArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int d = 0;
        if (d == 0) {
            System.out.println("На ноль делить нельзя");
        } else {
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        }

        // #3
        try {
            int a = 90;
            int b = 3;
            System.out.println(a / b);
            printSum(23, 234);
            int[] abc = {1, 2};
            abc[3] = 9;
        } catch (NullPointerException ex) {
            System.out.println("Указатель не может указывать на null!");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Массив выходит за пределы своего размера!");
        } catch (ArithmeticException ex) {
            System.out.println("На ноль делить нельзя");
        } catch (Throwable ex) {
            System.out.println("Что-то пошло не так...");
        }

        // #4
        userInput();
    }

    public static void printSum(Integer a, Integer b) {
        System.out.println(a + b);
    }


    public static float requestFloat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дробное число");
        while (true) {
            if (scanner.hasNextFloat()) {
                return scanner.nextFloat();
            }
            scanner.next();
            System.out.println("Ошибка: введено не дробное число");
        }
    }

    public static void userInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строки");
        while (true) {
            String input = scanner.nextLine();
            if (input.length() == 0) {
                throw new RuntimeException("Пустые строки вводить нельзя");
            }
        }
    }
}
