package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.result.AuthAccount;
import com.huawei.hms.support.account.service.AccountAuthService;

public class Account extends AppCompatActivity {
    private static final String TAG = "IntegrationAccountKit";
    AccountAuthParams authParams;
    AccountAuthService authService;
    Button btnSignInID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignInID = findViewById(R.id.btnSignInID);
        btnSignInID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call signinID method
                signInId();
            }
        });
    }

    ActivityResultLauncher<Intent> signInIDResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //xu ly ket qua tra ve khi dang nhap
                    Intent data = result.getData();
                    //id-token signIn
                    Task<AuthAccount> authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data);
                    if(authAccountTask.isSuccessful()){
                        AuthAccount authAccount = authAccountTask.getResult();
                        Log.i(TAG, authAccount.getDisplayName() + " signIn success ");
                        Log.i(TAG,"idToken + {" + authAccount.getIdToken()+"}");
                        setContentView(R.layout.activity);
                    }else {
                        Log.i(TAG,"sign in failed: " + ((ApiException)authAccountTask.getException()).getStatusCode());
                    }
                }
            });

    private void signInId(){
        authParams = new AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM).
                setIdToken().setAccessToken().createParams();
        authService= AccountAuthManager.getService(Account.this, authParams);
        signInIDResult.launch(authService.getSignInIntent());
    }
}


