package com.wenniuwuren.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件重载工具
 *
 */
public class FileConfigWatch extends Thread {
    private static Logger logger = LoggerFactory
        .getLogger(FileConfigWatch.class);

    public static final long DEFAULT_DELAY = 60000L;

    private static String CONFIG_DIR = null;

    private static final Map<String, FileConfigWatch> watchMap = new ConcurrentHashMap<>();

    private String filename;

    private long delay = 60000L;

    private Resource file;

    private long lastModif = 0L;

    private boolean warnedAlready = false;

    private boolean interrupted = false;

    private Properties prop;

    public static void setConfigDir(String dir) {
        CONFIG_DIR = dir;
    }

    public static String getConfigDir() {
        return CONFIG_DIR;
    }

    public FileConfigWatch(String filename, boolean reload) {
        this.filename = filename;
        this.file = new ClassPathResource(filename);
        checkAndConfigure();

        if (reload) {
            setDaemon(true);
            start();
        }
    }

    public static FileConfigWatch getFileConfigWatch(String filename,
        boolean reload) {
        if (CONFIG_DIR != null) {
            filename = CONFIG_DIR + filename;
        }
        FileConfigWatch fcw = watchMap.get(filename);
        if (fcw == null) {
            synchronized (watchMap) {
                fcw = watchMap.get(filename);
                if (fcw == null) {
                    fcw = new FileConfigWatch(filename, reload);
                    watchMap.put(filename, fcw);
                }
            }
        }
        return fcw;
    }

    public String getValue(String key) {
        if (this.prop == null) {
            logger.warn("config file not loaded, can't get its value {}",
                this.filename);

            return null;
        }
        return this.prop.getProperty(key);
    }

    public String getValue(String key, String defau) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return value;
    }

    public int getInt(String key, int defau) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defau;
        }
        return Integer.valueOf(value);
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    private void doOnChange() {
        if (logger.isDebugEnabled()) {
            logger.debug("doOnChange");
        }
        Properties p = new Properties();
        InputStream in = null;
        try {
            String innerFile = file.getFile().getAbsolutePath();
            BufferedReader brIn = new BufferedReader(
                new InputStreamReader(new FileInputStream(innerFile), "UTF-8"));
            p.load(brIn);
            this.prop = p;
            if (logger.isDebugEnabled()) {
                logger.debug("Properties:" + JSON.toJSON(p));
            }
            logger.info("load config file success:{}", this.filename);
        } catch (IOException e) {
            logger.error("load config file fail:{}", this.filename, e);
        } finally {
            logger.info("load config file finally:{}", this.filename);
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {}
        }
    }

    private void checkAndConfigure() {
        if (logger.isDebugEnabled()) {
            logger.debug("check and configure");
        }
        boolean fileExists;
        try {
            fileExists = this.file.exists();
        } catch (SecurityException e) {
            logger.warn(
                "Was not allowed to read check file existance, file:[{}].",
                this.filename);

            this.interrupted = true;
            return;
        }
        if (!fileExists) {
            this.filename = FilenameUtils.getName(this.filename);
            try {
                URL u = Thread.currentThread().getContextClassLoader()
                    .getResource(this.filename);

                if (u != null) {
                    this.file = new FileSystemResource(new File(u.toURI()));
                    fileExists = this.file.exists();
                }
            } catch (Exception e) {
                logger.error("check file exist fail through classloader:{}",
                    this.filename, e);
            }
        }

        if (!fileExists) {
            try {
                Properties p = new Properties();
                p.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(this.filename));

                this.prop = p;
                logger.info("load config file success through classloader:{}",
                    this.filename);
            } catch (Exception e) {
                logger.error("load config file fail through classloader:{}",
                    this.filename, e);
            }

        }

        if (fileExists) {
            try {
                if (logger.isDebugEnabled()) {
                    logger
                        .debug("fileExist:" + file.getFile().getAbsolutePath());
                }
                long l = this.file.lastModified();
                if (l > this.lastModif) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("file change, lastModif={}, l={}",
                            lastModif, l);
                    }
                    this.lastModif = l;
                    doOnChange();
                    this.warnedAlready = false;
                } else {
                    if (logger.isDebugEnabled()) {
                        logger.debug("file not change, lastModif={}, l={}",
                            lastModif, l);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (!this.warnedAlready) {
            logger.debug("[{}] does not exist.", this.filename);
            this.warnedAlready = true;
        }
    }

    public void run() {
        logger.info("start reload config file={}, interrupt={}", this.filename,
            this.interrupted);
        while (!this.interrupted)
            try {
                logger.info("reload config file={}", this.filename);
                Thread.sleep(this.delay);
                checkAndConfigure();
            } catch (InterruptedException e) {
                logger.error("interrupted error:{}", this.filename, e);
            } catch (Exception e) {
                logger.error("checkAndConfigure error:{}", this.filename, e);
            }
    }
}