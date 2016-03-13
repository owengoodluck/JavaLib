package com.owen.wms.web.utils;

import com.owen.wms.web.constants.AppConstant;
import com.owen.wms.web.constants.UpdateModel;
import com.owen.wms.web.entity.JewelryEntity;

public class JewelryMappingUtil {
	//----------------Target Audience------------------
	private static String[] targetAudienceKeywordList={"Adults","Baby Boys","Baby GirlsBoys","Children","Girls","Men","People","Teens","Unisex Adult","Unisex Babies","Unisex Children","Women"};
	
	//-------------Subject Matter-----------------
	private static String[] thesaurusSubjectKeywordsList={"Animals","Anniversary","April Birthstones" ,"Art","Artists","August Birthstones" ,"Birthday","Birthstones","Butterflies" ,"Christian Crosses","Christian Symbols","Christmas" ,"Crosses","Crucifixes","December Birthstones" ,"Dogs","Doves","Earth Based Religious Symbols" ,"Engagement","Fashion","February Birthstones" ,"Flowers","Good Luck Symbols","Graduation" ,"Hearts","Holiday","January Birthstones" ,"Jesus Depictions","Jewish Symbols","July Birthstones" ,"June Birthstones","Love Symbols","March Birthstones" ,"May Birthstones","Men","Military Affiliations" ,"Mothers Day","November Birthstones","October Birthstones" ,"Popular Symbols","Religious Symbols","Saint Depictions" ,"September Birthstones","Sports","Star of David" ,"Stars","Valentines Day","Wedding" ,"Women","Words" };
	
	//-------------Other Attributes-----------------
	private static String[] thesaurusAttributeKeywordsList={"Alternating Stone","Antique Reproduction Style","Antiqued Finish","Asian Style","Beaded","Bridal Style","Briolette Style","Brushed Satin","Cameo Style","Celtic Designs","Claddagh Style","Cluster Style","Combination Finish","Comfort Fit","Corded","Cross Shaped","Dangling Style","Decorative","Diamond Accented","Drop Style","Engraved","European Style","Fashion Style","Filigree Style","Gem Set","Hand Engraved","Handwoven","Heart Shaped","Indian Style","Machine Engraved","Matte Finish","Medal Style","Mosaic Style","Native American Style","Outlet Jewelry","Oval","Personalized","Polished Finish","Rectangular","Round","Slide Style","Solitaire Style","Southwestern Style","Square","Station Style","Strand Style","Traditional Style"};
	
	//-----------------Intended Use1---------------
	private static String[] specificUsesKeywordsList={"Anniversary","Bat Mitzvah","Birthday","Business Gifts","Christmas","Engagement","Fathers Day","Gift","Graduation","Heart Shaped","Holiday","Mothers Day", "Occasions","Polished Finish","Valentines Day", "Wedding" };
	
	public static String getFeedProductTypeByItemType(String itemType){
		if(itemType!=null){
			itemType = itemType.toLowerCase();
			switch (itemType){
			case "pendant-necklaces":return "FashionNecklaceBraceletAnklet";
			case "link-bracelets":return"FashionNecklaceBraceletAnklet";
			case "rings":return"FashionRing";
			case "earrings":return"FashionEarring";
			default:return null;//TODO TBC
			}
		}else{
			return null;
		}
	}
	
