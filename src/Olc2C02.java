import java.util.ArrayList;

public class Olc2C02 extends Olc2C02A{

    public Olc2C02(){
        this.tblName = new ArrayList<>(2);
        tblName.set(0, new ArrayList<>(1024));
        tblName.set(1, new ArrayList<>(1024));
        this.tblPalette = new ArrayList<>(32);
        this.tblPattern = new ArrayList<>(2);
        this.tblPattern.set(0, new ArrayList<>(4096));
        this.tblPattern.set(1, new ArrayList<>(4096));
    }

    @Override
    public short cpuRead(int addr, boolean readonly) {
        short data = 0x00;
        switch (addr)
        {
            case 0x0000: // Control
                break;
            case 0x0001: // Mask
                break;
            case 0x0002: // Status
                break;
            case 0x0003: // OAM Address
                break;
            case 0x0004: // OAM Data
                break;
            case 0x0005: // Scroll
                break;
            case 0x0006: // PPU Address
                break;
            case 0x0007: // PPU Data
                break;
        }

        return data;
    }

    @Override
    public short cpuRead(int addr) {
        short data = cpuRead(addr, false);
        return data;
    }

    @Override
    public void cpuWrite(int addr, short data) {
        switch (addr)
        {
            case 0x0000: // Control
                break;
            case 0x0001: // Mask
                break;
            case 0x0002: // Status
                break;
            case 0x0003: // OAM Address
                break;
            case 0x0004: // OAM Data
                break;
            case 0x0005: // Scroll
                break;
            case 0x0006: // PPU Address
                break;
            case 0x0007: // PPU Data
                break;
        }
    }

    @Override
    public short ppuRead(int addr, boolean readonly) {
        short data = 0x00;
        addr &= 0x3FFF;

        short[] cdata = new short[1];
        if (cart.ppuRead(addr, cdata)){

        }

        return data;
    }

    @Override
    public short ppuRead(int addr) {
        short data = ppuRead(addr, false);
        return data;
    }

    @Override
    public void ppuWrite(int addr, short data) {
        addr &= 0x3FFF;
        if (cart.ppuWrite(addr, data)){

        }
    }

    @Override
    public void ConnectCartridge(Cartridge cartridge) {
        this.cart = cartridge;
    }

    @Override
    public void clock() {

    }
}
