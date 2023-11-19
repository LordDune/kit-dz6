package pac.main;
import java.util.HashMap;
import java.util.Random;

public class Main {
    static Random random = new Random();
    static final int COUNT_DOORS = 3; // количесто дверей
    static final int COUNT_GAME = 10000; // количество игр

    public static void main(String[] args) {
        int countGame = 0;
        HashMap<Integer, Integer> result = new HashMap<>(); // сюда будут записывать результаты игры
        while (countGame < COUNT_GAME) {
            int prize = random.nextInt(COUNT_DOORS) + 1; // определен номер двери, за которой приз
            int playerChoice = random.nextInt(COUNT_DOORS) + 1; // игрок выбрал дверь, за которой по его мнению приз
            int doorOpen = openDoorAndsecondChoice(prize, playerChoice); // выбираем какую дверь открыть (кроме призовой и кроме той, что выбрал игрок)
            playerChoice = openDoorAndsecondChoice(playerChoice, doorOpen); // игрок меняет выбор (кроме той, то открыта и кроме той, что выбрал изначально)
            if (prize == playerChoice) {
                result.put(countGame++, 1); // если номер выбранной двери совпал с призовой, то добавляем в коллекцию единицу
            } else {
                result.put(countGame++, 0); // если номер выбранной двери НЕ совпал с призовой, то добавляем в коллекцию ноль
            }
        }
        int sum = result.values().stream().mapToInt(Integer::valueOf).sum(); // определяем количество единиц (побед) в коллекции
        System.out.println("Сыграно игр: " + result.size());
        System.out.println("Игрок победил, каждый раз меняя свой выбор, " + sum + " раз");
        System.out.println("Процент побед составляет " + (double) sum / (double) result.size() * 100);
//        Сыграно игр: 10000
//        Игрок победил, каждый раз меняя свой выбор, 6766 раз
//        Процент побед составляет 67.66
    }

    public static int openDoorAndsecondChoice(int prize, int playerChoice){
        int doorOpen;
        do {
            doorOpen = random.nextInt(COUNT_DOORS) + 1;
           } while (doorOpen == prize || doorOpen == playerChoice);
        return doorOpen;
    }
}

