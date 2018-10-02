package com.wallethub.log_parser.blocked;

import com.wallethub.log_parser.vo.IPAddress;
import org.springframework.stereotype.Service;

@Service
public class BlockedFactory {

    public BlockedEntity create(IPAddress address) {
        BlockedEntity e = new BlockedEntity();
        e.setAddress(address);
        e.setComment("WTF?! - I hope that this is one and only place where You said WTF to yourself :)");
        return e;
    }
}
