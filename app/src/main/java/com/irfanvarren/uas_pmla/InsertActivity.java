package com.irfanvarren.uas_pmla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.irfanvarren.uas_pmla.model.PostPutDelUser;
import com.irfanvarren.uas_pmla.rest.ApiClient;
import com.irfanvarren.uas_pmla.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    EditText editFirstName,editLastName,editAvatar,editEmail;
    Button btnInsert, btnBack;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        editFirstName = (EditText) findViewById(R.id.editFirstName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editAvatar= (EditText) findViewById(R.id.editAvatar);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelUser> postUserCall =  mApiInterface.postUser(editFirstName.getText().toString(),editLastName.getText().toString(), editEmail.getText().toString(),editAvatar.getText().toString());
                postUserCall.enqueue(new Callback<PostPutDelUser>(){
                @Override
                    public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response){
                    MainActivity.ma.refresh();
                    finish();
                }

                    @Override public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }


                });
            }
        });
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ma.refresh();
                finish();
            }
        });
    }
}