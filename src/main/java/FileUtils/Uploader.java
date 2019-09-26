package FileUtils;

import JsonUtils.JsonUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Uploader {

    private static final Logger LOG = Logger.getLogger(JsonUtils.class);

    public File getTestDataFromJson(String fileName) {
        final Path testDataJsonPath = Paths.get(
                getDataFolderAsString(),
                fileName
        );
        if (testDataJsonPath == null) {
            throw new IllegalArgumentException("Путь до json файла с тестовыми данными не может быть null");
        }
        if (!testDataJsonPath.toFile().exists()) {
            throw new UncheckedFileNotFoundException("Не могу найти файл по указанному пути "
                    + testDataJsonPath.toAbsolutePath().toString());
        }
        return testDataJsonPath.toFile();
    }

    public File getTestDataFromJson() {
        return getTestDataFromJson("importTestData.json");
    }

    protected Path getTestDataFolder() {
        return Paths.get(
                getCurrentWorkingDirectory().toAbsolutePath().toString(),
                "migration"
        );
    }

    protected String getDataFolderAsString() {
        return getTestDataFolder().toAbsolutePath().toString();
    }

}
