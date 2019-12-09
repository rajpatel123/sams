package com.samsapp.models.bookings;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("Booking_History")
@Expose
private List<BookingHistory> bookingHistory = new ArrayList<>();

public List<BookingHistory> getBookingHistory() {
return bookingHistory;
}

public void setBookingHistory(List<BookingHistory> bookingHistory) {
this.bookingHistory = bookingHistory;
}

}