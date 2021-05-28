package com.example.BookReview.business.model.observer;

public abstract class CustomSubject {
    public abstract void notifyObservers(Long id);
    public abstract void addObserver(CustomObserver observer);
    public abstract void deleteObserver(CustomObserver observer);
}
