package com.parkjinha.movie_backend.service;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

@Data
public class MovieDetailResponse {

    @JacksonXmlProperty(localName = "Options")
    private String movieDetailOption;

    @JacksonXmlProperty(localName = "Condition")
    private String movieDetailCondition;

    @JacksonXmlElementWrapper(localName = "Result")
    @JacksonXmlProperty(localName = "Row")
    private List<MovieDetail> movieDetail;
}

@Data
class MovieDetail {
    @JacksonXmlElementWrapper(localName = "directors")
    @JacksonXmlProperty(localName = "director")
    private List<Director> directors;   // 감독 이름

    @JacksonXmlProperty(localName = "runtime")
    private Integer runtime;       // 상영 시간

    @JacksonXmlProperty(localName = "rating")
    private String rating;         // 등급

    @JacksonXmlProperty(localName = "posters")
    private String posterUrl;      // 포스터 URL 또는 KMDB URL

    @JacksonXmlProperty(localName = "repRlsDate")
    private String releaseDate;    // 개봉일
}

@Data
class Director {
    @JacksonXmlProperty(localName = "directorNm")
    private String directorName;   // 감독 이름
}