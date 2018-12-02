package com.yinfajihua.controller;

import com.yinfajihua.dao.LabelRepository;
import com.yinfajihua.pojo.Info;
import com.yinfajihua.pojo.ReInfo;
import com.yinfajihua.service.InfoService;
import com.yinfajihua.util.ObjectToJsonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ResponseBody
@RequestMapping(value = "/info")
public class InfoController {
    @Autowired
    InfoService infoService;
    @Autowired
    LabelRepository labelRepository;


    @RequestMapping(value = "/infoList")
    public ReInfo getInfoList(int type_id, int start, int end) throws ParseException {
        List<Info> info_list = infoService.getInfoList(type_id,start,end);
        List<JSONObject> list = new ArrayList<JSONObject>();
        for (Info info:info_list){
            list.add(ObjectToJsonUtil.getInfoObject(info,new HashSet<String>()));
        }
        ReInfo reInfo = new ReInfo(0,"获取成功",list);
        return reInfo;
    }
    @RequestMapping(value = "/hotInfoList")
    public ReInfo getHotInfo(int count,int scale) throws ParseException {
        List<Info> info_list = infoService.getHotInfoList(count,scale);
        List<JSONObject> list = new ArrayList<JSONObject>();
        Set<String> set = new HashSet<String>();
        set.add("browse_times");
        for (Info info:info_list){
            list.add(ObjectToJsonUtil.getInfoObject(info,set));
        }
        ReInfo reInfo = new ReInfo(0,"获取成功",list);
        return reInfo;
    }

    @RequestMapping(value = "/addInfo",method = RequestMethod.POST,produces = { "application/json;charset=utf-8" })
    public ReInfo addInfo(@RequestBody String params){
        JSONObject request = JSONObject.fromObject(params);

        Info info = new Info();
        info.setTitle(request.get("title").toString());
        info.setContexts(request.get("context").toString());
        info.setAbstracts(request.get("abstracts").toString());
        info.setUrl(request.get("url").toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        info.setUploadTime(date);

        int author_id = Integer.valueOf(request.get("author_id").toString());
        int type_id = Integer.valueOf(request.get("type_id").toString());
        String label_ids = request.get("label_ids").toString();
        List<Integer> label_id_list = new ArrayList<Integer>();
        for (String id:label_ids.split(","))
            label_id_list.add(Integer.valueOf(id));

        boolean finished = infoService.addInfo(info,label_id_list,author_id,type_id);
        if (finished){
            return new ReInfo(0,"添加资讯成功",null);
        }else {
            return new ReInfo(1,"添加资讯失败",null);
        }

    }
    @RequestMapping(value = "/infoById")
    public ReInfo getInfoById(int id,int user_id) throws ParseException {
        Info info = infoService.getInfoById(id,user_id);
        Set<String> set = new HashSet<String>();
        set.add("contexts");
        return new ReInfo(0,"获取成功",ObjectToJsonUtil.getInfoObject(info,set));
    }
}
