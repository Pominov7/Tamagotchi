package com.company.tamagotchi;

import java.io.*;

// Класс -Сериализует и Десериализует объект Pet
@JavaFileInfo(name = "Pominov V.S.", month = 7, year = 2022)
public class Serialization {

    static String fileName = "Pet save.txt";

    public Serialization(String file) {
        fileName = file;
    }

    // 1. Метод сериализации объекта Pet
    public static void makeSerializationPet(Pet pet) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pet);
//            System.out.println("Data is synchronized");
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        }
    }

    // 2. Метод десериализации объекта Pet
    public static Pet makeDeserializationPet() {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Pet) ois.readObject();
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Deserialization exception: " + ex);
        }
        return null;
    }
}//class close