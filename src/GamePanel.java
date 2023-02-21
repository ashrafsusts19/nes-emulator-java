import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    GameFrame mainGame;
    int pixelSize;
    public GamePanel(GameFrame _mainGame, int SCREEN_WIDTH, int SCREEN_HEIGHT) {
        mainGame = _mainGame;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
//        this.pixelSize = Integer.min(SCREEN_HEIGHT / 240, SCREEN_WIDTH / 256);
        this.pixelSize = 2;
        this.setBackground(Color.decode("#FFFFFF"));
        this.setFocusable(true);
    }

    public void drawSprite(Graphics g, int x, int y, Olc2C02A.Sprite sprite){
        for (int ir = 0; ir < sprite.height; ir++){
            for (int ic = 0; ic < sprite.width; ic++){
                Olc2C02A.Pixel p = sprite.getPixel(ic, ir);
                if (p.a != 0){
                    int pr = p.r, pg = p.g, pb = p.b, pa = p.a;
                    Color color = new Color(pr, pg, pb, pa);
                    g.setColor(color);
                    g.drawRect((x + ic) * this.pixelSize, (y + ir) * this.pixelSize, this.pixelSize + 1, this.pixelSize + 1);
                }
            }
        }
    }

    public void drawCPU(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.drawCPU();

        this.drawSprite(g, 0, 0, mainGame.nes.ppu.getScreen());

        this.drawSprite(g, 257, 0, mainGame.nes.ppu.getPatternTable((short) 0, mainGame.nSelectedPalette));
        this.drawSprite(g, 257, 150, mainGame.nes.ppu.getPatternTable((short) 1, mainGame.nSelectedPalette));
    }

    public void gameLoop() {
        this.repaint();
    }
}

