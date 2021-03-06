
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串模式匹配    Boyer-Moore
 * 
 * @author ampthon
 *
 */
public class MatchTest3 {

	public static void main(String[] args) {
		String source = "aksdjfoiajsgpojawihgioqwwe4ijq23u4u3894u5t893u4tu34rq34r4";
		String pattern = "00asd";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		MatchTest3 match = new MatchTest3();
		System.out.println("start   " + format.format(new Date()));
		for (int i = 0; i < 1000000; i++) {
			match.match(source, pattern);
		}
		System.out.println("end   " + format.format(new Date()));
		System.out.println(match.match(source, pattern));
	}




	/**
	 * 计算滑动距离
	 *
	 * @param c 主串（源串）中的字符
	 * @param T 模式串（目标串）字符数组
	 * @param noMatchPos 上次不匹配的位置
	 * @return 滑动距离
	 */
	private int dist(char c, char T[], int noMatchPos) {
		int n = T.length;

		for (int i = noMatchPos; i >= 1; i--) {
			if (T[i - 1] == c) {
				return n - i;
			}
		}

		// c不出现在模式中时
		return n;
	}

	/**
	 * 找出指定字符串在目标字符串中的位置
	 *
	 * @param source 目标字符串
	 * @param pattern 指定字符串
	 * @return 指定字符串在目标字符串中的位置
	 */
	public int match(String source, String pattern) {
		char[] s = source.toCharArray();
		char[] t = pattern.toCharArray();
		int slen = s.length;
		int tlen = t.length;

		if (slen < tlen) {
			return -1;
		}

		int i = tlen;
		int j = -1;

		while (i <= slen) {
			j = tlen;
			// S[i-1]与T[j-1]若匹配，则进行下一组比较；反之离开循环。
			while (j > 0 && s[i - 1] == t[j - 1]) {
				i--;
				j--;
			}

			// j=0时，表示完美匹配，返回其开始匹配的位置
			if (0 == j) {
				return i;
			} else {
				// 把主串和模式串均向右滑动一段距离dist(s[i-1]).
				i = i + dist(s[i - 1], t, j - 1);
			}
		}

		// 模式串与主串无法匹配
		return -1;
	}
}
