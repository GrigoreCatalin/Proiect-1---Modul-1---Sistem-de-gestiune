package Exception;

public class InvalidNumberException extends Exception {

    public InvalidNumberException() {
        super("Introduceti un numar mai mare ca 0.");
    }
}