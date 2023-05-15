package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(() -> {
            try {
                WebScrapper.main("Computación");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        executor.submit(() -> {
            try {
                WebScrapper.main("Celulares/Celulares y Teléfonos");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        executor.submit(() -> {
            try {
                WebScrapper.main("Tecno/TV");
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
        executor.submit(() -> {
            try {
                WebScrapper.main("Electrohogar/Electrodomésticos Cocina");
            } catch (URISyntaxException | SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executor.shutdown();


    }
}