package com.yinfajihua.service;

import com.yinfajihua.pojo.Info;

import java.util.List;

public interface InfoService {
    List<Info> getInfoList(int type_id,int start,int end);
    List<Info> getHotInfoList(int count,int scale);
    boolean addInfo(Info info,List<Integer> label_id_list,int author_id,int type_id);
    Info getInfoById(int id,int user_id);
}
