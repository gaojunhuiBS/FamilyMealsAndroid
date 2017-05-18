package com.example.gaojunhui.textworld.rest.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class Data implements Serializable{
    @SerializedName("id")
    private String id;
    @SerializedName("userId")
    private String userId;
    @SerializedName("content")
    private String content;
    @SerializedName("isBest")
    private Object isBest;
    @SerializedName("isTop")
    private Object isTop;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("replyCount")
    private int replyCount;
    @SerializedName("praiseCount")
    private int praiseCount;
    @SerializedName("isDeleted")
    private int isDeleted;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("user")
    private UserBean user;
    @SerializedName("isPraised")
    private int isPraised;
    @SerializedName("userAtTag")
    private Object userAtTag;
    @SerializedName("deleteReason")
    private Object deleteReason;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("postedAt")
    private String postedAt;
    @SerializedName("parentId")
    private String parentId;
    @SerializedName("answerUserNick")
    private String answerUserNick;
    @SerializedName("answerUserId")
    private String answerUserId;
    @SerializedName("userNick")
    private String userNick;

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerUserId() {
        return answerUserId;
    }

    public void setAnswerUserId(String answerUserId) {
        this.answerUserId = answerUserId;
    }

    public String getAnswerUserNick() {
        return answerUserNick;
    }

    public void setAnswerUserNick(String answerUserNick) {
        this.answerUserNick = answerUserNick;
    }

    @SerializedName("answerContent")
    private String answerContent;
    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @SerializedName("parent")
    private Parent parent;

    @SerializedName("replyList")
    private List<ReplyListBean> replyList;

    @SerializedName("imageList")
    private List<ImageListBean> imageList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Object getIsBest() {
        return isBest;
    }

    public void setIsBest(Object isBest) {
        this.isBest = isBest;
    }

    public Object getIsTop() {
        return isTop;
    }

    public void setIsTop(Object isTop) {
        this.isTop = isTop;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
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

    public int getIsPraised() {
        return isPraised;
    }

    public void setIsPraised(int isPraised) {
        this.isPraised = isPraised;
    }

    public Object getUserAtTag() {
        return userAtTag;
    }

    public void setUserAtTag(Object userAtTag) {
        this.userAtTag = userAtTag;
    }

    public Object getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(Object deleteReason) {
        this.deleteReason = deleteReason;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(String postedAt) {
        this.postedAt = postedAt;
    }

    public List<ReplyListBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListBean> replyList) {
        this.replyList = replyList;
    }

    public List<ImageListBean> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageListBean> imageList) {
        this.imageList = imageList;
    }

}
