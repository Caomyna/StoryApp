package com.example.storyapp2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.storyapp2.database.StoryAppDatabase;
import com.example.storyapp2.databinding.ActivityStoryAddBinding;
import com.example.storyapp2.model.Category;
import com.example.storyapp2.model.Story;

import java.util.List;

public class StoryAddActivity extends AppCompatActivity {

    public static final String TAG = StoryAppDatabase.class.getName();
    private static final int MY_REQUEST_CODE = 10;
    private ActivityStoryAddBinding binding;
    private StoryAppDatabase storyAppDatabase;
    private List<Category> listCategory;

    /*
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData(); //dữ liệu ảnh chọn từ gallery
//                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgUpload.setImageBitmap(bitmap);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    )
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryAddBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

        //handle click, backBtn
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //click pick category
        binding.categoryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickCategory();
            }
        });

        //handle AddBtn
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataToAdd();
            }
        });

    }

    private void pickCategory() {
        listCategory =StoryAppDatabase.getInstance(this).categoryDAO().getListCategory();

        String[] categoryArray = new String[listCategory.size()];
        for (int i = 0; i < listCategory.size(); i++) {
            categoryArray[i] = listCategory.get(i).getNameCategory();
        }

        //alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Category").setItems(categoryArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //handle item click from list
                String nameCategory = categoryArray[i];
                binding.categoryTv.setText(nameCategory);
                Log.d("nameCategory", nameCategory);
            }
        }).show();
    }


    private void getDataToAdd() {
        //lấy dữ liệu
        String title = binding.titleEt.getText().toString().trim();
        String author = binding.authorEt.getText().toString().trim();
        String image = binding.imageEt.getText().toString().trim();
        String content = binding.contentEt.getText().toString().trim();
        String nameCategory = binding.categoryTv.getText().toString().trim();
        Log.d("nameCategory: ", nameCategory);


        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(image) || TextUtils.isEmpty(author) || TextUtils.isEmpty(content)) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin...", Toast.LENGTH_SHORT).show();
        } else {
            //lấy idcategory
            listCategory = StoryAppDatabase.getInstance(this).categoryDAO().getListCategory();
            for (Category category : listCategory) {
                if (nameCategory.equals(category.getNameCategory())) {
                    int idCategory = category.getIdCategory();
                    Log.d("idCategory ", String.valueOf(idCategory));
                    Story st = new Story(title, author, content, image, idCategory);
                    addStories(st);
                }
            }
        }
    }

    private void addStories(Story st) {
        Story story = st;

        if (isStoryExist(story)) {
            //da ton tai
            Toast.makeText(this, "Story existed...", Toast.LENGTH_SHORT).show();
            return;
        } else {
            StoryAppDatabase.getInstance(this).storyDAO().insertStory(st);
            Toast.makeText(this, "Add story successfully ...", Toast.LENGTH_SHORT).show();

            binding.titleEt.setText("");
            binding.authorEt.setText("");
            binding.contentEt.setText("");
            binding.imageEt.setText("");

        }
        Intent intent = new Intent(StoryAddActivity.this, DashboardAdminActivity.class);
        startActivity(intent);

    }


    private boolean isStoryExist(Story st) {
        List<Story> list = StoryAppDatabase.getInstance(this).storyDAO().checkStory(st.getTitle());
        return list != null && !list.isEmpty();
    }

}