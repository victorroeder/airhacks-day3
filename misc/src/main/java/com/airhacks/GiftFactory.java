package com.airhacks;

import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class GiftFactory {

    public String nextGift() {
        throw new IllegalStateException("santa is overloaded");
        //return "mindstorms " + System.currentTimeMillis();
    }

}
