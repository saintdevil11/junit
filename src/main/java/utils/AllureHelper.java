package utils;

import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Это вспомогательный класс чтобы выводить лог и в терминал и в аллюр
*/
public class AllureHelper {
    private static final Logger log = LoggerFactory.getLogger(AllureHelper.class);

    public static void logToAllure(String contName, String value){
        Allure.addAttachment(contName, value);
        log.info(contName);
        log.info(value);
    }
}
