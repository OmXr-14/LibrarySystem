package com.LibrarySystem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiClient{


    private static final String BASE_URL = "http://localhost:8080/api";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();


    public static List<Libro> getTuttiLibri() throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/libri"))
            .GET()
            .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Type listType = new TypeToken<ArrayList<Libro>> (){}.getType();
            return gson.fromJson(response.body(), listType);
    }

    public static String prestaLibro(String idUtente, String idLibro) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(BASE_URL +"/prestiti/presta?idUtente=" + idUtente + "&idLibro" + idLibro))
        .POST(HttpRequest.BodyPublishers.noBody())
        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode()==200){
            return "SUCESSO: " + response.body();
        }else{
            throw new Exception("ERRORE" + response.statusCode()+ ":" + response.body());
        }


    }
}