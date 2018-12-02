package com.yinfajihua.service.serviceImpl;

import com.yinfajihua.dao.LabelRepository;
import com.yinfajihua.pojo.Label;
import com.yinfajihua.service.LabelService;
import com.yinfajihua.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Transactional
@Service
public class LabelServiceImpl implements LabelService{
    @Autowired
    private LabelRepository labelRepository;
    @Override
    public List<Label> getHotLabelList(int scale,int count) {
        if (scale==0)
            return labelRepository.getAllHotLabel(count);
        else {
            List<Label> list = labelRepository.getLatestHotLabel(TimeUtils.getCurrentTime(),scale,count);
            return list;
        }
    }

    @Override
    public Label getLabelById(int id) {
        Label label = labelRepository.getOne(id);
        return label;
    }
}
