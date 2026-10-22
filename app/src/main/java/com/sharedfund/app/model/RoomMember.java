package com.sharedfund.app.model;

import java.io.Serializable;

/**
 * Model biểu diễn thành viên trong quỹ (RoomMember)
 * Dùng để hiển thị danh sách thành viên kèm số tiền đóng góp và vai trò
 */
public class RoomMember implements Serializable {

    private String userId;
    private String displayName;
    private String avatarUrl;
    private double contributedAmount; // Tổng số tiền đã đóng góp
    private boolean isAdmin;          // Trưởng quỹ hay không
    private String joinedAt;

    public RoomMember() {}

    public RoomMember(String userId, String displayName, String avatarUrl,
                      double contributedAmount, boolean isAdmin, String joinedAt) {
        this.userId = userId;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
        this.contributedAmount = contributedAmount;
        this.isAdmin = isAdmin;
        this.joinedAt = joinedAt;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public double getContributedAmount() { return contributedAmount; }
    public void setContributedAmount(double contributedAmount) { this.contributedAmount = contributedAmount; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public String getJoinedAt() { return joinedAt; }
    public void setJoinedAt(String joinedAt) { this.joinedAt = joinedAt; }

    /**
     * Lấy chữ cái đầu của tên để hiển thị avatar
     */
    public String getInitials() {
        if (displayName == null || displayName.isEmpty()) return "?";
        String[] parts = displayName.trim().split(" ");
        if (parts.length >= 2) {
            return String.valueOf(parts[0].charAt(0)).toUpperCase()
                    + String.valueOf(parts[parts.length - 1].charAt(0)).toUpperCase();
        }
        return String.valueOf(displayName.charAt(0)).toUpperCase();
    }
}
