package com.zzp.fengmiweb.controller;

import com.zzp.fengmiweb.entity.GoodsType;
import com.zzp.fengmiweb.service.IGoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GoodTypeController {
    @Autowired
    private IGoodTypeService iGoodTypeService;

    //跳转到添加页面
    @RequestMapping("goodTypeAddShow")
    public String showAdd( Model model){
        model.addAttribute("gtlist",iGoodTypeService.queryByLevel1());
        return "addGoodsType";
    }

    //添加商品
    @RequestMapping("goodTypeAdd")
    public String add(GoodsType goodsType, Model model) {
        if("1".equals(goodsType.getParentName())) {
            goodsType.setLevel(1);
            goodsType.setParentName(null);
        }else {
            goodsType.setLevel(2);
        }
        goodsType.setFlag(1);
        if (iGoodTypeService.save(goodsType)) {
            return "redirect:getGoodsType";
        } else {
            model.addAttribute("msg", "服务器异常，请稍后再来");
            return "redirect:goodTypeAddShow";
        }
    }

    //实现删除商品
    @RequestMapping("delGoodsType")
    @ResponseBody
    public String delGoodsType(int id){
        iGoodTypeService.deleteById(id);
        return "success";
    }

    //显示商品类型列表
    @RequestMapping("getGoodsType")
    public String show(Model model){
        model.addAttribute("gtlist", iGoodTypeService.queryAll());
        return "/admin/showGoodsType";
    }

    //显示商品类型列表---json格式
    @RequestMapping("goodsTypeJson")
    @ResponseBody
    public List<GoodsType> showJson(){
        return iGoodTypeService.queryByLevel1();
    }

    //根据商品等级和名称查找
    @RequestMapping("selectByNameFlag")
    public String queryNameAndFlag(String name,int flag,Model model){
        model.addAttribute("gtlist", iGoodTypeService.queryByLAndN(flag, name));
        return "/admin/showGoodsType";
    }
}
