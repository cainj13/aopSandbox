package org.semper.reformanda.training.domain;

public class NeedMoMoneyException extends Exception {

    public NeedMoMoneyException(String s) {
        super(s);
    }

    public NeedMoMoneyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NeedMoMoneyException(Throwable throwable) {
        super(throwable);
    }
}