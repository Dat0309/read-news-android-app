package com.dinhtrongdat.readnewsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryDetailActivity extends AppCompatActivity implements AdapterDetail.ListItemClickListener{

    ImageView imgCategory;
    TextView cateTitle;
    Category category;
    RecyclerView detailRecycle;
    private List<Detail> mdata;
    RecyclerView.Adapter detailAdapter;
    public String link_category="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        mdata = new ArrayList<Detail>();
        detailRecycle = findViewById(R.id.rv_detail);
        initReadRss();
        initUI();
    }

    private void initUI() {
        imgCategory = findViewById(R.id.img_cate_detail);
        cateTitle = findViewById(R.id.txt_Title_cate_detail);

        category = (Category) getIntent().getExtras().getSerializable("categoryObject");
        imgCategory.setImageResource(category.getDrawableResource());
        cateTitle.setText(category.getTitle());

        /*
        * custom adapter RecycleView*/
        detailAdapter = new AdapterDetail(mdata, this);
        detailAdapter.notifyDataSetChanged();
        detailRecycle.setLayoutManager(new LinearLayoutManager(CategoryDetailActivity.this));
        detailRecycle.setHasFixedSize(true);
        setAnimation(R.anim.layout_animation_dow_up);

    }
    public class ReadData extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return ReadContent(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLParser parser = new XMLParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDes = document.getElementsByTagName("description");
            String image ="";
            String title = "";
            String link = "";
            for(int i=0;i<nodeList.getLength();i++){
                String cdata = nodeListDes.item(i+1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if(matcher.find()){
                    image = matcher.group(1);
                }
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element,"title");
                link = parser.getValue(element,"link");
                mdata.add(new Detail(title,link,image));
            }
            detailRecycle.setAdapter(detailAdapter);
            super.onPostExecute(s);
        }
    }

    @Override
    public void onDetailListClick(int clickedItemIndex) {

    }


    private void initReadRss(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                link_category = (String) getIntent().getExtras().get("link");
                new ReadData().execute(link_category);
            }
        });
    }
    //Đọc dữ liệu từ url
    private String ReadContent(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {

            URL url = new URL(theUrl);

            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            Log.d("content", content.toString());
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    private void setAnimation(int anim){
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(this, anim);
        detailRecycle.setLayoutAnimation(layoutAnimationController);
    }
}