package com.atletii.mhpdeskbookingbackend.rooms.service;

import com.atletii.mhpdeskbookingbackend.common.service.BaseService;
import com.atletii.mhpdeskbookingbackend.rooms.api.dto.BookingEventDto;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.Booking;
import com.atletii.mhpdeskbookingbackend.rooms.service.model.BookingEventType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
public class BookingEventService extends BaseService {
    private final List<SseEmitter> subscribers = new ArrayList<>();

    public void subscribe(SseEmitter sseEmitter) {
        subscribers.add(sseEmitter);
    }

    public void unsubscribe(SseEmitter sseEmitter) {
        subscribers.remove(sseEmitter);
    }

    public void sendBundleEvent(Booking booking, BookingEventType type) {
        BookingEventDto bookingEventDto = new BookingEventDto();
        bookingEventDto.setBooking(booking);
        bookingEventDto.setBookingEventType(type);

        for (int i = 0; i < subscribers.size(); i++) {
            try {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data(bookingEventDto);
                subscribers.get(i).send(event);

            } catch (IOException e) {
                unsubscribe(subscribers.get(i)); // connection is broken; remove client from subscribers list
                i--;
            }
        }
    }

}
