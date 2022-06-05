package com.cellulam.spring.test.utils;

import com.cellulam.core.enums.CodeDescriptionFeature;
import com.cellulam.core.enums.EnumUtils;
import com.cellulam.core.exceptions.SysException;
import com.cellulam.core.utils.UUIDUtils;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Slf4j
public final class TestUtils {
    private static final Set<String> EMPTY_IGNORE_FIELDS = Sets.newHashSet();

    public static final <T extends CodeDescriptionFeature> T randomEnum(Class<T> enumClass) {
        List<T> enums = EnumUtils.getEnums(enumClass);

        if (CollectionUtils.isEmpty(enums)) {
            throw new SysException("value is not supported enum");
        }

        return enums.get(new Random(System.currentTimeMillis()).nextInt(enums.size() - 1));
    }

    public static final void assertSameObject(Object except, Object object) throws Exception {
        assertSameObject(except, object, EMPTY_IGNORE_FIELDS);
    }

    public static final void assertSameObject(Object except, Object actual, Set<String> ignoreFields) throws Exception {

        if (except == null && actual == null) {
            return;
        }

        Class<?> tClass = except.getClass();

        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            if (ignoreFields.contains(field.getName())) {
                continue;
            }
            field.setAccessible(true);

            Object value = field.get(except);
            Assert.assertEquals(field.getName(), value, field.get(actual));
        }
    }

    public static final <T> T randomBean(Class<T> tClass) {
        return randomBean(tClass, 10);
    }

    public static final <T> T randomBean(Class<T> tClass, int stringLength) {
        return randomBean(tClass, stringLength, EMPTY_IGNORE_FIELDS);
    }

    public static final <T> T randomBean(Class<T> tClass, int stringLength, Set<String> ignoreFields) {

        T bean;

        try {
            bean = tClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new SysException(e);
        }

        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            if (ignoreFields.contains(field.getName())) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(bean, randomValue(field.getType(), stringLength));
            } catch (IllegalAccessException e) {
                throw new SysException(String.format("set field fail, tClass: %s, field: %s",
                        tClass.getName(),
                        field.getName()),
                        e);
            }
        }

        return bean;
    }

    public static final <T> T randomValue(List<T> list) {
        return list.get(RandomUtils.nextInt(0, list.size()));
    }

    public static Object randomValue(Class<?> tClass, int stringLength) {
        if (Integer.class.equals(tClass)) {
            return RandomUtils.nextInt();
        }
        if (Integer.TYPE.equals(tClass)) {
            return RandomUtils.nextInt();
        }
        if (Boolean.class.equals(tClass)) {
            return RandomUtils.nextBoolean();
        }
        if (Boolean.TYPE.equals(tClass)) {
            return RandomUtils.nextBoolean();
        }
        if (Short.class.equals(tClass)) {
            return (short) RandomUtils.nextInt(0, 100);
        }
        if (Short.TYPE.equals(tClass)) {
            return (short) RandomUtils.nextInt(0, 100);
        }
        if (Long.class.equals(tClass)) {
            return RandomUtils.nextLong();
        }
        if (Long.TYPE.equals(tClass)) {
            return RandomUtils.nextLong();
        }
        if (Float.class.equals(tClass)) {
            return RandomUtils.nextFloat();
        }
        if (Float.TYPE.equals(tClass)) {
            return RandomUtils.nextFloat();
        }
        if (Double.class.equals(tClass)) {
            return RandomUtils.nextDouble();
        }
        if (Double.TYPE.equals(tClass)) {
            return RandomUtils.nextDouble();
        }
        if (String.class.equals(tClass)) {
            return UUIDUtils.randomUUID32().substring(0, stringLength).toUpperCase();
        }
        if (Date.class.equals(tClass)) {
            return new Date();
        }
        if (LocalDateTime.class.equals(tClass)) {
            return LocalDateTime.now();
        }
        if (LocalDate.class.equals(tClass)) {
            return LocalDate.now();
        }
        return null;
    }

    public static <T> ResultMatcher dataJsonPath(int index, String expression, Matcher<T> matcher) {
        return MockMvcResultMatchers.jsonPath("$.data[" + index + "]." + expression, matcher);
    }

    public static JsonPathResultMatchers dataJsonPath(int index, String expression, Object... args) {
        return MockMvcResultMatchers.jsonPath("$.data[" + index + "]." + expression, args);
    }

    public static <T> ResultMatcher dataJsonPath(String expression, Matcher<T> matcher) {
        return MockMvcResultMatchers.jsonPath("$.data." + expression, matcher);
    }

    public static JsonPathResultMatchers dataJsonPath(String expression, Object... args) {
        return MockMvcResultMatchers.jsonPath("$.data." + expression, args);
    }

}
