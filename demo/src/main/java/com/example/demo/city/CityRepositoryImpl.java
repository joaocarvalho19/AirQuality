package com.example.demo.city;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Repository
public class CityRepositoryImpl implements CityRepository{

    private EntityManager entityManager;

    final String URL1 = "https://api.waqi.info/feed/";
    final String URL2 = "/?token=3b42a1a4b7028abfb26999365a4f97594cb54212";

    private void sendGET(String name) throws Exception {
        final String finalurl = URL1 + name + URL2;

        URL obj = new URL(finalurl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    @Override
    public City findById(Long id) throws Exception {
        //Session currSession = entityManager.unwrap(Session.class);
        //City city = currSession.getId(City.class.getId());
        return null;
    }

    @Override
    public City findByName(String name) throws Exception {
        City city = null;
        sendGET(name);
        return null;
    }

    @Override
    public List<City> findAll()  throws Exception {
        return null;
    }

    @Override
    public City save(City city) throws Exception {
        // currSession = entityManager.unwrap(Session.class);
        //currSession.saveOrUpdate(city);
        return city;
    }

    @Override
    public boolean exists(String some_name) {
        return false;
    }
}
