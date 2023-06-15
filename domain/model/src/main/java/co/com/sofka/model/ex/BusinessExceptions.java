package co.com.sofka.model.ex;

import java.util.function.Supplier;

public class BusinessExceptions extends AppException{

    public enum Type {

        INVALID_ID_ACOUNT("No existe cuenta con ese id"),
        INVALID_TOKEN("Token no valido"),
        INVALID_ID_CLIENT("No existe cliente con ese id"),
        INVALID_ID_MOVE("No existe movimiento con ese id"),
        INVALID_ID_BALANCE_CERO("saldo igual a cero"),
        INVALID_ID_BALANCE("saldo inferior a valor a retirar");

        private final String message;

        public String getMessage() {
            return message;
        }

        public BusinessExceptions build() {
            return new BusinessExceptions(this);
        }

        public Supplier<Throwable> defer() {
            return () -> new BusinessExceptions(this);
        }

        Type(String message) {
            this.message = message;
        }

    }

    private final Type type;

    public BusinessExceptions(Type type){
        super(type.message);
        this.type = type;
    }

    @Override
    public String getCode(){
        return type.name();
    }
}
