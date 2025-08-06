package io.github.keishispl.knockDC.utils;

import com.google.gson.JsonParser;
import io.github.keishispl.knockDC.KnockDC;
import org.bukkit.Bukkit;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class getVersion {
    public static String bukkit() {
        return Bukkit.getBukkitVersion().split("-")[0];
    }

    public static String latest() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.modrinth.com/v2/project/knockdc/version"))
                .build();
        try {
            String body = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get().body();
            return JsonParser.parseString(body).getAsJsonArray().get(0).getAsJsonObject().get("version_number").getAsString();
        } catch (Exception e) {
            return KnockDC.getPlugin().getDescription().getVersion();
        }
    }
}
