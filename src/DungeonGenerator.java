import java.util.ArrayList;
import java.util.Random;

public class DungeonGenerator {
    private int allRoom;
    private int amountOfRoom;
    public Room[][] dungeon;
    public int width, length;
    private final Random random = new Random();

    public DungeonGenerator() {
        generateRoom();
    }

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
        int y = random.nextInt(length);
        dungeon[y][x].setPosition(x, y);
        dungeon[y][x].value = 2;
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

            Room room = existRoom.get(random.nextInt(existRoom.size()));
            int pathToNextRoom = random.nextInt(room.path.length);

            if (!room.path[pathToNextRoom].created) {

                if (pathToNextRoom == 0) {
                    if (room.x + 1 < width) {
                        if (dungeon[room.y][room.x + 1].value == 0) {
                            if (amountOfRoom > 1) {
                                dungeon[room.y][room.x + 1].value = 1;
                            } else {
                                dungeon[room.y][room.x + 1].value = 3;
                            }
                            dungeon[room.y][room.x + 1].setPosition(room.x + 1, room.y);
                            dungeon[room.y][room.x + 1].path[1].created = true;
                            dungeon[room.y][room.x].path[0].created = true;
                            amountOfRoom--;
                        }
                    }
                }
                if (pathToNextRoom == 1) {
                    if (room.x - 1 >= 0) {
                        if (dungeon[room.y][room.x - 1].value == 0) {
                            if (amountOfRoom > 1) {
                                dungeon[room.y][room.x - 1].value = 1;
                            } else {
                                dungeon[room.y][room.x - 1].value = 3;
                            }
                            dungeon[room.y][room.x - 1].setPosition(room.x - 1, room.y);
                            dungeon[room.y][room.x - 1].path[0].created = true;
                            dungeon[room.y][room.x].path[1].created = true;
                            amountOfRoom--;
                        }
                    }
                }
                if (pathToNextRoom == 2) {
                    if (room.y + 1 < length) {
                        if (dungeon[room.y + 1][room.x].value == 0) {
                            if (amountOfRoom > 1) {
                                dungeon[room.y + 1][room.x].value = 1;
                            } else {
                                dungeon[room.y + 1][room.x].value = 3;
                            }
                            dungeon[room.y + 1][room.x].setPosition(room.x, room.y + 1);
                            dungeon[room.y + 1][room.x].path[3].created = true;
                            dungeon[room.y][room.x].path[2].created = true;
                            amountOfRoom--;
                        }
                    }
                }
                if (pathToNextRoom == 3) {
                    if (room.y - 1 >= 0) {
                        if (dungeon[room.y - 1][room.x].value == 0) {
                            if (amountOfRoom > 1) {
                                dungeon[room.y - 1][room.x].value = 1;
                            } else {
                                dungeon[room.y - 1][room.x].value = 3;
                            }
                            dungeon[room.y - 1][room.x].setPosition(room.x, room.y - 1);
                            dungeon[room.y - 1][room.x].path[2].created = true;
                            dungeon[room.y][room.x].path[3].created = true;
                            amountOfRoom--;
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
        amountOfRoom = allRoom;
        generateSpawnRoom();
        generateOtherRoom();
        printDungeon();
    }
}
