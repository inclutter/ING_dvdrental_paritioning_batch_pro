package com.dvdrental.batch.business.mapper;

import com.dvdrental.batch.business.vo.FilmVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FilmMapper {

    /**
     *
     * @return
     */
    public List<FilmVO> selectFilmList();

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
