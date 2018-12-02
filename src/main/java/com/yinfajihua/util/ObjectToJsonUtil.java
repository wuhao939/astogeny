package com.yinfajihua.util;

import com.yinfajihua.pojo.Info;
import com.yinfajihua.pojo.Label;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObjectToJsonUtil {
    public static JSONObject getInfoObject(Info info,Set<String> keys) throws ParseException {
        String upload_time = info.getUploadTime();
        String time_diff = TimeUtils.cal_time_diff(upload_time);
        JSONObject jo = new JSONObject();

        if (keys.contains("browse_times"))
            jo.put("browse_times",info.getBrowseTimes());
        if (keys.contains("contexts"))
            jo.put("contexts",info.getContexts());
        jo.put("id",info.getId());
        jo.put("title",info.getTitle());
        jo.put("abstracts",info.getAbstracts());
        jo.put("time_diff",time_diff);
        jo.put("url",info.getUrl());
        jo.put("likeCount",info.getLikeCount());
        List<JSONObject> labelSet = new ArrayList<JSONObject>();
        for (Label label:info.getLabelSet()){
            JSONObject subjo = new JSONObject();
            subjo.put("id",label.getId());
            subjo.put("name",label.getName());
            labelSet.add(subjo);
        }
        jo.put("labelSet",labelSet);
        JSONObject jo_type = new JSONObject();
        jo_type.put("id",info.getType().getId());
        jo_type.put("name",info.getType().getName());
        jo.put("type",jo_type);
        JSONObject jo_author = new JSONObject();
        jo_author.put("id",info.getAuthor().getId());
        jo_author.put("name",info.getAuthor().getName());
        jo.put("author",jo_author);

        return jo;
    }
    public static JSONObject getLabelObject(Label label,Set<String> keys) throws ParseException {
        JSONObject jo = new JSONObject();
        jo.put("id",label.getId());
        jo.put("name",label.getName());
        if (keys.contains("infoSet")){
            List<JSONObject> list = new ArrayList<JSONObject>();
            for (Info info:label.getInfoSet()){
                list.add(getInfoObject(info,new HashSet<String>()));
            }
            jo.put("infoSet",list);
        }
        return jo;
    }
}
