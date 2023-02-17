import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameFrame extends JFrame implements ActionListener {
    public class MyKeyAdapter extends KeyAdapter {
        GameFrame mainGame;
        MyKeyAdapter(GameFrame _mainGame) {
            super();
            mainGame = _mainGame;
        }
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    mainGame.keyPressed("UP");
                    break;
                case KeyEvent.VK_DOWN:
                    mainGame.keyPressed("DOWN");
                    break;
                case KeyEvent.VK_LEFT:
                    mainGame.keyPressed("LEFT");
                    break;
                case KeyEvent.VK_RIGHT:
                    mainGame.keyPressed("RIGHT");
                    break;
                case KeyEvent.VK_ENTER:
                    mainGame.keyPressed("ENTER");
                    break;
                case KeyEvent.VK_ESCAPE:
                    mainGame.keyPressed("ESCAPE");
                    break;
                case KeyEvent.VK_SPACE:
                    mainGame.keyPressed("SPACE");
                    break;
                case KeyEvent.VK_C:
                    mainGame.keyPressed("C");
                    break;
                case KeyEvent.VK_R:
                    mainGame.keyPressed("R");
                case KeyEvent.VK_F:
                    mainGame.keyPressed("F");
                    break;
            }
        }
    }

    Timer timer;
    boolean running = false;
    int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
    int DELAY = 1000 / 60;
    Cartridge cart;
    Bus nes;
    boolean bEmulationRun = false;
    long fResidualTime = 0l;
    GamePanel gamePanel;

    GameFrame(){
        try {
            cart = new Cartridge("D:\\Projects\\Java\\nes-emulator-java\\Test Roms\\color_test.nes");
        } catch (IOException e) {
            e.printStackTrace();
        }

        nes = new Bus();
        nes.insertCartridge(cart);
        nes.reset();
        this.setTitle("TEST");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);
        this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.gamePanel = new GamePanel(this, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.add(this.gamePanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new MyKeyAdapter(this));
        this.running = true;
        this.timer = new Timer(DELAY, this);
        this.timer.start();
    }

    public void gameLoop() {
        if (this.bEmulationRun){
            do {
                nes.clock();
            }
            while (!nes.ppu.frame_complete);
            nes.ppu.frame_complete = false;
        }
        this.gamePanel.gameLoop();
    }


    public void keyPressed(String key) {
        if (!this.bEmulationRun) {
            if (key == "C"){
                do {
                    nes.clock();
                }
                while (nes.cpu.cycles != 0);
                do {
                    nes.clock();
                }
                while (nes.cpu.cycles == 0);
                System.out.println(key);
            }
            if (key == "F"){
                do {
                    nes.clock();
                }
                while (!nes.ppu.frame_complete);
                do {
                    nes.clock();
                }
                while (nes.cpu.cycles != 0);
                System.out.println(key);
                nes.ppu.frame_complete = false;
            }
        }
        if (key == "R"){
            nes.reset();
            System.out.println(key);
        }
        if (key == "SPACE"){
            this.bEmulationRun = !this.bEmulationRun;
            System.out.println(key);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            this.gameLoop();
        }
    }
}
