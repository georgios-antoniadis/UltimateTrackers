package gr.codelearn.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Simple entity that groups together results from the database.
 */
@Data
@AllArgsConstructor
public class Die {
    private Long id;
    private Integer result;
    private Date throwDate;
}
