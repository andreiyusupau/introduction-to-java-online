package com.nevermind.notepad.model;

import java.time.LocalDate;

/*  • У пользователя должна быть возможность найти запись по любому параметру
        или по группе параметров (группу параметров можно определить
        самостоятельно), получить требуемые записи в отсортированном виде, найти
        записи, текстовое поле которой содержит определенное слово, а также
        добавить новую запись.*/
@Deprecated
public class QueryBuilder {
   private String topicContains;
    private LocalDate from;
    private LocalDate to;
    private String email;
    private String messageContains;
    private boolean sorted=false;

    public QueryBuilder topicContains(String topicContains){
        this.topicContains=topicContains;
        return this;
    }

    public QueryBuilder dateFrom(LocalDate from){
        this.from=from;
        return this;
    }

    public QueryBuilder dateTo(LocalDate to){
        this.to=to;
        return this;
    }

    public QueryBuilder withEmail(String email){
        this.email=email;
        return this;
    }

    public QueryBuilder messageContains(String messageContains){
        this.messageContains=messageContains;
        return this;
    }

    public QueryBuilder sorted(){
        sorted=true;
        return this;
    }

    public String getTopicContains() {
        return topicContains;
    }

    public void setTopicContains(String topicContains) {
        this.topicContains = topicContains;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessageContains() {
        return messageContains;
    }

    public void setMessageContains(String messageContains) {
        this.messageContains = messageContains;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }
}
