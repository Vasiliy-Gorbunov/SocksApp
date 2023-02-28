package me.gorbunov.socks.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Socks {

    private Color color;
    private Size size;
    private int cottonPercent;

    public Socks(Color color, Size size, int cottonPercent) {
        this.color = color;
        this.size = size;
        setCottonPercent(cottonPercent);
    }

    public void setCottonPercent(int cottonPercent) {
        if (cottonPercent > 100 || cottonPercent < 0) {
            throw new IllegalArgumentException();
        }
        this.cottonPercent = cottonPercent;
    }
}
