package jru.module1.crypto.caesar.validator;

import jru.module1.crypto.caesar.exception.FileManagerException;

import java.nio.file.*;
import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    private static final List<String> FORBIDDEN_DIR_FILES = List.of(
            ".bash_history", ".bash_profile", ".bashrc", "etc", "proc", "System32", "Windows"
    );

    public void validateForReading(String fileName) {
        Path path = validatePath(fileName);

        if (!Files.exists(path)) {
            throw new FileManagerException("Файл по данному пути не найден: " + fileName);
        }
        if (Files.isDirectory(path)) {
            throw new FileManagerException("Указана директория, а не файл: " + fileName);
        }
        if (!Files.isReadable(path)) {
            throw new FileManagerException("Нет разрешения на чтение файла: " + fileName);
        }
    }

    public void validateForWriting(String fileName) {
        Path path = validatePath(fileName);

        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new FileManagerException("Нельзя писать в директорию: " + fileName);
            }
            if (!Files.isWritable(path)) {
                throw new FileManagerException("Нет разрешения на запись в файл " + fileName);
            }
        }
    }

    public Path validatePath(String filename) {
        String stringSeparator = Pattern.quote(FileSystems.getDefault().getSeparator());

        for (String str : filename.split(stringSeparator)) {
            if (FORBIDDEN_DIR_FILES.contains(str)) {
                throw new FileManagerException("Введенный путь содержит запрещенный фрагмент " + str);
            }
        }

        try {
            return Path.of(filename);
        } catch (InvalidPathException ex) {
            throw new FileManagerException("Недопустимый путь " + filename, ex);
        }
    }
}
