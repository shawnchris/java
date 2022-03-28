package amplitude;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindEmailAddresses {
  public static String crawl(String url) {
    String content = null;
    URLConnection connection = null;
    try {
      connection =  new URL(url).openConnection();
      Scanner scanner = new Scanner(connection.getInputStream());
      scanner.useDelimiter("\\Z");
      content = scanner.next();
      scanner.close();
    }catch ( Exception ex ) {
      ex.printStackTrace();
    }

    return content;
  }

  static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE);

  public static List<String> findEmailAddress(String str) {
    List<String> allMatches = new ArrayList<>();
    Matcher m = VALID_EMAIL_ADDRESS_REGEX.matcher(str);
    while (m.find()) {
      allMatches.add(m.group());
    }
    return allMatches;
  }

  public static void main(String[] args) {
    String url = "https://customerservice-bloomingdales.com/articles/contact-us";
    System.out.println(String.join("\n", findEmailAddress(crawl(url))));
  }

}
