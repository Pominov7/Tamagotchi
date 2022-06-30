package com.company.tamagotchi;

import java.io.*;

//Класс - "Питомец"
@JavaFileInfo(name = "Pominov V.S.", month = 7, year = 2022)
public class Pet implements Serializable {

    // Поля
    @Serial
    private static final long serialVersionUID = 1L; // номер версии UID для класса
    private int energyMax = 100; // максимальное кол-во энергии
    private int hungryMax = 100; // максимальное кол-во голода (уровень сытости питомца)
    private int purityMax = 100; // максимальное кол-во чистоты питомца
    private int energy; // кол-во энергии питомца
    private int hungry; // голод (уровень сытости питомца)
    private int purity; // чистота питомца
    private int exp; // кол-во опыта
    private int age; // возраст питомца

    // Конструкторы
    // 1. Конструктор без параметров
    public Pet() {
        energy = energyMax;
        hungry = hungryMax;
        purity = purityMax;

    }

    // 2. Конструктор с 3-мя параметрами
    public Pet(int energy, int hungry, int purity) {
        energyMax = energy;
        hungryMax = hungry;
        purityMax = purity;

        this.age = 0;
        this.exp = 0;
        // устанавливаем значения параметров
        setEnergy(energy);
        setHungry(hungry);
        setPurity(purity);
    }

    // getters

    public int getEnergy() {
        return energy;
    }

    public int getHungry() {
        return hungry;
    }

    public int getPurity() {
        return purity;
    }

    public int getExp() {
        return exp;
    }

    public int getAge() {
        return age;
    }

    public int getEnergyMax() {
        return energyMax;
    }

    public int getHungryMax() {
        return hungryMax;
    }

    public int getPurityMax() {
        return purityMax;
    }

    public Pet getPet() {
        return this;
    }

    // setters (с проверкой состояния питомца)

    public void setEnergy(int energyValue) {
        if (energyValue <= 0) { // если энергия <= 0 питомец умер от слабости
            this.energy = 0;
            System.out.println("The pet died of weakness (▽◕ ᴥ ◕▽)...");
            // иначе возвращаем меньший уровень энергии
        } else this.energy = Math.min(energyValue, this.energyMax);
    }

    public void setHungry(int hungryValue) {
        if (hungryValue <= 0) { // если голод <= 0 питомец умер от голода
            this.hungry = 0;
            System.out.println("The pet starved to death (×﹏×)...");
            // иначе возвращаем меньший уровень голода
        } else this.hungry = Math.min(hungryValue, this.hungryMax);
    }

    public void setPurity(int purityValue) {
        if (purityValue <= 0) { // если чистота <= 0 питомец умер от грязи
            this.purity = 0;
            System.out.println("The pet died of dirt (×﹏×)...");
            // иначе возвращаем меньший уровень чистоты
        } else this.purity = Math.min(purityValue, this.purityMax);
    }

    // Методы
    // 1. Метод проверки состояния питомца  true == мертвый, false == живой
    public boolean petDied() {
        return this.energy <= 0 || this.hungry <= 0 || this.purity <= 0;
    }


    // 2.Метод  - играть
    public void play() {
        setEnergy(this.energy);
        setHungry(this.hungry);
        setPurity(this.purity);
        // если один из параметров жизни питомца <= 0
        if (petDied()) {
            System.out.println("Pet died (×﹏×)..."); // питомец умер
        } else { // иначе отнимаем жизненные параметры параметры
            this.energy -= 10; // 10 очков от энергии
            this.hungry -= 10;   // 10 очков от сытости
            this.purity -= 10; // 10 очков от чистоты
            this.exp++; // прибавляем опыт
            this.age++; // прибавляем возраст
        }
    }

    // 3. Метод - кушать
    public void eat() {
        // если один из параметров жизни питомца <= 0
        if (petDied()) {
            System.out.println("Pet died (×﹏×)...");
        } else {
            this.energy -= 5;// отнимаем 5 очков от энергии
            this.hungry = this.hungryMax; // заполняем сытость до максимума
            this.purity -= 10; //  отнимаем 10 очков от чистоты
            this.age++; // прибавляем возраст
        }
    }

    // 4. Метод -  спать
    public void sleep() {
        // если один из параметров жизни питомца <= 0
        if (petDied()) {
            System.out.println("Pet died (×﹏×)...");
            //и если энергия питомца составляет >= 90
        } else if (this.energy >= (this.energyMax - 10)) {
            System.out.println("Don't feel like sleeping (⊙_⊙) !!! ");
            // питомец спать не хочет
        } else  {
            this.age++; // питомец взрослеет
            this.energy = this.energyMax; // заполняем энергию до максимума
            this.hungry -= 5; // уровень сытости уменьшается
        }
    }


    // 5. Метод - мыться
    public void wash() {
        // если один из параметров жизни питомца <= 0
        if (petDied()) {
            System.out.println("Pet died (×﹏×)...");
        } else {
            this.energy -= 20; // отнимаем 20 очков от энергии
            this.hungry -= 10; // отнимаем 10 очков от сытости
            this.purity = this.purityMax; // устанавливаем уровень максимальной чистоты
            this.age++; // прибавляем возраст
        }
    }

    // 6. Метод toString
    @Override
    public String toString() {
        return "Energy: " + this.getEnergy() + "/" + this.getEnergyMax() + ", Satiety: " +
                this.getHungry() + "/" + this.getHungryMax() + ", Pure: " + this.getPurity() +
                "/" + this.getPurityMax() + ", Experience: " + this.getExp() + ", Age: " + this.getAge() + "\n";
    }
}//class close