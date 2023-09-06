package com.sn.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class MaxSubString_230905 {

    /**
     * 滑动窗口
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> maxSub = new HashSet<>();
        int max = 0;
        int right = 0, left = 0;
        int length = s.length() - 1;
        while (right <= length) {
            if (length - right <= max)
                break;
            if (!maxSub.contains(s.charAt(right))) {//下一个字符串不在子串中出现
                maxSub.add(s.charAt(right));
                right++;
            } else {//当前字符已经在子串中重复，需要从当前子串中去除这个字符，并将left移至子串中不重复单前字符的位置
                maxSub.remove(s.charAt(left));
                left++;
            }
            max = Math.max(max, maxSub.size());
        }
        return max;
    }

    public int lengthOfLongestSubstringByMap(String s) {
        Map<Character, Integer> hashMap = new HashMap<>();
        int max = 0;
        int right = 0,  left = 0;
        int length = s.length() - 1;
        while (right <= length) {
            if (length - left < max)
                break;
            char c = s.charAt(right);
            if (hashMap.containsKey(c)) {//当前字符出现在map中代表重复，需要将左下标移至上一次出现该字符的后一个位置
                left =Math.max( left,hashMap.get(c) + 1);//防止左下标回退
            }  //当前字符不存在，移动右下标，并记录最大长度
                max = Math.max(max, right-left+1);
                hashMap.put(c,right);
               right++;


        }
        return max;
    }


    public static void main(String[] args) {
        MaxSubString_230905 maxSubString_230905 = new MaxSubString_230905();
        int i = maxSubString_230905.lengthOfLongestSubstringByMap(" ");
        System.out.println(i);
    }
}
