import java.util.ArrayList;
import java.util.Random;

public class Program {
    private int allRoom;
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

    private void generateSpawnRoom() {
        int x = random.nextInt(width);
        dungeon[0][x].setPosition(x, 0);
        dungeon[0][x].value = 2;
        amountOfRoom--;
    }

    private void printDungeon() {
        System.out.println("Width : " + width);
        System.out.println("Length : " + length);
        for (int x = 0; x < width; x++) {
            if (dungeon[0][x].value == 2) {
                System.out.println("Spawn room : " + "[0]" + "[" + x + "]");
            }
        }
        System.out.println("Amount of room : " + allRoom + "\n");

        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(dungeon[y][x].value + "\t");
            }
            System.out.print("\n");
        }
    }

    private void generateOtherRoom() {
        while (amountOfRoom > 0) {

            ArrayList<Room> existRoom = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < length; y++) {
                    if (dungeon[y][x].value >= 1) {
                        existRoom.add(dungeon[y][x]);
                    }
                }
            }

            /*
            Step 1 : choose a random room.
            Step 2 : check if it have a uncreated path.
            Step 3 : If yes, check if the next room is outside matrix. If not, return to step 1.
            Step 4 : If next room isn't outside matrix then set value and location for it and set "created" of path of room is true.
            Step 5 : return step 1 until amount of room = 0.
             */

            Room room = existRoom.get(random.nextInt(existRoom.size()));
            int pathToNextRoom = random.nextInt(3);

            if (!room.path[pathToNextRoom].created) {

                if (pathToNextRoom == 0) {
                    if (room.x + 1 < width) {
                        if (dungeon[room.y][room.x + 1].value == 0) {
                            dungeon[room.y][room.x + 1].value = 1;
                            dungeon[room.y][room.x + 1].setPosition(room.x + 1, room.y);
                            dungeon[room.y][room.x].path[0].created = true;
                            amountOfRoom--;
                            System.out.println("\n");
                            System.out.println("Chosen room : " + "[" + room.x + "]" + "[" + room.y + "]");
                            System.out.println("Path to next room : " + pathToNextRoom);
                            System.out.println("Next room's position :" + "[" + (room.x + 1) + "]" + "[" + room.y + "]");
                            System.out.println("Next room's value : " + dungeon[room.y][room.x + 1].value);
                            System.out.println("Path to next room : " + pathToNextRoom + ", " + dungeon[room.y][room.x].path[0].created);
                            System.out.println("\n");
                        }
                    }
                }
                if (pathToNextRoom == 1) {
                    if (room.x - 1 >= 0) {
                        if (dungeon[room.y][room.x - 1].value == 0) {
                            dungeon[room.y][room.x - 1].value = 1;
                            dungeon[room.y][room.x - 1].setPosition(room.x - 1, room.y);
                            dungeon[room.y][room.x].path[1].created = true;
                            amountOfRoom--;
                            System.out.println("\n");
                            System.out.println("Chosen room : " + "[" + room.x + "]" + "[" + room.y + "]");
                            System.out.println("Path to next room : " + pathToNextRoom);
                            System.out.println("Next room's position :" + "[" + (room.x - 1) + "]" + "[" + room.y + "]");
                            System.out.println("Next room's value : " + dungeon[room.y][room.x - 1].value);
                            System.out.println("Path to next room : " + pathToNextRoom + ", " + dungeon[room.y][room.x].path[1].created);
                            System.out.println("\n");
                        }
                    }
                }
                if (pathToNextRoom == 2) {
                    if (room.y + 1 < length) {
                        if (dungeon[room.y + 1][room.x].value == 0) {
                            dungeon[room.y + 1][room.x].value = 1;
                            dungeon[room.y + 1][room.x].setPosition(room.x, room.y + 1);
                            dungeon[room.y][room.x].path[2].created = true;
                            amountOfRoom--;
                            System.out.println("\n");
                            System.out.println("Chosen room : " + "[" + room.x + "]" + "[" + room.y + "]");
                            System.out.println("Path to next room : " + pathToNextRoom);
                            System.out.println("Next room's position :" + "[" + room.x + "]" + "[" + (room.y + 1) + "]");
                            System.out.println("Next room's value : " + dungeon[room.y + 1][room.x].value);
                            System.out.println("Path to next room : " + pathToNextRoom + ", " + dungeon[room.y][room.x].path[2].created);
                            System.out.println("\n");
                        }
                    }
                }

            }

        }
    }

    private void generateRoom() {
        dungeon = getDungeon();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                dungeon[y][x] = new Room();
            }
        }
        allRoom = getAmountOfRoom();
        amountOfRoom = getAmountOfRoom();
        generateSpawnRoom();
        generateOtherRoom();
        printDungeon();
    }

    public static void main(String[] args) {
        new Program().generateRoom();
    }
}
