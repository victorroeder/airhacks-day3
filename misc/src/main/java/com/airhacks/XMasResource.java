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
import javax.ws.rs.core.Response;

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
        response.setTimeout(2, java.util.concurrent.TimeUnit.SECONDS);
        response.setTimeoutHandler((a) -> {
            Response r = Response.status(503).
                    header("reason", "i'm overloaded").
                    build();
            a.resume(r);
        });
        Consumer<String> giftAcceptor = response::resume;
        Supplier<String> giftFactory = factory::nextGift;
        supplyAsync(giftFactory, xmasPipeline).
                handle((t, u) -> u.getCause().toString()).
                thenAccept(giftAcceptor);
    }

}
