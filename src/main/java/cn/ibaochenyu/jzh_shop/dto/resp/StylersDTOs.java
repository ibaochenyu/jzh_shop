package cn.ibaochenyu.jzh_shop.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StylersDTOs {


    private List<StylerDTO> listStylerDTOs;

}
