package com.example.studioJudo.controllers;

import com.example.studioJudo.dto.BookingDto;
import com.example.studioJudo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<BookingDto> findAllBooking() {
        return bookingService.findAllBooking();
    }

    @PostMapping
    public BookingDto saveBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.saveBooking(bookingDto);
    }

    @GetMapping("/{id}")
    public BookingDto findBookingById(@PathVariable("id") Integer id) {
        return bookingService.findBookingById(id);
    }

    @PutMapping("/{id}")
    public BookingDto updateBooking(@PathVariable("id") Integer id , @RequestBody BookingDto bookingDto) {
        return bookingService.updateBooking(id, bookingDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable("id") Integer id) {
        bookingService.deleteBooking(id);
    }

}
