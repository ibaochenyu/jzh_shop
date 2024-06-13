package cn.ibaochenyu.jzh_shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPurchaseRespDTO {
    private int rtUpdateCnt;
    private Long changeId;
}
