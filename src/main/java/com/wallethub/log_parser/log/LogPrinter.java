package com.wallethub.log_parser.log;

import com.wallethub.log_parser.vo.IPAddress;
import org.springframework.stereotype.Service;

@Service
public class LogPrinter {

    public void print(IPAddress address) {
        System.out.println(address);;
    }

}
