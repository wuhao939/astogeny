package com.yinfajihua.dao;

import com.yinfajihua.pojo.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoRepository extends JpaRepository<Info,Integer> {
    //最新资讯
    @Query(nativeQuery = true,value = "select * from info ORDER BY upload_time DESC limit ?1,?2")
    List<Info> getNewestInformation(int start,int end);
    //按照类型获取资讯
    @Query(nativeQuery = true,value = "SELECT * FROM info WHERE type_id = ?1 ORDER BY upload_time DESC LIMIT ?2,?3")
    List<Info> getInfoByTypeId(int type_id,int start,int end);

    //总体热门资讯
    @Query(nativeQuery = true,value = "SELECT info.id as id,title,abstracts,author_id,type_id,upload_time,contexts" +
            ",like_count,url,info.deleteflag as deleteflag,count(*) as browse_times " +
            "FROM info_browse JOIN info on info_browse.info_id=info.id " +
            "GROUP BY info.id " +
            "ORDER BY browse_times DESC LIMIT 0,?1")
    List<Info> getAllHotType(int count);
    //几天内热门资讯
    @Query(nativeQuery = true,value = "SELECT info.id as id,title,abstracts,author_id,type_id,upload_time,contexts" +
            ",like_count,url,info.deleteflag as deleteflag,count(*) as browse_times " +
            "FROM info_browse JOIN info on info_browse.info_id=info.id " +
            "WHERE TIMESTAMPDIFF(DAY,info_browse.time,?1)<?2 " +
            "GROUP BY info.id " +
            "ORDER BY browse_times DESC LIMIT 0,?3")
    List<Info> getLatestHotType(String current_time,int scale,int count);
    @Query(nativeQuery = true,value = "SELECT * from info WHERE id=?1")
    Info getInfoById(int id);

}
