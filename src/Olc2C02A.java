import java.util.ArrayList;

public abstract class Olc2C02A {
    public class Pixel{
        public int r, g, b, a = 255;
        public Pixel(int _r, int _g, int _b){
            this.r = _r;
            this.g = _g;
            this.b = _b;
        }
        public Pixel(int _r, int _g, int _b, int _a){
            this.r = _r;
            this.g = _g;
            this.b = _b;
            this.a = _a;
        }
    }

    public class Sprite{
        int width, height;
        Pixel[][] pixels;
        public Sprite(int w, int h){
            this.width = w;
            this.height = h;
            this.pixels = new Pixel[h][w];
            for (int ih = 0; ih < h; ih++){
                for (int iw = 0; iw < w; iw++){
                    pixels[ih][iw] = new Pixel(0, 0, 0);
                }
            }
        }

        public Pixel getPixel(int x, int y){
            if (x >= this.width || x < 0 || y >= this.height || y < 0){
                return new Pixel(0, 0, 0, 0);
            }
            return this.pixels[y][x];
        }
        public void setPixel(int x, int y, Pixel p){
            if (x >= this.width || x < 0 || y >= this.height || y < 0){
                return;
            }
            this.pixels[y][x] = p;
        }


    }

    public Cartridge cart;
    public short[][] tblName;
    public short[] tblPalette;
    public short[][] tblPattern;

    public Pixel[] palScreen;
    public Sprite sprScreen = new Sprite(256, 240);
    public Sprite[] sprNameTable = new Sprite[2];//{ olc::Sprite(256, 240), olc::Sprite(256, 240) };
    public Sprite[] sprPatternTable = new Sprite[2];//{ olc::Sprite(128, 128), olc::Sprite(128, 128) };

    public abstract Sprite getScreen();
    public abstract Sprite getNameTable(short i);
    public abstract Sprite getPatternTable(short i);
    boolean frame_complete = false;

    public int scanline = 0, cycle = 0;

    public abstract short cpuRead(int addr, boolean readonly);
    public abstract short cpuRead(int addr);
    public abstract void cpuWrite(int addr, short data);

    public abstract short ppuRead(int addr, boolean readonly);
    public abstract short ppuRead(int addr);
    public abstract void ppuWrite(int addr, short data);

    public abstract void ConnectCartridge(Cartridge cartridge);
    public abstract void clock();
}
