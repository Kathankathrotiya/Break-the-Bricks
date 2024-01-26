import java.awt.*;
import java.util.Random;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int row, int col) {
        map = new int[row][col];
        brickWidth = 540 / col;
        brickHeight = 150 / row;
        setLevel(1);  // Default to level 1
    }

    public void setLevel(int level) {
        // Reset the map for each level
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if ((i + j) % 2 == 0 || (i - j) % 3 == 0) 
					map[i][j] = Math.random() > 0.3 ? 1 : 0;
				else
					map[i][j] = 1;
            }
        }
        // Generate random bricks based on the level
        Random rand = new Random();
        for (int k = 0; k < level * 2; k++) {
            int i = rand.nextInt(map.length);
            int j = rand.nextInt(map[0].length);
            map[i][j] = 0;
        }
    }

    public void draw(Graphics2D g) {
        Random rand = new Random();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    int red = rand.nextInt(256);
                    int green = rand.nextInt(256);
                    int blue = rand.nextInt(256);
                    Color randomColor = new Color(red, green, blue);

                    g.setColor(randomColor);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
