import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numPersons = askNumPersons(input);

        CheckCalculator calculator = new CheckCalculator(numPersons);
        int numAdded = 0;
        do {
            String name = askName(input);
            double price = askPrice(input);
            calculator.addCheckItem(name,price);
            System.out.println("Добавленные товары:\n"+name);
            numAdded++;
        } while (askContinue(input));

        if (numAdded > 0) {
            calculator.printCheckItems();
            double sumPerOne = calculator.calculate();
            rublesFormatter fmt = new rublesFormatter();
            System.out.printf("%s с каждого человека\n", fmt.format(sumPerOne));
        }
    }

    public static int askNumPersons(Scanner scanner) {
        System.out.println("Введите количество человек (больше 1), между которыми разделить счет");
        int num;
        while (true) {
            num = scanner.nextInt();
            if (num <= 1) {
                System.out.println("Неверное количество персон, введите число больше 1");
            } else {
                break;
            }
        }
        return num;
    }

    public static String askName(Scanner scanner) {
        System.out.println("Введите название блюда");
        return scanner.next();
    }

    public static double askPrice(Scanner scanner) {
        System.out.println("Введите стоимость (руб.коп)");
        return scanner.nextDouble();
    }

    public static boolean askContinue(Scanner scanner) {
        System.out.println("Хотите что-то добавить? Напишите любой символ для продолжения, 'завершить' для выхода");
        String input = scanner.next();
        return !input.equalsIgnoreCase("завершить");
    }


}

class rublesFormatter {
    public String format(double num) {
        return String.format("%.2f %s",num,numToRublesWord(Double.valueOf(num).intValue()));
    }

    public String numToRublesWord(int num) {
        num = num % 100;
        if ((num > 4) && (num < 20)) {
            return "рублей";
        } else {
            num = num % 10;
            switch (num) {
                case 1:
                    return "рубль";
                case 2:
                case 3:
                case 4:
                    return "рубля";
                default:
                    return "рублей";
            }
        }
    }
}