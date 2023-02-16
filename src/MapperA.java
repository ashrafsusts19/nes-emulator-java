public abstract class MapperA {
    public MapperA(short prgBanks, short chrBanks){
        this.nPRGBanks = prgBanks;
        this.nCHRBanks = chrBanks;
    }

    public abstract boolean cpuMapRead(int addr, long[] mapped_addr);
    public abstract boolean cpuMapWrite(int addr, long[] mapped_addr);
    public abstract boolean ppuMapRead(int addr, long[] mapped_addr);
    public abstract boolean ppuMapWrite(int addr, long[] mapped_addr);
    public short nPRGBanks = 0, nCHRBanks = 0;
}