package top.ptcc9.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import top.ptcc9.annotations.LoginRequired;
import top.ptcc9.common.R;
import top.ptcc9.entity.Comment;
import top.ptcc9.entity.CommentVo;
import top.ptcc9.mapper.CommentMapper;
import top.ptcc9.utils.CommonUtil;
import top.ptcc9.utils.JwtUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static top.ptcc9.common.State.SUCCESS;
import static top.ptcc9.common.State.SUCCESS_WITH_NOTIFY;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class CommentController {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private JwtUtil jwtUtil;

    @LoginRequired
    @RequestMapping(value = "/viewCommentTable",method = RequestMethod.GET)
    public R<List<CommentVo>> viewCommentTable(String filmName, String filmId, String userId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        if (!CommonUtil.isEmpty(filmName)) {
            wrapper.like("film_name",filmName);
        }
        if (!CommonUtil.isEmpty(filmId)) {
            wrapper.like("film_id",filmId);
        }
        if (!CommonUtil.isEmpty(userId)) {
            wrapper.like("user_id",userId);
        }
        wrapper.orderByDesc("create_time");
        List<Comment> commentList = commentMapper.selectList(wrapper);

        List<CommentVo> resultList = new ArrayList<>();
        for (int i = 0; i < commentList.size(); i++) {
            Comment comment = commentList.get(i);
            CommentVo commentVo = new CommentVo();
            BeanUtil.copyProperties(comment,commentVo);
            commentVo.setNo(String.valueOf(i + 1));
            resultList.add(commentVo);
        }
        return R.build(SUCCESS,resultList);
    }


    @LoginRequired
    @RequestMapping(value = "/updateComment",method = RequestMethod.POST)
    public R<Boolean> updateComment(@RequestBody Comment comment) {
        commentMapper.updateById(comment);
        return R.build(SUCCESS_WITH_NOTIFY,"提交成功");
    }


    @LoginRequired
    @RequestMapping(value = "/deleteComment",method = RequestMethod.POST)
    public R<Boolean> deleteComment(String id) {
        commentMapper.deleteById(id);
        return R.build(SUCCESS_WITH_NOTIFY,"删除成功");
    }


    @LoginRequired
    @RequestMapping(value = "/createComment",method = RequestMethod.POST)
    public R<Boolean> createComment(@RequestBody Comment comment) {
        comment.setId(CommonUtil.getSimpleUUID());
        comment.setCreateTime(CommonUtil.getSimpleDateTime());
        commentMapper.insert(comment);
        return R.build(SUCCESS_WITH_NOTIFY,"提交成功");
    }


}