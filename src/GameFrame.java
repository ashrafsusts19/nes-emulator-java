import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameFrame extends JFrame implements ActionListener {
    public class MyKeyAdapter extends KeyAdapter {
        GameFrame mainGame;
        MyKeyAdapter(GameFrame _mainGame) {
            super();
            mainGame = _mainGame;
        }
        @Override
        public void keyReleased(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    mainGame.keyReleased("UP");
                    break;
                case KeyEvent.VK_DOWN:
                    mainGame.keyReleased("DOWN");
                    break;
                case KeyEvent.VK_LEFT:
                    mainGame.keyReleased("LEFT");
                    break;
                case KeyEvent.VK_RIGHT:
                    mainGame.keyReleased("RIGHT");
                    break;
                case KeyEvent.VK_A:
                    mainGame.keyReleased("A");
                    break;
                case KeyEvent.VK_S:
                    mainGame.keyReleased("S");
                    break;
                case KeyEvent.VK_Q:
                    mainGame.keyReleased("Q");
                    break;
                case KeyEvent.VK_W:
                    mainGame.keyReleased("W");
                    break;
            }
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
                    break;
                case KeyEvent.VK_F:
                    mainGame.keyPressed("F");
                    break;
                case KeyEvent.VK_A:
                    mainGame.keyPressed("A");
                    break;
                case KeyEvent.VK_S:
                    mainGame.keyPressed("S");
                    break;
                case KeyEvent.VK_Q:
                    mainGame.keyPressed("Q");
                    break;
                case KeyEvent.VK_W:
                    mainGame.keyPressed("W");
                    break;
            }
        }
    }

    Timer timer;
    boolean running = false;
    int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
//    int DELAY = 1000 / 60;
    int DELAY = 1;
    Cartridge cart;
    Bus nes;
    boolean bEmulationRun = false;
    long fResidualTime = 0l;
    GamePanel gamePanel;
    short nSelectedPalette = 0x00;
    int frameSkip = 2;
    int frameCount = 0;

    GameFrame(){
        try {
            cart = new Cartridge("D:\\Projects\\Java\\nes-emulator-java\\Test Roms\\Super Mario Bros (E).nes");
            //cart = new Cartridge("D:\\Projects\\Java\\nes-emulator-java\\Test Roms\\nestest.nes");
            //cart = new Cartridge("D:\\Projects\\Java\\nes-emulator-java-overhaul\\Test Roms\\color_test.nes");
            //cart = new Cartridge("D:\\Projects\\Java\\nes-emulator-java\\Test Roms\\Donkey Kong (World) (Rev A).nes");
            //cart = new Cartridge("D:\\Projects\\Java\\nes-emulator-java\\Test Roms\\Fire Emblem Gaiden (J) [T-Eng97b].nes");
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
        if (this.frameCount == this.frameSkip){
            this.gamePanel.gameLoop();
            this.frameCount = 0;
        }
        else {
            this.frameCount++;
        }
    }

    public void keyReleased(String key){
        switch (key){
            case "S":
                nes.controller[0] &= ~0x80;
                break;
            case "A":
                nes.controller[0] &= ~0x40;
                break;
            case "Q":
                nes.controller[0] &= ~0x20;
                break;
            case "W":
                nes.controller[0] &= ~0x10;
                break;
            case "UP":
                nes.controller[0] &= ~0x08;
                break;
            case "DOWN":
                nes.controller[0] &= ~0x04;
                break;
            case "LEFT":
                nes.controller[0] &= ~0x02;
                break;
            case "RIGHT":
                nes.controller[0] &= ~0x01;
                break;
        }
//        System.out.println(nes.controller[0]);
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
        if (key == "P"){
            this.nSelectedPalette = (short) ((this.nSelectedPalette + 1) & 0x07);
        }
        switch (key){
            case "S":
                nes.controller[0] |= 0x80;
                break;
            case "A":
                nes.controller[0] |= 0x40;
                break;
            case "Q":
                nes.controller[0] |= 0x20;
                break;
            case "W":
                nes.controller[0] |= 0x10;
                break;
            case "UP":
                nes.controller[0] |= 0x08;
                break;
            case "DOWN":
                nes.controller[0] |= 0x04;
                break;
            case "LEFT":
                nes.controller[0] |= 0x02;
                break;
            case "RIGHT":
                nes.controller[0] |= 0x01;
                break;
        }
//        System.out.println(nes.controller[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            this.gameLoop();
        }
    }
}
