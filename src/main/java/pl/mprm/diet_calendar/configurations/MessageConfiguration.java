package pl.mprm.diet_calendar.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "message")
public class MessageConfiguration {
    private String duplicatedMessage;
}
