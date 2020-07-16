package service.validation;

public class ClientValidation {
    private static final String LOGIN_REGEXP = "^[а-яА-ЯёЁa-zA-Z0-9]+$";
    private static final String PASSWORD_REGEXP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    private static final String MAIL_REGEXP = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";


}
