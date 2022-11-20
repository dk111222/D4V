package com.frostwire.d4v.gui.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.frostwire.d4v.R;
import com.frostwire.d4v.gui.fragments.PrivacyFragment;
import com.tvc.network.utils.NetworkUtils;

/**
 * 搜索界面
 */
public class PrivacyActivity extends AppCompatActivity {
    private static final String TAG = "PrivacyActivity";

    public static void show(Context context) {
        Intent intent = new Intent(context, PrivacyActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private PrivacyFragment pFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple_container);

        if (savedInstanceState == null) {
            pFragment = PrivacyFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, pFragment)
                    .commitNow();
        }
    }

    public void onBackPressed() {
        if (isPrivacyAgreed(this)) {
            super.onBackPressed();
        }
    }

    public static boolean isPrivacyAgreed(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean("privacy", false);
    }

    public static void agreePrivacy(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("privacy", true).apply();
    }
}