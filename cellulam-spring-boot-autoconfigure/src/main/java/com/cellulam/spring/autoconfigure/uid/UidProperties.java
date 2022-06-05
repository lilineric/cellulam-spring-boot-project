package com.cellulam.spring.autoconfigure.uid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.cellulam.uid")
public class UidProperties {
    /**
     * start timestamp
     * It should be as close to the release time as possible.
     * Can not be changed once it released.
     */
    private long epoch = 1654238882L;

    /**
     * Take the last digits of the pid and append them to the UID
     */
    private int pidSubDigits = 3;

    /**
     * The workerId cannot be null when using {@link com.cellulam.uid.snowflake.SnowflakeGenerator}
     * The workerId will be ignored when using {@link com.cellulam.uid.metadata.MetadataUidGenerator} or {@link com.cellulam.uid.metadata.MetadataAppendUidGenerator}
     */
    private Long workerId;
}
