import java.util.Random;

public class Olc2C02 extends Olc2C02A{
    Random rnd;
    public Olc2C02(){
        this.rnd = new Random();
        this.tblName = new short[2][1024];
        this.tblPalette = new short[32];
        this.tblPattern = new short[2][4096];
        this.palScreen = new Pixel[0x40];
        palScreen[0x00] = new Pixel(84, 84, 84);
        palScreen[0x01] = new Pixel(0, 30, 116);
        palScreen[0x02] = new Pixel(8, 16, 144);
        palScreen[0x03] = new Pixel(48, 0, 136);
        palScreen[0x04] = new Pixel(68, 0, 100);
        palScreen[0x05] = new Pixel(92, 0, 48);
        palScreen[0x06] = new Pixel(84, 4, 0);
        palScreen[0x07] = new Pixel(60, 24, 0);
        palScreen[0x08] = new Pixel(32, 42, 0);
        palScreen[0x09] = new Pixel(8, 58, 0);
        palScreen[0x0A] = new Pixel(0, 64, 0);
        palScreen[0x0B] = new Pixel(0, 60, 0);
        palScreen[0x0C] = new Pixel(0, 50, 60);
        palScreen[0x0D] = new Pixel(0, 0, 0);
        palScreen[0x0E] = new Pixel(0, 0, 0);
        palScreen[0x0F] = new Pixel(0, 0, 0);

        palScreen[0x10] = new Pixel(152, 150, 152);
        palScreen[0x11] = new Pixel(8, 76, 196);
        palScreen[0x12] = new Pixel(48, 50, 236);
        palScreen[0x13] = new Pixel(92, 30, 228);
        palScreen[0x14] = new Pixel(136, 20, 176);
        palScreen[0x15] = new Pixel(160, 20, 100);
        palScreen[0x16] = new Pixel(152, 34, 32);
        palScreen[0x17] = new Pixel(120, 60, 0);
        palScreen[0x18] = new Pixel(84, 90, 0);
        palScreen[0x19] = new Pixel(40, 114, 0);
        palScreen[0x1A] = new Pixel(8, 124, 0);
        palScreen[0x1B] = new Pixel(0, 118, 40);
        palScreen[0x1C] = new Pixel(0, 102, 120);
        palScreen[0x1D] = new Pixel(0, 0, 0);
        palScreen[0x1E] = new Pixel(0, 0, 0);
        palScreen[0x1F] = new Pixel(0, 0, 0);

        palScreen[0x20] = new Pixel(236, 238, 236);
        palScreen[0x21] = new Pixel(76, 154, 236);
        palScreen[0x22] = new Pixel(120, 124, 236);
        palScreen[0x23] = new Pixel(176, 98, 236);
        palScreen[0x24] = new Pixel(228, 84, 236);
        palScreen[0x25] = new Pixel(236, 88, 180);
        palScreen[0x26] = new Pixel(236, 106, 100);
        palScreen[0x27] = new Pixel(212, 136, 32);
        palScreen[0x28] = new Pixel(160, 170, 0);
        palScreen[0x29] = new Pixel(116, 196, 0);
        palScreen[0x2A] = new Pixel(76, 208, 32);
        palScreen[0x2B] = new Pixel(56, 204, 108);
        palScreen[0x2C] = new Pixel(56, 180, 204);
        palScreen[0x2D] = new Pixel(60, 60, 60);
        palScreen[0x2E] = new Pixel(0, 0, 0);
        palScreen[0x2F] = new Pixel(0, 0, 0);

        palScreen[0x30] = new Pixel(236, 238, 236);
        palScreen[0x31] = new Pixel(168, 204, 236);
        palScreen[0x32] = new Pixel(188, 188, 236);
        palScreen[0x33] = new Pixel(212, 178, 236);
        palScreen[0x34] = new Pixel(236, 174, 236);
        palScreen[0x35] = new Pixel(236, 174, 212);
        palScreen[0x36] = new Pixel(236, 180, 176);
        palScreen[0x37] = new Pixel(228, 196, 144);
        palScreen[0x38] = new Pixel(204, 210, 120);
        palScreen[0x39] = new Pixel(180, 222, 120);
        palScreen[0x3A] = new Pixel(168, 226, 144);
        palScreen[0x3B] = new Pixel(152, 226, 180);
        palScreen[0x3C] = new Pixel(160, 214, 228);
        palScreen[0x3D] = new Pixel(160, 162, 160);
        palScreen[0x3E] = new Pixel(0, 0, 0);
        palScreen[0x3F] = new Pixel(0, 0, 0);

        this.sprNameTable[0] = new Sprite(256, 240);
        this.sprNameTable[1] = new Sprite(256, 240);
        this.sprPatternTable[0] = new Sprite(128, 128);
        this.sprPatternTable[1] = new Sprite(128, 128);

        this.status = new Status();
        this.mask = new Mask();
        this.control = new Control();
        this.vram_addr = new Loopy_register();
        this.tram_addr = new Loopy_register();
    }

