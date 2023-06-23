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
import java.util.*;
import org.springframework.web.multipart.MultipartFile;
import top.ptcc9.mapper.*;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class FilmController {
    @Resource
    private FilmMapper filmMapper;
    @Resource
    private JwtUtil jwtUtil;

    @LoginRequired
    @RequestMapping(value = "/viewFilmTable",method = RequestMethod.GET)
    public R<List<Film>> viewFilmTable(String name, String theaterName) {
        QueryWrapper<Film> wrapper = new QueryWrapper<>();
        if (!CommonUtil.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if (!CommonUtil.isEmpty(theaterName)) {
            wrapper.like("theater_name",theaterName);
        }
        List<Film> list = filmMapper.selectList(wrapper);

        for (Film item : list) {
            item.setTicket(item.getTotalVotes() - item.getTicket());
        }

        return R.build(SUCCESS,list);
    }

    @RequestMapping(value = "/viewFilm",method = RequestMethod.GET)
    public R<Page<Film>> viewFilm(String name, String theaterName,Integer current,Integer size) {
        QueryWrapper<Film> wrapper = new QueryWrapper<>();
        if (!CommonUtil.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if (!CommonUtil.isEmpty(theaterName)) {
            wrapper.like("theater_name",theaterName);
        }
        wrapper.orderByDesc("create_time");
        wrapper.ge("start_time",new Date());
        Page<Film> page = new Page<>(current,size);

        Page<Film> filmPage = filmMapper.selectPage(page, wrapper);

        for (Film item : filmPage.getRecords()) {
            item.setTicket(item.getTotalVotes() - item.getTicket());
        }
        return R.build(SUCCESS,filmPage);
    }

    @LoginRequired
    @RequestMapping(value = "/updateFilm",method = RequestMethod.POST)
    public R<Boolean> updateFilm(@RequestBody Film film) {
        filmMapper.updateById(film);
        return R.build(SUCCESS_WITH_NOTIFY,"提交成功");
    }


    @LoginRequired
    @RequestMapping(value = "/deleteFilm",method = RequestMethod.POST)
    public R<Boolean> deleteFilm(String id) {
        filmMapper.deleteById(id);
        return R.build(SUCCESS_WITH_NOTIFY,"删除成功");
    }


    @LoginRequired
    @RequestMapping(value = "/createFilm",method = RequestMethod.POST)
    public R<Boolean> createFilm(@RequestBody Film film) {
        film.setId(CommonUtil.getSimpleUUID());
        film.setCreateTime(CommonUtil.getSimpleDateTime());
        film.setTicket(0);
        filmMapper.insert(film);
        return R.build(SUCCESS_WITH_NOTIFY,"提交成功");
    }


}