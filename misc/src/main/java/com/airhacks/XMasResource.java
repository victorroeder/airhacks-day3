package com.airhacks;

import com.airhacks.porcupine.execution.boundary.Dedicated;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("xmas")
public class XMasResource {

    @Inject
    @Dedicated
    ExecutorService xmasPipeline;

    @Inject
    GiftFactory factory;

    @GET
    public void msg(@Suspended AsyncResponse response) {
        Consumer<String> giftAcceptor = response::resume;
        Supplier<String> giftFactory = factory::nextGift;
        supplyAsync(giftFactory, xmasPipeline).thenAccept(giftAcceptor);
    }

}
