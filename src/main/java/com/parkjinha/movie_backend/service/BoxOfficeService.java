package com.parkjinha.movie_backend.service;

//import org.json.simple.parser.ParseException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;

//public class BoxOfficeService {
//
////    public String getBoxOffice() throws IOException, ParseException {
////        // API URL 구성
////        String urlBuilder = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_xml2.jsp?collection=kmdb_new2&nation=대한민국" + "?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=서비스키" + // 서비스 키
////                "&" + URLEncoder.encode("val001", "UTF-8") + "=" + URLEncoder.encode("2018", "UTF-8") + // 상영년도
////                "&" + URLEncoder.encode("val002", "UTF-8") + "=" + URLEncoder.encode("01", "UTF-8"); // 상영 월
////
////        // URL 연결
////
////        return getMovieData(urlBuilder);
////    }
////    private String getMovieData(String urlString) throws IOException {
////        URL url = new URL(urlString);
////        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////        conn.setRequestMethod("GET");
////        conn.setRequestProperty("Content-type", "application/json");
////        System.out.println("Response code: " + conn.getResponseCode());
////        BufferedReader rd;
////        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
////            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////        }
////        else {
////            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
////        }
////        StringBuilder sb = new StringBuilder();
////        String line;
////        while ((line = rd.readLine()) != null) {
////            sb.append(line);
////        } rd.close();
////        conn.disconnect();
////        return sb.toString();
////    }
//}

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.parkjinha.movie_backend.entity.BoxOffice;
import com.parkjinha.movie_backend.entity.Movie;
import com.parkjinha.movie_backend.repository.BoxOfficeRepository;
import com.parkjinha.movie_backend.repository.MovieRepository;
import com.parkjinha.movie_backend.utils.ApiKeyProperties;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;


@Service
public class BoxOfficeService {
    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final BoxOfficeRepository boxOfficeRepository;
    @Autowired
    private ApiKeyProperties apiKeyProperties;

    public BoxOfficeService(MovieRepository movieRepository, BoxOfficeRepository boxOfficeRepository) {
        this.movieRepository = movieRepository;
        this.boxOfficeRepository = boxOfficeRepository;
    }

    public void updateOrFetchBoxOffice(Date targetDate) {
        List<BoxOffice> existingBoxOffice = boxOfficeRepository.findByDate(targetDate);
        if (existingBoxOffice.isEmpty()) {
            BoxOfficeApiResponse fetchedBoxOffice = fetchBoxOfficeFromApi(targetDate);
            for (DailyBoxOffice dailyBoxOffice : fetchedBoxOffice.getDailyBoxOfficeList()) {
                Movie movie = movieRepository.findByMovieName(dailyBoxOffice.getMovieNm());
                if (movie == null) {
                    movie = fetchAndSaveMovieDetails(dailyBoxOffice.getMovieNm(), targetDate);
                }

                if (movie != null) {
                    BoxOffice boxOffice = new BoxOffice();
                    boxOffice.setDate(targetDate);
                    boxOffice.setRank(Integer.parseInt(dailyBoxOffice.getRank()));
                    boxOffice.setMovie(movie);
                    boxOfficeRepository.save(boxOffice);
                }
            }
        } else {
            // 이미 존재하는 데이터 처리 (예: 로깅 또는 데이터 반환)
            Logger.getLogger("BoxOfficeService").info("이미 존재하는 데이터 처리");
        }
    }

    private BoxOfficeApiResponse fetchBoxOfficeFromApi(Date date) {
        RestTemplate restTemplate = new RestTemplate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String formattedDate = dateFormat.format(date);
        String apikey = apiKeyProperties.getKobisApiKey();
        String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=" + apikey + "&targetDt=" + formattedDate;
        try {
            String response = restTemplate.getForObject(url, String.class);
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(response, BoxOfficeApiResponse.class);
        } catch (Exception e) {
            // 예외 처리 로직 (예: 로깅)
            Logger.getLogger("BoxOfficeService").warning(Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
            return new BoxOfficeApiResponse();
        }
    }

    private Movie fetchAndSaveMovieDetails(String movieName, Date targetDate) {
        MovieDetailResponse movieDetails = fetchMovieDetailsFromKmdbApi(movieName, targetDate);
        try {
            if (movieDetails.getMovieDetail() != null) {
                Movie movie = getMovie(movieName, movieDetails);
                movieRepository.save(movie);
                return movie;
            }
        } catch (Exception e) {
            // 예외 처리 로직 (예: 로깅)
            Logger.getLogger("BoxOfficeService").warning(Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private static Movie getMovie(String movieName, MovieDetailResponse movieDetails) throws ParseException {
        Movie movie = new Movie();
        movie.setMovieName(movieName);
        System.out.println(movieName);
        // movieDetails를 사용하여 movie 엔티티의 필드를 채움
        if (movieDetails.getMovieDetail().isEmpty()) {
            return new Movie();
        } else {
            MovieDetail movieDetail = movieDetails.getMovieDetail().get(0);
            movie.setDirector(movieDetail.getDirectors().get(0).getDirectorName().trim());
            movie.setPoster(movieDetail.getPosterUrl().trim());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            System.out.println(movieDetail.getReleaseDate());
            movie.setReleaseDate(dateFormat.parse(movieDetail.getReleaseDate().trim()));
            movie.setRunningTime(movieDetail.getRuntime());
            movie.setRate(movieDetail.getRating().trim());
            return movie;
        }
    }


    private MovieDetailResponse fetchMovieDetailsFromKmdbApi(String movieName, Date targetDate) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(targetDate);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 5);
            String urlBuilder = "https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_xml2.jsp?collection=kmdb_new2&" + URLEncoder.encode("ServiceKey", StandardCharsets.UTF_8) + "=" + apiKeyProperties.getKmdbApiKey() + // 서비스 키
                    "&" + URLEncoder.encode("query", StandardCharsets.UTF_8) + "=" + movieName +
                    "&" + URLEncoder.encode("releaseDts", StandardCharsets.UTF_8) + "=" + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE);
            String response = restTemplate.getForObject(urlBuilder, String.class);
//             System.out.println(response);
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return xmlMapper.readValue(response, MovieDetailResponse.class);
        } catch (Exception e) {
            Logger.getLogger("BoxOfficeService").warning(Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
            return new MovieDetailResponse();
        }
    }

    // 기타 필요한 메소드...
}
