package com.petrock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetRockTest {
    @Test
    public void testFeed() {
        PetRock rock = new PetRock("Test");
        rock.feed();
        assertEquals(0, rock.getHunger());
        assertEquals(1, rock.getBoredom());
        assertEquals(9, rock.getEnergy());
    }

    @Test
    public void testPlay() {
        PetRock rock = new PetRock("Test");
        rock.play();
        assertEquals(0, rock.getBoredom());
        assertEquals(1, rock.getHunger());
        assertEquals(8, rock.getEnergy());
    }

    @Test
    public void testPolish() {
        PetRock rock = new PetRock("Test");
        rock.polish();
        assertEquals(0, rock.getHunger());
        assertEquals(0, rock.getBoredom());
        assertEquals(10, rock.getEnergy());
    }

    @Test
    public void testMoodTired() {
        PetRock rock = new PetRock("Test");
        rock.setEnergy(2);
        rock.updateMood();
        assertEquals("Tired", rock.getMood());
    }

    @Test
    public void testMoodSad() {
        PetRock rock = new PetRock("Test");
        rock.setHunger(8);
        rock.updateMood();
        assertEquals("Sad", rock.getMood());
    }

    @Test
    public void testMoodBored() {
        PetRock rock = new PetRock("Test");
        rock.setBoredom(5);
        rock.updateMood();
        assertEquals("Bored", rock.getMood());
    }

    @Test
    public void testMoodHappy() {
        PetRock rock = new PetRock("Test");
        rock.setHunger(3);
        rock.setBoredom(3);
        rock.setEnergy(5);
        rock.updateMood();
        assertEquals("Happy", rock.getMood());
    }

    @Test
    public void testSaveAndLoad() {
        PetRock rock = new PetRock("Test");
        rock.saveToFile();
        PetRock loadedRock = PetRock.loadFromFile();
        assertNotNull(loadedRock);
        assertEquals("Test", loadedRock.getName());
    }
}

