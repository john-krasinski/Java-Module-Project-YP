import java.util.ArrayList;
import java.util.HashMap;

public class CheckCalculator {

    private int numPersons;
    private double finalSum;
    private HashMap<String,CheckItem> checkItems;
    public CheckCalculator(int numPersons) {
        this.numPersons = numPersons;
        this.checkItems = new HashMap<>();
        this.finalSum = 0;
    }

    public double calculate() {
        if (numPersons < 2) {
            System.out.println("Количество человек меньше 2");
            return 0.0;
        }
        return finalSum / numPersons;
    }
    public double reCalculate() {
        if (numPersons < 2) {
            System.out.println("Количество человек меньше 2");
            return 0.0;
        }
        double sum = 0;
        for (CheckItem i: checkItems.values()) {
            sum += i.calculateCost();
        }
        return sum / numPersons;
    }

    public void addCheckItem(String name, double price, int num) {
        if (checkItems.containsKey(name)) {
            CheckItem item = checkItems.get(name);
            item.addNum(num);
            checkItems.put(name,item);
        } else {
            checkItems.put(name, new CheckItem(name,price,num));
        }

        finalSum += price*num;
    }

    public void addCheckItem(String name, double price) {
        addCheckItem(name,price,1);
    }

    public void printCheckItems() {
        System.out.println("Добавленные товары:");
        for (CheckItem i : this.checkItems.values()) {
            System.out.println(i);
        }
    }
}

class CheckItem {
    String name;
    double price;
    int num;

    public CheckItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.num = 1;
    }
    public CheckItem(String name, double price, int num) {
        this.name = name;
        this.price = price;
        this.num = num;
    }

    void addNum(int num) {
        if (num > 0) {
            this.num += num;
        }
    }
    double calculateCost() {
        return price*num;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f * %d",name,price,num);
    }
}
