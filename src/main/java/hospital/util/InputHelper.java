package hospital.util;

import java.util.Scanner;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("✗ Ошибка: введите целое число.");
            }
        }
    }

    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("✗ Введите число от " + min + " до " + max + ".");
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                return value;
            } catch (NumberFormatException e) {
                System.out.println("✗ Ошибка: введите число (например, 50000.0).");
            }
        }
    }
}