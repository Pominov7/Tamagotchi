package com.company.tamagotchi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Класс тест - Тамагочи
public class PetTest {

    Pet pet; // тестируемый pet

    @BeforeEach
    // Метод создания объекта Pet
    public void createPet() {
        pet = new Pet(100, 100, 100);
    }

    @Test
    void getEnergy() {
        assertEquals(100, pet.getEnergy());
    }

    @Test
    void getHungry() {
        assertEquals(100, pet.getHungry());
    }

    @Test
    void getPurity() {
        assertEquals(100, pet.getPurity());
    }

    @Test
    void getExp() {
        assertEquals(0, pet.getExp());
    }

    @Test
    void getAge() {
        assertEquals(0, pet.getAge());
    }

    @Test
    void getEnergyMax() {
        assertEquals(100, pet.getEnergyMax());
    }

    @Test
    void getHungryMax() {
        assertEquals(100, pet.getHungryMax());
    }

    @Test
    void getPurityMax() {
        assertEquals(100, pet.getPurityMax());
    }

    @Test
    void setEnergy() {
        // проверка 1
        pet.setEnergy(20);
        assertEquals(20, pet.getEnergy());

        // проверка 2
        pet.setEnergy(0);
        assertEquals(0, pet.getEnergy());
    }

    @Test
    void setHungry() {
        // проверка 1
        pet.setHungry(20);
        assertEquals(20, pet.getHungry());

        // проверка 2
        pet.setHungry(0);
        assertEquals(0, pet.getHungry());
    }

    @Test
    void setPurity() {
        // проверка 1
        pet.setPurity(20);
        assertEquals(20, pet.getPurity());

        // проверка 2
        pet.setPurity(0);
        assertEquals(0, pet.getPurity());
    }

    @Test
    void petDied() {

        // проверка 1
        pet.petDied();
        System.out.println("Pet is live");

        // проверка 2
        Pet pet_1 = new Pet(0, 100, 100);
        pet_1.petDied();

        // проверка 3
        Pet pet_2 = new Pet(100, 0, 100);
        pet_2.petDied();

        // проверка 4
        Pet pet_3 = new Pet(100, 100, 0);
        pet_3.petDied();
    }

    @Test
    void play() {

        // проверка 1
        pet.play();
        assertEquals(90, pet.getEnergy());

        // проверка 2
        assertEquals(90, pet.getHungry());

        // проверка 3
        assertEquals(90, pet.getPurity());

        // проверка 4
        assertEquals(1, pet.getAge());

        // проверка 5
        assertEquals(1, pet.getExp());
    }

    @Test
    void eat() {

        // проверка 1
        pet.setHungry(20);
        pet.eat();
        assertEquals(95, pet.getEnergy());

        // проверка 2
        assertEquals(100, pet.getHungry());

        // проверка 3
        assertEquals(90, pet.getPurity());

        // проверка 4
        assertEquals(1, pet.getAge());
    }

    @Test
    void sleep() {

        // проверка 1
        pet.setEnergy(95);
        pet.sleep();

        // проверка 2
        pet.setEnergy(20);
        pet.sleep();
        assertEquals(100, pet.getEnergy());

        // проверка 3
        assertEquals(95, pet.getHungry());

        // проверка 4
        assertEquals(1, pet.getAge());
    }

    @Test
    void wash() {
        // проверка 1
        pet.setPurity(50);
        pet.wash();
        assertEquals(80, pet.getEnergy());

        // проверка 2
        assertEquals(90, pet.getHungry());

        // проверка 3
        assertEquals(100, pet.getPurity());

        // проверка 4
        assertEquals(1, pet.getAge());
    }
}