package tipstat.demo.ilab;
import java.util.List;
import com.nostra13.universalimageloader.core.ImageLoader;
import helper.Feed;
import helper.Var;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewAdapter extends ArrayAdapter<Feed> {
	  private final Context context;
	  private List<Feed> values;
	  private OnClickListener click;
	
	  

	  public ViewAdapter(Context context, List<Feed> values) {
	    super(context, R.layout.holder, values);
	    this.context = context;
	    this.values = values;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) 
	  {
	    LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    final View rowView = inflater.inflate(R.layout.holder, parent, false);
	    final TextView  userstatus = (TextView) rowView.findViewById(R.id.userstatus);
	    final TextView  userinfo  = (TextView) rowView.findViewById(R.id.userinfo);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
	    ImageLoader.getInstance().displayImage(Var.tempfeedlist.get(position).imageurl,imageView);

	    userstatus.setText(Var.tempfeedlist.get(position).status);
	    userinfo.setText("Weight:"+(float)(Var.tempfeedlist.get(position).weight/1000)+"Kg"+".  Height:"+(Var.tempfeedlist.get(position).height)+"cms");
	    
	    return rowView;
			    
	  }



}
