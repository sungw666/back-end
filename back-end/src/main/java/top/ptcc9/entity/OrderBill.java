package top.ptcc9.entity;

import top.ptcc9.annotations.FieldsDes;

import java.math.BigDecimal;

/**
 * @Author PU GUO QING
 * @Description TODO
 * @Date 2023-05-18 14:07:04
 */
public class OrderBill {
    private String id;

    @FieldsDes(desc = "电影名称")
    private String filmName;

    private String filmId;

    private String count;

    @FieldsDes(desc = "放映时间")
    private String startTime;
    @FieldsDes(desc = "购票数量")
    private Integer number;
    @FieldsDes(desc = "金额")
    private BigDecimal totalPrice;

    private String userId;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
