public class Mapper_000 extends MapperA{
    public Mapper_000(short prgBanks, short chrBanks) {
        super(prgBanks, chrBanks);
    }

    @Override
    public boolean cpuMapRead(int addr, long[] mapped_addr) {
        if (addr >= 0x8000 && addr <= 0xFFFF){
            mapped_addr[0] = addr & (this.nPRGBanks > 1 ? 0x7FFF : 0x3FFF);
            return true;
        }
        return false;
    }

    @Override
    public boolean cpuMapWrite(int addr, long[] mapped_addr) {
        if (addr >= 0x8000 && addr <= 0xFFFF){
            mapped_addr[0] = addr & (this.nPRGBanks > 1 ? 0x7FFF : 0x3FFF);
            return true;
        }
        return false;
    }

    @Override
    public boolean ppuMapRead(int addr, long[] mapped_addr) {
        if (addr >= 0x0000 && addr <= 0x1FFF){
            mapped_addr[0] = addr;
            return true;
        }
        return false;
    }

    @Override
    public boolean ppuMapWrite(int addr, long[] mapped_addr) {
//        if (addr >= 0x0000 && addr <= 0x1FFF){
//
//            return true;
//        }
        return false;
    }
}
