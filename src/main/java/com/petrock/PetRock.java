package com.petrock;



import org.json.JSONObject;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PetRock {
    private String name;
    private String mood;
    private int hunger;
    private int boredom;
    private int energy;
    private int polishCount;

    public PetRock(String name) {
        this.name = name;
        this.mood = "Happy";
        this.hunger = 0;
        this.boredom = 0;
        this.energy = 10;
        this.polishCount = 0;
    }

    public void feed() {
        if (energy < 1) return;
        hunger = Math.max(0, hunger - 2);
        boredom = Math.min(10, boredom + 1);
        energy--;
        updateMood();
    }

    public void play() {
        if (energy < 2) return;
        boredom = Math.max(0, boredom - 3);
        hunger = Math.min(10, hunger + 1);
        energy -= 2;
        updateMood();
    }

    public void polish() {
        if (polishCount >= 2) return;
        hunger = Math.max(0, hunger - 1);
        boredom = Math.max(0, boredom - 1);
        energy = Math.min(10, energy + 1);
        polishCount++;
        updateMood();
    }

    public void updateMood() {
        if (energy <= 2) {
            mood = "Tired";
        } else if (hunger > 7 || boredom > 7 || energy <= 3) {
            mood = "Sad";
        } else if (hunger >= 4 || boredom >= 4) {
            mood = "Bored";
        } else {
            mood = "Happy";
        }
    }

    public void saveToFile() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("mood", mood);
        json.put("hunger", hunger);
        json.put("boredom", boredom);
        json.put("energy", energy);

        try (FileWriter file = new FileWriter("rock.json")) {
            file.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PetRock loadFromFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("rock.json")));
            JSONObject json = new JSONObject(content);
            PetRock rock = new PetRock(json.getString("name"));
            rock.mood = json.getString("mood");
            rock.hunger = json.getInt("hunger");
            rock.boredom = json.getInt("boredom");
            rock.energy = json.getInt("energy");
            return rock;
        } catch (Exception e) {
            return null;
        }
    }

    // Getters and Setters
    public String getName() { return name; }
    public String getMood() { return mood; }
    public int getHunger() { return hunger; }
    public int getBoredom() { return boredom; }
    public int getEnergy() { return energy; }
    public void setHunger(int hunger) { this.hunger = Math.max(0, Math.min(10, hunger)); }
    public void setBoredom(int boredom) { this.boredom = Math.max(0, Math.min(10, boredom)); }
    public void setEnergy(int energy) { this.energy = Math.max(0, Math.min(10, energy)); }
}
