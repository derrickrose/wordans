import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.pushtech.commons.Product;
import com.pushtech.crawler.beans.Page;
import com.pushtech.crawler.launcher.VariantParser;

public class Test {

   public static void main(String[] args) {

      File file = new File("lib/html_file/aa.html");
      Document doc = null;
      try {
         doc = Jsoup.parse(file, "UTF-8", "http://www.google.com");
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      Page page = new Page(200, doc.html(), doc);

      VariantParser variantParser = VariantParser.getExtractor(new Product(), page);
      variantParser.doAction(page);
      System.out.println("a");

   }
}
