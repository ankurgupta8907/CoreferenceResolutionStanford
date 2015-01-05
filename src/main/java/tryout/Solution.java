package tryout;

/**
 * Created by ankurgupta on 12/30/14.
 */

public class Solution {

    private static long B = 257;

    private static long M = 683303;

    private long mod(long a, long b) {
        return (a + b) % b;
    }

    private long createHash(String str) {
        long hash = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            hash = mod(mod(hash*B, M) + ch, M);
        }
        return hash;
    }

    private long updateHash(long hash, String str, int pos, int len, long Blen) {
        char ch = str.charAt(pos - len);
        long val = mod(ch*Blen, M);
        hash = mod(hash - val, M);
        hash = mod(hash*B, M);
        ch = str.charAt(pos);
        return mod(hash + ch, M);
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int needleLen = needle.length();
        long Blen = 1;
        for (int i = 0; i < needleLen - 1; i++) {
            Blen = mod(Blen*B, M);
        }
        long needleHash = createHash(needle);
        long runHash = createHash(haystack.substring(0, needleLen));
        if (needleHash == runHash) {
            return 0;
        }
        for (int i = needleLen; i < haystack.length(); i++) {
            runHash = updateHash(runHash, haystack, i, needleLen, Blen);
            if (needleHash == runHash) {
                return i - needleLen + 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] argv) {
        Solution sol = new Solution();
        System.out.println(sol.strStr("mississippi", "issi"));
    }
}
