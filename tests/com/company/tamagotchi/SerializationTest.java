package com.company.tamagotchi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//Класс тест - сериализация,десериализация
public class SerializationTest {

    // фикстура
    static Pet pet = null; // питомец для тестирования
    static String fileName = null;   // ассоциированный файл
    static Serialization sr = null; // сериализатор для тестирования

    // // вызов перед всем тестированием
    @BeforeEach
    public void createPet() {
        pet = new Pet(20, 20, 20); // создаём нового питомца
        fileName = "Pet testSave.txt"; // создаём имя файла
        sr = new Serialization(fileName); // создаём сериализатор
        Serialization.makeSerializationPet(pet); // сериализуем тестируемого питомца
    }

    @AfterEach
    public void deleteFile() { // удаляем файл после каждого теста
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println(file.getName() + " deleted");
        }
    }

    @Test
    void makeSerializationPet() {
        // проверяем работоспособность
        Serialization.makeSerializationPet(pet);
        // делаем десериализамцию
        Pet deserialized = Serialization.makeDeserializationPet();
        // проверка
        assertNotNull(deserialized);    // не null
        Assertions.assertEquals(pet.getEnergy(), deserialized.getEnergy());
        Assertions.assertEquals(pet.getHungry(), deserialized.getHungry());
        Assertions.assertEquals(pet.getPurity(), deserialized.getPurity());
        Assertions.assertEquals(pet.getAge(), deserialized.getAge());
        Assertions.assertEquals(pet.getExp(), deserialized.getExp());
    }

    @Test
    void makeDeserializationPet() {
        //  проверяем работоспособность
        Pet pet_1 = Serialization.makeDeserializationPet();
        assertNotNull(pet_1);    // не null
        Assertions.assertEquals(pet.getEnergy(), pet_1.getEnergy());
        Assertions.assertEquals(pet.getEnergy(), pet_1.getEnergy());
        Assertions.assertEquals(pet.getHungry(), pet_1.getHungry());
        Assertions.assertEquals(pet.getPurity(), pet_1.getPurity());
        Assertions.assertEquals(pet.getAge(), pet_1.getAge());
        Assertions.assertEquals(pet.getExp(), pet_1.getExp());
    }
}
