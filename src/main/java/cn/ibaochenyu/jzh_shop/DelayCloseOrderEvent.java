package cn.ibaochenyu.jzh_shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DelayCloseOrderEvent {


    /**
     * 车次 ID
     */
    private String rtInsertId;

    private String rtInsertId2;

}
