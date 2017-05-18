package com.example.gaojunhui.textworld.rest.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public  class UserBean implements Serializable {
            @SerializedName("userId")
            private String userId;
            @SerializedName("nickName")
            private String nickName;
            @SerializedName("avatar")
            private String avatar;
            @SerializedName("trueName")
            private String trueName;
            @SerializedName("sex")
            private Object sex;
            @SerializedName("birth")
            private Object birth;
            @SerializedName("createdAt")
            private Object createdAt;
            @SerializedName("updatedAt")
            private Object updatedAt;
            @SerializedName("level")
            private int level;
            @SerializedName("lvName")
            private String lvName;
            @SerializedName("attentionCount")
            private int attentionCount;
            @SerializedName("fansCount")
            private int fansCount;
            @SerializedName("topicCount")
            private int topicCount;
            @SerializedName("tagList")
            private List<?> tagList;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getTrueName() {
                return trueName;
            }

            public void setTrueName(String trueName) {
                this.trueName = trueName;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getBirth() {
                return birth;
            }

            public void setBirth(Object birth) {
                this.birth = birth;
            }

            public Object getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(Object createdAt) {
                this.createdAt = createdAt;
            }

            public Object getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(Object updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getLvName() {
                return lvName;
            }

            public void setLvName(String lvName) {
                this.lvName = lvName;
            }

            public int getAttentionCount() {
                return attentionCount;
            }

            public void setAttentionCount(int attentionCount) {
                this.attentionCount = attentionCount;
            }

            public int getFansCount() {
                return fansCount;
            }

            public void setFansCount(int fansCount) {
                this.fansCount = fansCount;
            }

            public int getTopicCount() {
                return topicCount;
            }

            public void setTopicCount(int topicCount) {
                this.topicCount = topicCount;
            }

            public List<?> getTagList() {
                return tagList;
            }

            public void setTagList(List<?> tagList) {
                this.tagList = tagList;
            }
        }
