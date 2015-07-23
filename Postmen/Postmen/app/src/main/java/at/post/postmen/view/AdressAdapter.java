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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.post.postmen.R;
import at.post.postmen.data.Adress;
import at.post.postmen.data.SigRelAutDataSource;
import at.post.postmen.data.SignatureReleaseAuthorisation;

/**
 * Created by Michael on 22.07.2015.
 */
public class AdressAdapter extends BaseAdapter {
    private Activity mActivity;
    private List<Adress> mData = new ArrayList();
    private LayoutInflater mInflater;
    public Resources mRes;

    Adress tempVal = null;
    int i = 0;

    public AdressAdapter(Activity activity, Resources resLocal, List<Adress> data){
        mActivity = activity;
        mData = data;
        mRes = resLocal;

        mInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void remove(Adress toRemove) {
        mData.remove(toRemove);
    }

    @Override
    public int getCount() {
        if(mData.size() <= 0)
            return 1;
        return mData.size();
    }

    @Override
    public Adress getItem(int position) {
        return mData.get(position);
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

            view = mInflater.inflate(R.layout.adress_item, null);

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
            holder.parcelCount.setVisibility(View.INVISIBLE);
            holder.sigRelAuts.setVisibility(View.INVISIBLE);
        } else {
            tempVal = null;
            tempVal = (Adress) mData.get( position);

            holder.adress.setText(tempVal.getStreet() + " " + tempVal.getNumber());
            holder.parcelCount.setText(String.valueOf(tempVal.getParcels()));
            String sigRelAuts = null;
            sigRelAuts = getSigRelAuts(tempVal);
            holder.sigRelAuts.setText(sigRelAuts);
        }
        return view;
    }

    private String getSigRelAuts(Adress adress){
        String sigRelAuts = "Abstellgenehmigungen: \n";
        List<SignatureReleaseAuthorisation> sigRelList = new ArrayList<>();

        SigRelAutDataSource sigRelSource = new SigRelAutDataSource(mActivity.getApplicationContext());

        try {
            sigRelSource.open();
            sigRelList = sigRelSource.getAllSigRelAutOfAdress(adress);
            sigRelSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(sigRelList.size() == 1)
            sigRelAuts = "Abstellgenehmigung: ";

        if(sigRelList.size() <= 0 || sigRelList == null)
        {
            sigRelAuts = "";
        } else {
            int i = 0;
            while(i < sigRelList.size())
            {
                sigRelAuts += sigRelList.get(i).getName();
                i++;
                if(i < sigRelList.size())
                    sigRelAuts += "; ";

            }
        }
        return  sigRelAuts;
    }
}
