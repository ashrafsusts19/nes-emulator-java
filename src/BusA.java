import java.util.ArrayList;

public abstract class BusA {
    public Olc6502 cpu;

    public ArrayList<Short> RAM = new ArrayList<>(64 * 1024);
    public abstract void write(int addr, short data);
    public abstract short read(int addr);
    public abstract short read(int addr, boolean readOnly);
}
