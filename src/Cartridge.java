import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Cartridge extends CartridgeA {
    private class Header {
        char[] name;
        short prg_rom_chunks, chr_rom_chunks;
        short mapper1, mapper2;
        short prg_ram_size;
        short tv_system1, tv_system2;
        char[] unused;
        public Header(byte[] header) {
            this.name = new char[4];
            this.unused = new char[5];
            for (int i = 0; i < 4; i++){
                name[i] = (char) header[i];
            }
            prg_rom_chunks = header[4];
            chr_rom_chunks = header[5];
            mapper1 = header[6];
            mapper2 = header[7];
            prg_ram_size = header[8];
            tv_system1 = header[9];
            tv_system2 = header[10];
            for (int i = 0; i < 5; i++){
                unused[i] = (char) header[11 + i];
            }
        }
    }

    public Cartridge(String sFileName) throws IOException {
        byte[] header = new byte[16];
        byte[] junk = new byte[512];
        InputStream cartFile = new FileInputStream(sFileName);
        cartFile.read(header);
        cartFile.read(junk);
        Header headerData = new Header(header);
        this.nMapperID = (short) (((headerData.mapper2 >> 4) << 4) | (headerData.mapper1 >> 4));
        short nFileType = 1;
        if (nFileType == 0){

        }
        else if (nFileType == 1){
            this.nPRGBanks = headerData.prg_rom_chunks;
            this.vPRGMemory = new ArrayList<>(this.nPRGBanks * 16384);
            byte[] vPRGMtemp = new byte[this.vPRGMemory.size()];
            cartFile.read(vPRGMtemp);
            for (int i = 0; i < this.vPRGMemory.size(); i++){
                this.vPRGMemory.set(i, (short) vPRGMtemp[i]);
            }

            this.nCHRBanks = headerData.chr_rom_chunks;
            this.vCHRMemory = new ArrayList<>(this.nCHRBanks * 8192);
            byte[] vCHRMtemp = new byte[this.vCHRMemory.size()];
            cartFile.read(vCHRMtemp);
            for (int i = 0; i < this.vCHRMemory.size(); i++){
                this.vCHRMemory.set(i, (short) vCHRMtemp[i]);
            }
        }
        else if (nFileType == 2){

        }

        switch (this.nMapperID){
            case 0:
                pMapper = new Mapper_000(this.nPRGBanks, this.nCHRBanks);
                break;
        }

        cartFile.close();
    }

    @Override
    public boolean cpuRead(int addr, short[] data) {
        long[] mapped_addr = new long[1];
        if (pMapper.cpuMapRead(addr, mapped_addr)){
            data[0] = this.vPRGMemory.get((int) mapped_addr[0]);
            return true;
        }
        return false;
    }

    @Override
    public boolean cpuWrite(int addr, short data) {
        long[] mapped_addr = new long[1];
        if (pMapper.cpuMapWrite(addr, mapped_addr)){
            this.vPRGMemory.set((int) mapped_addr[0], data);
            return true;
        }
        return false;
    }

    @Override
    public boolean ppuRead(int addr, short[] data) {
        long[] mapped_addr = new long[1];
        if (pMapper.ppuMapRead(addr, mapped_addr))
        {
            data[0] = vCHRMemory.get((int) mapped_addr[0]);
            return true;
        }
        return false;
    }

    @Override
    public boolean ppuWrite(int addr, short data) {
        long[] mapped_addr = new long[1];
        if (pMapper.ppuMapWrite(addr, mapped_addr)){
            this.vCHRMemory.set((int) mapped_addr[0], data);
            return true;
        }
        return false;
    }
}
