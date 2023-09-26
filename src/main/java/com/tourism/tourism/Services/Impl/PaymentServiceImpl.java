package com.tourism.tourism.Services.Impl;

import com.tourism.tourism.Entities.Payments;
import com.tourism.tourism.Entities.User;
import com.tourism.tourism.Exception.ResourceNotFoundException;
import com.tourism.tourism.Payloads.PaymentDto;
import com.tourism.tourism.Repositories.PaymentRepo;
import com.tourism.tourism.Repositories.UserRepo;
import com.tourism.tourism.Services.PaymentServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentServiceImpl  implements PaymentServices {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public PaymentDto pay(PaymentDto paymentDto,Long userId) {
        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("name", userId));
        Payments payments=this.modelMapper.map(paymentDto,Payments.class);
        payments.setDate(new Date());
        payments.setUser(user);
        payments.setAmount(paymentDto.getAmount());
        Payments newPay=this.paymentRepo.save(payments);
        return this.modelMapper.map(newPay,PaymentDto.class);
    }

    @Override
    public PaymentDto updatePayment(PaymentDto paymentDto,Long userId, Long paymentId) {
        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("name", userId));
        Payments payments1=this.paymentRepo.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException("name", paymentId));
        payments1.setAmount(paymentDto.getAmount());
        payments1.setUser(user);
        this.paymentRepo.save(payments1);
        return this.modelMapper.map(payments1,PaymentDto.class);
    }

    @Override
    public Void deletePayments(Long userId, Long paymentId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("name",userId));
        Payments payments=this.paymentRepo.findById(paymentId).orElseThrow(()->new ResourceNotFoundException("name",paymentId));
        this.paymentRepo.delete(payments);
        return null;
    }

    @Override
    public PaymentDto getPaymentById(Long userId,Long paymentId) {
        this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("name", paymentId));
        Payments payments=this.paymentRepo.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException("name", paymentId));
        return this.modelMapper.map(payments,PaymentDto.class);
    }

    @Override
    public List<PaymentDto> getAllUserPayments(Long userId) {
        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("name", userId));
        List<Payments> payLists=this.paymentRepo.findByUser(user);
        List<PaymentDto> paymentList = payLists.stream().map((payments)->this.modelMapper.map(payments,PaymentDto.class)).collect(Collectors.toList());
        return paymentList;
    }

    @Override
    public List<PaymentDto> getALlPayment() {
        List<Payments> allPay=this.paymentRepo.findAll();
        List<PaymentDto> allPayment=allPay.stream().map((all)->this.modelMapper.map(all,PaymentDto.class)).collect(Collectors.toList());
        return allPayment;
    }
}
