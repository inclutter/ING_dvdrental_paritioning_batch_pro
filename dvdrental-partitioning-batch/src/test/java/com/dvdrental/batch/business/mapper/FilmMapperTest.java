package com.dvdrental.batch.business.mapper;

import com.dvdrental.batch.business.vo.FilmVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class FilmMapperTest {

    @Autowired
    FilmMapper filmMapper;

    @Test
    public void selectFilmList() {
        for (FilmVO filmList:filmMapper.selectFilmList()) {
            log.info("filmList : {},{},{},{},{},{},{},{},{},{},special : {},{}",
                    filmList.getFilmId(),filmList.getDescription(),filmList.getLanguageId(),
                    filmList.getLength(),filmList.getRating(),filmList.getReleaseYear(),
                    filmList.getRentalDuration(),filmList.getRentalRate(),filmList.getReplacementCost(),
                    filmList.getTitle(),filmList.getFulltext()
            );
        }
    }
}