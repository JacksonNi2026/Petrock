package com.petrock;

import java.util.Scanner;
import java.util.Random;

public class PetRockSimulator {
    private static PetRock rock;
    private static boolean canFeed = true;
    private static boolean canPlay = true;
    private static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rock = PetRock.loadFromFile();

        if (rock == null) {
            System.out.print("Enter a name for your pet rock: ");
            String name = scanner.nextLine();
            rock = new PetRock(name);
        }

        while (true) {
            System.out.println("\n=== Pet Rock Caretaker ===");
            System.out.println("1. Feed the Rock");
            System.out.println("2. Play with the Rock");
            System.out.println("3. Polish the Rock");
            System.out.println("4. Check Status");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            handleAction(choice);
            advanceTurn();
            checkGameOver();
            rock.saveToFile();
        }
    }

    private static void handleAction(int choice) {
        switch (choice) {
            case 1:
                if (canFeed) {
                    rock.feed();
                    canFeed = false;
                    canPlay = true;
                } else {
                    System.out.println("You can't feed the rock twice in a row!");
                }
                break;
            case 2:
                if (canPlay) {
                    rock.play();
                    canPlay = false;
                    canFeed = true;
                } else {
                    System.out.println("You can't play with the rock twice in a row!");
                }
                break;
            case 3:
                rock.polish();
                break;
            case 4:
                printStatus();
                break;
            case 5:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void advanceTurn() {
        rock.setHunger(rock.getHunger() + 1);
        rock.setBoredom(rock.getBoredom() + 1);
        rock.setEnergy(rock.getEnergy() + 1);
        rock.updateMood();

        if (random.nextDouble() < 0.3) {
            triggerRandomEvent();
        }
    }

    private static void triggerRandomEvent() {
        int event = random.nextInt(4);
        switch (event) {
            case 0:
                System.out.println("Your rock found a shiny pebble! Mood improved!");
                rock.setBoredom(rock.getBoredom() - 2);
                break;
            case 1:
                System.out.println("Your rock got extra sleep! Energy +2!");
                rock.setEnergy(rock.getEnergy() + 2);
                break;
            case 2:
                System.out.println("A loud noise scared your rock! Boredom +2!");
                rock.setBoredom(rock.getBoredom() + 2);
                break;
            case 3:
                System.out.println("Your rock is grumpy! Hunger +2!");
                rock.setHunger(rock.getHunger() + 2);
                break;
        }
        rock.setHunger(rock.getHunger());
        rock.setBoredom(rock.getBoredom());
        rock.setEnergy(rock.getEnergy());
    }

    private static void printStatus() {
        System.out.println("\n=== Rock Status ===");
        System.out.println("Name: " + rock.getName());
        System.out.println("Mood: " + rock.getMood());
        System.out.println("Hunger: " + rock.getHunger() + "/10");
        System.out.println("Boredom: " + rock.getBoredom() + "/10");
        System.out.println("Energy: " + rock.getEnergy() + "/10");
    }

    private static void checkGameOver() {
        if (rock.getHunger() >= 10 || rock.getBoredom() >= 10 || rock.getEnergy() <= 0) {
            System.out.println("\nGame Over! Your rock has left you...");
            System.exit(0);
        }
    }
}