package bsu.edummf.task12.exception;

public class AuctionException extends Exception {
    public AuctionException() {}

    public AuctionException(String message) {
        super(message);
    }

    public AuctionException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuctionException(Throwable cause) {
        super(cause);
    }

    public AuctionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}