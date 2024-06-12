package cn.ibaochenyu.jzh_shop.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StylerDTOForUser {
    private String FactoryNameForUser;
    private Long truthStylerId;
    private Integer wantProductCount;
}
