package com.pushtech.crawler.launcher;

import static com.pushtech.crawler.logging.LoggingHelper.logger;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.pushtech.commons.Product;
import com.pushtech.crawler.beans.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class VariantParser {
	  private static final Locale CURRENT_LOCALE = Locale.FRENCH;
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
		if (colorsSizesJsonRaw != null
				&& colorsSizesJsonRaw
						.contains(Selectors.JSONKEY_STARTING_FIELD)
				&& colorsSizesJsonRaw.contains(Selectors.JSONKEY_ENDING_FIELD)) {
			colorsSizesJsonRaw = colorsSizesJsonRaw
					.substring(
							colorsSizesJsonRaw
									.indexOf(Selectors.JSONKEY_STARTING_FIELD)
									+ Selectors.JSONKEY_STARTING_FIELD.length(),
							colorsSizesJsonRaw
									.indexOf(Selectors.JSONKEY_ENDING_FIELD))
					.trim();
			colorsSizesJsonRaw = colorsSizesJsonRaw.substring(
					colorsSizesJsonRaw.indexOf("["),
					colorsSizesJsonRaw.lastIndexOf("]") + 1);
		} else
			return null;
		return colorsSizesJsonRaw;
	}

	private JSONArray toArray(Page page) {
		if (colorsSizesJsonRaw == null)
			colorsSizesJsonRaw = getColorsSizesJsonRaw(page);
		JSONArray jsonArray = null;
		try {
			jsonArray = JSONArray.fromObject((JSONArray) JSONSerializer
					.toJSON(colorsSizesJsonRaw));
		} catch (Exception e) {
			logger.error("" + e.getMessage());
		}
		return jsonArray;
	}

	public ArrayList<Product> doAction(Page page) {
		Product variantProduct = null;
		JSONArray jsonArray = toArray(page);
		Iterator iterator = null;
		JSONObject colorSizes = null;
		JSONObject color = null;
		JSONArray sizes = null;
		Iterator sizesIterator = null;
		JSONObject size = null;
		JSONObject sizeOnly = null;
		JSONArray intervalsArray = null;
		Iterator intervalsIterator = null;
		JSONObject interval = null;
		JSONObject intervalOnly = null;
		String colorId = null;
		String colorName = null;
		String coloredImage = null;
		String sizeId = null;
		String sizeName = null;
		// String sizeOrder = null;
		String sizeQuantity = null;
		// String sizeOnSale = null;
		String intervalId = null;
		String intervalName = null;
		String priceRaw = null;
		int min = 0;
		if (jsonArray != null) {
			iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				colorSizes = (JSONObject) iterator.next();
				color = colorSizes.getJSONObject(Selectors.COLOR_OBJECT);
				colorId = color.get(Selectors.COLOR_ID).toString();
				colorName = color.get(Selectors.COLOR_NAME).toString();
				coloredImage = color.getString(Selectors.COLORED_IMAGE)
						.toString();
//				logger.debug("Color id : " + colorId);
				logger.debug("Color name : " + colorName);
				logger.debug("Color image : " + coloredImage);
				sizes = colorSizes.getJSONArray(Selectors.SIZES_ARRAY);
				sizesIterator = sizes.iterator();
				while (sizesIterator.hasNext()) {
					variantProduct = (Product) product.clone();
					variantProduct.setColorName(colorName);
					variantProduct.setImage(coloredImage);
					size = (JSONObject) sizesIterator.next();
					sizeOnly = (JSONObject) size
							.getJSONObject(Selectors.SIZE_OBJECT);
					sizeId = (String) sizeOnly.get(Selectors.SIZE_ID)
							.toString();
					sizeName = (String) sizeOnly.get(Selectors.SIZE_NAME)
							.toString();
					sizeQuantity = (String) sizeOnly.get(
							Selectors.SIZE_QUANTITY).toString();
//					logger.debug("Size id : " + sizeId);
					logger.debug("Size name : " + sizeName);
					logger.debug("Size quantity : " + sizeQuantity);
					variantProduct.setSizeName(sizeName);
					variantProduct.setQuantity(Integer.valueOf(sizeQuantity));
					intervalsArray = size
							.getJSONArray(Selectors.SIZE_INTERVALS_ARRAY);
					intervalsIterator = intervalsArray.iterator();
					while (intervalsIterator.hasNext()) {
						interval = (JSONObject) intervalsIterator.next();
						intervalOnly = (JSONObject) interval
								.getJSONObject(Selectors.SIZE_INTERVAL);
						// logger.debug("a"+interval);
						intervalId = (String) intervalOnly.get(
								Selectors.SIZE_INTERVAL_ID).toString();
						intervalName = (String) intervalOnly.get(
								Selectors.SIZE_INTERVAL_NAME).toString();
						priceRaw = (String) interval.get(
								Selectors.SIZE_INTERVAL_PRICE).toString();
//						logger.debug("Interval id : " + intervalId);
//						logger.debug("Interval name : " + intervalName);
						logger.debug("Price raw : " + priceRaw);
						if (intervalId.equals("219")) {// intervall de prix de
														// deteillant
							break;
						}

						// if(min ==0){
						// min = Integer.parseInt(intervalId);
						// }else if(min>0 && min>Integer.parseInt(intervalId)){
						// min = Integer.parseInt(intervalId);
						// }
					}
					variantProduct.setName(product.getName()+" "+colorName+ " "+sizeName);
					variantProduct.setId(product.getId()+"-"+colorId+"-"+sizeId);
					variantProduct.setPrice(parseLocalizedPrice(priceRaw.replace(".", ",")));
					if(variantList==null) variantList = new ArrayList<Product>();
					variantList.add(variantProduct);
				}
			}
		}
		logger.error("min" + min);
		return variantList;
	}

	private float parseLocalizedPrice(final String priceRaw) {
	      final String priceText = cleanPrice(priceRaw);
	      // logger.warn("price test " + priceRaw);
	      if (StringUtils.isNotBlank(priceText)) {
	         try {
	            NumberFormat priceFormat = NumberFormat.getNumberInstance(CURRENT_LOCALE);
	            Number priceNumber = priceFormat.parse(priceText);
	            // return (float) (priceNumber.floatValue() * (1 + (19.8 / 100)));
	            return priceNumber.floatValue();
	         } catch (ParseException pexc) {
	            logger.error("Price number not parseable [" + priceText + "]");
	         }
	      }
	      return -1f;
	   }
	
	private String cleanPrice(final String priceRaw) {
	      // TODO
	      return priceRaw.replaceAll("[^\\d.,]", "");
	   }
	
}
