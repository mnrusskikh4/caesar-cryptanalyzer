package jru.module1.crypto.caesar;

import jru.module1.crypto.caesar.cipher.CaesarCipher;
import jru.module1.crypto.caesar.exception.FileManagerException;
import jru.module1.crypto.caesar.alphabets.RuAlphabet;
import jru.module1.crypto.caesar.validator.Validator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CaesarService {
    FileManager fileManager;
    Validator validator;
    CaesarCipher caesarCipher;
    RuAlphabet ruAlphabet;

    public CaesarService() {
        this.fileManager = new FileManager();
        this.validator = new Validator();
        this.ruAlphabet = new RuAlphabet();
        this.caesarCipher = new CaesarCipher(ruAlphabet);
    }

    public void encrypt(String inputFile, String outputFile, int key, boolean decryptFlag) {
        process(inputFile, outputFile, key, decryptFlag);
    }

    public void decrypt(String inputFile, String outputFile, int key, boolean decryptFlag) {
        process(inputFile, outputFile, key, decryptFlag);
    }

    private void process(String inputFile, String outputFile, int key, boolean decryptFlag) {
        validator.validateForReading(inputFile);
        validator.validateForWriting(outputFile);

        int validKey = getValidKey(key);

        List<String> readLines = fileManager.readFile(inputFile);
        List<String> resultList = new ArrayList<>();

        for (String string : readLines) {
            if (!decryptFlag) {
                resultList.add(caesarCipher.encrypt(string, validKey));
            } else {
                resultList.add(caesarCipher.decrypt(string, validKey));
            }
        }

        try (BufferedWriter bufferedWriter = fileManager.openWriter(outputFile)) {
            for (String string : resultList) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            throw new FileManagerException("Произошла ошибка записи в файл " + outputFile + ex);
        }
    }

    /**
     * Защита от ключей больше размера алфавита или отрицательных
     */
    private int getValidKey(int key) {
        int size = ruAlphabet.getSize();
        int newKey = key % size;

        if (newKey < 0) {
            newKey = newKey + size;
        }

        return newKey;
    }
}