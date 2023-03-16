package com.example.fragments;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class Map extends Fragment {
    private MapView mMapView;
    private EditText input;
    private Button search;

    private List searchLocation(String locationName, GoogleMap googleMap) {
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(locationName, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(addresses.get(0));
        if (addresses != null && !addresses.isEmpty()) {
            Address address = addresses.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
        return addresses;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el archivo de layout para este fragmento
        View rootView = inflater.inflate(R.layout.menu_maps, container, false);

        // Obtener la referencia al MapView
        mMapView = rootView.findViewById(R.id.map_view);
        input = rootView.findViewById(R.id.input);
        search = rootView.findViewById(R.id.search_button);

        // Inicializar el mapa
        mMapView.onCreate(savedInstanceState);

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // Configurar el mapa
                LatLng location = new LatLng(40.416775, -3.703790); // Ubicación predeterminada (Madrid, España)
                googleMap.addMarker(new MarkerOptions().position(location).title("Ubicación predeterminada"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10)); // Zoom inicial de 10

                //Obtener ubicacion del dispisitivo
                /*
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Location locationDevice = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (locationDevice != null) {
                    LatLng currentLocation = new LatLng(locationDevice.getLatitude(), locationDevice.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(currentLocation).title("Ubicación actual"));
                }
                 */

                // Agregar listener al botón "search" para ubicar la localidad ingresada en el mapa
                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = input.getText().toString(); // Obtener el texto del EditText "input"
                        List<Address> addresses = searchLocation(text, googleMap);
                        if (addresses != null && !addresses.isEmpty()) {
                            Address address = addresses.get(0);
                            LatLng location = new LatLng(address.getLatitude(), address.getLongitude()); // Crear nuevo objeto LatLng con la latitud y longitud obtenidos
                            googleMap.clear(); // Limpiar marcadores previos
                            googleMap.addMarker(new MarkerOptions().position(location).title(text)); // Agregar marcador en la ubicación encontrada
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12)); // Zoom de 12
                        } else {
                            // Manejar el caso en que no se encontró ninguna dirección para el texto ingresado
                        }
                    }
                });
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}