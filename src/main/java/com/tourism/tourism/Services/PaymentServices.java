package com.tourism.tourism.Services;


import com.tourism.tourism.Payloads.PaymentDto;

import java.util.List;

public interface PaymentServices {
    PaymentDto pay(PaymentDto paymentDto ,Long userId);
    PaymentDto updatePayment(PaymentDto paymentDto,Long userId,Long paymentId);
    Void deletePayments(Long userId, Long paymentId);
    PaymentDto getPaymentById(Long userId,Long paymentId);
    List<PaymentDto> getAllUserPayments(Long userId);
    List<PaymentDto> getALlPayment();

}
