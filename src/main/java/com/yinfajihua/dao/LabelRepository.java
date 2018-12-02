package com.yinfajihua.dao;

import com.yinfajihua.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface LabelRepository extends JpaRepository<Label,Integer> {
    //近日内热门标签
    @Query(nativeQuery = true,value = "SELECT label.id as id,label.name as name,label.deleteflag as deleteflag," +
            "count(info_browse.id) as browse_times FROM info_browse " +
            "JOIN info_label on info_browse.info_id=info_label.info_id " +
            "JOIN label on label_id=label.id " +
            "WHERE TIMESTAMPDIFF(DAY,info_browse.time,?1)<?2 " +
            "GROUP BY label.id " +
            "ORDER BY browse_times DESC LIMIT 0,?3")
    List<Label> getLatestHotLabel(String current_time,int scale,int count);

    //总体热门标签
    @Query(nativeQuery = true,value = "SELECT label.id as id,label.name as name,label.deleteflag as deleteflag," +
            "count(info_browse.id) as browse_times FROM info_browse " +
            "JOIN info_label on info_browse.info_id=info_label.info_id " +
            "JOIN label on label_id=label.id " +
            "GROUP BY label.id " +
            "ORDER BY browse_times DESC LIMIT 0,?1")
    List<Label> getAllHotLabel(int count);
    //按照id获取标签列表
    @Query(nativeQuery = true,value = "SELECT * FROM label where id in (?1)")
    Set<Label> getLabelList(List<Integer> label_id_list);


}
