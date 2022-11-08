package angelbaby.inbound.controller;

import angelbaby.inbound.services.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/inbound")
public class InboundController {

    @Autowired
    private InboundService service;

    @GetMapping
    public String getInboundList() {
        try {
            return service.getInboundList().toString();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public String postInbound(@RequestBody String payload) {
        try {
            return service.postInbound(payload);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }





}
