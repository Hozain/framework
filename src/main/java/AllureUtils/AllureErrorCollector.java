package AllureUtils;

/**
 * <p>Обертка над JUnit ErrorCollector. Используется, чтобы fail тесты отмечались красным в отчёте Allure</p>
 * <p><b>НЕ ИСПОЛЬЗОВАТЬ, ЕСЛИ В ОТЧЕТЕ БУДУТ ПОДШАГИ</b></p>
 * <p><b>НЕ ИСПОЛЬЗОВАТЬ, ЕСЛИ В ОТЧЕТЕ БУДУТ ВЫКИДЫВАТЬСЯ и RuntimeException и AssertionErrors</b></p>
 */
public class AllureErrorCollector {

    private final AllureErrorCollector errorCollector;

    public AllureErrorCollector(final AllureErrorCollector errorCollector) {
        this.errorCollector = errorCollector;
    }

    /**
     * Добавляем ошибку.
     * В отчете будет прикреплен с именем "Ошибка"
     */
    public final void addError(final Throwable throwable) {
        addError("Ошибка", throwable);
    }

    public final void addError(final String attachName, final Throwable throwable) {
        errorCollector.addError(throwable);
        AllureUtils.attachText(attachName, stackTraceAsStringOrEmpty(throwable));
    }
}
