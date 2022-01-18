package ships;

public class SeePortMain {
    public static void main(String[] args) {
        Port port = Port.getInstance(20, 3, 10);
        for (Pier pier:
             port.getPiers()) {
            System.out.println("Pier " + pier.getPierId());
        }

        LogisticDepartment logisticDepartment = new LogisticDepartment(port);
    }
}
