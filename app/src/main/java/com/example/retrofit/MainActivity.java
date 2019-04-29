package com.example.retrofit;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Retrofit retrofit;
    JPHAPI jphapi;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view_result);
        progressBar = findViewById(R.id.progrssbar);

        progressBar.setMax(100);

        retrofit = new Retrofit.Builder().baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        JPHAPI jsonPlaceHolderApi = retrofit.create(JPHAPI.class);

        Call<List<CommentC>> call = jsonPlaceHolderApi.getcomment();

        call.enqueue(new Callback<List<CommentC>>() {
            @Override
            public void onResponse(Call<List<CommentC>> call, Response<List<CommentC>> response) {
                if(!response.isSuccessful()){
                    textView.setText(response.code());
                }

                List<CommentC> commentCS = response.body();

                for(CommentC commentC :commentCS){

                    String content = "";
                    content += "Post ID:"+commentC.getPostid() + "\n";
                    content += " ID:" + commentC.getId() + "\n";
                    content += "Name:" + commentC.getName() + "\n";
                    content += "Email:" + commentC.getEmail() + "\n";
                    content += "Body:" + commentC.getText() + "\n";

                    textView.append(content);
                    progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onFailure(Call<List<CommentC>> call, Throwable t) {

            }
        });


    }
}