    public void IncrementScrollX()
    {
        if (mask.get(Mask.MEMBER.RENDER_BACKGROUND) > 0 || mask.get(Mask.MEMBER.RENDER_SPRITES) > 0)
        {
            // A single name table is 32x30 tiles. As we increment horizontally
            // we may cross into a neighbouring nametable, or wrap around to
            // a neighbouring nametable
            if (vram_addr.get(Loopy_register.MEMBER.COARSE_X) == 31)
            {
                // Leaving nametable so wrap address round
                vram_addr.set(Loopy_register.MEMBER.COARSE_X, (short) 0);
                // Flip target nametable bit
                vram_addr.set(Loopy_register.MEMBER.NAMETABLE_X, (short) ~vram_addr.get(Loopy_register.MEMBER.NAMETABLE_X));
            }
            else
            {
                // Staying in current nametable, so just increment
                vram_addr.set(Loopy_register.MEMBER.COARSE_X,
                        (short) (vram_addr.get(Loopy_register.MEMBER.COARSE_X) + 1));
            }
        }
    };

    public void IncrementScrollY()
    {
        // Ony if rendering is enabled
        if (mask.get(Mask.MEMBER.RENDER_BACKGROUND) > 0 || mask.get(Mask.MEMBER.RENDER_SPRITES) > 0)
        {
            // If possible, just increment the fine y offset
            if (vram_addr.get(Loopy_register.MEMBER.FINE_Y) < 7)
            {
                vram_addr.set(Loopy_register.MEMBER.FINE_Y,
                        (short) (vram_addr.get(Loopy_register.MEMBER.FINE_Y) + 1));
            }
            else
            {
                // Reset fine y offset
                vram_addr.set(Loopy_register.MEMBER.FINE_Y, (short) 0);

                // Check if we need to swap vertical nametable targets
                if (vram_addr.get(Loopy_register.MEMBER.COARSE_Y) == 29)
                {
                    // We do, so reset coarse y offset
                    vram_addr.set(Loopy_register.MEMBER.COARSE_Y, (short) 0);
                    // And flip the target nametable bit
                    vram_addr.set(Loopy_register.MEMBER.NAMETABLE_Y,
                            (short) ~vram_addr.get(Loopy_register.MEMBER.NAMETABLE_Y));
                }
                else if (vram_addr.get(Loopy_register.MEMBER.COARSE_Y) == 31)
                {
                    // In case the pointer is in the attribute memory, we
                    // just wrap around the current nametable
                    vram_addr.set(Loopy_register.MEMBER.COARSE_Y, (short) 0);
                }
                else
                {
                    // None of the above boundary/wrapping conditions apply
                    // so just increment the coarse y offset
                    vram_addr.set(Loopy_register.MEMBER.COARSE_Y,
                            (short) (vram_addr.get(Loopy_register.MEMBER.COARSE_Y) + 1));
                }
            }
        }
    };

    public void TransferAddressX()
    {
        // Ony if rendering is enabled
        if (mask.get(Mask.MEMBER.RENDER_BACKGROUND) > 0 || mask.get(Mask.MEMBER.RENDER_SPRITES) > 0)
        {
            vram_addr.set(Loopy_register.MEMBER.NAMETABLE_X, tram_addr.get(Loopy_register.MEMBER.NAMETABLE_X));
            vram_addr.set(Loopy_register.MEMBER.COARSE_X, tram_addr.get(Loopy_register.MEMBER.COARSE_X));
        }
    };

    public void TransferAddressY()
    {
        // Ony if rendering is enabled
        if (mask.get(Mask.MEMBER.RENDER_BACKGROUND) > 0 || mask.get(Mask.MEMBER.RENDER_SPRITES) > 0)
        {
            vram_addr.set(Loopy_register.MEMBER.FINE_Y, tram_addr.get(Loopy_register.MEMBER.FINE_Y));
            vram_addr.set(Loopy_register.MEMBER.NAMETABLE_Y, tram_addr.get(Loopy_register.MEMBER.NAMETABLE_Y));
            vram_addr.set(Loopy_register.MEMBER.COARSE_Y, tram_addr.get(Loopy_register.MEMBER.COARSE_Y));
        }
    };

