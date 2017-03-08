package com.yinrong.gateway.validator;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.money.Money;
import com.yinrong.gateway.utils.Utils;
import org.springframework.util.Assert;

/**
 *
 * <p>公用校验器</p>
 * @author David Liu
 * @version $Id: CommonValidator.java 47623 2013-06-03 08:54:07Z fangjilue $
 */
public class CommonValidator {

    /**
     * 非空校验
     * @param value
     * @param propertyName
     */
    public void assertNotNull(Object value, String propertyName) {
        Assert.notNull(value, propertyName + "不能为空");
    }

    /**
     * 非空校验
     * @param value
     * @param propertyName
     */
    public void assertNotBlank(String value, String propertyName) {
        Assert.isTrue(StringUtil.isNotBlank(value), propertyName + "不能为空");
    }

    /**
     * 校验金额必须大于0
     * @param value
     * @param propertyName
     */
    public void assertGreeterZero(Money value, String propertyName) {
        if (value != null) {
            Assert.isTrue(value.compareTo(new Money("0.00")) > 0, propertyName + "必须大于零");
        }
    }

    /**
     * 校验金额必须大于等于0
     * @param value
     * @param propertyName
     */
    public void assertGreeterEqualZero(Money value, String propertyName) {
        if (value != null) {
            Assert.isTrue(value.compareTo(new Money("0.00")) >= 0, propertyName + "必须大于等于零");
        }
    }

    /**
     * 校验金额必须小于0
     * @param value
     * @param propertyName
     */
    public void assertLessZero(Money value, String propertyName) {
        if (value != null) {
            Assert.isTrue(value.compareTo(new Money("0.00")) < 0, propertyName + "必须小于零");
        }
    }

    /**
     * 校验字符串长度必须大于某一指定值
     * @param value
     * @param length GBK
     * @param propertyName
     */
    public void assertLengthGreeter(String value, int length, String propertyName) {
        if (value != null) {
            Assert.isTrue(Utils.getByteLen(value) > length, propertyName + "长度必须大于" + length + "位");
        }
    }

    /**
     * 校验字符串长度必须等于某一指定值
     * @param value
     * @param length GBK
     * @param propertyName
     */
    public void assertLengthEqual(String value, int length, String propertyName) {
        if (value != null) {
            Assert.isTrue(Utils.getByteLen(value) == length, propertyName + "长度必须等于" + length + "位");
        }
    }

    /**
     * 校验字符串长度必须小于某一指定值
     * @param value
     * @param length GBK
     * @param propertyName
     */
    public void assertLengthLess(String value, int length, String propertyName) {
        if (value != null) {
            Assert.isTrue(Utils.getByteLen(value) < length, propertyName + "长度必须小于" + length + "位");
        }
    }

    /**
     * 校验字符串长度必须小于或等于某一指定值
     * @param value
     * @param length GBK
     * @param propertyName
     */
    public void assertLengthLessEqual(String value, int length, String propertyName) {
        if (value != null) {
            Assert.isTrue(Utils.getByteLen(value) <= length, propertyName + "长度必须小于等于" + length + "位");
        }
    }
    
    /**
     * 校验字符串长度必须小于或等于某一指定值
     * @param value
     * @param length 字长度
     * @param propertyName
     */
    public void assertNatureLengthLessEqual(String value, int length, String propertyName) {
        if (value != null) {
            Assert.isTrue(value.length() <= length, propertyName + "长度必须小于等于" + length + "位");
        }
    }
    /**
     * 校验字符串长度等于某个长度或者为空
     * @param value
     * @param length GBK
     * @param propertyName
     */
    public void assertLengthEqualOrEmpty(String value, int length, String propertyName) {
        if (StringUtil.isNotEmpty(value)) {
            Assert.isTrue(value==null||Utils.getByteLen(value.trim()) ==0||Utils.getByteLen(value) == length, propertyName + "长度必须小于" + length + "位或者为空");
        }
    }
}
