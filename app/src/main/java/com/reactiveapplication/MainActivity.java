package com.reactiveapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reactiveapplication.observability.Injection;
import com.reactiveapplication.observability.ui.UserViewModel;
import com.reactiveapplication.observability.ui.ViewModelFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mUserName;
    private EditText mUserNameInput;
    private Button mUpdateButton;
    private ViewModelFactory mViewModelFactory;
    private UserViewModel mViewModel;
    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = findViewById(R.id.user_name);
        mUserNameInput = findViewById(R.id.user_name_input);
        mUpdateButton = findViewById(R.id.update_user);

        mViewModelFactory = Injection.provideViewModelFactory(this);
        mViewModel = new ViewModelProvider(this, mViewModelFactory).get(UserViewModel.class);
        mUpdateButton.setOnClickListener(v -> updateUserName());
    }

    @Override
    protected void onStart() {
        super.onStart();

        mDisposable.add(mViewModel.getUserName()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(userName -> mUserName.setText(userName),
                throwable -> Log.e(TAG, "Unable to gte UserName", throwable)));

    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposable.clear();
    }

    private void updateUserName() {
        String userName = mUserNameInput.getText().toString();
        mUpdateButton.setEnabled(false);
        mDisposable.add(mViewModel.updateUserName(userName)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(() -> mUpdateButton.setEnabled(true),
                throwable -> Log.e(TAG, "Unable to update username", throwable)));
    }
}
