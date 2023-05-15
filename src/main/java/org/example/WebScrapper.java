package org.example;
import org.example.models.Producto;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class WebScrapper {

    public static int saveProducts(JSONObject obj) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;TrustServerCertificate=True", "sa", "Maxsteel1");
            JSONArray listaPrecios = obj.getJSONArray("products");
            ArrayList<Producto> lista = new ArrayList<Producto>();
            for (Object val : listaPrecios
            ) {
                JSONObject objetito = new JSONObject(val.toString());
                String nombre = objetito.getString("description");
                Integer precio = objetito.getJSONObject("price").getInt("BasePriceSales");
                String imagen = objetito.getJSONObject("images").getString("defaultImage");
                String sku = objetito.getString("sku");
                Producto producto = new Producto(1,nombre, precio, imagen, sku, "Computacion");
                PreparedStatement statement = conn.prepareStatement("INSERT INTO Productos VALUES (?,?,?,?,?,?)");
                statement.setInt(1, producto.sitiosID);
                statement.setString(2,producto.nombre);
                statement.setInt(3,Integer.parseInt(String.valueOf(producto.precio)));
                statement.setString(4,producto.imagen);
                statement.setString(5,producto.sku);
                statement.setString(6,producto.categoria);
                int filasAfectadas = statement.executeUpdate();
                System.out.println("Filas afectadas: " + filasAfectadas);
                lista.add(producto);
                System.out.println(objetito);
            }
            return lista.size();
        }
        public static void GetPages(ArrayList<String> urls) throws URISyntaxException, SQLException {
            int cantidad = 0;
            for (int i= 0; i <= urls.size(); i++){
                String jsonBody = urls.get(i);
                HttpRequest requests = HttpRequest.newBuilder(new URI("https://apps.lider.cl/catalogo/bff/category"))
                        .headers("tenant","catalogo","x-channel","BuySmart","x-flowid","041ea6bc-193a-4331-b8df-b693a550a3ab","x-sessionid","e6dd7eab-2271-4a03-b9aa-b874ddd6b8bf","Content-Type","application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                        .build();

                HttpResponse<String> responses = null;
                try {
                    responses = HttpClient.newBuilder()
                            .build()
                            .send(requests, HttpResponse.BodyHandlers.ofString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                JSONObject objs = new JSONObject(responses.body());
                int cantidadProductos = saveProducts(objs);
                cantidad += cantidadProductos;

            }
            System.out.println(cantidad);
            System.out.println("Finalizado");
        }
        public static void main(String categoria) throws URISyntaxException, IOException, InterruptedException, SQLException {
            System.out.println("Starting..");
            String jsonBody = String.format("{\"categories\":\"%s\",\"page\":1,\"facets\":[],\"sortBy\":\"\",\"hitsPerPage\":100}", categoria);
            HttpRequest request = null;
            try {
                request = HttpRequest.newBuilder(new URI("https://apps.lider.cl/catalogo/bff/category"))
                    .headers("tenant","catalogo","x-channel","BuySmart","x-flowid","041ea6bc-193a-4331-b8df-b693a550a3ab","x-sessionid","e6dd7eab-2271-4a03-b9aa-b874ddd6b8bf","Content-Type","application/json")
                     .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                     .build();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            HttpResponse<String> response = HttpClient.newBuilder()
                        .build()
                        .send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject obj = new JSONObject(response.body());
        Integer numberPages = obj.getInt("nbPages");
            System.out.println(numberPages);
        ArrayList<String> urls = new ArrayList<>();
        for(int i= 1; i <= numberPages; i++){
           String url = String.format("{\"categories\":\"" + categoria + "\",\"page\":" + i + ",\"facets\":[],\"sortBy\":\"\",\"hitsPerPage\":100}");
            System.out.println(url);
           urls.add(url);
        }
        System.out.println(urls);
        GetPages(urls);
        }

    }
