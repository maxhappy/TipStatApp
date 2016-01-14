package helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tipstat.demo.ilab.MainApp;
import tipstat.demo.ilab.QueryApp;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

public class Var 
{
	
	public static Context context;
	public static String networkfailmessage="Sorry internet not working on this Device";
	public static CharSequence ratemsg="Rate this";
	
	public static String apiurl="https://tipstat.0x10.info/api/tipstat?type=json&query=";
	public static String [] querys={ "list_status", "api_hits"};
	public static String [] ethnicity={"All", "Asian","Indian","African Americans","Asian Americans","European","British","Jewish","Latino","Native American","Arabic"};
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String apihits;
	public static String apiResponse;
	public static int cat=0;
	
	public static List<Feed> feedlist=new  ArrayList<Feed>();
	public static List<Feed> tempfeedlist=new  ArrayList<Feed>();
	
	
	
	public static enum QueryIndex { News,ApiHits}
	
	
	////////////////////////////initiate the first run///////////////////////////
	
	static  public void init()
	{
		isloaded=0;
		sourcelist="";
		titlelist="";
		
		MainApp.loadbookmark();
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
	    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(MainApp.ctx).defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);
		if(checkNetworkState(true))
        refresh();
		
    }
	//////////////////////////////////////////refresh web request//////////////////////////
	
	static public void refresh()
	{
		
		if(QueryApp.pb!=null)
		  if(QueryApp.pb.getVisibility()!=View.VISIBLE)
			  QueryApp.pb.setVisibility(View.VISIBLE);
		
		isloaded=1;
	
		new WebRequest().execute(""+QueryIndex.ApiHits.ordinal());
		new WebRequest().execute(""+QueryIndex.News.ordinal());
		
		
	}
	
	public static int index;
	public static int isloaded;
	public static int selected=-1;
	public static String seldate;
	public static String selectedcity;
	public static String Bookmark=",";
	public static int Sourcecount=0;
	public static int nossource;
	public static String sourcelist="";
	public static String titlelist="";
	public static int current;
	public static Feed feed;
	public static String weightList="";
	public static String heightList="";
	
	
	
	static  public void sort( )
	{
		Collections.sort(feedlist, new Comparator<Feed>()
				{
					@Override
					public int compare(Feed lhs, Feed rhs) 
					{
						
						if(Var.index==1)
						{
							return lhs.weight-rhs.weight;
							
						}
						if(Var.index==2)
						{
							return lhs.height-rhs.height;
							
							
						}
						 return lhs.weight-rhs.weight;
						
					}
		         }
		       );		
		
 }
	
	

	 public  static boolean  checkNetworkState(boolean  flag)
	 {
	    ConnectivityManager connMgr = (ConnectivityManager) Var.context.getSystemService(Var.context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	    
	    if (networkInfo != null && networkInfo.isConnected()) 
	    {
	    	return true;
	    } 
	    
	    else 
	    {networkInfo=connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    
	    if (networkInfo != null && networkInfo.isConnected()) 
	    {
	    	return true;
	    }
	    	
	    	
	    }
	    	
	    if(flag)
	    {
	    Toast.makeText(MainApp.ctx, Var.networkfailmessage, Toast.LENGTH_SHORT).show();
	    }
	    return false;
	    
	}
	
	
	
	
	

	

}
