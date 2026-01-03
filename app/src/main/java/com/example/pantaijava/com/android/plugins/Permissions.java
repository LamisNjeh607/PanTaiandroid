package com.android.plugins;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Permissions extends CordovaPlugin {
    private static final String ACTION_CHECK_PERMISSION = "checkPermission";
    private static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 5469;
    private static final String ACTION_REQUEST_PERMISSION = "requestPermission";
    private static final String ACTION_REQUEST_PERMISSIONS = "requestPermissions";
    private static final String KEY_ERROR = "error";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_RESULT_PERMISSION = "hasPermission";
    private static final int REQUEST_CODE_ENABLE_PERMISSION = 55433;
    private static String TAG = "Permissions";
    /* access modifiers changed from: private */
    public CallbackContext permissionsCallback;

    public boolean execute(String str, final JSONArray jSONArray, final CallbackContext callbackContext) throws JSONException {
        if (ACTION_CHECK_PERMISSION.equals(str)) {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    Permissions.this.checkPermissionAction(callbackContext, jSONArray);
                }
            });
            return true;
        } else if (!ACTION_REQUEST_PERMISSION.equals(str) && !ACTION_REQUEST_PERMISSIONS.equals(str)) {
            return false;
        } else {
            this.cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    try {
                        Permissions.this.requestPermissionAction(callbackContext, jSONArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JSONObject jSONObject = new JSONObject();
                        Permissions.this.addProperty(jSONObject, Permissions.KEY_ERROR, Permissions.ACTION_REQUEST_PERMISSION);
                        Permissions.this.addProperty(jSONObject, Permissions.KEY_MESSAGE, "Request permission has been denied.");
                        callbackContext.error(jSONObject);
                        Permissions.this.permissionsCallback = null;
                    }
                }
            });
            return true;
        }
    }

    public void onRequestPermissionResult(int i, String[] strArr, int[] iArr) throws JSONException {
        if (this.permissionsCallback != null) {
            JSONObject jSONObject = new JSONObject();
            if (strArr == null || strArr.length <= 0) {
                addProperty(jSONObject, KEY_ERROR, ACTION_REQUEST_PERMISSION);
                addProperty(jSONObject, KEY_MESSAGE, "Unknown error.");
                this.permissionsCallback.error(jSONObject);
            } else {
                addProperty(jSONObject, KEY_RESULT_PERMISSION, Boolean.valueOf(hasAllPermissions(strArr)));
                this.permissionsCallback.success(jSONObject);
            }
            this.permissionsCallback = null;
        }
    }

    /* access modifiers changed from: private */
    public void checkPermissionAction(CallbackContext callbackContext, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0 || jSONArray.length() > 1) {
            JSONObject jSONObject = new JSONObject();
            addProperty(jSONObject, KEY_ERROR, ACTION_CHECK_PERMISSION);
            addProperty(jSONObject, KEY_MESSAGE, "One time one permission only.");
            callbackContext.error(jSONObject);
            return;
        }
        try {
            String string = jSONArray.getString(0);
            JSONObject jSONObject2 = new JSONObject();
            if ("android.permission.SYSTEM_ALERT_WINDOW".equals(string)) {
                addProperty(jSONObject2, KEY_RESULT_PERMISSION, Boolean.valueOf(Settings.canDrawOverlays(this.cordova.getActivity().getApplicationContext())));
            } else {
                addProperty(jSONObject2, KEY_RESULT_PERMISSION, Boolean.valueOf(this.cordova.hasPermission(string)));
            }
            callbackContext.success(jSONObject2);
        } catch (JSONException e) {
            JSONObject jSONObject3 = new JSONObject();
            addProperty(jSONObject3, KEY_ERROR, ACTION_REQUEST_PERMISSION);
            addProperty(jSONObject3, KEY_MESSAGE, "Check permission has been failed." + e);
            callbackContext.error(jSONObject3);
        }
    }

    /* access modifiers changed from: private */
    public void requestPermissionAction(CallbackContext callbackContext, JSONArray jSONArray) throws Exception {
        if (jSONArray == null || jSONArray.length() == 0) {
            JSONObject jSONObject = new JSONObject();
            addProperty(jSONObject, KEY_ERROR, ACTION_REQUEST_PERMISSION);
            addProperty(jSONObject, KEY_MESSAGE, "At least one permission.");
            callbackContext.error(jSONObject);
        } else if (hasAllPermissions(jSONArray)) {
            JSONObject jSONObject2 = new JSONObject();
            addProperty(jSONObject2, KEY_RESULT_PERMISSION, true);
            callbackContext.success(jSONObject2);
        } else {
            this.permissionsCallback = callbackContext;
            String[] permissions = getPermissions(jSONArray);
            if (permissions.length == 1 && "android.permission.SYSTEM_ALERT_WINDOW".equals(permissions[0])) {
                Log.i(TAG, "Request permission SYSTEM_ALERT_WINDOW");
                AppCompatActivity activity = this.cordova.getActivity();
                if (!Settings.canDrawOverlays(this.cordova.getActivity().getApplicationContext())) {
                    Log.w(TAG, "Request permission SYSTEM_ALERT_WINDOW start intent because canDrawOverlays=false");
                    activity.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + activity.getPackageName())), ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
                    return;
                }
            }
            this.cordova.requestPermissions(this, REQUEST_CODE_ENABLE_PERMISSION, permissions);
        }
    }

    private String[] getPermissions(JSONArray jSONArray) {
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                strArr[i] = jSONArray.getString(i);
            } catch (JSONException unused) {
            }
        }
        return strArr;
    }

    private boolean hasAllPermissions(JSONArray jSONArray) throws JSONException {
        return hasAllPermissions(getPermissions(jSONArray));
    }

    private boolean hasAllPermissions(String[] strArr) throws JSONException {
        for (String hasPermission : strArr) {
            if (!this.cordova.hasPermission(hasPermission)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void addProperty(JSONObject jSONObject, String str, Object obj) {
        if (obj == null) {
            try {
                jSONObject.put(str, JSONObject.NULL);
            } catch (JSONException unused) {
            }
        } else {
            jSONObject.put(str, obj);
        }
    }
}
