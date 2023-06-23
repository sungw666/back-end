package top.ptcc9.common;

import java.util.List;

/**
 * @Author HE LONG CAN
 * @Description 分页专用
 * @Date 2022-04-03 20:28:08
 */
public class PageVo<T> {
    private Integer pageIndex;
    private Integer total;
    private List<T> list;
    private List<TotalItemVo> totalList;

    public static <T> PageVo<T> build(Integer pageIndex, Integer total, List<T> list, List<TotalItemVo> totalItemVoList) {
        return new PageVo<>(pageIndex,total,list,totalItemVoList);
    }

    public static <T> PageVo<T> build(Integer pageIndex, Integer total, List<T> list) {
        return new PageVo<>(pageIndex,total,list,null);
    }

    public static <T> PageVo<T> buildEmpty() {
        return build(1,0,null,null);
    }

    private PageVo(Integer pageIndex, Integer total, List<T> list, List<TotalItemVo> totalItemVoList) {
        this.pageIndex = pageIndex;
        this.total = total;
        this.list = list;
        this.totalList = totalItemVoList;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<TotalItemVo> getTotalList() {
        return totalList;
    }

    public void setTotalList(List<TotalItemVo> totalList) {
        this.totalList = totalList;
    }
}
