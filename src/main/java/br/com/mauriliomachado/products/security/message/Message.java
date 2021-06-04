

package br.com.mauriliomachado.products.security.message;

public class Message {

    private String message;

    public Message(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
