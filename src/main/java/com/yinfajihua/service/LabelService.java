package com.yinfajihua.service;

import com.yinfajihua.pojo.Label;

import java.util.List;

public interface LabelService {
    List<Label> getHotLabelList(int scale,int count);
    Label getLabelById(int id);
}
