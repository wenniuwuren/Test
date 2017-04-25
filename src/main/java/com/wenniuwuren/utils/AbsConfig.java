package com.wenniuwuren.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自动重载配置文件
 *
 */
public abstract class AbsConfig {
    private String fileName;

    private boolean reload;

    public AbsConfig(String fileName, boolean reload) {
        this.fileName = fileName;
        this.reload = reload;
    }

    public String getString(String key) {
        FileConfigWatch fcw = FileConfigWatch.getFileConfigWatch(this.fileName,
            this.reload);

        return fcw.getValue(key);
    }

    public String getString(String key, String defau) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return value;
    }

    public int getInt(String key, int defau) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return Integer.valueOf(value);
    }

    public long getLong(String key, long defau) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return Long.valueOf(value);
    }

    public boolean getBoolean(String key, boolean defau) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return Boolean.valueOf(value);
    }

    public Date getDate(String key, Date defau) {
        String value = getString(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
        } catch (ParseException e) {}
        return defau;
    }
}