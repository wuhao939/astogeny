package com.yinfajihua.controller;

import com.yinfajihua.pojo.Label;
import com.yinfajihua.pojo.ReInfo;
import com.yinfajihua.service.LabelService;
import com.yinfajihua.util.ObjectToJsonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@ResponseBody
@RequestMapping(value = "/label")
public class LabelController {
    @Autowired
    private LabelService labelService;
    @RequestMapping(value = "/hotLabelList")
    public ReInfo getHotLabelList(int scale,int count) throws ParseException {
        List<Label> hotLabelList = labelService.getHotLabelList(scale,count);
        List<JSONObject> list = new ArrayList<JSONObject>();
        for(Label label:hotLabelList){
            list.add(ObjectToJsonUtil.getLabelObject(label,new HashSet<String>()));
        }
        return new ReInfo(0,"查询成功",list);
    }
    @RequestMapping(value = "/labelById")
    public ReInfo getLabelById(int id) throws ParseException {
        Label label = labelService.getLabelById(id);
        Set<String> set = new HashSet<String>();
        set.add("infoSet");
        return new ReInfo(0,"查询成功",ObjectToJsonUtil.getLabelObject(label,set));
    }
}
