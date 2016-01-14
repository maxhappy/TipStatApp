
package tipstat.demo.ilab;


import tipstat.demo.ilab.R.drawable;
import helper.Feed;
import helper.Var;
import helper.WebRequest;
import helper.Var.QueryIndex;

import com.nostra13.universalimageloader.core.ImageLoader;









import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

 





 

public class ReaderApp extends Activity 
{
	
	 
	
	public static boolean Loaded;
	static Context ctx;
	public static String text="";
	private static ImageView im;
	private static ProgressBar pb;
	private static TextView tex;
	public static String titlelist;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewer);
		ctx=this;
		
		
		
		
		final ImageButton b1=(ImageButton) ((Activity) ctx).findViewById(R.id.but1);
		final ImageButton b2=(ImageButton) ((Activity) ctx).findViewById(R.id.but2);
		final ImageButton b3=(ImageButton) ((Activity) ctx).findViewById(R.id.but3);
		final ImageButton b4=(ImageButton) ((Activity) ctx).findViewById(R.id.but4);
		
	    final ImageView	image=(ImageView) ((Activity) ctx).findViewById(R.id.imageView1);
		final TextView ethnicity=(TextView)((Activity) ctx).findViewById(R.id.eth);
		final TextView birthday=(TextView)((Activity) ctx).findViewById(R.id.birthday);
		final TextView weight=(TextView)((Activity) ctx).findViewById(R.id.weight);
		final TextView height=(TextView)((Activity) ctx).findViewById(R.id.height);
		final TextView drink=(TextView)((Activity) ctx).findViewById(R.id.drink);
		final TextView isveg=(TextView)((Activity) ctx).findViewById(R.id.veg);
		final TextView status=(TextView)((Activity) ctx).findViewById(R.id.status);
		
	/*	if(Var.Bookmark.contains(","+Var.feed.key+","))
		{
			b2.setImageResource(drawable.unfav);
			
		}
		else
			b2.setImageResource(drawable.fav);
			*/
		
		ImageLoader.getInstance().displayImage(Var.feed.imageurl,image);
		ethnicity.setText(Var.ethnicity[Var.feed.ethnicity+1]);
		birthday.setText(Var.feed.dobs);
		weight.setText(""+(float)(Var.feed.weight/1000)+" kg");
		height.setText(""+Var.feed.height+" cm");
		
		
		if(!Var.feed.teetotaler)
			drink.setText("Yes");
		else
		drink.setText("No");
		if(Var.feed.vegetarian)
		isveg.setText("Yes");
		else
		isveg.setText("No");
		status.setText(Var.feed.status);
		
		
        OnClickListener click=new OnClickListener() 
         {
			
			@Override
			public void onClick(View v) 
			{
			
				if(v==b1)
				{
		            finish();
					
				}
             	
            	if(v==b2)
             	{
            		bookmark();
            	 	
             	}
            	
            	if(v==b3)
            	{
            		
            		
            		String temp=ethnicity.getText()+" and  born on:"+birthday.getText()+".weighing:"+weight.getText()+".height:"+height.getText()+",Eat veg:"+isveg.getText()+",Drink :"+drink.getText()+"and saying:"+status.getText();
            		share(temp);
            	}
            	
            	if(v==b4)
            	{
            		String temp=ethnicity.getText()+"dob:"+birthday.getText()+"Saying:"+status.getText();
            		sharesms(temp);
            	}
            	
            	
				
			}

		
		};
		
		
		b1.setOnClickListener(click);
		b2.setOnClickListener(click);
		b3.setOnClickListener(click);
		b4.setOnClickListener(click);
	  
		
		
	}

	private void bookmark() 
	{
		
			if(Var.Bookmark.contains(","+Var.feed.key+","))
			{
				
				//Toast.makeText(ctx,"User  was already a favourite so it is removed",Toast.LENGTH_LONG).show();
				//String s=","+Var.feed.key+",";
				//Var.Bookmark=Var.Bookmark.replace(s,",");
				//MainApp.deletebookmark();
				
				Toast.makeText(ctx,"User  is  already  favourite",Toast.LENGTH_LONG).show();
			}
			else
				{
				Toast.makeText(ctx,"User  added to favourite",Toast.LENGTH_LONG).show();
		 		MainApp.savebookmaek(Var.feed.key);
			}
			
		
	}

	private void share(String msg) 
	{
		
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/html");
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,msg);
		startActivity(Intent.createChooser(sharingIntent,"Share using"));
		
	}
	
	private void sharesms(String sms) 
	{
		
		 
		Intent sendIntent = new Intent(Intent.ACTION_VIEW);         
		sendIntent.setData(Uri.parse("sms:"));
		sendIntent.putExtra("sms_body", sms); 
		startActivity(sendIntent);
		
	}
	
	
	
	
	
	
	 
}
	

