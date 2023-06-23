package top.ptcc9.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import top.ptcc9.entity.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import static top.ptcc9.common.State.*;
import top.ptcc9.utils.*;
import top.ptcc9.annotations.*;
import top.ptcc9.common.*;

import java.math.BigDecimal;
import java.util.*;
import org.springframework.web.multipart.MultipartFile;
import top.ptcc9.mapper.*;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderBillController {
    @Resource
    private OrderBillMapper orderBillMapper;
    @Resource
    private FilmMapper filmMapper;
    @Resource
    private JwtUtil jwtUtil;

    @LoginRequired
    @RequestMapping(value = "/viewOrderBillTable",method = RequestMethod.GET)
    public R<Page<OrderBill>> viewOrderBillTable(String filmName,String userId,Integer current,Integer size) {
        QueryWrapper<OrderBill> wrapper = new QueryWrapper<>();
        if (!CommonUtil.isEmpty(filmName)) {
            wrapper.like("film_name",filmName);
        }
        if (!CommonUtil.isEmpty(userId)) {
            wrapper.like("user_id",userId);
        }
        wrapper.orderByDesc("create_time");
        Page<OrderBill> page = new Page<>(current,size);
        Page<OrderBill> orderBillPage = orderBillMapper.selectPage(page, wrapper);
        return R.build(SUCCESS,orderBillPage);
    }

    @LoginRequired
    @RequestMapping(value = "/viewOrderBill",method = RequestMethod.GET)
    public R<List<OrderBill>> viewOrderBill() {
        QueryWrapper<OrderBill> wrapper = new QueryWrapper<>();
        List<OrderBill> list = orderBillMapper.selectList(wrapper);
        List<OrderBill> orderBills = new ArrayList<>();
        Set<Object> set = new HashSet<>();
        for (OrderBill item : list) {
            if (set.add(item.getFilmId())) {
                orderBills.add(item);
            } else {
                for (OrderBill cur : orderBills) {
                    if (item.getFilmId().equals(cur.getFilmId())) {
                        cur.setNumber(cur.getNumber()+item.getNumber());
                        cur.setTotalPrice(cur.getTotalPrice().add(item.getTotalPrice()));
                        break;
                    }
                }
            }
        }
        return R.build(SUCCESS,orderBills);
    }

    @LoginRequired
    @RequestMapping(value = "/updateOrderBill",method = RequestMethod.POST)
    public R<Boolean> updateOrderBill(@RequestBody OrderBill orderBill) {
        orderBillMapper.updateById(orderBill);
        return R.build(SUCCESS_WITH_NOTIFY,"提交成功");
    }


    @LoginRequired
    @RequestMapping(value = "/deleteOrderBill",method = RequestMethod.POST)
    public R<Boolean> deleteOrderBill(String id) {
        orderBillMapper.deleteById(id);
        return R.build(SUCCESS_WITH_NOTIFY,"删除成功");
    }


    @LoginRequired
    @RequestMapping(value = "/createOrderBill",method = RequestMethod.POST)
    public R<Boolean> createOrderBill(@RequestBody OrderBill orderBill) {
        Film film = filmMapper.selectById(orderBill.getFilmId());
        if (film.getTicket() + orderBill.getNumber() > film.getTotalVotes()) {
            return R.build(ERROR_WITH_NOTIFY,"余票不足");
        }
        orderBill.setId(CommonUtil.getSimpleUUID());
        orderBill.setCreateTime(CommonUtil.getSimpleDateTime());
        orderBill.setTotalPrice(orderBill.getTotalPrice().multiply(new BigDecimal(orderBill.getNumber())));
        film.setTicket(film.getTicket() + orderBill.getNumber());
        filmMapper.updateById(film);
        orderBillMapper.insert(orderBill);
        return R.build(SUCCESS_WITH_NOTIFY,"提交成功");
    }


}