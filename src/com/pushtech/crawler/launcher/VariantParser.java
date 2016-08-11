package com.pushtech.crawler.launcher;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.pushtech.commons.Product;
import com.pushtech.crawler.beans.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class VariantParser {

   private Logger logger = Logger.getLogger(this.getClass());
   private Product product;
   private String colorsSizesJsonRaw = null;
   private ArrayList<Product> variantList = null;

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   private VariantParser(Product facialProduct, Page page) {
      this.product = facialProduct;
      this.colorsSizesJsonRaw = getColorsSizesJsonRaw(page);
   }

   private VariantParser() {
      // TODO Auto-generated constructor stub
   }

   public static VariantParser getExtractor(Product facialProduct, Page page) {
      return new VariantParser(facialProduct, page);
   }

   public static VariantParser getExtractor() {
      return new VariantParser();
   }

   private String getColorsSizesJsonRaw(Page page) {
      String colorsSizesJsonRaw = page.getContent();
      // logger.warn("==>" + colorsSizesJsonRaw);
      if (colorsSizesJsonRaw != null && colorsSizesJsonRaw.contains(Selectors.JSONKEY_STARTING_FIELD) && colorsSizesJsonRaw.contains(Selectors.JSONKEY_ENDING_FIELD)) {
         colorsSizesJsonRaw = colorsSizesJsonRaw.substring(colorsSizesJsonRaw.indexOf(Selectors.JSONKEY_STARTING_FIELD) + Selectors.JSONKEY_STARTING_FIELD.length(), colorsSizesJsonRaw.indexOf(Selectors.JSONKEY_ENDING_FIELD)).trim();
         colorsSizesJsonRaw = colorsSizesJsonRaw.substring(colorsSizesJsonRaw.indexOf("["), colorsSizesJsonRaw.lastIndexOf("]") + 1);
      } else return null;
      return colorsSizesJsonRaw;
   }

   private JSONArray toArray(Page page) {
      if (colorsSizesJsonRaw == null) colorsSizesJsonRaw = getColorsSizesJsonRaw(page);
      JSONArray jsonArray = null;
      // logger.debug("ici" + colorsSizesJsonRaw);
      try {

         // colorsSizesJsonRaw = colorsSizesJsonRaw.replace("\"color\"", "\"\\ncolor\"");
         logger.warn("ici" + colorsSizesJsonRaw);
         jsonArray = JSONArray.fromObject((JSONArray) JSONSerializer.toJSON(colorsSizesJsonRaw));
         logger.warn("ici" + colorsSizesJsonRaw);
      } catch (Exception e) {
         logger.error("" + e.getMessage());
      }
      logger.debug("ici" + colorsSizesJsonRaw);
      return jsonArray;
   }

   public ArrayList<Product> doAction(Page page) {
      JSONArray jsonArray = toArray(page);
      Iterator iterator = null;
      JSONObject colorSizes = null;
      JSONObject color = null;
      JSONArray sizes = null;
      Iterator sizesIterator = null;
      String colorId = null;
      String colorName = null;
      String coloredImage = null;
      JSONObject size = null;
      String sizeId = null;
      String sizeName = null;
      String sizeOrder = null;
      String sizeQuantity = null;
      String sizeOnSale = null;
      if (jsonArray != null) {
         iterator = jsonArray.iterator();
         while (iterator.hasNext()) {
            colorSizes = (JSONObject) iterator.next();
            color = colorSizes.getJSONObject(Selectors.COLOR_OBJECT);
            colorId = color.getString(Selectors.COLOR_ID);
            colorName = color.getString(Selectors.COLOR_NAME);
            coloredImage = color.getString(Selectors.COLORED_IMAGE);
            logger.debug("Color id : " + colorId);
            logger.debug("Color name : " + colorName);
            logger.debug("Color image : " + coloredImage);
            sizes = colorSizes.getJSONArray(Selectors.SIZES_ARRAY);
            sizesIterator = sizes.iterator();
            while (sizesIterator.hasNext()) {
               size = (JSONObject) sizesIterator.next();
               sizeId = size.getString(Selectors.SIZE_ID);
               sizeName = size.getString(Selectors.SIZE_NAME);
               sizeQuantity = size.getString(Selectors.SIZE_QUANTITY);
               logger.debug("Size id : " + sizeId);
               logger.debug("Size name : " + sizeName);
               logger.debug("SIze quantity : " + sizeQuantity);
            }
         }
      }
      return variantList;
   }

}
