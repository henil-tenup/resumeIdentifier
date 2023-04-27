package com.tenup.resumeIdentifier.controller.dto;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class ResponseDTO<T> {

    /**
     * @param httpResponseBody httpResponseBody.
     * @param httpStatus       httpResponseBody.
     */
    public ResponseDTO(T httpResponseBody, MultiValueMap<String, String> headers, HttpStatus httpStatus) {
        super();
        this.body = httpResponseBody;
        this.headers = headers;
        this.statusCode = httpStatus;
    }

    /**
     * @param httpResponseBody httpResponseBody.
     * @param httpStatus       httpResponseBody.
     */
    public ResponseDTO(T httpResponseBody, HttpStatus httpStatus) {
        super();
        this.body = httpResponseBody;
        this.statusCode = httpStatus;
    }

    /**
     * Constructor.
     */
    public ResponseDTO() {
        super();
    }

    /**
     * Generic Response Body.
     */
    private T body;
    /**
     * Http Status Response code.
     */
    private HttpStatus statusCode;

    /**
     * The headers.
     */
    private MultiValueMap<String, String> headers;

    /**
     * Getter for Http Response Body.
     *
     * @return Generic Object.
     */
    public final T getBody() {
        return body;
    }

    /**
     * Getter for Http Status.
     *
     * @return integer
     */
    public final HttpStatus getHttpStatus() {
        return statusCode;
    }

    /**
     * @return the headers
     */
    public MultiValueMap<String, String> getHeaders() {
        return headers;
    }

    /**
     * @param headers the headers to set
     */
    public void setHeaders(MultiValueMap<String, String> headers) {
        this.headers = headers;
    }

    /**
     * @param httpResponseBody the httpResponseBody to set
     */
    public void setBody(T httpResponseBody) {
        this.body = httpResponseBody;
    }

    /**
     * @param httpStatus the httpStatus to set
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.statusCode = httpStatus;
    }

}

