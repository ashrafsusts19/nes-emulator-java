import java.util.ArrayList;

public abstract class CartridgeA {
    short nMapperID = 0;
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
