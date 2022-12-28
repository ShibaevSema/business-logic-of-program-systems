package backend.services.pinService;

import backend.dto.requests.PinRequest;
import backend.dto.responses.PinWithPhotoResponse;

import java.util.List;

public interface PinService {
    public void createPin(PinRequest pinRequest) throws Exception;

    public List<PinWithPhotoResponse> findUserPins(Long id);

    public List<PinWithPhotoResponse> findBoardPins(Long id);

    public boolean findPin(Long id);
}
