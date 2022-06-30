package com.company.tamagotchi;

// Класс сохраняет состояние питомца
@JavaFileInfo(name = "Pominov V.S.", month = 7, year = 2022)
public class SavePet extends Thread {

    // Поля
    private final String fileName;  // имя файла, в который будем сохранять состояние питомца
    private Pet pet;// питомец, состояние которого будем сохранять
    private final int waitIntervalMS;  // интервал ожидания

    // Конструкторы
    // 1. Конструктор с 2-мя параметрами
    public SavePet(Pet pet, String fileName, int waitIntervalMS1) {
        super(); // вызов конструктора базового класса
        this.pet = pet;
        this.fileName = fileName;
        this.waitIntervalMS = waitIntervalMS1;
    }

    // getter
    public Pet getPet() {
        return pet;
    }

    // Метод запуска синхронизации
    public void run() {
        // главный цикл фоновой задачи
        while (true) {
            // 1. Если питомец умер, создать нового
            if (pet.petDied()) {
                pet = new Pet();
            }
            // сериализовать состояние питомца в файл
            Serialization.makeSerializationPet(pet);
            // 2. подождать определенное время
            try {
                Thread.sleep(waitIntervalMS);
            } catch (InterruptedException ex) {
                // если мы попали в этот код, то поток прервали из вне через interrupt
                interrupt();    // установим флаг interrupt для потока еще раз
            }
            // 3. в конце цикла проверяем поток на прерывание
            if (isInterrupted()) {
                Serialization.makeSerializationPet(pet); // сделать последнюю синхронизацию
                System.out.println("Synchronizer stopped.");
                break;          // завершить главный цикл
            }
        }
    }

}//class close
