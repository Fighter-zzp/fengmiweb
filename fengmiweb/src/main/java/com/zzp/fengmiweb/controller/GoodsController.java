package com.zzp.fengmiweb.controller;

import com.zzp.fengmiweb.entity.Goods;
import com.zzp.fengmiweb.service.IGoodTypeService;
import com.zzp.fengmiweb.service.IGoodsService;
import com.zzp.fengmiweb.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IGoodTypeService service;

    // 跳转到新增页面
    @RequestMapping("toAddGoods")
    public String showadd(HttpServletRequest request, Model model) {
        model.addAttribute("gtlist", service.queryByLevel1());
        return "addGoods";
    }
    // 商品新增
    @RequestMapping("addGoods")
    public String save(MultipartFile file, Goods goods, HttpServletRequest request)
            throws IllegalStateException, IOException {
        //将图片保存到服务器里
        //获取上传文件的文件名和后缀名
        String fn = file.getOriginalFilename();
        //创建需要保存的的文件对象  target/fengmi-web/assters/images/201811
        var desFile = new File(request.getServletContext().getRealPath("/assters/images/201811/"), fn);
        file.transferTo(desFile);
        goods.setPicture("201811/"+ desFile.getName());
        goods.setFlag(1);//上架
        if (goodsService.save(goods)) {
            return "addGoods";
        } else {
            request.setAttribute("msg", "添加失败，重新再来");
            return "addGoods";
        }
    }
    // 查看商品详情
    @RequestMapping("getGoodsById")
    public String goodsbyid(int id, Model model) {
        model.addAttribute("goods", goodsService.querySingle(id));
        return "goodsDetail";
    }
    // 查看商品详情
    @RequestMapping("getGoodsIndex")
    @ResponseBody
    public List<List<Goods>> goodsindex(HttpServletResponse response) throws IOException {
        return goodsService.queryIndex();
    }
    // 查看商品列表
    @RequestMapping("getGoodsListByTn")
    public String goodsbytn(String tn, Model model) {
        model.addAttribute("glist", goodsService.queryByType(tn));
        return "goodsList";
    }
    // 查看商品列表
    @RequestMapping("getGoodsList")
    public String goodslist(Model model) {
        model.addAttribute("goodsList", goodsService.queryAll());
        return "admin/showGoods";
    }
    // 模糊查询商品列表
    @RequestMapping("selectByName")
    public String selectByName(String name, Model model) {
        model.addAttribute("glist", goodsService.queryByName(name));
        return "goodsList";
    }
    // 根据商品名称和上架时间查询商品（admin）
    @RequestMapping("selectByNameAndPub")
    public String queryNameAndPub(String name,String pubdate, Model model) {
        List<Goods> goods = goodsService.queryNameAndPub(name,pubdate);
        model.addAttribute("goodsList",goods );
        return "admin/showGoods";
    }
    //删除商品
    @RequestMapping("goodsDeleteById")
    public String goodsDeleteById(Integer id ,Model model) {
        goodsService.deleteById(id);
        return "redirect:getGoodsList";
    }
}
