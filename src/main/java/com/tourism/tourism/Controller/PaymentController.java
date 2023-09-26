package com.tourism.tourism.Controller;

import com.tourism.tourism.Payloads.PaymentDto;
import com.tourism.tourism.Services.PaymentServices;
import com.tourism.tourism.Utilities.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/payments")
public class PaymentController
{
    @Autowired
    private PaymentServices paymentServices;
    @PostMapping("/user/{userId}/new")
    public ResponseEntity<PaymentDto> newPayment(@Validated @RequestBody PaymentDto paymentDto, @PathVariable("userId") Long userId)
    {
        PaymentDto newPayment=this.paymentServices.pay(paymentDto,userId);
        return new ResponseEntity<>(newPayment,HttpStatus.ACCEPTED);
    }

    @PutMapping("/user/{userId}/payId/{paymentId}/update")
    public ResponseEntity<PaymentDto> updatePayment(@Validated @RequestBody PaymentDto paymentDto,@PathVariable("userId") Long userId, @PathVariable("paymentId") Long paymentId)
    {
        PaymentDto updatePayment=this.paymentServices.updatePayment(paymentDto,userId,paymentId);
        return new ResponseEntity<>(updatePayment, HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}/payId/{paymentId}/del")
    public ResponseEntity<ApiResponse> deletePay(@PathVariable("userId") Long userId,@PathVariable("paymentId") Long paymentId)
    {
        this.paymentServices.deletePayments(userId,paymentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Payment Record Deleted",true,HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/pay")
    public ResponseEntity<List<PaymentDto>> allPayByUser(@PathVariable("userId") Long userId)
    {
        List<PaymentDto> payment=this.paymentServices.getAllUserPayments(userId);
        return new ResponseEntity<>(payment,HttpStatus.FOUND);
    }
    @GetMapping("/allpayments")
    public ResponseEntity<List<PaymentDto>> allPayments()
    {
        List<PaymentDto> allPayRec=this.paymentServices.getALlPayment();
        return new ResponseEntity<>(allPayRec,HttpStatus.FOUND);
    }
    @GetMapping("/user/{userId}/pay/{paymentId}")
    public ResponseEntity<PaymentDto> getById(@PathVariable("userId") Long userid,@PathVariable("paymentId") Long paymentId)
    {
        PaymentDto paymentDto=this.paymentServices.getPaymentById(userid,paymentId);
        return new ResponseEntity<>(paymentDto,HttpStatus.FOUND);
    }
}
 