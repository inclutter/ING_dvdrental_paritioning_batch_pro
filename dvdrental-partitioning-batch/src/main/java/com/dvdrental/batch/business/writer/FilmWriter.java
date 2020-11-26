package com.dvdrental.batch.business.writer;

import com.dvdrental.batch.business.service.FilmService;
import com.dvdrental.batch.business.vo.FilmVO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * date : 2020/11/26
 * file_name : FilmWriter
 * package_name : com.dvdrental.batch.business.writer
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */

@StepScope
@Component
public class FilmWriter implements ItemWriter<FilmVO> {

    @Autowired
    FilmService filmService;

    /**
     *
     * @param list
     * @throws Exception
     */
    @Override
    public void write(List<? extends FilmVO> list) throws Exception {
        for (FilmVO films:list
             ) {
            filmService.insertFilmInfo(films);
        }
        ;
    }
}
