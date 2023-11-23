package com.parkjinha.movie_backend.controller;


import com.parkjinha.movie_backend.service.BoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private BoxOfficeService boxOfficeService;

    @GetMapping("/boxOffice")
    public ResponseEntity<?> getBoxOffice(@RequestParam String date) throws ParseException {
        // 날짜에 해당하는 BoxOffice 데이터를 가져오는 로직
        // 데이터가 없으면 boxOfficeService.updateOrFetchBoxOffice를 호출
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

        Date parsedDate = formatter.parse(date);
        boxOfficeService.updateOrFetchBoxOffice(parsedDate);
        return null;
    }
}





