package com.example.gaojunhui.textworld.rest.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Parent implements Serializable{
            @SerializedName("id")
            private int id;
            @SerializedName("activeId")
            private long activeId;
            @SerializedName("parentId")
            private Object parentId;
            @SerializedName("parent")
            private Object parent;
            @SerializedName("userId")
            private String userId;
            @SerializedName("content")
            private String content;
            @SerializedName("updatedAt")
            private String updatedAt;
            @SerializedName("userAtTag")
            private Object userAtTag;
            @SerializedName("userAnswerTag")
            private Object userAnswerTag;
            @SerializedName("isDeleted")
            private int isDeleted;
            @SerializedName("createdAt")
            private String createdAt;
            @SerializedName("user")
            private UserBean user;
            @SerializedName("deleteReason")
            private String deleteReason;
            @SerializedName("timestamp")
            private long timestamp;
            @SerializedName("postedAt")
            private String postedAt;
            @SerializedName("userAnswerTagAsString")
            private Object userAnswerTagAsString;
            @SerializedName("imageList")
            private List<?> imageList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getActiveId() {
                return activeId;
            }

            public void setActiveId(long activeId) {
                this.activeId = activeId;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public Object getParent() {
                return parent;
            }

            public void setParent(Object parent) {
                this.parent = parent;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public Object getUserAtTag() {
                return userAtTag;
            }

            public void setUserAtTag(Object userAtTag) {
                this.userAtTag = userAtTag;
            }

            public Object getUserAnswerTag() {
                return userAnswerTag;
            }

            public void setUserAnswerTag(Object userAnswerTag) {
                this.userAnswerTag = userAnswerTag;
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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getDeleteReason() {
                return deleteReason;
            }

            public void setDeleteReason(String deleteReason) {
                this.deleteReason = deleteReason;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            public String getPostedAt() {
                return postedAt;
            }

            public void setPostedAt(String postedAt) {
                this.postedAt = postedAt;
            }

            public Object getUserAnswerTagAsString() {
                return userAnswerTagAsString;
            }

            public void setUserAnswerTagAsString(Object userAnswerTagAsString) {
                this.userAnswerTagAsString = userAnswerTagAsString;
            }

            public List<?> getImageList() {
                return imageList;
            }

            public void setImageList(List<?> imageList) {
                this.imageList = imageList;
            }
        }