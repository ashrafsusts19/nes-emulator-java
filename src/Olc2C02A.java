import java.util.ArrayList;

public abstract class Olc2C02A {
    public Cartridge cart;
    public ArrayList<ArrayList<Short>> tblName;
    public ArrayList<Short> tblPalette;
    public ArrayList<ArrayList<Short>> tblPattern;

    public abstract short cpuRead(int addr, boolean readonly);
    public abstract short cpuRead(int addr);
    public abstract void cpuWrite(int addr, short data);

    public abstract short ppuRead(int addr, boolean readonly);
    public abstract short ppuRead(int addr);
    public abstract void ppuWrite(int addr, short data);

    public abstract void ConnectCartridge(Cartridge cartridge);
    public abstract void clock();
}
