package top.ptcc9.exceptions;

import cn.hutool.crypto.CryptoException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.ptcc9.common.R;
import top.ptcc9.common.R;
import top.ptcc9.utils.CommonUtil;

import java.net.UnknownHostException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static top.ptcc9.common.State.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(code = HttpStatus.OK)
    public R<List<String>> handleException(RuntimeException e) {
        e.printStackTrace();
        return R.build(ERROR_WITH_NOTIFY,"系统错误");
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.OK)
    public R<List<String>> handleBeanValidationException(MethodArgumentNotValidException e) {
        return R.build(ERROR_ARGS,resolveFieldErrors(e.getBindingResult().getFieldErrors()));
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(code = HttpStatus.OK)
    public R<List<String>> handleBeanValidationException(BindException e) {
        ERROR_ARGS.setMessage(resolveFieldErrors(e.getBindingResult().getFieldErrors()));
        return R.build(ERROR_ARGS);
    }

    private String resolveFieldErrors(List<FieldError> fieldErrors) {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (FieldError error : fieldErrors) {
            String str = "期望 [ " +
                    error.getDefaultMessage() +
                    " ] 实际 [ " +
                    error.getRejectedValue() + " ]";
            builder.append(str);
            if (index < fieldErrors.size() - 1) {
                builder.append(", ");
            }
            index++;
        }
        return String.valueOf(builder);
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> invalidTokenException(InvalidTokenException e) {
        return R.build(ERROR_TOKEN,e.getMessage());
    }

    @ExceptionHandler(UnknownHostException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> unKnownHostException(UnknownHostException e) {
        return R.build(ERROR_IP_ADDRESS,e.getMessage());
    }

    @ExceptionHandler(NoAuthException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> noAuthException(NoAuthException e) {
        return R.build(ERROR_AUTH,e.getMessage());
    }

    @ExceptionHandler(IllegalParameterException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> illegalParameterException(IllegalParameterException e) {
        return R.build(ERROR_ARGS, CommonUtil.isEmpty(e.getMessage()) ? "参数错误" : e.getMessage());
    }

    @ExceptionHandler(EnumParsingException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> enumParsingException(EnumParsingException e) {
        return R.build(ERROR_ENUM);
    }

    @ExceptionHandler(UserBannedException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> userBannedException(UserBannedException e) {
        return R.build(ERROR_BANNED);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> duplicateKeyException(DuplicateKeyException e) {
        return R.build(ERROR_INSERT_DUPLICATION_KEY);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        return R.build(ERROR_INSERT_NULL_FIELDS);
    }

    @ExceptionHandler(GlobalConfigRefreshFailedException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> globalConfigRefreshFailedException(GlobalConfigRefreshFailedException e) {
        return R.build(ERROR_REFRESH_GLOBAL_CONFIG);
    }

    @ExceptionHandler(SmsSendErrorException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> smsSendErrorException(SmsSendErrorException e) {
        return R.build(ERROR_SMS_SEND);
    }

    @ExceptionHandler(HttpEncryptException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> httpEncryptException(HttpEncryptException e) {
        return R.build(ERROR_ENCRYPT_SECRET_KEY);
    }


    @ExceptionHandler(CryptoException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> httpEncryptException(CryptoException e) {
        return R.build(ERROR_INVALID_CIPHER_TEXT);
    }

    @ExceptionHandler(MulLoginException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> mulLoginException(MulLoginException e) {
        return R.build(ERROR_MUL_LOGIN);
    }

    @ExceptionHandler(TokenExpireException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> tokenExpireException(TokenExpireException e) {
        return R.build(ERROR_TOKEN_EXPIRE);
    }

    @ExceptionHandler(LockMaxWaitingException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R<String> lockMaxWaitingException(LockMaxWaitingException e) {
        return R.build(LOCK_MAX_WAITING_TIME);
    }
}
