import java.util.ArrayList;

public abstract class CartridgeA {
    enum MIRROR {
        HORIZONTAL,
        VERTICAL,
        ONESCREEN_LO,
        ONESCREEN_HI;
    }

    short nMapperID = 0;
    MIRROR mirror = MIRROR.HORIZONTAL;
    short nPRGBanks = 0;
    short nCHRBanks = 0;

    public short[] vPRGMemory;
    public short[] vCHRMemory;

    MapperA pMapper;
    
    public abstract boolean cpuRead(int addr, short[] data);
    public abstract boolean cpuWrite(int addr, short data);

    public abstract boolean ppuRead(int addr, short[] data);
    public abstract boolean ppuWrite(int addr, short data);
}
