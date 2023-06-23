package top.ptcc9.entity;

import top.ptcc9.annotations.FieldsDes;

import java.math.BigDecimal;

/**
 * @Author PU GUO QING
 * @Description TODO
 * @Date 2023-04-28 11:25:15
 */
public class Film {

    private String id;

    @FieldsDes(desc = "剧院名称")
    private String theaterName;

    @FieldsDes(desc = "电影名称")
    private String name;

    @FieldsDes(desc = "上映时间")
    private String startTime;

    @FieldsDes(desc = "已售")
    private Integer ticket;

    @FieldsDes(desc = "总票")
    private Integer totalVotes;

    @FieldsDes(desc = "票价")
    private BigDecimal price;

    @FieldsDes(desc = "宣传照")
    private String picture;

    @FieldsDes(desc = "场次")
    private String count;

    private String content;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
