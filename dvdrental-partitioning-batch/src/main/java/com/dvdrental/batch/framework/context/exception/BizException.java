package com.dvdrental.batch.framework.context.exception;

/**
 * date : 2020/11/26
 * file_name : BizException
 * package_name : com.dvdrental.batch.framework.context.exception
 * project_name : dvdrental-partitioning-batch
 * user : gangmin-u
 * Outline :
 * Desction :
 */

public class BizException extends Exception {
    private String errorCode;
    private String errorInfo;
    private String errorType;

    public BizException(String errorCode){
        super(errorCode);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String errorInfo){
        super(errorCode);
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorInfo
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    /**
     * @param errorInfo the errorInfo to set
     */
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * @return the errorType
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * @param errorType the errorType to set
     */
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

}
