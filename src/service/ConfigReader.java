package service;


import com.fasterxml.jackson.databind.ObjectMapper;
import repository.config.InitConfig;
import repository.config.util.ConfigUtil;

import java.io.File;
import java.io.IOException;

public class ConfigReader {

    public void readFrom(String pathname) throws IOException {
        ObjectMapper readMapper = new ObjectMapper();
        InitConfig initConfig = readMapper.readValue(new File(pathname), InitConfig.class);
        //ConfigUtil.save(initConfig);
    }
}
