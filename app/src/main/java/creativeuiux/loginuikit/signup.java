package creativeuiux.loginuikit;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
TextView name,username,pass,register;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(TextView)findViewById(R.id.name);
        username=(TextView)findViewById(R.id.username_input);
        pass=(TextView)findViewById(R.id.pass);
        mauth=FirebaseAuth.getInstance();
        register=(TextView)findViewById(R.id.sign);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=username.getText().toString();
                String passs=pass.getText().toString();
                if(TextUtils.isEmpty(Email)){
                    username.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(passs)){
                    pass.setError("Password is Required.");
                    return;
                }
                if(passs.length()<6){
                    pass.setError("Password is too short");
                    return;
                }
                //Register the user in firebase
                mauth.createUserWithEmailAndPassword(Email,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signup.this,"User Created",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(signup.this,Logged.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(signup.this,"Error Occurred"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}
