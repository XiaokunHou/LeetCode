//https://oj.leetcode.com/problems/wildcard-matching/
//Wildcard Matching
import java.util.ArrayList;

public class Solution {
	public boolean isMatch(String s, String p) {
		ArrayList<String> strs = new ArrayList<String>();

		for (int i = 0, j = 0; i < p.length(); i++) {
			if ('*' == p.charAt(i)) {
				String tem = p.substring(j, i);
				strs.add(tem);
				j = i + 1;
			}
			if (i == (p.length() - 1)) {
				String tem = p.substring(j);
				strs.add(tem);
			}
			// no star
			if (i == (p.length() - 1) && 0 == j) {
				return (s.length() == p.length())
						&& contain(s, strs, strs.size());
			}
		}
		if (0 == p.length() && !s.equals("")) {
			return false;
		}
		if (0 == p.length() && s.equals("")) {
			return true;
		}
		return contain(s, strs, strs.size());

	}

	public boolean contain(String s, ArrayList<String> strs, int size) {
		if (0 == strs.size()) {
			return true;
		}
		if (s.length() < strs.get(0).length()) {
			return false;
		}

		String mp = strs.get(0);
		boolean startB = true;
		boolean lastB = true;
		if (size == strs.size()) {
			String start = strs.get(0);
			String last = strs.get(strs.size() - 1);
			if (!start.equals("") && !start.contains("?")) {
				startB = s.startsWith(start);
			}
			if (!last.equals("") && !last.contains("?")) {
				lastB = s.endsWith(last);
			}
		}
		if (!mp.contains("?")) {

			int result = s.indexOf(mp);
			if (result > -1) {
				String subString = s.substring(result + mp.length());
				strs.remove(0);
				return startB && lastB && true && contain(subString, strs, -1);
			}
		} else if (mp.contains("?")) {
			// First one will do it from front
			if (size == strs.size()) {

				int i, j;
				boolean flag = false;
				for (i = 0; i < s.length() - mp.length() + 1; i++) {
					for (j = 0; j < mp.length(); j++) {
						if (!(mp.charAt(j) == s.charAt(i + j) || mp.charAt(j) == '?')) {
							return false;

						}
					}
					if (j == (mp.length())) {
						flag = true;
						i++;
						break;
					}
					String subString = s.substring(i + mp.length() - 1);
					strs.remove(0);
					return startB && lastB && flag
							&& contain(subString, strs, -1);
				}
			}
			// last one will do it from end
			if (1 == strs.size()) {

				boolean flag = false;
				int i, j;
				for (i = s.length() - 1; i > s.length() - mp.length() - 2; i--) {
					for (j = mp.length() - 1; j > -1; j--) {
						if (!(mp.charAt(j) == s.charAt(i + j - mp.length() + 1) || mp
								.charAt(j) == '?')) {
							return false;
						}
					}
					if (j == -1) {
						return true;
					}
				}
			}
			boolean flag = false;
			int i, j;
			for (i = 0; i < s.length() - mp.length() + 1; i++) {
				for (j = 0; j < mp.length(); j++) {
					if (!(mp.charAt(j) == s.charAt(i + j) || mp.charAt(j) == '?')) {
						flag = false;
						break;
					}

				}
				if (j == (mp.length())) {
					flag = true;
					i++;
					break;
				}
			}
			String subString = s.substring(i + mp.length() - 1);
			strs.remove(0);
			return startB && lastB && flag && contain(subString, strs, -1);
		} else
			return false;
		return false;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out
				.print(s.isMatch(
						"abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
						"**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));

	}
}
