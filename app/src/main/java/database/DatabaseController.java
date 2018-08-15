package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import bean.UserBean;

/**
 * Created by Dikshant Manocha on 14-03-2018.
 */

public class DatabaseController extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LOGIN_DETAILS";
    private static final String USERS = "USERS";
    private static final String USERNAME="USERNAME";
    private static final String FIRST_NAME="FIRST_NAME";
    private static final String LAST_NAME="LAST_NAME";
    private static final String EMAIL="EMAIL";
    private static final String MOBILE="MOBILE";
    private static final String ROLE="ROLE";
    private static final String DESIGNATION="DESIGNATION";
    private static final String STATUS="STATUS";
    private static final String PASSWORD="PASSWORD";


    public DatabaseController(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + USERS + "("
                + USERNAME + " TEXT PRIMARY KEY," + FIRST_NAME + " TEXT,"
                + LAST_NAME + " TEXT ," + EMAIL + " TEXT,"+ MOBILE + " TEXT," + ROLE + " TEXT," +DESIGNATION +
                " TEXT,"+ STATUS + " TEXT,"+ PASSWORD + " TEXT"+ ");";
        db.execSQL(CREATE_USERS_TABLE);

        ContentValues values = new ContentValues();
        values.put(USERNAME,"DNY");
        values.put(FIRST_NAME,"Dikshant Namit");
        values.put(LAST_NAME,"Yatin");
        values.put(EMAIL,"ddnnyyddnnyy@gmail.com");
        values.put(MOBILE,"8130101700");
        values.put(ROLE,"Admin");
        values.put(DESIGNATION,"Owner");
        values.put(STATUS,"1");
        values.put(PASSWORD,"dny@99999");
        db.insert(USERS, null, values);
        //db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);

    }
    public void addUser(UserBean user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME,user.getUsername());
        values.put(FIRST_NAME,user.getFirstName());
        values.put(LAST_NAME,user.getLastName());
        values.put(EMAIL,user.getEmail());
        values.put(MOBILE,user.getMobile());
        values.put(ROLE,user.getRole());
        values.put(DESIGNATION,user.getDesignation());
        values.put(STATUS,user.getStatus());
        values.put(PASSWORD,user.getPassword());
        db.insert(USERS, null, values);
        db.close();
    }
    public UserBean getUser(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        UserBean userBean=new UserBean();
        Cursor cursor = db.query(USERS, new String[] { USERNAME,
                FIRST_NAME, LAST_NAME,EMAIL,MOBILE,ROLE,DESIGNATION,STATUS,PASSWORD }, USERNAME +" =?" , new String[] {username}, null, null, null, null);
        if (cursor != null && !cursor.isClosed())
            cursor.moveToFirst();

            userBean.setUsername(cursor.getString(0));
            userBean.setFirstName(cursor.getString(1));
            userBean.setLastName(cursor.getString(2));
            userBean.setEmail(cursor.getString(3));
            userBean.setMobile(cursor.getString(4));
            userBean.setRole(cursor.getString(5));
            userBean.setDesignation(cursor.getString(6));
            userBean.setStatus(cursor.getString(7));
            userBean.setPassword(cursor.getString(8));
            cursor.close();
            return userBean;


    }
    public int updateUser(UserBean user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USERNAME,user.getUsername());
        values.put(FIRST_NAME,user.getFirstName());
        values.put(LAST_NAME,user.getLastName());
        values.put(EMAIL,user.getEmail());
        values.put(MOBILE,user.getMobile());
        values.put(ROLE,user.getRole());
        values.put(DESIGNATION,user.getDesignation());
        values.put(STATUS,user.getStatus());
        values.put(PASSWORD,user.getPassword());

        return db.update(USERS, values, USERNAME + " = ?",
                new String[] { user.getUsername() });
    }

    public List<UserBean> getAllUsers() {
        List<UserBean> userList = new ArrayList<UserBean>();
        UserBean userBean;
        String selectQuery = "SELECT  * FROM " + USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                userBean=new UserBean();
                userBean.setUsername(cursor.getString(0));
                userBean.setFirstName(cursor.getString(1));
                userBean.setLastName(cursor.getString(2));
                userBean.setEmail(cursor.getString(3));
                userBean.setMobile(cursor.getString(4));
                userBean.setRole(cursor.getString(5));
                userBean.setDesignation(cursor.getString(6));
                userBean.setStatus(cursor.getString(7));
                userBean.setPassword(cursor.getString(8));
                userList.add(userBean);
            } while (cursor.moveToNext());
        }

        return userList;
    }
    public void delete(String username)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USERS,USERNAME+"=?",new String[]{username});
        db.close();
    }
}
