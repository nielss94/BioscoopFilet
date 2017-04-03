package com.filet.bioscoopfilet.PresentationApplicationLogicLayer;

import java.util.Random;

/**
 * Created by Niels on 3/31/2017.
 */

public class QRCode {

    private int qrCode;

    public QRCode()
    {
        Random rand = new Random();
        qrCode = rand.nextInt(99999);
    }

    public int getQrCode() {
        return qrCode;
    }

}
