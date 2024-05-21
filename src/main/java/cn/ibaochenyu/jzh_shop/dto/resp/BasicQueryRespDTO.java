package cn.ibaochenyu.jzh_shop.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


//@Builder可以让你以下面显示的那样调用你的代码，来初始化你的实例对象：
//        Student.builder()
//        .sno( "001" )
//        .sname( "admin" )

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicQueryRespDTO {

   private String name;
//   private int sex;
   private String homeAddress;

    ////////
//    private int id;
//    private String workId;
//    private String name;
//    private Date happenTime;
//
//    private int productID;
//    private int count;
//    private int unitPrice;

    ////////

//    public BasicQueryRespDTO() {
//    }

    //private int allPrice;

}
