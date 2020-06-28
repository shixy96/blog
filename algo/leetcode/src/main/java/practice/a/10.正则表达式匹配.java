package practice.a;/*
 * @lc app=leetcode.cn id=10 lang=java
 *
 * [10] 正则表达式匹配
 */

// @lc code=start
class Solution {
    public boolean isMatch(String string, String partten) {
        int stringLength = string.length();
        int patternLength = partten.length();

        boolean[][] f = new boolean[stringLength + 1][patternLength + 1];
        f[0][0] = true;
        for (int i = 0; i <= stringLength; ++i) {
            for (int j = 1; j <= patternLength; ++j) {
                if (partten.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(string, partten, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(string, partten, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[stringLength][patternLength];
    }

    public boolean matches(String string, String partten, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (partten.charAt(j - 1) == '.') {
            return true;
        }
        return string.charAt(i - 1) == partten.charAt(j - 1);
    }
}
// @lc code=end
