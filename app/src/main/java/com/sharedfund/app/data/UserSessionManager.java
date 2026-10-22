package com.sharedfund.app.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.sharedfund.app.model.Profile;

/**
 * UserSessionManager - Quản lý phiên đăng nhập người dùng
 * Lưu trữ thông tin người dùng vào SharedPreferences để duy trì phiên đăng nhập.
 * Tích hợp với Supabase Auth ở giai đoạn sau: thay thế mock bằng JWT token từ API.
 */
public class UserSessionManager {

    private static final String PREF_NAME = "sharedfund_session";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_AVATAR_URL = "avatar_url";

    private static UserSessionManager instance;
    private final SharedPreferences prefs;

    private UserSessionManager(Context context) {
        prefs = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized UserSessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserSessionManager(context);
        }
        return instance;
    }

    /**
     * Lưu phiên đăng nhập sau khi xác thực thành công
     */
    public void saveSession(String userId, String email, String fullName, String avatarUrl) {
        prefs.edit()
                .putBoolean(KEY_IS_LOGGED_IN, true)
                .putString(KEY_USER_ID, userId)
                .putString(KEY_EMAIL, email)
                .putString(KEY_FULL_NAME, fullName)
                .putString(KEY_AVATAR_URL, avatarUrl)
                .apply();
    }

    /**
     * Kiểm tra trạng thái đăng nhập
     */
    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    /**
     * Lấy thông tin profile người dùng hiện tại
     */
    public Profile getCurrentUser() {
        if (!isLoggedIn()) return null;
        return new Profile(
                prefs.getString(KEY_USER_ID, ""),
                prefs.getString(KEY_EMAIL, ""),
                prefs.getString(KEY_FULL_NAME, ""),
                prefs.getString(KEY_AVATAR_URL, "")
        );
    }

    public String getCurrentUserId() {
        return prefs.getString(KEY_USER_ID, "");
    }

    public String getCurrentUserName() {
        return prefs.getString(KEY_FULL_NAME, "Người dùng");
    }

    public String getCurrentUserEmail() {
        return prefs.getString(KEY_EMAIL, "");
    }

    /**
     * Xóa phiên đăng nhập (Đăng xuất)
     */
    public void clearSession() {
        prefs.edit().clear().apply();
    }
}
