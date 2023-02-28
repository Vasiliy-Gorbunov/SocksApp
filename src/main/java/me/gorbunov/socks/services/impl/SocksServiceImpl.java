package me.gorbunov.socks.services.impl;

import me.gorbunov.socks.model.Color;
import me.gorbunov.socks.model.Size;
import me.gorbunov.socks.model.Socks;
import me.gorbunov.socks.model.exceptions.IncorrectArgumentException;
import me.gorbunov.socks.model.exceptions.InsufficientQuantityException;
import me.gorbunov.socks.services.SocksService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SocksServiceImpl implements SocksService {

    public Map<Socks, Integer> socksStorage = new HashMap<>();

    @Override
    public void addSocks(Color color, Size size, int cottonPercent, int quantity) throws IncorrectArgumentException {
        Socks socks = new Socks(color, size, cottonPercent);
        socksStorage.merge(socks, quantity, Integer::sum);
    }

    @Override
    public Integer getSocksCount(Color color, Size size, int minCottonPercent, int maxCottonPercent) {
        int count = 0;
        for (Map.Entry<Socks, Integer> socks : socksStorage.entrySet()) {
            if (socks.getKey().getColor().equals(color)
                    && socks.getKey().getSize().equals(size)
                    && socks.getKey().getCottonPercent() >= minCottonPercent
                    && socks.getKey().getCottonPercent() <= maxCottonPercent) {
                count += socks.getValue();
            }
        }
        return count;
    }

    @Override
    public void releaseSocks(Color color, Size size, int cottonPercent, int quantity) throws InsufficientQuantityException, IncorrectArgumentException {
        Socks socks = new Socks(color, size, cottonPercent);
        if (socksStorage.containsKey(socks) && socksStorage.get(socks)>=quantity) {
            socksStorage.merge(socks, -quantity, Integer::sum);
        } else {
            throw new InsufficientQuantityException("Недостаточно носков выбранного типа на складе");
        }
    }
}
