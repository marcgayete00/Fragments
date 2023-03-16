package com.example.fragments;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationActions;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Weather extends Fragment {
    //private MainActivity mainActivity;
    private TextView temperature;

    // La URL base de la API de OpenWeather
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";

    // Tu clave de API de OpenWeather
    private static final String API_KEY = "74d608fc5fefa6a3135666c2080da295";
    /*
    public Weather(MainActivity activity) {
        mainActivity = activity;
    }
    */


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla la vista raíz utilizando LayoutInflater y establece los estilos del archivo menu_weather
        View rootView = inflater.inflate(R.layout.menu_weather, container, false);
        temperature = rootView.findViewById(R.id.temperature);

        // Crea un cliente de OkHttpClient para hacer solicitudes HTTP
        OkHttpClient client = new OkHttpClient();

        // Crea una solicitud GET con la ciudad que deseas obtener el clima
        Request request = new Request.Builder()
                .url(BASE_URL + "?q=London&appid=" + API_KEY)
                .build();

        // Usa el cliente para hacer la solicitud asincrónicamente
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Manejo de error en caso de que la solicitud falle
                Log.e("Weather", "Error making API call", e);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    // Convierte la respuesta en un objeto JSON
                    String responseBody = response.body().string();
                    JSONObject json = new JSONObject(responseBody);
                    Log.d("Weather", "API Response: " + responseBody);

                    // Extrae la temperatura actual de la respuesta JSON
                    JSONArray list = json.getJSONArray("list");
                    JSONObject firstInterval = list.getJSONObject(0);
                    double temperatureValue = firstInterval.getJSONObject("main").getDouble("temp");

                    // Actualiza la temperatura en la interfaz de usuario
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            temperature.setText(String.valueOf(temperatureValue));
                        }
                    });
                } catch (JSONException e) {
                    // Manejo de error en caso de que la respuesta no pueda ser parseada como JSON
                    Log.e("Weather", "Error parsing API response as JSON", e);
                }
            }
        });

        return rootView;
    }
}