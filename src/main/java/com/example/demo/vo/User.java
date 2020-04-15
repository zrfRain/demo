package com.example.demo.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @ApiModelProperty(value="用户名")
    private String name;

    @ApiModelProperty(value="密码")
    private String pwd;

    private Integer id;
}
