package asst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/** Helpful tools
 * @author Material Gain
 * @since 2020 11
 */
public class Utils {
	static String HTML_HEAD = null;

	public static String getHtmlHead() throws IOException {
		if (HTML_HEAD == null) {
			InputStream is = Utils.class.getResourceAsStream("/asst/one/index.html");
			HTML_HEAD = slurpStream(is);
		}
		return HTML_HEAD;
	}

	public static String slurpStream(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String aLine;
		BufferedReader br = new BufferedReader(new InputStreamReader
				(is, Charset.forName(StandardCharsets.UTF_8.name())));
		while ( (aLine = br.readLine() ) != null) {
			sb.append(aLine + "\n");
		}
		br.close();
		return sb.toString();
	}

	/**
	 * Replace all occurrences of s1 in the string with s2
	 * @param sb string to be modified
	 * @param s1 string to be replaced
	 * @param s2 replacement string.  If null, changed to the empty string
	 * @return modified string
	 */
	public static StringBuilder replaceAll(StringBuilder sb,
			String s1, String s2) {
		int ix  = 0;
		int len = s1.length();
		if (s2 == null) { s2 = ""; }

		while ( (ix = sb.indexOf(s1, ix)) >-1) {
			sb = sb.replace(ix, ix+len, s2);
			ix += s2.length(); // do not search replacement string
		}
		return sb;
	}
}
