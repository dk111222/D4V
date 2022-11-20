package com.frostwire.d4v.gui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.frostwire.d4v.R;
import com.frostwire.d4v.gui.activities.MainActivity;
import com.frostwire.d4v.gui.activities.PrivacyActivity;
import com.tvc.network.utils.NetworkUtils;

/**
 * 购买会员
 */
public class PrivacyFragment extends Fragment {
    private static final String TAG = "PrivacyFragment";

    private final String PAY_HOSt = "https://365vpnapp.gitbook.io/privacy-agreement/";

    public static PrivacyFragment newInstance() {
        return new PrivacyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_privacy, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View root) {
        WebView payWebview = root.findViewById(R.id.webview);

        payWebview.clearCache(true);
        configWebView(payWebview);
        payWebview.loadUrl(PAY_HOSt);

        //系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
        payWebview.setWebChromeClient(new WebChromeClient());
        payWebview.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            public void onPageFinished(WebView view, String url) {
            }

        });


        root.findViewById(R.id.agree).setOnClickListener(v->{
            Context context = getContext();
            if (NetworkUtils.hasNetwork(context)) {
                getActivity().finish();
                if (!PrivacyActivity.isPrivacyAgreed(context)) {
                    PrivacyActivity.agreePrivacy(context);

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }


            } else {
                Toast.makeText(context, "Network Error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configWebView(WebView payWebview) {
        WebSettings webSettings = payWebview.getSettings();
        // 无缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
// User settings
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：
         * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    }

//    public void showExitAlertDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_DeviceDefault_Light_Dialog);
//        builder.setMessage(R.string.alert_pay_exit);
//        builder.setPositiveButton(R.string.btn_pay_exit, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                getActivity().finish();
//            }
//        });
//
//        builder.setNegativeButton(R.string.btn_pay_continue, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.create().show();
//    }
}