	public static void enrichDefaultValue(JewelryEntity e){
		if(StringUtils.isEmpty(e.getTargetAudienceKeywords1())){
			e.setTargetAudienceKeywords1(targetAudienceKeywordList[(int)(1+Math.random()*(targetAudienceKeywordList.length-1))]);
			e.setTargetAudienceKeywords2(targetAudienceKeywordList[(int)(1+Math.random()*(targetAudienceKeywordList.length-1))]);
			e.setTargetAudienceKeywords3(targetAudienceKeywordList[(int)(1+Math.random()*(targetAudienceKeywordList.length-1))]);
		}
		
		if(StringUtils.isEmpty(e.getThesaurusSubjectKeywords1())){
			e.setThesaurusSubjectKeywords1(thesaurusSubjectKeywordsList[(int)(1+Math.random()*(thesaurusSubjectKeywordsList.length-1))]);
			e.setThesaurusSubjectKeywords2(thesaurusSubjectKeywordsList[(int)(1+Math.random()*(thesaurusSubjectKeywordsList.length-1))]);
			e.setThesaurusSubjectKeywords3(thesaurusSubjectKeywordsList[(int)(1+Math.random()*(thesaurusSubjectKeywordsList.length-1))]);
			e.setThesaurusSubjectKeywords4(thesaurusSubjectKeywordsList[(int)(1+Math.random()*(thesaurusSubjectKeywordsList.length-1))]);
			e.setThesaurusSubjectKeywords5(thesaurusSubjectKeywordsList[(int)(1+Math.random()*(thesaurusSubjectKeywordsList.length-1))]);
		}
		
		if(StringUtils.isEmpty(e.getThesaurusAttributeKeywords1())){
			e.setThesaurusAttributeKeywords1(thesaurusAttributeKeywordsList[(int)(1+Math.random()*(thesaurusAttributeKeywordsList.length-1))]);
			e.setThesaurusAttributeKeywords2(thesaurusAttributeKeywordsList[(int)(1+Math.random()*(thesaurusAttributeKeywordsList.length-1))]);
			e.setThesaurusAttributeKeywords3(thesaurusAttributeKeywordsList[(int)(1+Math.random()*(thesaurusAttributeKeywordsList.length-1))]);
			e.setThesaurusAttributeKeywords4(thesaurusAttributeKeywordsList[(int)(1+Math.random()*(thesaurusAttributeKeywordsList.length-1))]);
			e.setThesaurusAttributeKeywords5(thesaurusAttributeKeywordsList[(int)(1+Math.random()*(thesaurusAttributeKeywordsList.length-1))]);
		}
		
		if(StringUtils.isEmpty(e.getSpecificUsesKeywords1())){
			e.setSpecificUsesKeywords1(specificUsesKeywordsList[(int)(1+Math.random()*(specificUsesKeywordsList.length-1))]);
			e.setSpecificUsesKeywords2(specificUsesKeywordsList[(int)(1+Math.random()*(specificUsesKeywordsList.length-1))]);
			e.setSpecificUsesKeywords3(specificUsesKeywordsList[(int)(1+Math.random()*(specificUsesKeywordsList.length-1))]);
			e.setSpecificUsesKeywords4(specificUsesKeywordsList[(int)(1+Math.random()*(specificUsesKeywordsList.length-1))]);
			e.setSpecificUsesKeywords5(specificUsesKeywordsList[(int)(1+Math.random()*(specificUsesKeywordsList.length-1))]);
		}
		
		if(StringUtils.isEmpty(e.getManufacturer())){
			e.setManufacturer(AppConstant.manufacturer);
		}
		if(StringUtils.isEmpty(e.getDisplayDimensionsUnitOfMeasure())){
			e.setDisplayDimensionsUnitOfMeasure("MM");
		}
		if(StringUtils.isEmpty(e.getGemType1())){
			e.setGemType1("NA");
		}
		if(StringUtils.isEmpty(e.getGemType2())){
			e.setGemType2("NA");
		}
		if(StringUtils.isEmpty(e.getGemType3())){
			e.setGemType3("NA");
		}
		if(StringUtils.isEmpty(e.getMetalStamp())){
			e.setMetalStamp("NA");
		}
		if(StringUtils.isEmpty(e.getExternalProductIdType())){
			e.setExternalProductIdType("UPC");
		}
		if(StringUtils.isEmpty(e.getCurrency())){
			e.setCurrency("USD");
		}
		if(e.getListPrice()==null){
			e.setListPrice(26.9);
		}
		if(StringUtils.isEmpty(e.getCountryOfOrigin())){
			e.setCountryOfOrigin("China");
		}
		if(StringUtils.isEmpty(e.getUpdateDelete())){
			e.setUpdateDelete(UpdateModel.Update.toString());
		}

		if(StringUtils.isEmpty(e.getMetalType())){
			e.setMetalType("stainless-steel");
		}
	}
}
