package com.parkjinha.movie_backend.controller;

import com.parkjinha.movie_backend.entity.Movies;
import com.parkjinha.movie_backend.repository.MovieRepository;

import com.parkjinha.movie_backend.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RestController
public class GetController {
    private final MovieService movieService;

    @Autowired
    public GetController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/getDataFromApi")
    public String getDataFromApi() throws IOException, ParseException {
        /*URL*/

        String urlBuilder = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2" + "&" + URLEncoder.encode("ServiceKey", StandardCharsets.UTF_8) + "=DA2J2D5FPX341TF60D07" /*Service Key*/
                + "&" + URLEncoder.encode("listCount", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) /*상영 월*/
                + "&" + URLEncoder.encode("query", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("30일", StandardCharsets.UTF_8);
        String sb = getMovieData(urlBuilder);

        JSONParser jsonParser1 = new JSONParser();
        JSONObject jsonObject1 = (JSONObject) jsonParser1.parse(sb);
        JSONArray dataArray1 = (JSONArray) jsonObject1.get("Data");
        JSONObject movieArray1 = (JSONObject) dataArray1.get(0);
        JSONArray movieObject1 = (JSONArray) movieArray1.get("Result");
        JSONObject movieData = (JSONObject) movieObject1.get(0);
        JSONObject directors = (JSONObject) movieData.get("directors");
        JSONArray directorAry = (JSONArray) directors.get("director");
        JSONObject directorAry1 = (JSONObject) directorAry.get(0);
        String director = (String) directorAry1.get("directorNm");

        System.out.println(director);

        return sb.toString();
    }

    @RequestMapping("/getBoxOffice")
    public String getBoxOffice() throws IOException {
        /*URL*/
        String urlBuilder = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json" + "?" + URLEncoder.encode("key", StandardCharsets.UTF_8) + "=bcac9d14da5f659dbbf4f1b0c0440ec4" + /*Service Key*/
                "&" + URLEncoder.encode("targetDt", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20231013", "UTF-8"); /*yyyymmdd*/
        /*
        * Name
        * Director
        * Release Date
        * */

        /*
        * Poster
        * Rate
        * Running Time
        *  */
        String boxOffice = getMovieData(urlBuilder);

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(boxOffice);
            JSONObject dataArray = (JSONObject) jsonObject.get("boxOfficeResult");
            JSONArray movieArray = (JSONArray) dataArray.get("dailyBoxOfficeList");
            for (Object o : movieArray) {
                JSONObject movieObject = (JSONObject) o;
                System.out.println(movieObject.get("movieNm"));

                String urlBuilder1 = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2" + "&" + URLEncoder.encode("ServiceKey", StandardCharsets.UTF_8) + "=DA2J2D5FPX341TF60D07" /*Service Key*/
                        + "&" + URLEncoder.encode("listCount", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) /*상영 월*/
                        + "&" + URLEncoder.encode("query", StandardCharsets.UTF_8) + "=" + URLEncoder.encode((String) movieObject.get("movieNm"), StandardCharsets.UTF_8);

                String filmData = getMovieData(urlBuilder1);
                System.out.println(filmData);

                JSONParser jsonParser1 = new JSONParser();
                JSONObject jsonObject1 = (JSONObject) jsonParser1.parse(filmData);
                JSONArray dataArray1 = (JSONArray) jsonObject1.get("Data");
                JSONObject movieArray1 = (JSONObject) dataArray1.get(0);
                JSONArray movieObject1 = (JSONArray) movieArray1.get("Result");
                JSONObject movieData = (JSONObject) movieObject1.get(0);

                String poster = (String) movieData.get("posters");
                String[] posters = poster.split("[|]", 0);

                String rate = (String) movieData.get("rating");

                int rating = 0;
                switch (rate) {
                    case "전체관람가" -> {
                    }
                    case "12세관람가" -> rating = 12;
                    case "15세관람가" -> rating = 15;
                    case "청소년관람불가" -> rating = 19;
                }

                String runningTime = (String) movieData.get("runtime");

                JSONObject directors = (JSONObject) movieData.get("directors");
                JSONArray directorAry = (JSONArray) directors.get("director");
                JSONObject directorAry1 = (JSONObject) directorAry.get(0);
                String director = (String) directorAry1.get("directorNm");

                Movies movieReturn = new Movies();
                movieReturn.setMovie_name((String) movieObject.get("movieNm"));
                movieReturn.setDirector(director);
                Date d = new SimpleDateFormat("yyyy-MM-dd").parse((String) movieObject.get("openDt"));
                movieReturn.setRelease_date(d);
                movieReturn.setRate(rating);
                movieReturn.setPoster(posters[0]);
                movieReturn.setRunning_time(Integer.valueOf(runningTime));
                movieService.saveDataToDB(movieReturn);
//                this.movieRepository.save(movieReturn);
            }
            System.out.println(jsonParser);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return boxOffice;
    }

    private String getMovieData(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }
        else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        } rd.close();
        conn.disconnect();
        return sb.toString();
    }
}

