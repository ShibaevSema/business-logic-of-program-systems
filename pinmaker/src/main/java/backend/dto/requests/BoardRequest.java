package backend.dto.requests;

import backend.exceptions.ErrorEnum;
import lombok.Data;

@Data
public class BoardRequest {
    private String name;
    private boolean isPublic;
    private Long userId;

    public BoardRequest() {
    }

    public void validate() {
        if (this.name == null || this.name.isEmpty())
            throw ErrorEnum.NULL_BOARD_NAME.exception();
    }
}

