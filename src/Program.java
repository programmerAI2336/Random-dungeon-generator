import java.util.Random;

public class Program {
    private int amountOfRoom;
    private Room[][] dungeon;
    private int width, length;
    private final Random random = new Random();

    private Room[][] getDungeon() {
        int[] size = new int[5];
        for (int i = 0; i < size.length; i++) {
            size[i] = i + 1;
        }
        while (width * length < 3) {
            width = size[random.nextInt(size.length)];
            length = size[random.nextInt(size.length)];
        }
        return new Room[length][width];
    }


    private int getAmountOfRoom() {
        int amount = 0;
        while (amount < 3) {
            amount = random.nextInt(width * length + 1);
        }
        return amount;
    }

    private void generateRoom() {
        dungeon = getDungeon();
        System.out.println("Width : " + width);
        System.out.println("Length : " + length);
        System.out.println("Room : " + amountOfRoom);
    }

    public static void main(String[] args) {
        new Program().generateRoom();
    }
}
