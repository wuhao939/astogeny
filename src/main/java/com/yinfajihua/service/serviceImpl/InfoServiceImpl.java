package com.yinfajihua.service.serviceImpl;

import com.yinfajihua.dao.*;
import com.yinfajihua.pojo.*;
import com.yinfajihua.service.InfoService;
import com.yinfajihua.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoRepository infoRespository;
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private InfoBrowseRepository infoBrowseRepository;

    @Override
    public List<Info> getInfoList(int type_id, int start, int end) {
        if (type_id==0)
            return infoRespository.getNewestInformation(start,end);
        else
            return infoRespository.getInfoByTypeId(type_id,start,end);
    }

    @Override
    public List<Info> getHotInfoList(int count,int scale) {
        List<Info> info_list;
        if (scale==0)
            info_list = infoRespository.getAllHotType(count);
        else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String current_time = df.format(new Date());
            info_list = infoRespository.getLatestHotType(current_time,scale,count);
        }
        return info_list;
    }


    @Override
    public boolean addInfo(Info info, List<Integer> label_id_list, int author_id, int type_id) {
        Set<Label> labelList = labelRepository.getLabelList(label_id_list);
        Author author = authorRepository.findOne(author_id);
        Type type = typeRepository.findOne(type_id);
        info.setLabelSet(labelList);
        info.setAuthor(author);
        info.setType(type);
        infoRespository.save(info);
        return true;
    }
    //按照id获取资讯
    @Override
    public Info getInfoById(int id,int user_id) {
        Info info = infoRespository.getInfoById(id);
        InfoBrowse infoBrowse = new InfoBrowse(id,user_id, TimeUtils.getCurrentTime());
        infoBrowseRepository.save(infoBrowse);
        return info;
    }


}
