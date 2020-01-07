package hospital.management.Hospital.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){
        super("The Object was not found!");
    }
}
