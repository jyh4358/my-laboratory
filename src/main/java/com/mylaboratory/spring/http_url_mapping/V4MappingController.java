package com.mylaboratory.spring.http_url_mapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v4/order")
public class V4MappingController {

    @GetMapping(params = "order_name")
    public String getOrderList(
            @RequestParam("order_name") String orderName
    ) {
        return "method GET - orderName = " + orderName;
    }

    @PostMapping
    public String saveOrder(
            @RequestHeader("member_id") Long memberId
    ) {
        return "method POST - memberId = " + memberId;
    }

    @GetMapping("/{order_id}")
    public String getOrder(
            @PathVariable("order_id") Long orderId
    ) {
        return "method detail GET";
    }

    @PutMapping("/{order_id}")
    public String updateOrder(
            @PathVariable("order_id") Long orderId
    ) {
        return "method PUT";
    }

    @DeleteMapping("/{order_id}")
    public String deleteOrder(
            @PathVariable("order_id") Long orderId
    ) {
        return "method delete";
    }
}
