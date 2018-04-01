package Algorithm2;

import java.util.Arrays;

public class KMPAlgorithm {
    /*
    如果失配，即text[i+j]≠pattern[j]，令i不变，j=next[j]，
    (这里，next[j]≤j-1)，即模式串pattern相对于文本串text
    向右移动了至少1位(移动的实际位数j-next[j]≥1)
     */
    public static int[] getKMPNext(char[] str) {
        int len = str.length;
        int k = -1;
        int j = 0;
        int[] next = new int[len];
        next[0] = k;
        while (j < len - 1) {
            if (k == -1 || str[k] == str[j + 1]) {
                k++;
                j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public static int KMPMethod(char[] tar, char[] pat) {
        int m = tar.length;
        int n = pat.length;

        int[] next = getKMPNext(pat);
        int j = 0;
        int i = 0;
        while (i < m) {

            if (j == -1 || tar[i] == pat[j]) {
                //回溯到第一个都配不上，或者配上了，那就j++ i++,
                //j++是因为j现在指向-1
                i++;
                j++;
            } else {
                j = next[j];
            }

            if (j == n) {
                return i - n;

            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String str1 = "bacbababaabcbab";
        char[] tar = str1.toCharArray();
        String pattern = "ababa";
        char[] pat = pattern.toCharArray();

        System.out.println(Arrays.toString(getKMPNext(pat)));
        System.out.println(KMPMethod(tar, pat));
    }

}
