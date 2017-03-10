package com.bamboo.opensource.apache.langthree;

import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017/3/9.
 */
public class MyStyle extends ToStringStyle {

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        if (value instanceof Date) {
            value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
        }
        super.appendDetail(buffer, fieldName, value);
    }
}
