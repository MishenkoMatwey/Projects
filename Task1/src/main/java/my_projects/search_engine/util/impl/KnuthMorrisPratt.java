package my_projects.search_engine.util.impl;

import my_projects.search_engine.util.Algorithm;

import java.util.function.BiFunction;


public class KnuthMorrisPratt implements Algorithm {
    private BiFunction<Character, Character, Boolean> cmp;

    @Override
    public void createCmp(boolean caseSensitive) {
        this.cmp = caseSensitive ? Character::equals : (x, y) -> Character.toUpperCase(x) == Character.toUpperCase(y);
    }


    @Override
    public boolean findSubStr(String pattern, String text) {

        int[] lsp = computeLspTable(pattern);
        int j = 0;  // Number of chars matched in pattern
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && (!cmp.apply(text.charAt(i), pattern.charAt(j)))) {
                j = lsp[j - 1];
            }
            if (cmp.apply(text.charAt(i), pattern.charAt(j))) {
                j++;
                if (j == pattern.length())
                    return true;
            }
        }
        return false;
    }


    private int[] computeLspTable(String pattern) {
        int[] lsp = new int[pattern.length()];
        lsp[0] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int j = lsp[i - 1];
            while (j > 0 && (!cmp.apply(pattern.charAt(i), pattern.charAt(j))))
                j = lsp[j - 1];
            if (cmp.apply(pattern.charAt(i), pattern.charAt(j)))
                j++;
            lsp[i] = j;
        }
        return lsp;
    }

}
