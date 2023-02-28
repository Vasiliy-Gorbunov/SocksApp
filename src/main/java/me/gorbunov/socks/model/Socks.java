package me.gorbunov.socks.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.gorbunov.socks.model.exceptions.IncorrectArgumentException;

@Data
@NoArgsConstructor
public class Socks {

    private Color color;
    private Size size;
    private int cottonPercent;

    public Socks(Color color, Size size, int cottonPercent) throws IncorrectArgumentException {
        this.color = color;
        this.size = size;
        setCottonPercent(cottonPercent);
    }

    public void setCottonPercent(int cottonPercent) throws IncorrectArgumentException {
        if (cottonPercent > 100 || cottonPercent < 0) {
            throw new IncorrectArgumentException("Значение должно быть от 0 до 100");
        }
        this.cottonPercent = cottonPercent;
    }
}
