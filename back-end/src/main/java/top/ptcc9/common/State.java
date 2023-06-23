package top.ptcc9.common;


public enum State {
    /**
     * 权限类错误
     */
    ERROR_TOKEN(4001,"未登录或登录态已失效,请重新登陆",5,"token失效"),
    ERROR_AUTH(4002,"无权限访问功能",3,"用户访问了超限接口"),
    ERROR_ARGS(4003,"错误的参数",4,"参数错误"),
    ERROR_ENUM(4004,"错误的状态",4,"枚举转换时失败"),
    ERROR_BANNED(4005,"这个用户已被禁用",5,"用户已被禁用"),
    ERROR_IP_ADDRESS(4006,"违法的IP地址,请重新登录",5,"无法获取其IP"),
    ERROR_REFRESH_GLOBAL_CONFIG(4007,"全局配置刷新异常",4,"刷新时反射执行失败，未找到对应setter方法"),
    ERROR_SMS_SEND(4008,"验证码发送失败",4,"具体请排查"),
    ERROR_INVALID_VERIFICATION_CODE(4009,"错误的验证码",4,"验证码错误"),
    ERROR_ENCRYPT_SECRET_KEY(4010,"无法解密，建议关闭浏览器重试",5,"缓存中无对称密钥"),
    ERROR_INVALID_CIPHER_TEXT(4011,"违法的密文",5,"违法密文"),
    ERROR_FREQUENT_OPERATION(4012,"操作频繁",1,"操作过于频繁"),
    ERROR_UPLOAD_FILE(4013,"上传文件时错误",4,"OSS错误"),
    ERROR_MUL_LOGIN(4014,"账号已在其他地址登录",5,"强制下线"),
    ERROR_TOKEN_EXPIRE(4015,"令牌已过期，请重新登录",5,"强制下线"),
    LOCK_MAX_WAITING_TIME(4016,"网络忙，请稍后重试",1,"并发时长时间无法获得锁时"),
    ERROR_MODIFIED(4017,"已被修改",4,"数据已被修改"),
    ERROR_IDEMPOTENCE(4018,"操作频繁",4,"接口幂等性"),
    ERROR_RUNTIME_EXCEPTION(4019,"系统错误",4,"接口幂等性"),
    //Frequent operation


    /**
     * 寻常类错误
     */
    ERROR_INVALID_ACCOUNT_PASSWORD(4201,"错误的账号或密码",4,null),
    ERROR(4202,"",0,null),
    ERROR_WITH_NOTIFY(4203,"",4,null),

    /**
     * 查询类
     */
    ERROR_NO_MATCH_ROWS(4204,"无数据",0,null),
    ERROR_NO_MATCH_ROW(4205,"未查询到此条信息",1,null),


    /**
     * 更新类
     */
    ERROR_UPDATE_NO_MATCH(4301,"更新时找不到此条信息",3,"更新时错误，where条件匹配0"),
    ERROR_UPDATE_DIFFERENT_ROWS_COUNT(4302,"修改数量与期望修改数量不符，已撤回操作",3,"修改数量与期望数量不符"),
    ERROR_UPDATE_CONCURRENT(4303,"此条数据已被修改，请确认",3,"更新时并发"),


    /**
     * 删除类
     */
    ERROR_DELETE_NO_MATCH(4401,"删除时找不到此条记录",4,null),
    ERROR_DELETE_DIFFERENT_ROWS_COUNT(4402,"删除数量与预期不符，已撤回操作",4,null),
    ERROR_DELETE_BANNED(4403,"",4,null),



    /**
     * 新增类
     */
    ERROR_INSERT_DUPLICATION_KEY(4501,"已有重复数据",4,null),
    ERROR_INSERT_DIFFERENT_ROWS_COUNT(4502,"新增数量与预期不符，已撤回操作",4,null),
    ERROR_INSERT_NULL_FIELDS(4503,"新增数据存在空值",4,null),



    /**
     * 寻常类成功
     */
    SUCCESS(2200,"操作成功",0,null),
    SUCCESS_WITH_NOTIFY(2205,"",2,null)
    ;

    /**
     * 0.  无提示
     * 1.  info 提示
     * 2.  success 提示
     * 3.  warning 提示
     * 4.  error 提示
     * 5.  error 提示 并 遣返回登录页
     */
    private Integer type;

    /**
     * 状态码
     */
    private Integer statusCode;

    /**
     * 提示给用户的 反馈
     */
    private String message;

    /**
     * 导致原因 - 此字段不返回前端 仅作排查
     */
    private String cause;

    private Integer code;

    State(Integer statusCode, String message, Integer type) {
        this(statusCode,message,type,null);
    }


    State(Integer statusCode, String message, Integer type, String cause) {
        this.statusCode = statusCode;
        this.message = message;
        this.type = type;
        this.code = (statusCode / 1000) * 100;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
