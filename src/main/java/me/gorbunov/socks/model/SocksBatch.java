package me.gorbunov.socks.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.gorbunov.socks.model.exceptions.IncorrectArgumentException;

@Data
@NoArgsConstructor
public class SocksBatch {
    private Socks socks;
    private int quantity;

    public SocksBatch(Socks socks, int quantity) throws IncorrectArgumentException {
        this.socks = socks;
        setQuantity(quantity);
    }

    public void setQuantity(int quantity) throws IncorrectArgumentException {
        if (quantity <= 0) {
            throw new IncorrectArgumentException("Количество не может быть неположительным числом");
        }
        this.quantity = quantity;
    }
}
