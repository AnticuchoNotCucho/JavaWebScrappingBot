package org.example.services;

import org.example.models.Producto;

import java.sql.Connection;

public class ProductoServiceImpl implements ProductoService {

    private Connection _connection;

    public ProductoServiceImpl(Connection connection) {
        _connection = connection;
    }

    @Override
    public boolean guardarProducto(Producto producto) {

        _connection.prepareStatement()
        return false;
    }
}
