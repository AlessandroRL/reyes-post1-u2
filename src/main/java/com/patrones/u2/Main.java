package com.patrones.u2;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Demo: Singleton + Factory Method ===\n");


        NotificationLogger logger1 = NotificationLogger.INSTANCE;
        NotificationLogger logger2 = NotificationLogger.INSTANCE;
        System.out.println("Misma instancia: " + (logger1 == logger2));

        Notifier email = NotifierFactory.create("email");
        email.send("usuario@ejemplo.com", "Bienvenido");

        NotifierFactory.register("slack", () -> new Notifier() {
            public String channel() { return "SLACK"; }
            public void send(String r, String m) { NotificationLogger.INSTANCE.log(channel(), r, m); }
        });

        NotifierFactory.create("slack").send("#general", "Notificación de sistema");

        NotificationLogger.INSTANCE.printAll();
    }
}