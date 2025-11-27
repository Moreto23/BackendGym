package com.example.backendgym.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MailService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    @Value("${resend.api.key:}")
    private String apiKey;

    @Value("${resend.from:onboarding@resend.dev}")
    private String from;

    public MailService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Async
    public void sendHtml(@Nullable String to, String subject, String html) {
        if (to == null || to.isBlank()) {
            System.err.println("[MAIL] destinatario vacío, no se envía.");
            return;
        }
        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("[MAIL] RESEND_API_KEY no configurado, no se envía.");
            return;
        }

        try {
            Map<String, Object> body = new HashMap<>();
            body.put("from", from);
            body.put("to", List.of(to));
            body.put("subject", subject);
            body.put("html", html);

            String json = objectMapper.writeValueAsString(body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.resend.com/emails"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                System.out.println("[MAIL] Enviado a " + to + " asunto='" + subject + "'");
            } else {
                System.err.println("[MAIL] ERROR Resend status="
                        + response.statusCode() + " body=" + response.body());
            }

        } catch (Exception e) {
            System.err.println("[MAIL] ERROR enviando a " + to + ": "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Async
    public void sendHtmlWithAttachment(@Nullable String to,
                                       String subject,
                                       String html,
                                       byte[] attachmentBytes,
                                       String attachmentFilename,
                                       String contentType) {
        if (to == null || to.isBlank()) {
            System.err.println("[MAIL] destinatario vacío, no se envía (adjunto).");
            return;
        }
        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("[MAIL] RESEND_API_KEY no configurado, no se envía (adjunto).");
            return;
        }

        try {
            Map<String, Object> body = new HashMap<>();
            body.put("from", from);
            body.put("to", List.of(to));
            body.put("subject", subject);
            body.put("html", html);

            if (attachmentBytes != null && attachmentBytes.length > 0) {
                String base64 = Base64.getEncoder().encodeToString(attachmentBytes);

                Map<String, Object> attachment = new HashMap<>();
                attachment.put("filename",
                        attachmentFilename != null ? attachmentFilename : "comprobante.pdf");
                attachment.put("content", base64);
                attachment.put("type",
                        contentType != null ? contentType : "application/pdf");

                body.put("attachments", List.of(attachment));
            }

            String json = objectMapper.writeValueAsString(body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.resend.com/emails"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                System.out.println("[MAIL] Enviado con adjunto a " + to
                        + " asunto='" + subject + "'");
            } else {
                System.err.println("[MAIL] ERROR Resend (adjunto) status="
                        + response.statusCode() + " body=" + response.body());
            }

        } catch (Exception e) {
            System.err.println("[MAIL] ERROR enviando (adjunto) a " + to + ": "
                    + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
}
