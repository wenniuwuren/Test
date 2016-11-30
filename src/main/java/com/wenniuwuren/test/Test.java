package com.wenniuwuren.test;

import javax.swing.tree.TreeNode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hzzhuyibin on 2016/8/29.
 */
public class Test {

    public static void main(String[] args) {
        //1472472480000
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(1474985018231l);
//        System.out.println(df.format(date));
//        Test.twoSum(new int[]{1,2,3,4,5,6,7,8}, 10);
//        System.out.println(Test.reverseString("hello"));
//        Test.fizzBuzz(15);
//        System.out.println(Test.addDigits(19));

        String s = "ymbgaraibkfmvocpizdydugvalagaivdbfsfbepeyccqfepzvtpyxtbadkhmwmoswrcxnargtlswqemafandgkmydtimuzvjwxvlfwlhvkrgcsithaqlcvrihrwqkpjdhgfgreqoxzfvhjzojhghfwbvpfzectwwhexthbsndovxejsntmjihchaotbgcysfdaojkjldprwyrnischrgmtvjcorypvopfmegizfkvudubnejzfqffvgdoxohuinkyygbdzmshvyqyhsozwvlhevfepdvafgkqpkmcsikfyxczcovrmwqxxbnhfzcjjcpgzjjfateajnnvlbwhyppdleahgaypxidkpwmfqwqyofwdqgxhjaxvyrzupfwesmxbjszolgwqvfiozofncbohduqgiswuiyddmwlwubetyaummenkdfptjczxemryuotrrymrfdxtrebpbjtpnuhsbnovhectpjhfhahbqrfbyxggobsweefcwxpqsspyssrmdhuelkkvyjxswjwofngpwfxvknkjviiavorwyfzlnktmfwxkvwkrwdcxjfzikdyswsuxegmhtnxjraqrdchaauazfhtklxsksbhwgjphgbasfnlwqwukprgvihntsyymdrfovaszjywuqygpvjtvlsvvqbvzsmgweiayhlubnbsitvfxawhfmfiatxvqrcwjshvovxknnxnyyfexqycrlyksderlqarqhkxyaqwlwoqcribumrqjtelhwdvaiysgjlvksrfvjlcaiwrirtkkxbwgicyhvakxgdjwnwmubkiazdjkfmotglclqndqjxethoutvjchjbkoasnnfbgrnycucfpeovruguzumgmgddqwjgdvaujhyqsqtoexmnfuluaqbxoofvotvfoiexbnprrxptchmlctzgqtkivsilwgwgvpidpvasurraqfkcmxhdapjrlrnkbklwkrvoaziznlpor";
        String t = "qhxepbshlrhoecdaodgpousbzfcqjxulatciapuftffahhlmxbufgjuxstfjvljybfxnenlacmjqoymvamphpxnolwijwcecgwbcjhgdybfffwoygikvoecdggplfohemfypxfsvdrseyhmvkoovxhdvoavsqqbrsqrkqhbtmgwaurgisloqjixfwfvwtszcxwktkwesaxsmhsvlitegrlzkvfqoiiwxbzskzoewbkxtphapavbyvhzvgrrfriddnsrftfowhdanvhjvurhljmpxvpddxmzfgwwpkjrfgqptrmumoemhfpojnxzwlrxkcafvbhlwrapubhveattfifsmiounhqusvhywnxhwrgamgnesxmzliyzisqrwvkiyderyotxhwspqrrkeczjysfujvovsfcfouykcqyjoobfdgnlswfzjmyucaxuaslzwfnetekymrwbvponiaojdqnbmboldvvitamntwnyaeppjaohwkrisrlrgwcjqqgxeqerjrbapfzurcwxhcwzugcgnirkkrxdthtbmdqgvqxilllrsbwjhwqszrjtzyetwubdrlyakzxcveufvhqugyawvkivwonvmrgnchkzdysngqdibhkyboyftxcvvjoggecjsajbuqkjjxfvynrjsnvtfvgpgveycxidhhfauvjovmnbqgoxsafknluyimkczykwdgvqwlvvgdmufxdypwnajkncoynqticfetcdafvtqszuwfmrdggifokwmkgzuxnhncmnsstffqpqbplypapctctfhqpihavligbrutxmmygiyaklqtakdidvnvrjfteazeqmbgklrgrorudayokxptswwkcircwuhcavhdparjfkjypkyxhbgwxbkvpvrtzjaetahmxevmkhdfyidhrdeejapfbafwmdqjqszwnwzgclitdhlnkaiyldwkwwzvhyorgbysyjbxsspnjdewjxbhpsvj";
        System.out.println(Test.findTheDifference(s,t));
    }

    // 存进Map，对不同 char 个数计数，最后对比 char 字符的数量差别就可以知道插入的 char 是什么
    public static char findTheDifference(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<Character, Integer>();
        Map<Character, Integer> tMap = new HashMap<Character, Integer>();
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        for (int i = 0; i < s.length(); i++) {
            if (sMap.get(sChars[i]) == null) {
                sMap.put(sChars[i], 1);
            } else {
                sMap.put(sChars[i], sMap.get(sChars[i])+1);
            }
        }

        for (int j = 0; j < t.length(); j++) {
            if (tMap.get(tChars[j]) == null) {
                tMap.put(tChars[j], 1);
            } else {
                tMap.put(tChars[j], tMap.get(tChars[j])+1);
            }
        }

        for (int k = 0; k < t.length(); k++) {
            if (tMap.get(tChars[k]) != sMap.get(tChars[k])) {
                return tChars[k];
            }
        }

        return Character.MIN_VALUE;

//        char result = 0;
//        for (char c : s.toCharArray()) {
//            result ^= c;
//        }
//
//        for (char c : t.toCharArray()) {
//            result ^= c;
//        }
//
//        return result;
    }



    public static int addDigits(int num) {
        while (num / 10 > 0) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num = num / 10;
            }
            num = sum;
        }
        return num;
    }


    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>(n);

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else {
                if (i % 3 == 0)
                    result.add("Fizz");
                else if (i % 5 == 0)
                    result.add("Buzz");
                else
                    result.add(i + "");
            }

        }
        return result;
    }


    public static int singleNumber(int[] nums) {


        // 1. 常规解法，要借助额外的空间
        /**
        Set<Integer> sets= new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!sets.contains(nums[i]))
                sets.add(nums[i]);
            else
                sets.remove(nums[i]);
        }
        return sets.iterator().next();
         **/

        // 借助 XOR 特性，当两个二进制值不同的时候返回 1
        // 因为A XOR A = 0，且XOR运算是可交换的，于是，对于实例{2,1,4,5,2,4,1}就会有这样的结果：
        // (2^1^4^5^2^4^1) => ((2^2)^(1^1)^(4^4)^(5)) => (0^0^0^5) => 5
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;

    }

    public static boolean canWinNim(int n) {
        if (n % 4 == 0)
            return false;
        return true;
    }



    public static int[] twoSum(int[] nums, int target) {
        int[] result = null;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result = new int[2];
                    result[0] = i;
                    result[1] = j;
                }

            }
        }
        return result;
    }

    public static String reverseString(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}

