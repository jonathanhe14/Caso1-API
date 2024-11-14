package ac.cr.cenfotec.caso1.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GenericResponse {
    LocalDateTime dateTime;
    List<Object> data;
    ResponseMetadata metadata;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public ResponseMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(ResponseMetadata metadata) {
        this.metadata = metadata;
    }

    public GenericResponse(List<Object> data, ResponseMetadata metadata) {
        this.dateTime = LocalDateTime.now();
        this.data = data;
        this.metadata = metadata;
    }

    public GenericResponse(Object data) {
        this.dateTime = LocalDateTime.now();
        this.data = new ArrayList<>();
        this.metadata = null;
        this.data.add(data);
    }
}
