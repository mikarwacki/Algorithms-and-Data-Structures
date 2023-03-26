import java.util.ArrayList;
import java.util.List;

public class KMPMatcher implements IStringMatcher {
    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        int n = textToSearch.length();
        int m = patternToFind.length();
        int[] pi = computePrefixFunction(patternToFind);
        int q = 0;
        List<Integer> appearances = new ArrayList<>();
        for(int i = 0; i < n; i++){
            while(q > 0 && patternToFind.charAt(q) != textToSearch.charAt(i)){
                q = pi[q];
            }
            if(patternToFind.charAt(q) == textToSearch.charAt(i)){
                q = q + 1;
            }
            if(q == m){
                int temp = i - m + 1;
                appearances.add(temp);
                q = pi[q - 1];
            }
        }
        return appearances;
    }

    private int[] computePrefixFunction(String pattern){
        int m = pattern.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int k = 0;
        for(int q = 2; q < m; q++){
            while(k > 0 && pattern.charAt(k + 1) != pattern.charAt(q)){
                k = pi[k];
            }
            if(pattern.charAt(k + 1) == pattern.charAt(q)){
                k++;
            }
            pi[q] = k;
        }
        return pi;
    }
}
