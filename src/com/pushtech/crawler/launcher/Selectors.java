package com.pushtech.crawler.launcher;

public class Selectors {

   // product page
   public static final String PRODUCT_PAGE_IDENTIFIER = ".comment-block";
   public static final String PRODUCT_NAME = "span[itemprop=name]:has(a)";
   public static final String PRODUCT_LINK = "p.product-name>a";
   public static final String PRODUCT_DESCRIPTION = ".details";
   public static final String PRODUCT_KEYWORDS = "meta[name=keywords]";
   public static final String PRODUCT_IDENTIFIER = ".details > div:contains(eference)";
   public static final String PRODUCT_CATEGORY = ".show-page-back";// listing
   public static final String PRODUCT_IMAGE = "#product-img";
   public static final String PRODUCT_PRICE = ".cost > strong";
   public static final String PRODUCT_QUANTITY = "p.availability.in-stock>span";
   public static final String PRODUCT_DELIVERY = "div.delay-livraison>div:contains(Livraison)";

   // listing page
   public static final String LISTING_PAGE_IDENTIFIER = ".content-container > .products";
   public static final String HOME_PAGE_IDENTIFIER = ".homepage_title";
   public static final String LISTING_PAGE_PRODUCTS = ".content-container > .products > div";
   public static final String LISTING_PAGE_PRODUCT_LINK = "a.product";
   public static final String NEXT_PAGE_LINK = "a.next_page";

   // home page
   public static final String ALL_LISTING = ".control-group > a"; //

}
