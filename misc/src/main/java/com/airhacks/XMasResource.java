package com.airhacks;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("xmas")
public class XMasResource {

    @Resource
    ManagedExecutorService mes;

    @Inject
    GiftFactory factory;

    @GET
    public void msg(@Suspended AsyncResponse response) {
        Consumer<String> giftAcceptor = response::resume;
        Supplier<String> giftFactory = factory::nextGift;
        supplyAsync(giftFactory, mes).thenAccept(giftAcceptor);
    }

}
