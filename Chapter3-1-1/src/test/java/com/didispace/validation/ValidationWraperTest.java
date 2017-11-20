package com.didispace.validation;

import com.didispace.entity.CouponUser;
import com.gitee.common.entity.ResultData;

import com.gitee.validation.ValidationWraper;
import org.junit.Test;

/**
 * Created by daiwei on 2017/11/17.
 */
public class ValidationWraperTest {
    ValidationWraper validationWraper=new ValidationWraper();
    @Test
    public void validate() throws Exception {
        CouponUser couponUser=new CouponUser("18428385839","daiwei");
        ResultData resultData = validationWraper.validate(couponUser);
        System.out.println(resultData.toString());
    }

}