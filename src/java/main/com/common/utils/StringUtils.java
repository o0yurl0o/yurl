package com.common.utils;

/**
 * Created by yurl on 2017/12/12.
 */
public class StringUtils {

    public static final String EMPTY = "";

    public static String join(String split, String... characters) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < characters.length; i++) {
            stringBuffer.append(characters[i]);
            if (i < characters.length - 1) {
                stringBuffer.append(split);
            }
        }
        return stringBuffer.toString();
    }

    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    public static String trimSQL(String sql) {
        return trimSql(sql, 1100);
    }

    public static String trimSql(String sql, int length) {
        StringBuffer sb = new StringBuffer();
        if (sql.length() > length) {
            sb.append(sql.substring(0, 500));
            sb.append("...");
            sb.append(sql.substring(sql.length() - 500, sql.length()));
            return sb.toString();
        } else {
            return sql;
        }
    }


    public static boolean isChineseCharacter(char c) {
        Character.UnicodeBlock var1 = Character.UnicodeBlock.of(c);
        return var1 == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || var1 == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || var1 == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || var1 == Character.UnicodeBlock.GENERAL_PUNCTUATION || var1 == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || var1 == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }
}
