package com.se.bookingmanagementsystembackend.api;

import com.se.bookingmanagementsystembackend.service.AdminDashboardService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/admins/")
public class AdminDashboardController {


    private final AdminDashboardService adminDashboardService;

    @GetMapping("customercount/{fromDate}/{toDate}/")
    public int getNoOfCustomers(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")LocalDate fromDate,
                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")LocalDate toDate){
        return adminDashboardService.getNoOfCustomers(fromDate,toDate);
    }

    @GetMapping("roomscount/")
    public List<String> getRoomsCount(){
        return adminDashboardService.getRoomsCount();
    }

    @GetMapping("revenue/{fromDate}/{toDate}/")
    public int getRevenueFromDateRange(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")LocalDate fromDate,
                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")LocalDate toDate){

        return adminDashboardService.getRevenueFromDateRange(fromDate,toDate);
    }

    @GetMapping("revenue/today/")
    public List<Integer> getTodayRevenue(){
        return adminDashboardService.getTodaysRevenue();
    }

}
