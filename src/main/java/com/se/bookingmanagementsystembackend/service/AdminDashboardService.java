package com.se.bookingmanagementsystembackend.service;

import com.se.bookingmanagementsystembackend.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AdminDashboardService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final RoomTypeRepository roomTypeRepository;


    public int getNoOfCustomers(LocalDate fromDate, LocalDate toDate) {
        return userRepository.findUsersRegisteredBetween(fromDate, toDate);
    }

    @Transactional
    public List<String> getRoomsCount() {
        var obj = roomTypeRepository.noOfRoomsForEachProperty();
        int total = 0;
        for (var entry : obj) {
            total += Integer.parseInt(entry.split(",")[0]);
        }
        obj.add(total + ",Total");
        return obj;
    }

    public int getRevenueFromDateRange(LocalDate fromDate, LocalDate toDate) {
        var count =  paymentRepository.calculateRevenueBetweenDateRange(fromDate, toDate);
        return count!= null ? count : 0;
    }

    public List<Integer> getTodaysRevenue() {
        System.out.println(LocalDate.now(ZoneId.of("America/New_York")));
        return Collections.singletonList(paymentRepository.getTodaysRevenue(LocalDate.now(ZoneId.of("America/New_York"))));
    }
}
