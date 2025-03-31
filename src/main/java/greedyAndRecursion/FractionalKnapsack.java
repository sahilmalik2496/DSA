package greedyAndRecursion;

    /*
Problem Statement: The weight of N items and their corresponding values are given. We have to put these items in a knapsack of weight W such that the total value obtained is maximized.

Note: We can either take the item as a whole or break it into smaller units.

Example:

Input: N = 3, W = 50, values[] = {100,60,120}, weight[] = {20,10,30}.

Output: 240.00

Explanation: The first and second items  are taken as a whole  while only 20 units of the third item is taken. Total value = 100 + 60 + 80 = 240.00
    */

    
import java.util.*;

public class FractionalKnapsack {

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

    double fractionalKnapsack(int capacity, Item[] items) {
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

    public void main(String[] args) {
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
