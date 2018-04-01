package Algorithm1;

import java.util.Arrays;

public class EdittingDistance {
    /*
    计算字符串的编辑距离，是就是记过多少次编辑可以便捷成下一个字符串。
    可以使用lcs
    最后一个字母：
    最后一个字符：s[i],t[j]
    S+空白
    T+字符x：
    dp[i,j]=dp[i,j-1]+1;
    S+字符x
    T+字符Y
    dp[i,j]=dp[i-1,j-1]+(x==y?0:1);
    S+字符x
    T+空白
    dp[i,j]=dp[i-1,j]+1;
     */
    public static int getEditDis(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int lc1 = ch1.length;
        int lc2 = ch2.length;

        int[][] matRes = new int[lc1 + 1][lc2 + 1];
        for (int i = 0; i <= lc1; i++) {
            matRes[i][0] = i;
        }
        for (int i = 0; i <= lc2; i++) {
            matRes[0][i] = i;
        }

        screenMat(matRes);
        System.out.println();
        for (int i = 1; i <= lc1; i++) {
            for (int j = 1; j <= lc2; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    matRes[i][j] = matRes[i - 1][j - 1];
                } else {
                    matRes[i][j] = getMin(matRes[i][j - 1], matRes[i - 1][j], matRes[i - 1][j - 1]) + 1;
                }
            }

        }
        screenMat(matRes);
        return matRes[lc1 - 1][lc2 - 1];
    }

    private static int getMin(int... args) {
        int min = Integer.MAX_VALUE;
        for (int num : args) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public static void screenMat(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.println(Arrays.toString(mat[i]));
        }
    }

    /*
    最长公共子串：
    如果相等：
    S(i,j)=S(i-1,j-1)+1；
    不相等：
    S(i,j)=S(i-1,j-1)；
     */
    public static int getLongSubString(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int lc1 = ch1.length;
        int lc2 = ch2.length;

        int[][] matRes = new int[lc1][lc2];
        for (int i = 0; i < lc1; i++) {
            if (ch1[i] == ch2[0]) {
                matRes[i][0] = 1;
            }
        }
        for (int i = 0; i < lc2; i++) {
            if (ch2[i] == ch1[0]) {
                matRes[0][i] = 1;
            }
        }
        int max = 0;
        screenMat(matRes);

        for (int i = 1; i < lc1; i++) {
            for (int j = 1; j < lc2; j++) {
                if (ch1[i] == ch2[j]) {
                    matRes[i][j] = matRes[i - 1][j - 1] + 1;
                    if (matRes[i][j] > max) {
                        max = matRes[i][j];
                    }
                }
            }
        }

        return max;

    }

    public static void main(String[] args) {
        String s1 = "kit1en";
        String s2 = "sitten";
        System.out.println(getEditDis(s1, s2));
        System.out.println(getLongSubString(s1, s2));
    }
}
