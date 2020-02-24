package com.epam.osipchik.gym;

import com.epam.osipchik.gym.service.impl.ServiceFactory;
import com.epam.osipchik.gym.service.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        logger.info("hello logger te");
        logger.error("error logger te");

    }
}
