package fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dikshantmanocha.machinecontrolingsystem.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import adapter.UserAdapter;
import bean.UserBean;
import database.DatabaseController;

/**
 * Created by Dikshant Manocha on 13-03-2018.
 */

public class ManageUsers extends Fragment {
    private Context context;
    private ArrayList<UserBean> listUsers;
    private ListView lv;
    private  UserAdapter userAdapter;

    public ManageUsers(Context context) {
        this.context = context;
        listUsers=(ArrayList<UserBean>)new DatabaseController(context).getAllUsers();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manage_users, container, false);

        Collections.sort(listUsers, new Comparator<UserBean>() {
            @Override
            public int compare(UserBean userBean, UserBean t1) {
                return userBean.getFirstName().compareTo(t1.getFirstName());
            }
        });

       userAdapter=new UserAdapter(listUsers ,context);
        lv = (ListView) rootView.findViewById(R.id.lv_items);
        lv.setAdapter(userAdapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                if(listUsers.get(index).getUsername().equals("DNY"))
                {
                    Toast.makeText(getActivity(),"Admin User",Toast.LENGTH_LONG).show();
                }
                else {
                    showFilterPopup(v, listUsers, index);
                }
                return true;
            }
        });
        return rootView;
    }
    private void showFilterPopup(View v, final ArrayList<UserBean> listUsers, final int index) {

        final UserBean userBean=listUsers.get(index);
        PopupMenu popup = new PopupMenu(context, v);
        popup.inflate(R.menu.popup_filter);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_delete:
                        listUsers.remove(index);
                        userAdapter.notifyDataSetChanged();
                        new DatabaseController(context).delete(userBean.getUsername());
                        return true;
                    case R.id.menu_active:
                        if(userBean.getStatus().equals("1"))
                        {
                            listUsers.get(index).setStatus("0");
                            userBean.setStatus("0");
                            userAdapter.notifyDataSetChanged();
                            new DatabaseController(context).updateUser(userBean);
                        }
                        else
                        {
                            listUsers.get(index).setStatus("1");

                            userBean.setStatus("1");
                            userAdapter.notifyDataSetChanged();
                            new DatabaseController(context).updateUser(userBean);
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }
}
