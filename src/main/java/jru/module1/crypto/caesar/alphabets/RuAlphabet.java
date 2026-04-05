package jru.module1.crypto.caesar.alphabets;

import jru.module1.crypto.caesar.exception.AlphabetException;

import java.util.*;

public class RuAlphabet {

    private static final Character[] RU_CHARACTERS = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м',
            'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ',
            'ы', 'ь', 'э', 'ю', 'я'
    };

    private static final Character[] SYMBOLS = {
            '.', ',', '«', '»', '"', '\'', ':', '-', '!', '?', ' '
    };

    private final List<Character> characterList;
    private final Map<Character, Integer> characterIndexMap;

    public RuAlphabet() {

        this.characterList = new ArrayList<>();
        characterList.addAll(Arrays.asList(RU_CHARACTERS));
        characterList.addAll(Arrays.asList(SYMBOLS));

        this.characterIndexMap = new HashMap<>();

        for (int i = 0; i < characterList.size(); i++) {
            characterIndexMap.put(characterList.get(i), i);
        }
    }

    public int getSize() {
        return characterList.size();
    }

    public Character getCharByIndex(int index) {
        if (index < 0 || index >= characterList.size()) {
            throw new AlphabetException("Индекс вне диапазона алфавита: " + index);
        }
        return characterList.get(index);
    }

    public int getIndexByChar(Character character) {
        Integer index = characterIndexMap.get(character);

        if (index == null) {
            throw new AlphabetException("Символ отсутствует в алфавите: " + character);
        }

        return index;
    }

    public boolean contains(Character character) {
        return characterIndexMap.containsKey(character);
    }
}
