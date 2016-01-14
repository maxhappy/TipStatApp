package helper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tipstat.demo.ilab.MainApp;
import tipstat.demo.ilab.QueryApp;
import android.app.Activity;
import android.app.Application;
import android.view.View;


public class JSONParser 
{
	static JSONObject jsonobjcet;
	
	
	
	static public void parseInfo()
	{
		
		
		
		
		
		try{
			 ParseFeed();
			}
		 catch (JSONException e) 
		 {
			 
			  try{
					ParseApiHits();
				 }
				 catch (JSONException e1)
				 {
					
				 }
				 
			 }
			 
		 }
		
		
		
		




	
      private static void ParseFeed()throws JSONException 
      {
    	  
    	  JSONObject object = new JSONObject(Var.apiResponse);
    	  JSONArray array=object.getJSONArray(Feed.keys[0]);
    	  
    	  
    	  int i=0;
    	  Var.feedlist.clear();
      	  Var.nossource=0;
  		while(i<array.length())
  		{
  			
  			
  			Feed feed=new Feed();
  			JSONObject object1 = new JSONObject();
  			object1=array.getJSONObject(i);
  			feed.id=Integer.parseInt(object1.get(Feed.keys[1]).toString());
  			feed.key=i;
  			feed.dobs=object1.get(Feed.keys[2]).toString();
  			
  			feed.dob=Date.valueOf((object1.get(Feed.keys[2]).toString()));
  			feed.status=object1.get(Feed.keys[3]).toString();
  			feed.ethnicity=Integer.parseInt(object1.get(Feed.keys[4]).toString());
  			feed.weight=Integer.parseInt(object1.get(Feed.keys[5]).toString());
  			feed.height=Integer.parseInt(object1.get(Feed.keys[6]).toString());
  			int a=0;
  			a=Integer.parseInt(object1.get(Feed.keys[7]).toString());
  			if(a==1)
  			feed.vegetarian=true;
  			else
  			feed.vegetarian=false;
  			
  			
  			a=Integer.parseInt(object1.get(Feed.keys[8]).toString());
  			if(a==1)
  			feed.teetotaler=false;
  			else
  			feed.teetotaler=true;
  		
  			
  			feed.imageurl=object1.get(Feed.keys[9]).toString();
  			Var.feedlist.add(feed);
  		 
  		
  			
  			
  			
  			if(!Var.weightList.contains(""+(float)feed.weight/1000))
  		    Var.weightList+="❥"+(float)feed.weight/1000;
  			
  			if(!Var.weightList.contains(""+feed.height))
  	  		Var.weightList+="❥"+feed.height;	
  	  			
  			
  			Var.titlelist+="❥"+feed.status;
  			
  			i++;
  		 
  		 
  		 
  		 
  	  		
		}

	    Var.nossource=i;

  		//Var.titlelist=Var.titlelist.substring(1);
        //Var.sourcelist=Var.sourcelist.substring(1);
  		Var.index=0;
  		
  	    Var.isloaded=2;
  	    runonuithread();
    	  
        }

	 
	 

	private static void ParseApiHits() throws JSONException 
	{
			jsonobjcet=new JSONObject(Var.apiResponse);
			Var.apihits=jsonobjcet.get("api_hits").toString();
		
    }
	
	

	public static void runonuithread( )
	{
		((Activity) MainApp.ctx).runOnUiThread(new Runnable() 
		{
		    public void run() 
		    {
		    	
		    	QueryApp.init_and_refreshView();
		    	
		    		
		    }
		    	
		});
		
	}


	



	
	
	
	
	
	
	
	
	
	
	
	
	

}