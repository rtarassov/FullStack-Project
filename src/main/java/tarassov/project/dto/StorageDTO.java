package tarassov.project.dto;

import lombok.Data;


@Data
public class StorageDTO {
    private String name;
    private String description;
    private Long subStorageId;
}
