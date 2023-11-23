package com.parkjinha.movie_backend.service;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "boxOfficeResult")
public class BoxOfficeApiResponse {
    @JacksonXmlProperty(localName = "boxofficeType")
    private String boxofficeType;

    @JacksonXmlProperty(localName = "showRange")
    private String showRange;

    @JacksonXmlElementWrapper(localName = "dailyBoxOfficeList")
    @JacksonXmlProperty(localName = "dailyBoxOffice")
    private List<DailyBoxOffice> dailyBoxOfficeList;
}


@Data
class DailyBoxOffice {

    @JacksonXmlProperty(localName = "rnum")
    private String rnum;

    @JacksonXmlProperty(localName = "rank")
    private String rank;

    @JacksonXmlProperty(localName = "rankInten")
    private String rankInten;

    @JacksonXmlProperty(localName = "rankOldAndNew")
    private String rankOldAndNew;

    @JacksonXmlProperty(localName = "movieCd")
    private String movieCd;

    @JacksonXmlProperty(localName = "movieNm")
    private String movieNm;

    @JacksonXmlProperty(localName = "openDt")
    private String openDt;

    @JacksonXmlProperty(localName = "salesAmt")
    private long salesAmt;

    @JacksonXmlProperty(localName = "salesShare")
    private double salesShare;

    @JacksonXmlProperty(localName = "salesInten")
    private long salesInten;

    @JacksonXmlProperty(localName = "salesChange")
    private double salesChange;

    @JacksonXmlProperty(localName = "salesAcc")
    private long salesAcc;

    @JacksonXmlProperty(localName = "audiCnt")
    private int audiCnt;

    @JacksonXmlProperty(localName = "audiInten")
    private int audiInten;

    @JacksonXmlProperty(localName = "audiChange")
    private double audiChange;

    @JacksonXmlProperty(localName = "audiAcc")
    private int audiAcc;

    @JacksonXmlProperty(localName = "scrnCnt")
    private int scrnCnt;

    @JacksonXmlProperty(localName = "showCnt")
    private int showCnt;
}