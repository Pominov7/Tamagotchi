package com.company.tamagotchi;

import java.io.File;
import java.util.Scanner;

// Класс- Игра "Тамагочи"
@JavaFileInfo(name = "Pominov V.S.", month = 7, year = 2022)
public class TamagotchiGame {

    // Метод проверки существования файла
    public static Pet existsFile(String file) {
        File f = new File(file);
        if (!f.exists()) { // если файла не существует
            return new Pet(); // создать нового питомца со 100% параметрами
        }
        // если файл существует, то десериализуем питомца из файла
        return Serialization.makeDeserializationPet();
    }

    public static void main(String[] args) throws InterruptedException {
        // 1. Создаём сканер
        Scanner in = new Scanner(System.in);

        // 2. Создаём поток, который будет сохранять состояние питомца
        SavePet petSave =
                new SavePet(existsFile("Pet save.txt"), "Pet save.txt", 3000);

        // 3. Сделали доп поток демоном
        petSave.setDaemon(true);

        // 4. Создаём питомца
        Pet pet = petSave.getPet();

        // 5. Запускаем синхронизатор в отдельном потоке
        petSave.start();

        // 6. Меню

        label:
        while (true) {
            System.out.println("\n0 - End ");
            System.out.println("1 - Show pet");
            System.out.println("2 - Play with a pet");
            System.out.println("3 - Feed the pet");
            System.out.println("4 - Wash the pet");
            System.out.println("5 - Putting a pet to bed");
            int input = in.nextInt();
            switch (input) {
                case 0:
                    break label;
                case 1:
                    System.out.println(pet);
                    break;
                case 2:
                    if (pet.petDied()) {
                        System.out.println("Pet died (×﹏×)...");
                        System.out.println("Please start the game again");
                        return;
                    }
                    pet.play();
                    System.out.println(pet);
                    break;
                case 3:
                    if (pet.petDied()) {
                        System.out.println("Pet died (×﹏×)...");
                        System.out.println("Please start the game again");
                        return;
                    }
                    pet.eat();
                    System.out.println(pet);
                    break;
                case 4:
                    if (pet.petDied()) {
                        System.out.println("Pet died (×﹏×)...");
                        System.out.println("Please start the game again");
                        return;
                    }
                    pet.wash();
                    System.out.println(pet);
                    break;
                case 5:
                    if (pet.petDied()) {
                        System.out.println("Pet died (×﹏×)...");
                        System.out.println("Please start the game again");
                        return;
                    }
                    pet.sleep();
                    System.out.println(pet);
                    break;
                default:
                    System.out.println("Error: Incorrect input...");
                    break;
            }
        }
        in.close();// сканер закрывается
        // 7. Прерывание отдельного потока синхронизации
        petSave.interrupt();

        // 8. Ожидание самостоятельного завершения потока
        petSave.join();

        // 9. Поток завершился, данные сохранены
        System.out.println("Sub thread stopped, all data saved");
    }
}//class close