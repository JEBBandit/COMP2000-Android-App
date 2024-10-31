package com.example.comp2000app.data;
import android.os.Bundle;

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

    // return the approval status
    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
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

    // Desconstruct method created for easier traversal between pages without serialisation
    public Bundle deconstruct() {
        Bundle bundle = new Bundle();
        bundle.putLong("startDate", startDate != null ? startDate.getTime() : -1);
        bundle.putLong("endDate", endDate != null ? endDate.getTime() : -1);
        bundle.putLong("requestDate", requestDate != null ? requestDate.getTime() : -1);
        bundle.putLong("approvalDate", approvalDate != null ? approvalDate.getTime() : -1);
        bundle.putLong("rejectionDate", rejectionDate != null ? rejectionDate.getTime() : -1);
        bundle.putLong("cancellationDate", cancellationDate != null ? cancellationDate.getTime() : -1);
        bundle.putString("approvalStatus", approvalStatus != null ? approvalStatus.name() : ApprovalStatus.PENDING.name());
        return bundle;
    }

    // reconstruct method for reconstructing a deconstructed HolidayBooking object
    public static HolidayBooking reconstruct(Bundle bundle) {
        HolidayBooking booking = new HolidayBooking();
        long startDateMillis = bundle.getLong("startDate", -1);
        long endDateMillis = bundle.getLong("endDate", -1);
        long requestDateMillis = bundle.getLong("requestDate", -1);
        long approvalDateMillis = bundle.getLong("approvalDate", -1);
        long rejectionDateMillis = bundle.getLong("rejectionDate", -1);
        long cancellationDateMillis = bundle.getLong("cancellationDate", -1);

        booking.startDate = (startDateMillis != -1) ? new Date(startDateMillis) : null;
        booking.endDate = (endDateMillis != -1) ? new Date(endDateMillis) : null;
        booking.requestDate = (requestDateMillis != -1) ? new Date(requestDateMillis) : null;
        booking.approvalDate = (approvalDateMillis != -1) ? new Date(approvalDateMillis) : null;
        booking.rejectionDate = (rejectionDateMillis != -1) ? new Date(rejectionDateMillis) : null;
        booking.cancellationDate = (cancellationDateMillis != -1) ? new Date(cancellationDateMillis) : null;
        booking.approvalStatus = ApprovalStatus.valueOf(bundle.getString("approvalStatus", ApprovalStatus.PENDING.name()));

        return booking;
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

    // No-argument constructor - allows reconstruction method to work
    public HolidayBooking() {
        this.approvalStatus = ApprovalStatus.PENDING;
    }

}