    public void LoadBackgroundShifters()
    {
        bg_shifter_pattern_lo = (bg_shifter_pattern_lo & 0xFF00) | bg_next_tile_lsb;
        bg_shifter_pattern_hi = (bg_shifter_pattern_hi & 0xFF00) | bg_next_tile_msb;

        bg_shifter_attrib_lo  = (bg_shifter_attrib_lo & 0xFF00) | ((bg_next_tile_attrib & 0b01) > 0 ? 0xFF : 0x00);
        bg_shifter_attrib_hi  = (bg_shifter_attrib_hi & 0xFF00) | ((bg_next_tile_attrib & 0b10) > 0 ? 0xFF : 0x00);
    };

    public void UpdateShifters()
    {
        if (mask.get(Mask.MEMBER.RENDER_BACKGROUND) > 0)
        {
            // Shifting background tile pattern row
            bg_shifter_pattern_lo <<= 1;
            bg_shifter_pattern_hi <<= 1;

            // Shifting palette attributes by 1
            bg_shifter_attrib_lo <<= 1;
            bg_shifter_attrib_hi <<= 1;
        }
    };

    private int ushort(int a){
        return a & 0xFFFF;
    }

    public Pixel getColourFromPaletteRam(short palette, short pixel){
        return this.palScreen[ppuRead((short) (0x3F00 + (palette << 2) + pixel))];
    }

    @Override
    public Sprite getScreen() {
        return this.sprScreen;
    }

    @Override
    public Sprite getNameTable(short i) {
        return this.sprNameTable[i];
    }

