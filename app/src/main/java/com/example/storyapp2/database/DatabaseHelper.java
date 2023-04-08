package com.example.storyapp2.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Cơ sở dữ liệu

    //Tên database
    private static String database_name = "StoryApp";
    private static final int version = 1;

    //Bảng Account
    private static final String tb_Account = "Account";
    private static final String col_id = "id";
    private static final String col_name = "name";
    private static final String col_email = "email";
    private static final String col_password = "password";
    private static final String col_cPassword = "cPassword";
    private static final String col_role = "role";

    //Bảng Story
    private static final String tb_Story= "Story";
    private static final String col_idStory = "id";
    private static final String col_title = "title";
    private static final String col_author = "author";
    private static final String col_content = "content";
    private static final String col_image= "image";

    //context
    private Context context;


    private static final String create_tbAccount = "create table "+tb_Account+"("
            +col_id+" integer primary key autoincrement,"
            +col_name+" text,"
            +col_email+" text,"
            +col_password+" text unique,"
            +col_cPassword+" text,"
            +col_role+" integer)";


    private static final String create_tbStory= "create table "+tb_Story+"("
            +col_idStory+" integer primary key autoincrement,"
            +col_title+" text unique,"
            +col_author+" text,"
            +col_content+" text,"
            +col_image+" text,"
            +col_id+" integer, foreign key ( "+col_id+") references " + tb_Account+"(" + col_id+"))";


    //INSERT dữ liệu vào bảng Account
    //Phân quyền: 1:admin, 0:user
    private String sqlQuery = "INSERT INTO Account VALUES (null, 'admin', 'admin@gmail.com', 'admin', 'admin',1)";
    private String sqlQuery1 = "INSERT INTO Account VALUES (null, 'myna', 'myna@gmail.com', '11111', '11111',0)";

    //INSERT dữ liệu vào bảng Story
    private String sqlQuery2 = "INSERT INTO Story VALUES (null, 'Dê Đen và Dê Trắng', 'Cổ tích Việt Nam', 'Dê đen và Dê trắng cùng sống trong một khu rừng. Hàng ngày, cả hai thường đến uống nước và tìm cái ăn ở trong khu rừng quen thuộc.\n" +
            "\n" +
            "Một hôm, Dê trắng đi tìm cái ăn và uống nước suối như mọi khi. Dê đang mải mê ngặm cỏ, bất chợt một con Sói ở đâu nhảy xổ ra.\n" +
            "\n" +
            "Sói quát hỏi:- Dê kia! mi đi đâu?\n" +
            "\n" +
            "Dê trắng sợ rúm cả người, lắp bắp:\n" +
            "- Dạ, dạ, tôi đi tìm... tìm cỏ non và...và uống nước suối ạ!\n" +
            "Sói lại quát hỏi:\n" +
            "- Mi có gì ở chân?\n" +
            "- Dạ, dạ, chân của tôi có móng ạ...ạ!\n" +
            "- Trên đầu mi có gì?\n" +
            "- Dạ, dạ, trên đầu tôi có đôi sừng mới nhú...\n" +
            "\n" +
            "Sói càng quát to hơn:\n" +
            "- Trái tim mi thế nào?\n" +
            "- Ôi, ôi, trái...trái tim tôi đang run sợ...sợ...\n" +
            "- Hahaha...\n" +
            "\n" +
            "Sói cười vang rồi ăn thịt chú Dê trắng tội nghiệp.\n" +
            "Dê đen cũng đi tới khu rừng để ăn cỏ non và uống nước suối. Đang tha thẩn ngặm cỏ, chợt Sói xuất hiện, nó quát hỏi:\n" +
            "\n" +
            "- Dê kia, mi đi đâu?\n" +
            "Dê đen nhìn con Sói từ đầu tới chân rồi ngước cổ trả lời:\n" +
            "- Ta đi tìm kẻ nào thích gây sự đây! Sói bị bất ngờ, nó hỏi tiếp:\n" +
            "- Thế dưới chân mi có gì?\n" +
            "- Chân thép của ta có móng bằng đồng.\n" +
            "- Thế...thế...trên đầu mi có gì?\n" +
            "- Trên đầu của ta có đôi sừng bằng kim cương!\n" +
            "\n" +
            "Sói sợ lắm rồi, nhưng vẫn cố hỏi:\n" +
            "- Mi...mi...trái tim mi thế nào?\n" +
            "\n" +
            "Dê đen dõng dạc trả lời:\n" +
            "- Trái tim thép của ta bảo ta rằng: hãy cắm đôi sừng kim cương vào đầu Sói. Nào, Sói hãy lại đây.\n" +
            "\n" +
            "Ôi trời, sợ quá, con Sói ba chân bốn cẳng chạy biến vào rừng, từ đó không ai trông thấy nó lởn vởn ở khu rừng đó nữa.', 'https://www.google.com/url?sa=i&url=https%3A%2F%2Fsonno.vn%2Fchuyen-co-tich-de-den-va-de-trang-3302%2F&psig=AOvVaw3GYqHHc6nd1skp_tnbtbLj&ust=1680889782859000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCOjtwMXolf4CFQAAAAAdAAAAABAI',1)";

    //Tạo bảng tại phương thức này
    public DatabaseHelper(Context context){
        super(context,database_name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //thực hiện các câu lệnh truy vấn không trả về kết quả
        db.execSQL(sqlQuery);
        db.execSQL(sqlQuery1);
        db.execSQL(sqlQuery2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addAccount(){

    }

    //Phương thức lấy tất cả tài khoản
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tb_Account,null);
        return res;
    }

//    public void add(Contact sv){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(col_name,sv.getName());
//        values.put(col_email,sv.getEmail());
//        values.put(col_address,sv.getAddress());;
//        db.insert(tb_name,null,values);
//        db.close();
//    }

}
