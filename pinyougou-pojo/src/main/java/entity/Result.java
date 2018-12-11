package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/11/21$ 17:13$
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String message;

   /* public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }*/
}
