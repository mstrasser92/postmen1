package at.post.postmen.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import at.post.postmen.R;
import at.post.postmen.data.Adress;

/**
 * Created by Michael on 22.07.2015.
 */
public class AdressAdapter extends BaseAdapter {
    private Activity mActivity;
    private ArrayList mData = new ArrayList();
    private LayoutInflater mInflater;
    public Resources mRes;

    Adress tempVal = null;
    int i = 0;

    public AdressAdapter(Activity activity, Resources resLocal, ArrayList data){
        mActivity = activity;
        mData = data;
        mRes = resLocal;

        mInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if(mData.size() <= 0)
            return 1;
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public TextView adress;
        public TextView parcelCount;
        public TextView sigRelAuts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(convertView == null){

            view = mInflater.inflate(R.layout.adressItem, null);

            holder = new ViewHolder();
            holder.adress = (TextView)view.findViewById(R.id.adress);
            holder.parcelCount = (TextView) view.findViewById(R.id.parcelCount);
            holder.sigRelAuts = (TextView) view.findViewById(R.id.sigRelAuts);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        if(mData.size() <= 0){
            holder.adress.setText("No Data");
        } else {
            tempVal = null;
            tempVal = (Adress) mData.get( position);

            holder.adress.setText(tempVal.getStreet() + " " + tempVal.getNumber());
            holder.parcelCount.setText(tempVal.getParcels());
            String sigRelAuts = null;
            sigRelAuts = getSigRelAuts();
            holder.sigRelAuts.setText(sigRelAuts);
        }
        return null;
    }

    private String getSigRelAuts(){
        return  null;
    }
}
