public class Bus extends BusA{

    public Bus() {
        for (int i = 0; i < 64 * 1024; i++){
            RAM.set(i, (short) 0x00);
        }
        cpu = new Olc6502();
        cpu.connectBus(this);
    }

    @Override
    public void write(int addr, short data) {
        if (addr >= 0x0000 && addr <= 0xFFFF)
            RAM.set(addr, data);
    }

    @Override
    public short read(int addr) {
        if (addr >= 0x0000 && addr <= 0xFFFF)
            return RAM.get(addr);
        return 0;
    }

    @Override
    public short read(int addr, boolean readOnly) {
        if (addr >= 0x0000 && addr <= 0xFFFF)
            return RAM.get(addr);
        return 0;
    }
}
