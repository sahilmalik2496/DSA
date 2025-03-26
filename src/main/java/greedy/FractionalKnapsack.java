package greedy

import java.util.*;

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}

class ItemComparator implements Comparator<Item> {
    @Override
    public int compare(Item a, Item b) {
        double r1 = (double) a.value / a.weight;
        double r2 = (double) b.value / b.weight;
        return Double.compare(r2, r1); // Sort in descending order
    }
}

public class FractionalKnapsack {
    static double fractionalKnapsack(int capacity, Item[] items) {
        Arrays.sort(items, new ItemComparator());

        int currentWeight = 0;
        double totalValue = 0.0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                int remainingWeight = capacity - currentWeight;
                totalValue += ((double) item.value / item.weight) * remainingWeight;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(100, 20),
            new Item(60, 10),
            new Item(120, 30)
        };
        int capacity = 50;

        double maxValue = fractionalKnapsack(capacity, items);
        System.out.println("The maximum value is " + maxValue);
    }
}
