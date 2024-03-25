package hei.school.soratra.endpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class SoratraApplication {

    // Simulated database to store the phrases
    private final Map<String, String> phrasesDatabase = new HashMap<>();

    @PutMapping("/soratra/{id}")
    public ResponseEntity<Void> putSoratra(@PathVariable String id, @RequestBody Map<String, String> requestData) {
        if (!requestData.containsKey("phrase")) {
            return ResponseEntity.badRequest().build();
        }

        String phrase = requestData.get("phrase");
        phrasesDatabase.put(id, phrase);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/soratra/{id}")
    public ResponseEntity<Map<String, String>> getSoratra(@PathVariable String id) {
        if (!phrasesDatabase.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        String originalUrl = "https://original.url/" + id;
        String transformedUrl = "https://transformed.url/" + id;

        Map<String, String> responseData = new HashMap<>();
        responseData.put("original_url", originalUrl);
        responseData.put("transformed_url", transformedUrl);

        return ResponseEntity.ok(responseData);
    }

    public static void main(String[] args) {
        SpringApplication.run(SoratraApplication.class, args);
    }
}
