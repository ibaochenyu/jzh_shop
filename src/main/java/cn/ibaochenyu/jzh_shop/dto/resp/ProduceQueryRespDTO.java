package cn.ibaochenyu.jzh_shop.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduceQueryRespDTO {
//    private int id;
//    private String date;
//    private int truth_item_id;
//    private int truth_worker_id;
//    private int item_count;
//    private int unit_price;
private Date produceDate;
    private int truthItemId;
    private int truthWorkerId;

    private int produceCount;



}
