package com.example.neo.storyfinderneo;

import android.graphics.Bitmap;
import java.util.UUID;

/**
 * Created by neo on 11/26/16.
 */



//TODO: using world of warcraft API to get PET information, get(name, family, icon, speciesID, health,power,speed, StrongAgaisnt, WeakAgaisnt, description)

public class Story {
   //set the global vars
   private UUID mUuid;
   private Bitmap mImage;
   private String mImageURL;
   private String mTitle, mName, mDescription;
   private double lat, lon;




   public Story(){
      mUuid = UUID.randomUUID();
   }

   public UUID getmUuid(){
      return mUuid;
   }

   public void setmDescription(String Description){
      mDescription = Description;
   }
   public String getmDescription(){
     return mDescription;
   }



   public void setmTitle(String Title){
      mTitle = Title;
   }
   public String getmTitle(){
     return mTitle;
   }


   public void setName(String name){
      mName = name;
   }

   public String getmName(){
      return mName;
   }

   public void setmImage(Bitmap Image){
      mImage = Image;
   }

   public String getImageURL(){
      return mImageURL;
   }

   public void setImageURL(String url){
      if(url.length() <= 0){
         mImageURL = "no Image URL";
      }else{
         mImageURL = url;
      }

   }

   public Bitmap getmImage(){
      return mImage;
   }

   public double getLat() {
      return lat;
   }

   public void setLat(double lat) {
      this.lat = lat;
   }

   public double getLon() {
      return lon;
   }


   public void setLon(double lon) {
      this.lon = lon;
   }

   public void setDistance(){

   }

}



