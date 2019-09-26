package JsonUtils;

import Errors.AutotestError;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.apache.log4j.Logger;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

import static org.apache.log4j.Logger.getLogger;

public final class JsonUtils {

    private static final Logger LOG = getLogger(JsonUtils.class);

    private JsonUtils() {
        throw new IllegalStateException("Do not instance");
    }

    public static String jsonStringFromObject(Object value) {
        return jsonStringFromObject(value, false);
    }

    /**
     * Конвертация объекта в JSON
     *
     * @param value     - Object
     * @param skipNulls - пропускать ключи с пустыми значениями
     * @return String
     */
    public static String jsonStringFromObject(Object value, boolean skipNulls) {
        ObjectMapper mapper = new ObjectMapper();
        if (skipNulls) {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new AutotestError("Ошибка конвертации объекта в JSON", e);
        }
    }

    public static <T> T jsonObjectFromString(String content, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            throw new AutotestError("Ошибка конвертации объекта в JSON", e);
        }
    }

    /**
     * Загружаем данные из внутреннего JSON файла
     *
     * @param filePath - путь к файлу
     * @return JsonPath
     */
    public static JsonPath internalJson(String filePath) {
        LOG.info("Загружаем данные из внутреннего JSON файла '{}'", filePath);
        return JsonPath.given(new InputStreamReader(JsonUtils.class.getClassLoader().getResourceAsStream(filePath)));
    }

    /**
     * Загрузка данных из внутреннего JSON файла как строки
     *
     * @param filePath - путь к файлу
     * @return String
     */
    public static String internalJsonAsString(String filePath) {
        LOG.info("Загружаем данные из внутреннего JSON файла '{}'", filePath);
        try {
            return IOUtils.toString(new InputStreamReader(JsonUtils.class.getClassLoader().getResourceAsStream(filePath)));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static JsonObject getPathAsJsonObject() {
        if (testData.getPath() == null) {
            return null;
        }
        return (JsonObject) new JsonParser().parse(testData.getPath().prettify());
    }

    public static <T> T getPathAsJsonObject(String json, String path) {
        JsonPath jsonPath = JsonPath.given(json);
        return jsonPath.get(path);
    }

    public static <T> T getPathAsStringObject(String json, String path) {
        JsonPath jsonPath = JsonPath.given(json);
        return (T) jsonPath.getString(path);
    }

    public static <T> T get(String key) {
        return testData.getPath().get(key);
    }

}
