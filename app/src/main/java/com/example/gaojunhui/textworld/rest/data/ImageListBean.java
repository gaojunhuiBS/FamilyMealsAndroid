package com.example.gaojunhui.textworld.rest.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public  class ImageListBean implements Serializable {
        @SerializedName("id")
        private long id;
        @SerializedName("activeId")
        private long activeId;
        @SerializedName("userId")
        private String userId;
        @SerializedName("type")
        private String type;
        @SerializedName("title")
        private String title;
        @SerializedName("fileUrl")
        private String fileUrl;
        @SerializedName("thumbUrl")
        private String thumbUrl;
        @SerializedName("isDeleted")
        private int isDeleted;
        @SerializedName("createdAt")
        private String createdAt;
        @SerializedName("updatedAt")
        private String updatedAt;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getActiveId() {
            return activeId;
        }

        public void setActiveId(long activeId) {
            this.activeId = activeId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }