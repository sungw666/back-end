package top.ptcc9.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.ptcc9.entity.Comment;


@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
}