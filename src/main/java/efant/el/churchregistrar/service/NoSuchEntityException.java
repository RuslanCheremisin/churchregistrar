package efant.el.churchregistrar.service;

public class NoSuchEntityException extends RuntimeException {
    public NoSuchEntityException(String message){
        super(message);
    }
}
