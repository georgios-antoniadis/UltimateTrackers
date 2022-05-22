package gr.codelearn.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Simple entity that is needed to fetch log result from the database.
 */
@Data
@AllArgsConstructor
public class Log {
    private Long id;
    private Date visitDate;
}
