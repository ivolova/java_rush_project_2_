package service;


import com.fasterxml.jackson.databind.ObjectMapper;
import repository.config.InitConfig;
import repository.config.util.ConfigUtil;

import java.io.File;
import java.io.IOException;

public class ConfigService {

    public void initConfig(String pathname) throws IOException {
        ConfigUtil.saveConfig(pathname);
    }
}
