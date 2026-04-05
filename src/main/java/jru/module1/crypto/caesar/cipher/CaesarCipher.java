package jru.module1.crypto.caesar.cipher;

import jru.module1.crypto.caesar.alphabets.RuAlphabet;

public class CaesarCipher {
    private final RuAlphabet ruAlphabet;

    public CaesarCipher(RuAlphabet ruAlphabet) {
        this.ruAlphabet = ruAlphabet;
    }

    /** Алгоритм шифрования
     *
     * @param text исходный текст
     * @param key ключ шифрования
     * @return зашифрованный текст
     */
    public String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        int alphabetSize = ruAlphabet.getSize();

        for (int i = 0; i < text.length(); i++) {
            char ch = Character.toLowerCase(text.charAt(i));

            int indexOriginalChar = ruAlphabet.getIndexByChar(ch);

            int newIndexChar = (indexOriginalChar + key) % alphabetSize;
            if (newIndexChar < 0) {
                newIndexChar = newIndexChar + alphabetSize;
            }

            char newChar = ruAlphabet.getCharByIndex(newIndexChar);
            result.append(newChar);
        }

        return result.toString();
    }

    /** Алгоритм дешифрования
     *
     * @param text зашифрованный текст
     * @param key ключ дешифрования
     * @return расшифрованный текст
     */
    public String decrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        int alphabetSize = ruAlphabet.getSize();

        for (int i = 0; i < text.length(); i++) {
            char ch = Character.toLowerCase(text.charAt(i));

            if (!ruAlphabet.contains(ch)) {
                result.append(ch);
                continue;
            }

            int indexOriginalChar = ruAlphabet.getIndexByChar(ch);

            int newIndexChar = (indexOriginalChar - key) % alphabetSize;
            if (newIndexChar < 0) {
                newIndexChar = newIndexChar + alphabetSize;
            }

            char newChar = ruAlphabet.getCharByIndex(newIndexChar);
            result.append(newChar);
        }

        return result.toString();
    }
}
