
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 物品订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/wupinOrder")
public class WupinOrderController {
    private static final Logger logger = LoggerFactory.getLogger(WupinOrderController.class);

    @Autowired
    private WupinOrderService wupinOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private AddressService addressService;
    @Autowired
    private WupinService wupinService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private ShangjiaService shangjiaService;
@Autowired
private WupinCommentbackService wupinCommentbackService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("商家".equals(role))
            params.put("shangjiaId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = wupinOrderService.queryPage(params);

        //字典表数据转换
        List<WupinOrderView> list =(List<WupinOrderView>)page.getList();
        for(WupinOrderView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WupinOrderEntity wupinOrder = wupinOrderService.selectById(id);
        if(wupinOrder !=null){
            //entity转view
            WupinOrderView view = new WupinOrderView();
            BeanUtils.copyProperties( wupinOrder , view );//把实体数据重构到view中

                //级联表
                AddressEntity address = addressService.selectById(wupinOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                    view.setAddressYonghuId(address.getYonghuId());
                }
                //级联表
                WupinEntity wupin = wupinService.selectById(wupinOrder.getWupinId());
                if(wupin != null){
                    BeanUtils.copyProperties( wupin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWupinId(wupin.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(wupinOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody WupinOrderEntity wupinOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,wupinOrder:{}",this.getClass().getName(),wupinOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            wupinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        wupinOrder.setInsertTime(new Date());
        wupinOrder.setCreateTime(new Date());
        wupinOrderService.insert(wupinOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WupinOrderEntity wupinOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,wupinOrder:{}",this.getClass().getName(),wupinOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            wupinOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<WupinOrderEntity> queryWrapper = new EntityWrapper<WupinOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WupinOrderEntity wupinOrderEntity = wupinOrderService.selectOne(queryWrapper);
        if(wupinOrderEntity==null){
            wupinOrderService.updateById(wupinOrder);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        wupinOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<WupinOrderEntity> wupinOrderList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            WupinOrderEntity wupinOrderEntity = new WupinOrderEntity();
//                            wupinOrderEntity.setWupinOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            wupinOrderEntity.setAddressId(Integer.valueOf(data.get(0)));   //收获地址 要改的
//                            wupinOrderEntity.setWupinId(Integer.valueOf(data.get(0)));   //物品 要改的
//                            wupinOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            wupinOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            wupinOrderEntity.setWupinOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            wupinOrderEntity.setWupinOrderCourierName(data.get(0));                    //快递公司 要改的
//                            wupinOrderEntity.setWupinOrderCourierNumber(data.get(0));                    //订单快递单号 要改的
//                            wupinOrderEntity.setWupinOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            wupinOrderEntity.setWupinOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            wupinOrderEntity.setInsertTime(date);//时间
//                            wupinOrderEntity.setCreateTime(date);//时间
                            wupinOrderList.add(wupinOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("wupinOrderUuidNumber")){
                                    List<String> wupinOrderUuidNumber = seachFields.get("wupinOrderUuidNumber");
                                    wupinOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> wupinOrderUuidNumber = new ArrayList<>();
                                    wupinOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("wupinOrderUuidNumber",wupinOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<WupinOrderEntity> wupinOrderEntities_wupinOrderUuidNumber = wupinOrderService.selectList(new EntityWrapper<WupinOrderEntity>().in("wupin_order_uuid_number", seachFields.get("wupinOrderUuidNumber")));
                        if(wupinOrderEntities_wupinOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WupinOrderEntity s:wupinOrderEntities_wupinOrderUuidNumber){
                                repeatFields.add(s.getWupinOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        wupinOrderService.insertBatch(wupinOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = wupinOrderService.queryPage(params);

        //字典表数据转换
        List<WupinOrderView> list =(List<WupinOrderView>)page.getList();
        for(WupinOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WupinOrderEntity wupinOrder = wupinOrderService.selectById(id);
            if(wupinOrder !=null){


                //entity转view
                WupinOrderView view = new WupinOrderView();
                BeanUtils.copyProperties( wupinOrder , view );//把实体数据重构到view中

                //级联表
                    AddressEntity address = addressService.selectById(wupinOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                }
                //级联表
                    WupinEntity wupin = wupinService.selectById(wupinOrder.getWupinId());
                if(wupin != null){
                    BeanUtils.copyProperties( wupin , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWupinId(wupin.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(wupinOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody WupinOrderEntity wupinOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,wupinOrder:{}",this.getClass().getName(),wupinOrder.toString());
            WupinEntity wupinEntity = wupinService.selectById(wupinOrder.getWupinId());
            if(wupinEntity == null){
                return R.error(511,"查不到该物品");
            }
            // Double wupinNewMoney = wupinEntity.getWupinNewMoney();

            if(false){
            }
            else if((wupinEntity.getWupinKucunNumber() -wupinOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }
            else if(wupinEntity.getWupinNewMoney() == null){
                return R.error(511,"物品价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            wupinOrder.setWupinOrderTypes(3); //设置订单状态为已支付
            wupinOrder.setWupinOrderTruePrice(0.0); //设置实付价格
            wupinOrder.setYonghuId(userId); //设置订单支付人id
            wupinOrder.setWupinOrderUuidNumber(String.valueOf(new Date().getTime()));
            wupinOrder.setWupinOrderPaymentTypes(1);
            wupinOrder.setInsertTime(new Date());
            wupinOrder.setCreateTime(new Date());
                wupinEntity.setWupinKucunNumber( wupinEntity.getWupinKucunNumber() -wupinOrder.getBuyNumber());
                wupinService.updateById(wupinEntity);
                wupinOrderService.insert(wupinOrder);//新增订单
            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String wupinOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer addressId = null;

        Integer wupinOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("wupinOrderPaymentTypes")));//支付类型
        if(wupinOrderPaymentTypes == 1){
            addressId = Integer.valueOf(String.valueOf(params.get("addressId")));
        }

        String data = String.valueOf(params.get("wupins"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> wupins = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<WupinOrderEntity> wupinOrderList = new ArrayList<>();
        //商家表
        ArrayList<ShangjiaEntity> shangjiaList = new ArrayList<>();
        //商品表
        List<WupinEntity> wupinList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);

        //循环取出需要的数据
        for (Map<String, Object> map : wupins) {
           //取值
            Integer wupinId = Integer.valueOf(String.valueOf(map.get("wupinId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            WupinEntity wupinEntity = wupinService.selectById(wupinId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //获取商家信息
            Integer shangjiaId = wupinEntity.getShangjiaId();
            ShangjiaEntity shangjiaEntity = shangjiaService.selectById(shangjiaId);//商家

            //判断商品的库存是否足够
            if(wupinEntity.getWupinKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(wupinEntity.getWupinName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                wupinEntity.setWupinKucunNumber(wupinEntity.getWupinKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            WupinOrderEntity wupinOrderEntity = new WupinOrderEntity<>();

            //赋值订单信息
            wupinOrderEntity.setWupinOrderUuidNumber(wupinOrderUuidNumber);//订单号
            wupinOrderEntity.setAddressId(addressId);//收获地址
            wupinOrderEntity.setWupinId(wupinId);//物品
            wupinOrderEntity.setYonghuId(userId);//用户
            wupinOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            wupinOrderEntity.setWupinOrderTypes(3);//订单类型
            wupinOrderEntity.setWupinOrderPaymentTypes(wupinOrderPaymentTypes);//支付类型
            wupinOrderEntity.setInsertTime(new Date());//订单创建时间
            wupinOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表线下支付
            if(wupinOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(wupinEntity.getWupinNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;

                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money);

                    wupinOrderEntity.setWupinOrderTruePrice(money);

                    //修改商家余额
                    shangjiaEntity.setNewMoney(shangjiaEntity.getNewMoney()+money);
                }
            }else if(wupinOrderPaymentTypes == 2){//线下支付
                wupinOrderEntity.setWupinOrderTruePrice(0.0);
            }else{
                return R.error("未知问题11,请联系管理员");

            }
            wupinOrderList.add(wupinOrderEntity);
            shangjiaList.add(shangjiaEntity);
            wupinList.add(wupinEntity);

        }
        wupinOrderService.insertBatch(wupinOrderList);
        shangjiaService.updateBatchById(shangjiaList);
        wupinService.updateBatchById(wupinList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);
        return R.ok();
    }











    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            WupinOrderEntity wupinOrder = wupinOrderService.selectById(id);
            Integer buyNumber = wupinOrder.getBuyNumber();
            Integer wupinOrderPaymentTypes = wupinOrder.getWupinOrderPaymentTypes();
            Integer wupinId = wupinOrder.getWupinId();
            if(wupinId == null)
                return R.error(511,"查不到该物品");
            WupinEntity wupinEntity = wupinService.selectById(wupinId);
            if(wupinEntity == null)
                return R.error(511,"查不到该物品");
            //获取商家信息
            Integer shangjiaId = wupinEntity.getShangjiaId();
            ShangjiaEntity shangjiaEntity = shangjiaService.selectById(shangjiaId);//商家
            Double wupinNewMoney = wupinEntity.getWupinNewMoney();
            if(wupinNewMoney == null)
                return R.error(511,"物品价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;


            //判断是什么支付方式 1代表余额 2代表积分
            if(wupinOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = wupinEntity.getWupinNewMoney() * buyNumber  * zhekou;

                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money);
                //修改商家余额
                shangjiaEntity.setNewMoney(shangjiaEntity.getNewMoney() - money);
            }else if(wupinOrderPaymentTypes == 2){//线下支付

            }else{
                return R.error("未知问题11,请联系管理员");

            }

            wupinEntity.setWupinKucunNumber(wupinEntity.getWupinKucunNumber() + buyNumber);



            wupinOrder.setWupinOrderTypes(2);//设置订单状态为退款
            wupinOrderService.updateById(wupinOrder);//根据id更新
            shangjiaService.updateById(shangjiaEntity);
            yonghuService.updateById(yonghuEntity);//更新用户信息
            wupinService.updateById(wupinEntity);//更新订单中物品的信息
            return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String wupinOrderCourierNumber, String wupinOrderCourierName){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        WupinOrderEntity  wupinOrderEntity = new  WupinOrderEntity();;
        wupinOrderEntity.setId(id);
        wupinOrderEntity.setWupinOrderTypes(4);
        wupinOrderEntity.setWupinOrderCourierNumber(wupinOrderCourierNumber);
        wupinOrderEntity.setWupinOrderCourierName(wupinOrderCourierName);
        boolean b =  wupinOrderService.updateById( wupinOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }









    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        WupinOrderEntity  wupinOrderEntity = new  WupinOrderEntity();
        wupinOrderEntity.setId(id);
        wupinOrderEntity.setWupinOrderTypes(5);
        boolean b =  wupinOrderService.updateById( wupinOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer wupinCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            WupinOrderEntity wupinOrder = wupinOrderService.selectById(id);
        if(wupinOrder == null)
            return R.error(511,"查不到该订单");
        if(wupinOrder.getWupinOrderTypes() != 5)
            return R.error(511,"您不能评价");
        Integer wupinId = wupinOrder.getWupinId();
        if(wupinId == null)
            return R.error(511,"查不到该物品");

        WupinCommentbackEntity wupinCommentbackEntity = new WupinCommentbackEntity();
            wupinCommentbackEntity.setId(id);
            wupinCommentbackEntity.setWupinId(wupinId);
            wupinCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            wupinCommentbackEntity.setWupinCommentbackText(commentbackText);
            wupinCommentbackEntity.setInsertTime(new Date());
            wupinCommentbackEntity.setReplyText(null);
            wupinCommentbackEntity.setUpdateTime(null);
            wupinCommentbackEntity.setCreateTime(new Date());
            wupinCommentbackService.insert(wupinCommentbackEntity);

            wupinOrder.setWupinOrderTypes(1);//设置订单状态为已评价
            wupinOrderService.updateById(wupinOrder);//根据id更新
            return R.ok();
    }







}
