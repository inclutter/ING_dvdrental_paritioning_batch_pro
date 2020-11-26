package com.dvdrental.batch.business.service;

import com.dvdrental.batch.business.vo.FilmVO;

import java.util.List;

/**
 * date : 2020/11/26
 * file_name : FilmService
 * package_name : com.dvdrental.batch.business.service
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */

public interface FilmService {

    /**
     *
     * @return
     */
    List<FilmVO> selectFilmList();

    /**
     *
     */
    public void deleteFilmInfo();

    /**
     *
     * @param filmVO
     */
    public void insertFilmInfo(FilmVO filmVO);

}
