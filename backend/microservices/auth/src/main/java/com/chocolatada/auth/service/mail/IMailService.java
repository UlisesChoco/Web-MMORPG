package com.chocolatada.auth.service.mail;

/**
 * Interfaz para el servicio de envío de correos electrónicos.
 */
public interface IMailService {

    /**
     * Envía un correo de verificación al usuario registrado.
     *
     * @param email      Email del usuario
     * @param verificationLink Link de verificación para confirmar el email
     */
    void sendVerificationEmail(String email, String verificationLink);

    /**
     * Envía un correo de notificación de login.
     *
     * @param email Email del usuario
     */
    void sendLoginNotification(String email);

    /**
     * Envía un correo genérico.
     *
     * @param toEmail Email del destinatario
     * @param subject Asunto del correo
     * @param body    Cuerpo del correo
     */
    void sendEmail(String toEmail, String subject, String body);
}
