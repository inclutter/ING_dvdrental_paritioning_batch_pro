package com.dvdrental.batch.business.service.impl;

import com.dvdrental.batch.business.mapper.FilmMapper;
import com.dvdrental.batch.business.service.FilmService;
import com.dvdrental.batch.business.vo.FilmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * date : 2020/11/26
 * file_name : FilmServiceImpl
 * package_name : com.dvdrental.batch.business.service.impl
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmMapper filmMapper;

    /**
     *
     * @return
     */
    @Override
    public List<FilmVO> selectFilmList() {
        return filmMapper.selectFilmList();
    }

    @Override
    public void deleteFilmInfo() {
        filmMapper.deleteFilmInfo();
    }

    @Override
    public void insertFilmInfo(FilmVO filmVO) {
        filmMapper.insertFilmInfo(filmVO);
    }


}
