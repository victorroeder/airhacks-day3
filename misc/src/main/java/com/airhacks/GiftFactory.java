package com.airhacks;

import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class GiftFactory {

    public String nextGift() {
        return "mindstorms " + System.currentTimeMillis();
    }

}
