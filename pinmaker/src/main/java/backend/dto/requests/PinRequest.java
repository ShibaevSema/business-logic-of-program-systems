package backend.dto.requests;

import backend.exceptions.ErrorEnum;
import lombok.Data;

@Data
public class PinRequest {
    private String name;
    private Long board_id;
    private String description;
    private String alt_text;
    private String link;
    private Long userId;
    private String fileName;

    public PinRequest() {
    }

    public void validate() {
        if (this.board_id == null)
            throw ErrorEnum.NULL_BOARD_NAME.exception();

        if (this.fileName == null || this.fileName.trim().isEmpty())
            throw ErrorEnum.PHOTO_NOT_UPLOADED.exception();
    }
}
