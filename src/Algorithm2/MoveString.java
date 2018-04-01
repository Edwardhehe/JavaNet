package Algorithm2;

public class MoveString {
    /*
    给一个字符串，将字符前k个字符移动到尾部
    时间复杂度为：O(n)空间复杂度O(1)

    暴力求解的
    (x'y')'=YX
    三次循环遍历，时间复杂度为O(n),只开辟了临时的变量t，空间复杂度为O(1)
    时间复杂度为n，空间复杂度为1
    有可能越界，所以要取mod
     */
    public static String moveString(String s, int mid) {
        char[] slst = s.toCharArray();
        int len = slst.length;
        int m = mid % len;
        char t;
        int i = 0;
        int j = m - 1;
        while (j > i) {
            t = slst[i];
            slst[i] = slst[j];
            slst[j] = t;
            j--;
            i++;
        }

        i = m;
        j = len - 1;
        while (j > i) {
            t = slst[i];
            slst[i] = slst[j];
            slst[j] = t;
            j--;
            i++;
        }

        i = 0;
        j = len - 1;
        while (j > i) {
            t = slst[i];
            slst[i] = slst[j];
            slst[j] = t;
            j--;
            i++;
        }

        return new String(slst);


    }

    public static void main(String[] args) {
        System.out.println(moveString("dasfevcvrweqwe", 4));
    }


    /*
    给定字符串s[0..N-1],设计算法枚举S的全排列
    方法1：递归，首字符拿出来，后面的全排列，有N种子类型
    试件复杂度N！
    就是递归以前保证顺序不能变化
    先把第一个字符和要计算的那个字符交换。
     */

    /*
    如果元素有重复字符怎么做全排列？
    加一个循环判断，这个字符和前面的有没有出现过，没出现过就交换
     */

    /*
    做一个存储的数组，如果这个东西做过首字符，标记为1，flag数组 完成
     */

    /*
    非递归的算法
    步骤：后找，小大，交换 翻转
    1.向后早最后一个升序的
    2.在后面找比A[i]大的最小的
    3.让两者交换位置
    4.翻转S[i+1...N-1]

    可以将给定的字符串A[0…N-1]首先升序排序，
    然后依次调用next_permutation直到返回false，
    即完成了非递归的全排列算法。

    关键就是求前缀串和后缀串，找到相等的并且最大的，就写这个数字
     */

    /*
     给定一个长度为n的字符串S，如果存在一个
    字符串T，重复若干次T能够得到S，那么，
    S叫做周期串，T叫做S的一个周期。
     如：字符串abababab是周期串，abab、ab都
    是它的周期，其中，ab是它的最小周期。
     设计一个算法，计算S的最小周期。如果S不
    存在周期，返回空串。
    计算前缀后缀
     */

    /*
    最长回子串
    Manacher算法
     */
}
