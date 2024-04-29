import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String input = scanner.nextLine();
            int result = calculate(input);
            System.out.println(result);
        } catch (AddZeroException e) {
            System.out.println("AddZeroException");
        } catch (SubtractZeroException e) {
            System.out.println("SubtractZeroException");
        } catch (OutOfRangeException e) {
            System.out.println("OutOfRangeException");
        } catch (Exception e) {
            System.out.println("InvalidInputException");
        } finally {
            scanner.close();
        }
    }

    private static int calculate(String input) throws AddZeroException, SubtractZeroException, OutOfRangeException {
        if (!input.matches("\\d+[+-]\\d+")) {
            throw new IllegalArgumentException("Invalid input format.");
        }

        char operator = input.charAt(input.indexOf('+') > -1 ? input.indexOf('+') : input.indexOf('-'));
        int index = input.indexOf(operator);
        int num1 = Integer.parseInt(input.substring(0, index));
        int num2 = Integer.parseInt(input.substring(index + 1));
        checkZeroOperation(num1, num2, operator);
        checkRange(num1, num2);

        int result = operator == '+' ? num1 + num2 : num1 - num2;
        checkRange(result);

        return result;
    }

    private static void checkZeroOperation(int num1, int num2, char operator) throws AddZeroException, SubtractZeroException {
        if (operator == '+') {
            if (num1 == 0 || num2 == 0) {
                throw new AddZeroException();
            }
        } else if (operator == '-') {
            if (num1 == 0 || num2 == 0) {
                throw new SubtractZeroException();
            }
        }
    }

    private static void checkRange(int... numbers) throws OutOfRangeException {
        for (int number : numbers) {
            if (number < 0 || number > 1000) {
                throw new OutOfRangeException();
            }
        }
    }

    static class AddZeroException extends Exception {}
    static class SubtractZeroException extends Exception {}
    static class OutOfRangeException extends Exception {}
}
