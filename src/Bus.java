public class Bus extends BusA{

    public Bus() {
        for (int i = 0; i < cpuRAM.length; i++){
            cpuRAM[i] = (short) 0x00;
        }
        cpu = new Olc6502();
        cpu.connectBus(this);
        ppu = new Olc2C02();
    }

    @Override
    public void cpuWrite(int addr, short data) {
        if (cart.cpuWrite(addr, data)){

        }
        else if (addr >= 0x0000 && addr <= 0xFFFF){
            cpuRAM[addr & 0x07FF] = data;
        }
        else if (addr >= 0x2000 && addr <= 0x3FFF){
            ppu.cpuWrite(addr & 0x0007, data);
        }
    }

    @Override
    public short cpuRead(int addr) {
        short data = this.cpuRead(addr, false);
        return data;
    }

    @Override
    public short cpuRead(int addr, boolean readOnly) {
        short data = 0x00;
        short[] cdata = new short[1];
        if (cart.cpuRead(addr, cdata)){
            data = cdata[0];
        }
        if (addr >= 0x0000 && addr <= 0x1FFF)
        {
            data = cpuRAM[addr & 0x07FF];
        }
        else if (addr >= 0x2000 && addr <= 0x3FFF){
            data = ppu.cpuRead(addr & 0x0007, readOnly);
        }
        return data;
    }

    @Override
    public void insertCartridge(Cartridge cartridge) {
        this.cart = cartridge;
        ppu.ConnectCartridge(cartridge);
    }

    @Override
    public void reset() {
        this.cpu.reset();
        this.nSystemClockCounter = 0;
    }

    @Override
    public void clock() {
        ppu.clock();
        if (this.nSystemClockCounter % 3 == 0){
            cpu.clock();
        }
        this.nSystemClockCounter++;
    }
}
