package me.gorbunov.socks.services;

import me.gorbunov.socks.model.Color;
import me.gorbunov.socks.model.Size;
import me.gorbunov.socks.model.exceptions.IncorrectArgumentException;
import me.gorbunov.socks.model.exceptions.InsufficientQuantityException;

public interface SocksService {

    void addSocks(Color color, Size size, int cottonPercent, int quantity) throws IncorrectArgumentException;

    Integer getSocksCount(Color color, Size size, int minCottonPercent, int maxCottonPercent);

    void releaseSocks(Color color, Size size, int cottonPercent, int quantity) throws InsufficientQuantityException, IncorrectArgumentException;
}
