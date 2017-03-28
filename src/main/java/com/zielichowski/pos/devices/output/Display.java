package com.zielichowski.pos.devices.output;

/**
 * Created by Tomasz Zielichowski on 2017-03-28.
 */
public interface Display extends OutputDevice {
    void printAdditionalInformation(String info);
}
