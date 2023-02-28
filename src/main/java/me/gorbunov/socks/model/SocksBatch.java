package me.gorbunov.socks.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocksBatch {
    private Socks socks;
    private int quantity;

    public SocksBatch(Socks socks, int quantity) {
        this.socks = socks;
        setQuantity(quantity);
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException();
        }
        this.quantity = quantity;
    }
}
