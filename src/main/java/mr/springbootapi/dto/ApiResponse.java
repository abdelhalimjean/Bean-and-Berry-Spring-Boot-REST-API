package mr.springbootapi.dto;

/**
 * ApiResponse is a simple data transfer object (DTO) representing a response
 * returned by the API. It contains a message and optional data.
 */
public class ApiResponse {
    // HTTP status code indicating the status of the response
    private int status;
    // A message providing additional information about the response
    private String message;
    // The actual data returned by the API (can be of any type)
    private Object data;

    /**
     * Constructs a new ApiResponse with the given status, message, and data.
     *
     * @param status  The HTTP status code associated with the response.
     * @param message The message associated with the response.
     * @param data    The data associated with the response.
     */
    public ApiResponse(int status, String message, Object data) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    /**
     * Retrieves the HTTP status code associated with the response.
     *
     * @return The HTTP status code associated with the response.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code associated with the response.
     *
     * @param status The HTTP status code to set.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Retrieves the message associated with the response.
     *
     * @return The message associated with the response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the data associated with the response.
     *
     * @return The data associated with the response.
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the message associated with the response.
     *
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sets the data associated with the response.
     *
     * @param data The data to set.
     */
    public void setData(Object data) {
        this.data = data;
    }
}
