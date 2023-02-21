import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//Use short (2 bytes, 16-bit) instead of uint8 (1 byte)
//Use int (4 bytes, 32-bit) instead of uint16 (2 Byte)
// 0xFF : one byte
public class test {


    public static short flipbyte(short b)
    {
        short c = 0;
        for (int i = 0; i < 8; i++){
            if ((b & (1 << i)) > 0){
                c |= (1 << (7 - i));
            }
        }
        return c;
    }
    public static void main(String[] args) {
//        short a = 0b01101001;
//        System.out.println(String.format("%x", a));
//        a = flipbyte(a);
//        System.out.println(String.format("%x", a));

        GameFrame gameFrame = new GameFrame();
    }

    public static void file_stuff(){
        String file_path = "D:\\Projects\\Java\\nes-emulator-java\\src\\Fire Emblem Gaiden (J) [T-Eng97b].nes";
        byte[] name = new byte[4];
        byte[] tst = new byte[4];
        try {
            InputStream is = new FileInputStream(file_path);
            is.read(name);
            is.read(tst);
            for (byte b: name){
                System.out.print((char) b);
            }
            System.out.println();
            for (byte b: tst){
                System.out.print(String.format("%x ", b));
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/*
lookup =
    {
		{ "BRK", "BRK", "IMM", 7 },{ "ORA", "ORA", "IZX", 6 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "???", "NOP", "IMP", 3 },{ "ORA", "ORA", "ZP0", 3 },{ "ASL", "ASL", "ZP0", 5 },{ "???", "XXX", "IMP", 5 },{ "PHP", "PHP", "IMP", 3 },{ "ORA", "ORA", "IMM", 2 },{ "ASL", "ASL", "IMP", 2 },{ "???", "XXX", "IMP", 2 },{ "???", "NOP", "IMP", 4 },{ "ORA", "ORA", "ABS", 4 },{ "ASL", "ASL", "ABS", 6 },{ "???", "XXX", "IMP", 6 },
		{ "BPL", "BPL", "REL", 2 },{ "ORA", "ORA", "IZY", 5 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "???", "NOP", "IMP", 4 },{ "ORA", "ORA", "ZPX", 4 },{ "ASL", "ASL", "ZPX", 6 },{ "???", "XXX", "IMP", 6 },{ "CLC", "CLC", "IMP", 2 },{ "ORA", "ORA", "ABY", 4 },{ "???", "NOP", "IMP", 2 },{ "???", "XXX", "IMP", 7 },{ "???", "NOP", "IMP", 4 },{ "ORA", "ORA", "ABX", 4 },{ "ASL", "ASL", "ABX", 7 },{ "???", "XXX", "IMP", 7 },
		{ "JSR", "JSR", "ABS", 6 },{ "AND", "AND", "IZX", 6 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "BIT", "BIT", "ZP0", 3 },{ "AND", "AND", "ZP0", 3 },{ "ROL", "ROL", "ZP0", 5 },{ "???", "XXX", "IMP", 5 },{ "PLP", "PLP", "IMP", 4 },{ "AND", "AND", "IMM", 2 },{ "ROL", "ROL", "IMP", 2 },{ "???", "XXX", "IMP", 2 },{ "BIT", "BIT", "ABS", 4 },{ "AND", "AND", "ABS", 4 },{ "ROL", "ROL", "ABS", 6 },{ "???", "XXX", "IMP", 6 },
		{ "BMI", "BMI", "REL", 2 },{ "AND", "AND", "IZY", 5 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "???", "NOP", "IMP", 4 },{ "AND", "AND", "ZPX", 4 },{ "ROL", "ROL", "ZPX", 6 },{ "???", "XXX", "IMP", 6 },{ "SEC", "SEC", "IMP", 2 },{ "AND", "AND", "ABY", 4 },{ "???", "NOP", "IMP", 2 },{ "???", "XXX", "IMP", 7 },{ "???", "NOP", "IMP", 4 },{ "AND", "AND", "ABX", 4 },{ "ROL", "ROL", "ABX", 7 },{ "???", "XXX", "IMP", 7 },
		{ "RTI", "RTI", "IMP", 6 },{ "EOR", "EOR", "IZX", 6 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "???", "NOP", "IMP", 3 },{ "EOR", "EOR", "ZP0", 3 },{ "LSR", "LSR", "ZP0", 5 },{ "???", "XXX", "IMP", 5 },{ "PHA", "PHA", "IMP", 3 },{ "EOR", "EOR", "IMM", 2 },{ "LSR", "LSR", "IMP", 2 },{ "???", "XXX", "IMP", 2 },{ "JMP", "JMP", "ABS", 3 },{ "EOR", "EOR", "ABS", 4 },{ "LSR", "LSR", "ABS", 6 },{ "???", "XXX", "IMP", 6 },
		{ "BVC", "BVC", "REL", 2 },{ "EOR", "EOR", "IZY", 5 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "???", "NOP", "IMP", 4 },{ "EOR", "EOR", "ZPX", 4 },{ "LSR", "LSR", "ZPX", 6 },{ "???", "XXX", "IMP", 6 },{ "CLI", "CLI", "IMP", 2 },{ "EOR", "EOR", "ABY", 4 },{ "???", "NOP", "IMP", 2 },{ "???", "XXX", "IMP", 7 },{ "???", "NOP", "IMP", 4 },{ "EOR", "EOR", "ABX", 4 },{ "LSR", "LSR", "ABX", 7 },{ "???", "XXX", "IMP", 7 },
		{ "RTS", "RTS", "IMP", 6 },{ "ADC", "ADC", "IZX", 6 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "???", "NOP", "IMP", 3 },{ "ADC", "ADC", "ZP0", 3 },{ "ROR", "ROR", "ZP0", 5 },{ "???", "XXX", "IMP", 5 },{ "PLA", "PLA", "IMP", 4 },{ "ADC", "ADC", "IMM", 2 },{ "ROR", "ROR", "IMP", 2 },{ "???", "XXX", "IMP", 2 },{ "JMP", "JMP", "IND", 5 },{ "ADC", "ADC", "ABS", 4 },{ "ROR", "ROR", "ABS", 6 },{ "???", "XXX", "IMP", 6 },
		{ "BVS", "BVS", "REL", 2 },{ "ADC", "ADC", "IZY", 5 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "???", "NOP", "IMP", 4 },{ "ADC", "ADC", "ZPX", 4 },{ "ROR", "ROR", "ZPX", 6 },{ "???", "XXX", "IMP", 6 },{ "SEI", "SEI", "IMP", 2 },{ "ADC", "ADC", "ABY", 4 },{ "???", "NOP", "IMP", 2 },{ "???", "XXX", "IMP", 7 },{ "???", "NOP", "IMP", 4 },{ "ADC", "ADC", "ABX", 4 },{ "ROR", "ROR", "ABX", 7 },{ "???", "XXX", "IMP", 7 },
		{ "???", "NOP", "IMP", 2 },{ "STA", "STA", "IZX", 6 },{ "???", "NOP", "IMP", 2 },{ "???", "XXX", "IMP", 6 },{ "STY", "STY", "ZP0", 3 },{ "STA", "STA", "ZP0", 3 },{ "STX", "STX", "ZP0", 3 },{ "???", "XXX", "IMP", 3 },{ "DEY", "DEY", "IMP", 2 },{ "???", "NOP", "IMP", 2 },{ "TXA", "TXA", "IMP", 2 },{ "???", "XXX", "IMP", 2 },{ "STY", "STY", "ABS", 4 },{ "STA", "STA", "ABS", 4 },{ "STX", "STX", "ABS", 4 },{ "???", "XXX", "IMP", 4 },
		{ "BCC", "BCC", "REL", 2 },{ "STA", "STA", "IZY", 6 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 6 },{ "STY", "STY", "ZPX", 4 },{ "STA", "STA", "ZPX", 4 },{ "STX", "STX", "ZPY", 4 },{ "???", "XXX", "IMP", 4 },{ "TYA", "TYA", "IMP", 2 },{ "STA", "STA", "ABY", 5 },{ "TXS", "TXS", "IMP", 2 },{ "???", "XXX", "IMP", 5 },{ "???", "NOP", "IMP", 5 },{ "STA", "STA", "ABX", 5 },{ "???", "XXX", "IMP", 5 },{ "???", "XXX", "IMP", 5 },
		{ "LDY", "LDY", "IMM", 2 },{ "LDA", "LDA", "IZX", 6 },{ "LDX", "LDX", "IMM", 2 },{ "???", "XXX", "IMP", 6 },{ "LDY", "LDY", "ZP0", 3 },{ "LDA", "LDA", "ZP0", 3 },{ "LDX", "LDX", "ZP0", 3 },{ "???", "XXX", "IMP", 3 },{ "TAY", "TAY", "IMP", 2 },{ "LDA", "LDA", "IMM", 2 },{ "TAX", "TAX", "IMP", 2 },{ "???", "XXX", "IMP", 2 },{ "LDY", "LDY", "ABS", 4 },{ "LDA", "LDA", "ABS", 4 },{ "LDX", "LDX", "ABS", 4 },{ "???", "XXX", "IMP", 4 },
		{ "BCS", "BCS", "REL", 2 },{ "LDA", "LDA", "IZY", 5 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 5 },{ "LDY", "LDY", "ZPX", 4 },{ "LDA", "LDA", "ZPX", 4 },{ "LDX", "LDX", "ZPY", 4 },{ "???", "XXX", "IMP", 4 },{ "CLV", "CLV", "IMP", 2 },{ "LDA", "LDA", "ABY", 4 },{ "TSX", "TSX", "IMP", 2 },{ "???", "XXX", "IMP", 4 },{ "LDY", "LDY", "ABX", 4 },{ "LDA", "LDA", "ABX", 4 },{ "LDX", "LDX", "ABY", 4 },{ "???", "XXX", "IMP", 4 },
		{ "CPY", "CPY", "IMM", 2 },{ "CMP", "CMP", "IZX", 6 },{ "???", "NOP", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "CPY", "CPY", "ZP0", 3 },{ "CMP", "CMP", "ZP0", 3 },{ "DEC", "DEC", "ZP0", 5 },{ "???", "XXX", "IMP", 5 },{ "INY", "INY", "IMP", 2 },{ "CMP", "CMP", "IMM", 2 },{ "DEX", "DEX", "IMP", 2 },{ "???", "XXX", "IMP", 2 },{ "CPY", "CPY", "ABS", 4 },{ "CMP", "CMP", "ABS", 4 },{ "DEC", "DEC", "ABS", 6 },{ "???", "XXX", "IMP", 6 },
		{ "BNE", "BNE", "REL", 2 },{ "CMP", "CMP", "IZY", 5 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "???", "NOP", "IMP", 4 },{ "CMP", "CMP", "ZPX", 4 },{ "DEC", "DEC", "ZPX", 6 },{ "???", "XXX", "IMP", 6 },{ "CLD", "CLD", "IMP", 2 },{ "CMP", "CMP", "ABY", 4 },{ "NOP", "NOP", "IMP", 2 },{ "???", "XXX", "IMP", 7 },{ "???", "NOP", "IMP", 4 },{ "CMP", "CMP", "ABX", 4 },{ "DEC", "DEC", "ABX", 7 },{ "???", "XXX", "IMP", 7 },
		{ "CPX", "CPX", "IMM", 2 },{ "SBC", "SBC", "IZX", 6 },{ "???", "NOP", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "CPX", "CPX", "ZP0", 3 },{ "SBC", "SBC", "ZP0", 3 },{ "INC", "INC", "ZP0", 5 },{ "???", "XXX", "IMP", 5 },{ "INX", "INX", "IMP", 2 },{ "SBC", "SBC", "IMM", 2 },{ "NOP", "NOP", "IMP", 2 },{ "???", "SBC", "IMP", 2 },{ "CPX", "CPX", "ABS", 4 },{ "SBC", "SBC", "ABS", 4 },{ "INC", "INC", "ABS", 6 },{ "???", "XXX", "IMP", 6 },
		{ "BEQ", "BEQ", "REL", 2 },{ "SBC", "SBC", "IZY", 5 },{ "???", "XXX", "IMP", 2 },{ "???", "XXX", "IMP", 8 },{ "???", "NOP", "IMP", 4 },{ "SBC", "SBC", "ZPX", 4 },{ "INC", "INC", "ZPX", 6 },{ "???", "XXX", "IMP", 6 },{ "SED", "SED", "IMP", 2 },{ "SBC", "SBC", "ABY", 4 },{ "NOP", "NOP", "IMP", 2 },{ "???", "XXX", "IMP", 7 },{ "???", "NOP", "IMP", 4 },{ "SBC", "SBC", "ABX", 4 },{ "INC", "INC", "ABX", 7 },{ "???", "XXX", "IMP", 7 },
	};

*/

/*
        lookupTable.add(new INSTRUCTION("BRK", Olc6502.class.getMethod("BRK"), Olc6502.class.getMethod("IMM"), 7)); lookupTable.add(new INSTRUCTION("ORA", Olc6502.class.getMethod("ORA"), Olc6502.class.getMethod("IZX"), 6)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 3)); lookupTable.add(new INSTRUCTION("ORA", Olc6502.class.getMethod("ORA"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "ASL", Olc6502.class.getMethod("ASL"), Olc6502.class.getMethod("ZP0"), 5)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5 )); lookupTable.add(new INSTRUCTION( "PHP", Olc6502.class.getMethod("PHP"), Olc6502.class.getMethod("IMP"), 3 )); lookupTable.add( new INSTRUCTION("ORA", Olc6502.class.getMethod("ORA"), Olc6502.class.getMethod("IMM"), 2) ); lookupTable.add( new INSTRUCTION("ASL", Olc6502.class.getMethod("ASL"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "ORA", Olc6502.class.getMethod("ORA"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "ASL", Olc6502.class.getMethod("ASL"), Olc6502.class.getMethod("ABS"), 6 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 ));
        lookupTable.add(new INSTRUCTION("BPL", Olc6502.class.getMethod("BPL"), Olc6502.class.getMethod("REL"), 2)); lookupTable.add(new INSTRUCTION("ORA", Olc6502.class.getMethod("ORA"), Olc6502.class.getMethod("IZY"), 5)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4)); lookupTable.add(new INSTRUCTION("ORA", Olc6502.class.getMethod("ORA"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION( "ASL", Olc6502.class.getMethod("ASL"), Olc6502.class.getMethod("ZPX"), 6)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 )); lookupTable.add(new INSTRUCTION( "CLC", Olc6502.class.getMethod("CLC"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("ORA", Olc6502.class.getMethod("ORA"), Olc6502.class.getMethod("ABY"), 4) ); lookupTable.add( new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "ORA", Olc6502.class.getMethod("ORA"), Olc6502.class.getMethod("ABX"), 4 )); lookupTable.add(new INSTRUCTION( "ASL", Olc6502.class.getMethod("ASL"), Olc6502.class.getMethod("ABX"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 ));
        lookupTable.add(new INSTRUCTION("JSR", Olc6502.class.getMethod("JSR"), Olc6502.class.getMethod("ABS"), 6)); lookupTable.add(new INSTRUCTION("AND", Olc6502.class.getMethod("AND"), Olc6502.class.getMethod("IZX"), 6)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("BIT", Olc6502.class.getMethod("BIT"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION("AND", Olc6502.class.getMethod("AND"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "ROL", Olc6502.class.getMethod("ROL"), Olc6502.class.getMethod("ZP0"), 5)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5 )); lookupTable.add(new INSTRUCTION( "PLP", Olc6502.class.getMethod("PLP"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add( new INSTRUCTION("AND", Olc6502.class.getMethod("AND"), Olc6502.class.getMethod("IMM"), 2) ); lookupTable.add( new INSTRUCTION("ROL", Olc6502.class.getMethod("ROL"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add(new INSTRUCTION( "BIT", Olc6502.class.getMethod("BIT"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "AND", Olc6502.class.getMethod("AND"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "ROL", Olc6502.class.getMethod("ROL"), Olc6502.class.getMethod("ABS"), 6 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 ));
        lookupTable.add(new INSTRUCTION("BMI", Olc6502.class.getMethod("BMI"), Olc6502.class.getMethod("REL"), 2)); lookupTable.add(new INSTRUCTION("AND", Olc6502.class.getMethod("AND"), Olc6502.class.getMethod("IZY"), 5)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4)); lookupTable.add(new INSTRUCTION("AND", Olc6502.class.getMethod("AND"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION( "ROL", Olc6502.class.getMethod("ROL"), Olc6502.class.getMethod("ZPX"), 6)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 )); lookupTable.add(new INSTRUCTION( "SEC", Olc6502.class.getMethod("SEC"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("AND", Olc6502.class.getMethod("AND"), Olc6502.class.getMethod("ABY"), 4) ); lookupTable.add( new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "AND", Olc6502.class.getMethod("AND"), Olc6502.class.getMethod("ABX"), 4 )); lookupTable.add(new INSTRUCTION( "ROL", Olc6502.class.getMethod("ROL"), Olc6502.class.getMethod("ABX"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 ));
        lookupTable.add(new INSTRUCTION("RTI", Olc6502.class.getMethod("RTI"), Olc6502.class.getMethod("IMP"), 6)); lookupTable.add(new INSTRUCTION("EOR", Olc6502.class.getMethod("EOR"), Olc6502.class.getMethod("IZX"), 6)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 3)); lookupTable.add(new INSTRUCTION("EOR", Olc6502.class.getMethod("EOR"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "LSR", Olc6502.class.getMethod("LSR"), Olc6502.class.getMethod("ZP0"), 5)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5 )); lookupTable.add(new INSTRUCTION( "PHA", Olc6502.class.getMethod("PHA"), Olc6502.class.getMethod("IMP"), 3 )); lookupTable.add( new INSTRUCTION("EOR", Olc6502.class.getMethod("EOR"), Olc6502.class.getMethod("IMM"), 2) ); lookupTable.add( new INSTRUCTION("LSR", Olc6502.class.getMethod("LSR"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add(new INSTRUCTION( "JMP", Olc6502.class.getMethod("JMP"), Olc6502.class.getMethod("ABS"), 3 )); lookupTable.add(new INSTRUCTION( "EOR", Olc6502.class.getMethod("EOR"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "LSR", Olc6502.class.getMethod("LSR"), Olc6502.class.getMethod("ABS"), 6 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 ));
        lookupTable.add(new INSTRUCTION("BVC", Olc6502.class.getMethod("BVC"), Olc6502.class.getMethod("REL"), 2)); lookupTable.add(new INSTRUCTION("EOR", Olc6502.class.getMethod("EOR"), Olc6502.class.getMethod("IZY"), 5)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4)); lookupTable.add(new INSTRUCTION("EOR", Olc6502.class.getMethod("EOR"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION( "LSR", Olc6502.class.getMethod("LSR"), Olc6502.class.getMethod("ZPX"), 6)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 )); lookupTable.add(new INSTRUCTION( "CLI", Olc6502.class.getMethod("CLI"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("EOR", Olc6502.class.getMethod("EOR"), Olc6502.class.getMethod("ABY"), 4) ); lookupTable.add( new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "EOR", Olc6502.class.getMethod("EOR"), Olc6502.class.getMethod("ABX"), 4 )); lookupTable.add(new INSTRUCTION( "LSR", Olc6502.class.getMethod("LSR"), Olc6502.class.getMethod("ABX"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 ));
        lookupTable.add(new INSTRUCTION("RTS", Olc6502.class.getMethod("RTS"), Olc6502.class.getMethod("IMP"), 6)); lookupTable.add(new INSTRUCTION("ADC", Olc6502.class.getMethod("ADC"), Olc6502.class.getMethod("IZX"), 6)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 3)); lookupTable.add(new INSTRUCTION("ADC", Olc6502.class.getMethod("ADC"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "ROR", Olc6502.class.getMethod("ROR"), Olc6502.class.getMethod("ZP0"), 5)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5 )); lookupTable.add(new INSTRUCTION( "PLA", Olc6502.class.getMethod("PLA"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add( new INSTRUCTION("ADC", Olc6502.class.getMethod("ADC"), Olc6502.class.getMethod("IMM"), 2) ); lookupTable.add( new INSTRUCTION("ROR", Olc6502.class.getMethod("ROR"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add(new INSTRUCTION( "JMP", Olc6502.class.getMethod("JMP"), Olc6502.class.getMethod("IND"), 5 )); lookupTable.add(new INSTRUCTION( "ADC", Olc6502.class.getMethod("ADC"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "ROR", Olc6502.class.getMethod("ROR"), Olc6502.class.getMethod("ABS"), 6 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 ));
        lookupTable.add(new INSTRUCTION("BVS", Olc6502.class.getMethod("BVS"), Olc6502.class.getMethod("REL"), 2)); lookupTable.add(new INSTRUCTION("ADC", Olc6502.class.getMethod("ADC"), Olc6502.class.getMethod("IZY"), 5)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4)); lookupTable.add(new INSTRUCTION("ADC", Olc6502.class.getMethod("ADC"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION( "ROR", Olc6502.class.getMethod("ROR"), Olc6502.class.getMethod("ZPX"), 6)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 )); lookupTable.add(new INSTRUCTION( "SEI", Olc6502.class.getMethod("SEI"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("ADC", Olc6502.class.getMethod("ADC"), Olc6502.class.getMethod("ABY"), 4) ); lookupTable.add( new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "ADC", Olc6502.class.getMethod("ADC"), Olc6502.class.getMethod("ABX"), 4 )); lookupTable.add(new INSTRUCTION( "ROR", Olc6502.class.getMethod("ROR"), Olc6502.class.getMethod("ABX"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 ));
        lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("STA", Olc6502.class.getMethod("STA"), Olc6502.class.getMethod("IZX"), 6)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6)); lookupTable.add(new INSTRUCTION("STY", Olc6502.class.getMethod("STY"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION("STA", Olc6502.class.getMethod("STA"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "STX", Olc6502.class.getMethod("STX"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 3 )); lookupTable.add(new INSTRUCTION( "DEY", Olc6502.class.getMethod("DEY"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add( new INSTRUCTION("TXA", Olc6502.class.getMethod("TXA"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add(new INSTRUCTION( "STY", Olc6502.class.getMethod("STY"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "STA", Olc6502.class.getMethod("STA"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "STX", Olc6502.class.getMethod("STX"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 4 ));
        lookupTable.add(new INSTRUCTION("BCC", Olc6502.class.getMethod("BCC"), Olc6502.class.getMethod("REL"), 2)); lookupTable.add(new INSTRUCTION("STA", Olc6502.class.getMethod("STA"), Olc6502.class.getMethod("IZY"), 6)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6)); lookupTable.add(new INSTRUCTION("STY", Olc6502.class.getMethod("STY"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION("STA", Olc6502.class.getMethod("STA"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION( "STX", Olc6502.class.getMethod("STX"), Olc6502.class.getMethod("ZPY"), 4)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "TYA", Olc6502.class.getMethod("TYA"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("STA", Olc6502.class.getMethod("STA"), Olc6502.class.getMethod("ABY"), 5) ); lookupTable.add( new INSTRUCTION("TXS", Olc6502.class.getMethod("TXS"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 5 )); lookupTable.add(new INSTRUCTION( "STA", Olc6502.class.getMethod("STA"), Olc6502.class.getMethod("ABX"), 5 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5 ));
        lookupTable.add(new INSTRUCTION("LDY", Olc6502.class.getMethod("LDY"), Olc6502.class.getMethod("IMM"), 2)); lookupTable.add(new INSTRUCTION("LDA", Olc6502.class.getMethod("LDA"), Olc6502.class.getMethod("IZX"), 6)); lookupTable.add(new INSTRUCTION("LDX", Olc6502.class.getMethod("LDX"), Olc6502.class.getMethod("IMM"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6)); lookupTable.add(new INSTRUCTION("LDY", Olc6502.class.getMethod("LDY"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION("LDA", Olc6502.class.getMethod("LDA"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "LDX", Olc6502.class.getMethod("LDX"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 3 )); lookupTable.add(new INSTRUCTION( "TAY", Olc6502.class.getMethod("TAY"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("LDA", Olc6502.class.getMethod("LDA"), Olc6502.class.getMethod("IMM"), 2) ); lookupTable.add( new INSTRUCTION("TAX", Olc6502.class.getMethod("TAX"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add(new INSTRUCTION( "LDY", Olc6502.class.getMethod("LDY"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "LDA", Olc6502.class.getMethod("LDA"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "LDX", Olc6502.class.getMethod("LDX"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 4 ));
        lookupTable.add(new INSTRUCTION("BCS", Olc6502.class.getMethod("BCS"), Olc6502.class.getMethod("REL"), 2)); lookupTable.add(new INSTRUCTION("LDA", Olc6502.class.getMethod("LDA"), Olc6502.class.getMethod("IZY"), 5)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5)); lookupTable.add(new INSTRUCTION("LDY", Olc6502.class.getMethod("LDY"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION("LDA", Olc6502.class.getMethod("LDA"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION( "LDX", Olc6502.class.getMethod("LDX"), Olc6502.class.getMethod("ZPY"), 4)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "CLV", Olc6502.class.getMethod("CLV"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("LDA", Olc6502.class.getMethod("LDA"), Olc6502.class.getMethod("ABY"), 4) ); lookupTable.add( new INSTRUCTION("TSX", Olc6502.class.getMethod("TSX"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "LDY", Olc6502.class.getMethod("LDY"), Olc6502.class.getMethod("ABX"), 4 )); lookupTable.add(new INSTRUCTION( "LDA", Olc6502.class.getMethod("LDA"), Olc6502.class.getMethod("ABX"), 4 )); lookupTable.add(new INSTRUCTION( "LDX", Olc6502.class.getMethod("LDX"), Olc6502.class.getMethod("ABY"), 4 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 4 ));
        lookupTable.add(new INSTRUCTION("CPY", Olc6502.class.getMethod("CPY"), Olc6502.class.getMethod("IMM"), 2)); lookupTable.add(new INSTRUCTION("CMP", Olc6502.class.getMethod("CMP"), Olc6502.class.getMethod("IZX"), 6)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("CPY", Olc6502.class.getMethod("CPY"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION("CMP", Olc6502.class.getMethod("CMP"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "DEC", Olc6502.class.getMethod("DEC"), Olc6502.class.getMethod("ZP0"), 5)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5 )); lookupTable.add(new INSTRUCTION( "INY", Olc6502.class.getMethod("INY"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("CMP", Olc6502.class.getMethod("CMP"), Olc6502.class.getMethod("IMM"), 2) ); lookupTable.add( new INSTRUCTION("DEX", Olc6502.class.getMethod("DEX"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add(new INSTRUCTION( "CPY", Olc6502.class.getMethod("CPY"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "CMP", Olc6502.class.getMethod("CMP"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "DEC", Olc6502.class.getMethod("DEC"), Olc6502.class.getMethod("ABS"), 6 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 ));
        lookupTable.add(new INSTRUCTION("BNE", Olc6502.class.getMethod("BNE"), Olc6502.class.getMethod("REL"), 2)); lookupTable.add(new INSTRUCTION("CMP", Olc6502.class.getMethod("CMP"), Olc6502.class.getMethod("IZY"), 5)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4)); lookupTable.add(new INSTRUCTION("CMP", Olc6502.class.getMethod("CMP"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION( "DEC", Olc6502.class.getMethod("DEC"), Olc6502.class.getMethod("ZPX"), 6)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 )); lookupTable.add(new INSTRUCTION( "CLD", Olc6502.class.getMethod("CLD"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("CMP", Olc6502.class.getMethod("CMP"), Olc6502.class.getMethod("ABY"), 4) ); lookupTable.add( new INSTRUCTION("NOP", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "CMP", Olc6502.class.getMethod("CMP"), Olc6502.class.getMethod("ABX"), 4 )); lookupTable.add(new INSTRUCTION( "DEC", Olc6502.class.getMethod("DEC"), Olc6502.class.getMethod("ABX"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 ));
        lookupTable.add(new INSTRUCTION("CPX", Olc6502.class.getMethod("CPX"), Olc6502.class.getMethod("IMM"), 2)); lookupTable.add(new INSTRUCTION("SBC", Olc6502.class.getMethod("SBC"), Olc6502.class.getMethod("IZX"), 6)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("CPX", Olc6502.class.getMethod("CPX"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION("SBC", Olc6502.class.getMethod("SBC"), Olc6502.class.getMethod("ZP0"), 3)); lookupTable.add(new INSTRUCTION( "INC", Olc6502.class.getMethod("INC"), Olc6502.class.getMethod("ZP0"), 5)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 5 )); lookupTable.add(new INSTRUCTION( "INX", Olc6502.class.getMethod("INX"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("SBC", Olc6502.class.getMethod("SBC"), Olc6502.class.getMethod("IMM"), 2) ); lookupTable.add( new INSTRUCTION("NOP", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("SBC"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add(new INSTRUCTION( "CPX", Olc6502.class.getMethod("CPX"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "SBC", Olc6502.class.getMethod("SBC"), Olc6502.class.getMethod("ABS"), 4 )); lookupTable.add(new INSTRUCTION( "INC", Olc6502.class.getMethod("INC"), Olc6502.class.getMethod("ABS"), 6 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 ));
        lookupTable.add(new INSTRUCTION("BEQ", Olc6502.class.getMethod("BEQ"), Olc6502.class.getMethod("REL"), 2)); lookupTable.add(new INSTRUCTION("SBC", Olc6502.class.getMethod("SBC"), Olc6502.class.getMethod("IZY"), 5)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 2)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 8)); lookupTable.add(new INSTRUCTION("???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4)); lookupTable.add(new INSTRUCTION("SBC", Olc6502.class.getMethod("SBC"), Olc6502.class.getMethod("ZPX"), 4)); lookupTable.add(new INSTRUCTION( "INC", Olc6502.class.getMethod("INC"), Olc6502.class.getMethod("ZPX"), 6)); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 6 )); lookupTable.add(new INSTRUCTION( "SED", Olc6502.class.getMethod("SED"), Olc6502.class.getMethod("IMP"), 2 )); lookupTable.add( new INSTRUCTION("SBC", Olc6502.class.getMethod("SBC"), Olc6502.class.getMethod("ABY"), 4) ); lookupTable.add( new INSTRUCTION("NOP", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 2) ); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("NOP"), Olc6502.class.getMethod("IMP"), 4 )); lookupTable.add(new INSTRUCTION( "SBC", Olc6502.class.getMethod("SBC"), Olc6502.class.getMethod("ABX"), 4 )); lookupTable.add(new INSTRUCTION( "INC", Olc6502.class.getMethod("INC"), Olc6502.class.getMethod("ABX"), 7 )); lookupTable.add(new INSTRUCTION( "???", Olc6502.class.getMethod("XXX"), Olc6502.class.getMethod("IMP"), 7 ));
 */