package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dikshantmanocha.machinecontrolingsystem.R;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bean.UserBean;
import database.DatabaseController;


/**
 * Created by Dikshant Manocha on 26-02-2018.
 */

public class UserAdapter extends BaseAdapter implements Filterable {

    List<UserBean> listUsers;
    private Context con;
    private static LayoutInflater inflater;
    private Bitmap bitmap;

    public UserAdapter(List<UserBean> listUsers, Context con) {
        this.listUsers = listUsers;
        this.con = con;
        inflater =(LayoutInflater)  con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return listUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view==null)
            view=inflater.inflate(R.layout.row_listview,null);
        Collections.sort(listUsers, new Comparator<UserBean>() {
            @Override
            public int compare(UserBean userBean, UserBean t1) {
                return userBean.getFirstName().compareTo(t1.getFirstName());
            }
        });
        TextView tv_fname= (TextView) view.findViewById(R.id.tv_fname);
        TextView tv_lname= (TextView) view.findViewById(R.id.tv_lname);

        TextView designation= (TextView) view.findViewById(R.id.designation);
        TextView status= (TextView) view.findViewById(R.id.status);

        tv_fname.setText(listUsers.get(position).getFirstName());
        tv_lname.setText(listUsers.get(position).getLastName());
        designation.setText(listUsers.get(position).getDesignation());

        if(listUsers.get(position).getStatus().equals("1"))
        {
            status.setText("Active");
        }
        else
        {
            status.setText("Inactive");
        }



        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }


}
