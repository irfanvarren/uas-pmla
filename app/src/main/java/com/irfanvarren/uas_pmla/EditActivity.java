package com.irfanvarren.uas_pmla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class EditActivity extends AppCompatActivity {
    EditText editId,editFirstName,editLastName,editEmail,editAvatar;
    Button btnUpdate, btnDelete, btnBack;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        editId = (EditText) findViewById(R.id.editID);
        editId.setText(intent.getStringExtra("Id"));
        editFirstName = (EditText) findViewById(R.id.editFirstName);
        editFirstName.setText(intent.getStringExtra("First Name"));
        editLastName = (EditText) findViewById(R.id.editLastName);
        editLastName.setText(intent.getStringExtra("Last Name"));
        editEmail = (EditText) findViewById(R.id.editEmail);
        editEmail.setText(intent.getStringExtra("Email"));
        editAvatar = (EditText) findViewById(R.id.editAvatar);
        editAvatar.setText(intent.getStringExtra("Avatar"));

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelUser> postUserCall =  mApiInterface.putUser(editFirstName.getText().toString(),editLastName.getText().toString(), editEmail.getText().toString(),editAvatar.getText().toString());
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

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelUser> postUserCall =  mApiInterface.deleteUser(editId.getText().toString());
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