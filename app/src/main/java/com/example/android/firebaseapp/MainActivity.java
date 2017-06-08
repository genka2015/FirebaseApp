package com.example.android.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String DB_NAME = "hello-firebase-5cbd6";
    private DatabaseReference reference;
    private static final String USER_COLLECTION = "users";
    FirebaseDatabase database;
    private static final String TAG = "_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(USER_COLLECTION);
        Button button = (Button) findViewById(R.id.btn_add_user);
        button.setOnClickListener(this);
    }

    private void addUser(){
        String userId = reference.push().getKey();
        User user = new User("Genka", "genka@example.com");
        reference.child(userId).setValue(user);
        addUserChangeListener(userId);
    }

    private void addUserChangeListener(String userId){
        reference.child(userId).addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user == null){
                    Log.d(TAG, "onDataChange: User data is null.");
                    return;
                }

                Log.d(TAG, "onDataChange: User data is changed.");
                Log.d(TAG, "onDataChange: Name: " + user.getName() + " Email: " + user.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.e(TAG, "onCancelled: Failed to read user." , databaseError.toException() );
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_user:
                addUser();
                break;
            default:
                break;
        }
    }
}
