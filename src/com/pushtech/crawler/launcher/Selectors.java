package com.pushtech.crawler.launcher;

public class Selectors {

   // product page
   public static final String PRODUCT_PAGE_IDENTIFIER = ".comment-block";
   public static final String PRODUCT_NAME = "meta[property*=title]";
   public static final String PRODUCT_LINK = "p.product-name>a";
   public static final String PRODUCT_DESCRIPTION = ".details";
   public static final String PRODUCT_KEYWORDS = "meta[name=keywords]";
   public static final String PRODUCT_IDENTIFIER = "input[name=id]";
   public static final String PRODUCT_CATEGORY = ".show-page-back";// listing
   public static final String PRODUCT_IMAGE = "#product-img";
   public static final String PRODUCT_PRICE = ".cost > strong";
   public static final String PRODUCT_QUANTITY = "p.availability.in-stock>span";
   public static final String PRODUCT_DELIVERY = "div.delay-livraison>div:contains(Livraison)";
   public static final String JSONKEY_STARTING_FIELD = "COLOR_SIZES";
   public static final String JSONKEY_ENDING_FIELD = "CURRENCY_SYMBOL";

   // variant json
   public static final String COLOR_OBJECT = "color";
   public static final String COLOR_ID = "id";
   public static final String COLOR_URI = "uri";
   public static final String COLOR_NAME = "name";
   public static final String COLORED_IMAGE = "img_original";

   public static final String SIZES_ARRAY = "sizes";
   public static final String SIZE_OBJECT = "size";
   public static final String SIZE_ID = "id";
   public static final String SIZE_NAME = "name";
   public static final String SIZE_ORDER = "order";
   public static final String SIZE_QUANTITY = "qty";
   public static final String SIZE_ON_SALE = "on_sale";

   public static final String SIZE_INTERVALS_ARRAY = "intervals";
   public static final String SIZE_INTERVAL = "interval";
   public static final String SIZE_INTERVAL_ID = "id";
   public static final String SIZE_INTERVAL_NAME = "name";
   public static final String SIZE_INTERVAL_MIN = "min_qty";
   public static final String SIZE_INTERVAL_MAX = "max_qty";
   public static final String SIZE_INTERVAL_PRICE = "price";

   // listing page
   public static final String LISTING_PAGE_IDENTIFIER = ".content-container > .products";
   public static final String HOME_PAGE_IDENTIFIER = ".homepage_title";
   public static final String LISTING_PAGE_PRODUCTS = ".content-container > .products > div";
   public static final String LISTING_PAGE_PRODUCT_LINK = "a.product";
   public static final String NEXT_PAGE_LINK = "a.next_page";

   // home page
   public static final String ALL_LISTING = ".control-group > a"; //

}
