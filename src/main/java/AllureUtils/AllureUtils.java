package AllureUtils;

public class AllureUtils {

    private AllureUtils() {

    }

    /**
     * Прикрепляем файл к отчёту
     *
     * @param attachName Название файла
     * @param attachBody Тело файла
     * @param mimeType   mime тип
     */
    public static void attach(String attachName, String attachBody, String mimeType) {
        Allure.LIFECYCLE.fire(new MakeAttachmentEvent(attachBody.getBytes(), attachName, mimeType));
    }

    /**
     * Прикрепляем JSON к отчёту
     *
     * @param attachName Название файла
     * @param attachBody Тело файла
     */
    public static void attachJson(String attachName, String attachBody) {
        attach(attachName, attachBody, "application/json");
    }

    /**
     * Прикрепляем XML к отчёту
     *
     * @param attachName Название файла
     * @param attachBody Тело файла
     */
    public static void attachXml(String attachName, String attachBody) {
        attach(attachName, attachBody, "application/xml");
    }

    /**
     * Прикрепляем текст к отчёту
     *
     * @param attachName Название файла
     * @param attachBody Тело файла
     */
    public static void attachText(String attachName, String attachBody) {
        attach(attachName, attachBody, "text/plain");
    }

    /**
     * Прикрепляем текст к отчёту с именем Trace
     *
     * @param attachBody Тело файла
     */
    public static void attachTrace(String attachBody) {
        attachText("Trace", attachBody);
    }

    /**
     * Прикрепляем к отчёту лог сервера
     *
     * @param attachBody Тело файла
     */
    public static void attachServerLog(String attachBody) {
        attachText("Server Log", attachBody);
    }

}
