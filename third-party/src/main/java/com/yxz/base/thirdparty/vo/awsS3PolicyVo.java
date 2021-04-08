package com.yxz.base.thirdparty.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class awsS3PolicyVo {

   private Date expiration;
   private List<awsS3PolicyConditionsVo> conditions;
   public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }
    public Date getExpiration() {
        return expiration;
    }

   public void setConditions(List<awsS3PolicyConditionsVo> conditions) {
        this.conditions = conditions;
    }
    public List<awsS3PolicyConditionsVo> getConditions() {
        return conditions;
    }

}