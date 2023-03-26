import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AutomatonMatcher implements IStringMatcher {
    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        int n = textToSearch.length();
        int m = patternToFind.length();
        int q = 1;
        HashMap<Character,Integer> map = generateMap();
        int[][] function = computeTransitionFunction(patternToFind);
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < n; i++){
            q = function[q][map.get(textToSearch.charAt(i))];
            if(q == m){
                // poprawne wystapienie wzorca w tekscie jest tylko gdy q jest rowne dlugosci wzroca,
                // jednak tabela jest indeksowana od 0, więc po wykryciu nalezy zmniejszyc q = q - 1
                q--;
                int temp = i - m + 1;
                list.add(temp);
            }
        }
        return list;
    }

    private int[][] computeTransitionFunction(String pattern){
        int m = pattern.length();
        int k = 0;
        int[][] function = new int[m][62];
        HashMap<Character,Integer> map = generateMap();
        char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for(int q = 0; q < m; q++){
            for(int i = 0; i < alphabet.length; i++){
                char letter = alphabet[i];
                k = Math.min(m + 1, q + 2);
                String temp = pattern.substring(0,q) + letter;
               do {
                    k = k - 1;
                } while(!temp.endsWith(pattern.substring(0,k)));
                function[q][map.get(letter)] = k;
            }
        }
        return function;
    }

    private HashMap<Character,Integer> generateMap(){
        HashMap<Character,Integer> map = new HashMap<>();
        int counter = 0;
        char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for(int i = 0; i < alphabet.length; i++){
            char letter = alphabet[i];
            map.put(letter,counter);
            counter++;
        }
        return map;
    }
}
