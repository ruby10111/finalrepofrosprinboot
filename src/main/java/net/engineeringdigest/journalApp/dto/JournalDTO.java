package net.engineeringdigest.journalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.engineeringdigest.journalApp.enums.Sentiment;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalDTO {
    @NonNull
    private String title;
    private String content;
    private Sentiment sentiment;
}