    @Override
    public Sprite getPatternTable(short i, short palette) {
        for (int nTileY = 0; nTileY < 16; nTileY++){
            for (int nTileX = 0; nTileX < 16; nTileX++){
                int nOffset = nTileY * 256 + nTileX * 16;
                for (int row = 0; row < 8; row++){
                    short tile_lsb = ppuRead((short) (i * 0x1000 + nOffset + row + 0));
                    short tile_msb = ppuRead((short) (i * 0x1000 + nOffset + row + 8));
                    for (int col = 0; col < 8; col++){
                        short pixel = (short) ((tile_lsb & 0x01) + (tile_msb & 0x01));
                        tile_lsb >>= 1;
                        tile_msb >>= 1;
                        this.sprPatternTable[i].setPixel(
                                nTileX * 8 + (7 - col),
                                nTileY * 8 + row,
                                getColourFromPaletteRam(palette, pixel)
                        );
                    }
                }
            }
        }
        return this.sprPatternTable[i];
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
                data = (short) ((this.status.get(Status.MEMBER.REG) & 0xE0) | (this.ppu_data_buffer & 0x1F));
                this.status.set(Status.MEMBER.VERTICAL_BLANK, (short) 0);
                this.address_latch = 0;
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
                data = this.ppu_data_buffer;
                this.ppu_data_buffer = this.ppuRead(this.vram_addr.get(Loopy_register.MEMBER.REG));
                if (this.vram_addr.get(Loopy_register.MEMBER.REG) > 0x3F00){
                    data = this.ppu_data_buffer;
                }
                vram_addr.set(Loopy_register.MEMBER.REG,
                        (short) (this.vram_addr.get(Loopy_register.MEMBER.REG) +
                                (control.get(Control.MEMBER.INCREMENT_MODE) > 0? 32 : 1)));
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
                control.set(Control.MEMBER.REG, data);
                tram_addr.set(Loopy_register.MEMBER.NAMETABLE_X, control.get(Control.MEMBER.NAMETABLE_X));
                tram_addr.set(Loopy_register.MEMBER.NAMETABLE_Y, control.get(Control.MEMBER.NAMETABLE_Y));
                break;
            case 0x0001: // Mask
                mask.set(Mask.MEMBER.REG, data);
                break;
            case 0x0002: // Status
                break;
            case 0x0003: // OAM Address
                break;
            case 0x0004: // OAM Data
                break;
            case 0x0005: // Scroll
                if (this.address_latch == 0)
                {
                    this.fine_x = (short) (data & 0x07);
                    this.tram_addr.set(Loopy_register.MEMBER.COARSE_X, (short) (data >> 3));
                    this.address_latch = 1;
                }
                else
                {
                    this.tram_addr.set(Loopy_register.MEMBER.FINE_Y, (short) (data & 0x07));
                    this.tram_addr.set(Loopy_register.MEMBER.COARSE_Y, (short) (data >> 3));
                    this.address_latch = 0;
                }
                break;
            case 0x0006: // PPU Address
                if (this.address_latch == 0){
                    this.tram_addr.set(Loopy_register.MEMBER.REG, (short)
                            ((this.tram_addr.get(Loopy_register.MEMBER.REG) & 0x00FF) | (data << 8)));
                    this.address_latch = 1;
                }
                else {
                    this.tram_addr.set(Loopy_register.MEMBER.REG, (short)
                            ((this.tram_addr.get(Loopy_register.MEMBER.REG) & 0xFF00) | data));
                    this.vram_addr.set(Loopy_register.MEMBER.REG,
                            this.tram_addr.get(Loopy_register.MEMBER.REG));
                    this.address_latch = 0;
                }
                break;
            case 0x0007: // PPU Data
                this.ppuWrite(addr, data);
                short treg = this.vram_addr.get(Loopy_register.MEMBER.REG);
                treg += control.get(Control.MEMBER.INCREMENT_MODE) > 0? 32: 1;
                vram_addr.set(Loopy_register.MEMBER.REG, treg);
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
        else if (addr >= 0x0000 && addr <= 0x1FFF){
            data = this.tblPattern[(addr & 0x1000) >> 12][addr & 0x0FFF];
        }
        else if (addr >= 0x2000 && addr <= 0x3EFF){
            if (this.cart.mirror == CartridgeA.MIRROR.VERTICAL){
                if (addr >= 0x0000 && addr <= 0x03FF)
                    data = this.tblName[0][addr & 0x03FF];
                if (addr >= 0x0400 && addr <= 0x07FF)
                    data = this.tblName[1][addr & 0x03FF];
                if (addr >= 0x0800 && addr <= 0x0BFF)
                    data = this.tblName[0][addr & 0x03FF];
                if (addr >= 0x0C00 && addr <= 0x0FFF)
                    data = this.tblName[1][addr & 0x03FF];
            }
            else if (this.cart.mirror == CartridgeA.MIRROR.HORIZONTAL){
                if (addr >= 0x0000 && addr <= 0x03FF)
                    data = this.tblName[0][addr & 0x03FF];
                if (addr >= 0x0400 && addr <= 0x07FF)
                    data = this.tblName[0][addr & 0x03FF];
                if (addr >= 0x0800 && addr <= 0x0BFF)
                    data = this.tblName[1][addr & 0x03FF];
                if (addr >= 0x0C00 && addr <= 0x0FFF)
                    data = this.tblName[1][addr & 0x03FF];
            }
        }
        else if (addr >= 0x3F00 && addr <= 0x3FFF){
            addr &= 0x001F;
            if (addr == 0x0010) addr = 0x0000;
            if (addr == 0x0014) addr = 0x0004;
            if (addr == 0x0018) addr = 0x0008;
            if (addr == 0x001C) addr = 0x000C;
            //data = tblPalette[addr] & (mask.grayscale ? 0x30 : 0x3F);
            data = tblPalette[addr];
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
        else if (addr >= 0x0000 && addr <= 0x1FFF){
            this.tblPattern[(addr & 0x1000) >> 12][addr & 0x0FFF] = data;
        }
        else if (addr >= 0x2000 && addr <= 0x3EFF){
            if (this.cart.mirror == CartridgeA.MIRROR.VERTICAL){
                if (addr >= 0x0000 && addr <= 0x03FF)
                    this.tblName[0][addr & 0x03FF] = data;
                if (addr >= 0x0400 && addr <= 0x07FF)
                    this.tblName[1][addr & 0x03FF] = data;
                if (addr >= 0x0800 && addr <= 0x0BFF)
                    this.tblName[0][addr & 0x03FF] = data;
                if (addr >= 0x0C00 && addr <= 0x0FFF)
                    this.tblName[1][addr & 0x03FF] = data;
            }
            else if (this.cart.mirror == CartridgeA.MIRROR.HORIZONTAL){
                if (addr >= 0x0000 && addr <= 0x03FF)
                    this.tblName[0][addr & 0x03FF] = data;
                if (addr >= 0x0400 && addr <= 0x07FF)
                    this.tblName[0][addr & 0x03FF] = data;
                if (addr >= 0x0800 && addr <= 0x0BFF)
                    this.tblName[1][addr & 0x03FF] = data;
                if (addr >= 0x0C00 && addr <= 0x0FFF)
                    this.tblName[1][addr & 0x03FF] = data;
            }
        }
        else if (addr >= 0x3F00 && addr <= 0x3FFF){
            addr &= 0x001F;
            if (addr == 0x0010) addr = 0x0000;
            if (addr == 0x0014) addr = 0x0004;
            if (addr == 0x0018) addr = 0x0008;
            if (addr == 0x001C) addr = 0x000C;
            this.tblPalette[addr] = data;
        }
    }

    @Override
    public void ConnectCartridge(Cartridge cartridge) {
        this.cart = cartridge;
    }

    @Override
    public void clock() {
        if (this.scanline >= -1 && this.scanline < 240){
            if (this.scanline == -1 && this.cycle == 1){
                this.status.set(Status.MEMBER.VERTICAL_BLANK, (short)  0);
            }
            if ((this.cycle >= 2 && this.cycle < 258) || (this.cycle >= 321 && this.cycle < 338)){
                this.UpdateShifters();
                switch ((this.cycle - 1) % 8){
                    case 0:
                        this.LoadBackgroundShifters();
                        this.bg_next_tile_id = this.ppuRead(0x2000 |
                                (vram_addr.get(Loopy_register.MEMBER.REG) & 0x0FFF));
                        break;
                    case 2:
                        bg_next_tile_attrib = ppuRead((short) (0x23C0 | (vram_addr.get(Loopy_register.MEMBER.NAMETABLE_Y) << 11)
                                | (vram_addr.get(Loopy_register.MEMBER.NAMETABLE_X) << 10)
                                | ((vram_addr.get(Loopy_register.MEMBER.COARSE_Y) >> 2) << 3)
                                | (vram_addr.get(Loopy_register.MEMBER.COARSE_X) >> 2)));
                        if ((vram_addr.get(Loopy_register.MEMBER.COARSE_Y) & 0x02) > 0) {
                            bg_next_tile_attrib >>= 4;
                        }
                        if ((vram_addr.get(Loopy_register.MEMBER.COARSE_X) & 0x02) > 0) {
                            bg_next_tile_attrib >>= 2;
                        }
                        bg_next_tile_attrib &= 0x03;
                        break;
                    case 4:
                        bg_next_tile_lsb = ppuRead((control.get(Control.MEMBER.PATTERN_BACKGROUND) << 12)
                                + (ushort(bg_next_tile_id) << 4)
                                + (vram_addr.get(Loopy_register.MEMBER.FINE_Y)) + 0);
                        break;
                    case 6:
                        bg_next_tile_msb = ppuRead((short) ((control.get(Control.MEMBER.PATTERN_BACKGROUND) << 12)
                                + (ushort(bg_next_tile_id) << 4)
                                + (vram_addr.get(Loopy_register.MEMBER.FINE_Y)) + 8));
                        break;
                    case 7:
                        this.IncrementScrollX();
                        break;
                }
            }
            if (cycle == 256){
                this.IncrementScrollY();
            }
            if (cycle == 257){
                TransferAddressX();
            }
            if (scanline == -1 && cycle >= 280 && cycle < 305){
                TransferAddressY();
            }
        }

        if (scanline == 240){

        }

        if (this.scanline == 241 && this.cycle == 1){
            this.status.set(Status.MEMBER.VERTICAL_BLANK, (short) 1);
            if (this.control.get(Control.MEMBER.ENABLE_NMI) > 0){
                this.nmi = true;
            }
        }

        short bg_pixel = 0x00;
        short bg_palette = 0x00;

        if (mask.get(Mask.MEMBER.RENDER_BACKGROUND) > 0){
            int bit_mux = 0x8000 >> fine_x;

            // Select Plane pixels by extracting from the shifter
            // at the required location.
            short p0_pixel = (short) ((bg_shifter_pattern_lo & bit_mux) > 0 ? 1 : 0);
            short p1_pixel = (short) ((bg_shifter_pattern_hi & bit_mux) > 0 ? 1 : 0);

            // Combine to form pixel index
            bg_pixel = (short) ((p1_pixel << 1) | p0_pixel);

            // Get palette
            short bg_pal0 = (short) ((bg_shifter_attrib_lo & bit_mux) > 0 ? 1 : 0);
            short bg_pal1 = (short) ((bg_shifter_attrib_hi & bit_mux) > 0 ? 1 : 0);
            bg_palette = (short) ((bg_pal1 << 1) | bg_pal0);
        }

        this.sprScreen.setPixel(cycle - 1, scanline, this.getColourFromPaletteRam(bg_palette, bg_pixel));
        this.cycle++;
        if (this.cycle >= 341){
            this.cycle = 0;
            scanline++;
            if (this.scanline >= 261){
                this.scanline = -1;
                this.frame_complete = true;
            }
        }
    }
}
