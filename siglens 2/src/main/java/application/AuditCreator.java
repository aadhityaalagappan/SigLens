package application;

import controller.AuditController;
import model.AuditEvent;

import java.util.List;

public class AuditCreator {
    public static void main(String[] args) {
        AuditController.createAuditEvent("Aadhitya", "User logged in", 16666L, 12345, "User logged in successfully");
        AuditController.createAuditEvent("Alagappan", "User deleted index", 18666L, 23443, "User deleted successfully");

        List<AuditEvent> logs = AuditController.readAuditEvent(12345, 16660L, 19666L);

        for (AuditEvent log : logs) {
            System.out.println(log);
        }
    }
}
