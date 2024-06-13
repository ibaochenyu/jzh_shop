package cn.ibaochenyu.jzh_shop.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

@Data

public class debugParam<T> {
    public String debug1;
    public  String debug2;
    public T oriData;
}
