import javax.swing.*;
import java.awt.*;

public class MainFrame extends JPanel {
    private final DungeonGenerator generator = new DungeonGenerator();

    public void paint(Graphics g) {
        super.paint(g);
        for (int x = 0; x < generator.width; x++) {
            for (int y = 0; y < generator.length; y++) {
                if (generator.dungeon[y][x].value >= 1) {
                    if(generator.dungeon[y][x].value == 2){
                        g.setColor(Color.RED);
                    } else if(generator.dungeon[y][x].value == 1){
                        g.setColor(Color.BLACK);
                    }
                    g.fillRect(x * 60 * 2, y * 60 * 2, 60, 60);
                    g.setColor(Color.DARK_GRAY);
                    if (generator.dungeon[y][x].path[0].created) {
                        g.fillRect(x * 60 * 2 + 60, y * 60 * 2 + 20, 60, 20);
                    }
                    if (generator.dungeon[y][x].path[1].created) {
                        g.fillRect(x * 60 * 2 - 60, y * 60 * 2 + 20, 60, 20);
                    }
                    if (generator.dungeon[y][x].path[2].created) {
                        g.fillRect(x * 60 * 2 + 20, y * 60 * 2 + 60, 20, 60);
                    }
                    if (generator.dungeon[y][x].path[3].created) {
                        g.fillRect(x * 60 * 2 + 20, y * 60 * 2 - 60, 20, 60);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        MainFrame mainFrame = new MainFrame();
        frame.add(mainFrame);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
