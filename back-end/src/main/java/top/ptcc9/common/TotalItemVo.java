package top.ptcc9.common;

import java.math.BigDecimal;

/**
 * @Author HE LONG CAN
 * @Description 用于合计展示
 * @Date 2022-05-22 20:37:55
 */
public class TotalItemVo {
    /* 列名 */
    private String name;
    private BigDecimal sum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
