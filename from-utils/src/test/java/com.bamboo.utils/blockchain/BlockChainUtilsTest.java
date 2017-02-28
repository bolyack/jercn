package com.bamboo.utils.blockchain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by admin on 2017/2/28.
 */
public class BlockChainUtilsTest {

    public static void main(String[] args) throws Exception {
        UserEntity entity = new UserEntity();
        entity.setId(1);
        entity.setName("boo");
        entity.setAge(27);
        entity.setGender(null);
        entity.setBorthDay(new Date());
        entity.setSalary(new BigDecimal(5000));
        entity.setAvgGrade(90);
        entity.setWeight(106.5);
        entity.setWalkCount(50928l);
        entity.setCourse(21);
        entity.setEnglishScore(95);

        String[] resStrArray = BlockChainUtils.parseObj2StringArray(entity, IgnoreAttr.class);
        for (String s : resStrArray) {
            System.out.print(s + ", ");
        }
    }

}
