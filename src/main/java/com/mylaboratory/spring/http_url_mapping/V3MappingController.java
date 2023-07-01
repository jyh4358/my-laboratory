package com.mylaboratory.spring.http_url_mapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v3/order")
public class V3MappingController {

    @RequestMapping(method = RequestMethod.GET)
    public String getOrderList(
            @RequestParam("order_name") String orderName
    ) {
        return "method GET - orderName = " + orderName;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrder(
            @RequestHeader("member_id") Long memberId
    ) {
        return "method POST - memberId = " + memberId;
    }

    @RequestMapping(value = "/{order_id}", method = RequestMethod.GET)
    public String getOrder(
            @PathVariable("order_id") Long orderId
    ) {
        return "method detail GET";
    }

    @RequestMapping(value = "{order_id}", method = RequestMethod.PUT)
    public String updateOrder(
            @PathVariable("order_id") Long orderId
    ) {
        return "method POST";
    }

    @RequestMapping(value = "{order_id}", method = RequestMethod.DELETE)
    public String deleteOrder(
            @PathVariable("order_id") Long orderId
    ) {
        return "method delete";
    }

    @RequestMapping(value = "/product", method = { RequestMethod.POST, RequestMethod.PUT})
    public String saveOrUpdateProduct() {
        return "method POST or PUT";
    }

    @RequestMapping(value = "/test")
    public String testMethod() {
        return "all method";
    }
}
