package com.example.comp2000app.data;
import java.text.SimpleDateFormat;
import java.util.Date;
public class HolidayBooking {
    private Date startDate;
    private Date endDate;
    private Date requestDate;
    private Date cancellationDate;
    private Date approvalDate;
    private Date rejectionDate;
    public enum ApprovalStatus {
        PENDING,
        APPROVED,
        REJECTED,
        CANCELLED
    }

    private ApprovalStatus approvalStatus;

    private boolean isCancelled(){
        // returns bool as to whether the request is cancelled or not
        return this.approvalStatus == ApprovalStatus.CANCELLED;
    }

    // method for cancelling the holiday request, with error check for if request is already
    // cancelled
    public void cancelRequest(){
        if (!this.isCancelled()) {
            this.approvalStatus = ApprovalStatus.CANCELLED;
            this.cancellationDate = new Date();
        }else{
            throw new IllegalStateException("Cannot cancel request; request is already cancelled.");
        }
    }

    public boolean isApproved(){
        return this.approvalStatus == ApprovalStatus.APPROVED;
    }

    public boolean isRejected(){
        return !isApproved();
    }

    // get the start date, as a date object
    public Date getStartDate(){
        return this.startDate;
    }

    // get the end date, as a date object
    public Date getEndDate(){
        return this.endDate;
    }

    // get start date as a formatted string
    public String getStartDateAsString(){
        if (this.startDate == null) {
            throw new IllegalStateException("Start date is not set.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.startDate);
    }

    // get end date as a formatted string
    public String getEndDateAsString(){
        if (this.endDate == null) {
            throw new IllegalStateException("End date is not set.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.endDate);
    }

    // Return the date the request was approved, if it is approved
    public Date getApprovalDate() {
        // Check if the request has been approved
        if (this.isApproved()) {
            return this.approvalDate;
        } else {
            // Throw an exception because the request is not approved
            throw new IllegalStateException("Request is not approved; approval date is not available.");
        }
    }

    public Date getRejectionDate(){
        if (this.isRejected()){
            return this.rejectionDate;
        }else{
            throw new IllegalStateException("Request is not rejected; rejection date is not available.");
        }
    }

    // return the cancellation date as a date object
    public Date getCancellationDate() {
        if (!isCancelled()) {
            throw new IllegalStateException("Request is not cancelled; cancellation date is not available.");
        }
        return this.cancellationDate;
    }

    // approve request, and update the approval date accordingly - future version can
    // include db insertion of approval details
    public void approveRequest() {
        this.approvalStatus = ApprovalStatus.APPROVED;
        this.approvalDate = new Date();
    }

    // reject request, and update the rejection date accordingly - future version can
    // include db insertion of rejection details
    public void rejectRequest() {
        this.approvalStatus = ApprovalStatus.REJECTED;
        this.rejectionDate = new Date();
    }

    // returns the request date as a date object
    public Date getRequestDate() {
        return this.requestDate;
    }

    // returns the request date as a formatted string
    public String getRequestDateAsString() {
        if (this.requestDate == null) {
            throw new IllegalStateException("Request date is not set.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.requestDate);
    }

    public String exportAsString() {
        return "HolidayBooking{" +
                "startDate=" + getStartDateAsString() +
                ", endDate=" + getEndDateAsString() +
                ", requestDate=" + (requestDate != null ? getRequestDateAsString() : "N/A") +
                ", approvalStatus=" + approvalStatus +
                ", approvalDate=" + (approvalDate != null ? new SimpleDateFormat("dd/MM/yyyy").format(approvalDate) : "N/A") +
                ", rejectionDate=" + (rejectionDate != null ? new SimpleDateFormat("dd/MM/yyyy").format(rejectionDate) : "N/A") +
                ", cancellationDate=" + (cancellationDate != null ? new SimpleDateFormat("dd/MM/yyyy").format(cancellationDate) : "N/A") +
                '}';
    }

    // for debugging purposes, return a log of the object
    @Override
    public String toString() {
        return "HolidayBooking{" +
                "startDate=" + getStartDateAsString() +
                ", endDate=" + getEndDateAsString() +
                ", requestDate=" + (requestDate != null ? getRequestDateAsString() : "N/A") +
                ", approvalStatus=" + approvalStatus +
                ", approvalDate=" + (approvalDate != null ? new SimpleDateFormat("dd/MM/yyyy").format(approvalDate) : "N/A") +
                ", rejectionDate=" + (rejectionDate != null ? new SimpleDateFormat("dd/MM/yyyy").format(rejectionDate) : "N/A") +
                ", cancellationDate=" + (cancellationDate != null ? new SimpleDateFormat("dd/MM/yyyy").format(cancellationDate) : "N/A") +
                '}';
    }



    // class constructor to construct an object
    public HolidayBooking(Date startDate, Date endDate, Date requestDate, ApprovalStatus approvalStatus){

        // check that the start date is before the end date, before allowing the object to be
        // initialised

        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
        this.requestDate = requestDate;
        this.approvalStatus = approvalStatus;

        // Set approvalDate or rejectionDate based on the initial approval status
        if (approvalStatus == ApprovalStatus.APPROVED) {
            this.approvalDate = new Date(); // assuming the approval date is now, will update in future
        } else if (approvalStatus == ApprovalStatus.REJECTED) {
            this.rejectionDate = new Date(); // assuming the rejection date is the current date
        }
    }

}
