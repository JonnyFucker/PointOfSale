package com.zielichowski.pos;

import com.zielichowski.pos.devices.input.BarcodeScanner;
import com.zielichowski.pos.devices.listeners.BarcodeListener;
import com.zielichowski.pos.devices.listeners.ExitCodeListener;
import com.zielichowski.pos.devices.output.Display;
import com.zielichowski.pos.devices.output.OutputDevice;
import com.zielichowski.pos.model.Product;
import com.zielichowski.pos.model.ProductReceipt;
import com.zielichowski.pos.model.Receipt;
import com.zielichowski.pos.repository.ProductRepository;
import com.zielichowski.pos.util.Message;

import java.util.Optional;

/**
 * Created by Tomek on 27-Mar-17.
 */
public class PointOfSale implements BarcodeListener, ExitCodeListener {
    private ProductRepository productRepository;
    private BarcodeScanner barcodeScanner;
    private OutputDevice printer;
    private Display lcdDisplay;
    private Receipt<Product> productReceipt = new ProductReceipt();


    public PointOfSale(BarcodeScanner barcodeScanner, OutputDevice printer, Display lcdDisplay) {
        this.barcodeScanner = barcodeScanner;
        this.printer = printer;
        this.lcdDisplay = lcdDisplay;
    }

    @Override
    public void onExitCode() {
        printer.print(productReceipt);
        lcdDisplay.printAdditionalInformation(productReceipt.getTotal().toString());
    }

    @Override
    public void onScan() {
        String barcode = barcodeScanner.readBarcode();

        if (isNotValid(barcode)) {
            lcdDisplay.printAdditionalInformation(Message.INVALID_BARCODE);
        } else {
            handleValidCode(barcode);
        }
    }

    private void handleValidCode(String barcode) {
        Optional<Product> productByBarcode = getProductByBarcode(barcode);

        if (productByBarcode.isPresent()) {
            Product product = productByBarcode.get();
            handleCorrectProduct(product);
        } else {
            lcdDisplay.printAdditionalInformation(Message.PRODUCT_NOT_FOUND);
        }
    }

    private Optional<Product> getProductByBarcode(String barcode) {
        return Optional.ofNullable(productRepository.findByBarcode(barcode));
    }

    private void handleCorrectProduct(Product product) {
        lcdDisplay.print(product);
        productReceipt.addToReceipt(product);
    }

    private boolean isNotValid(String barcode) {
        return (barcode.isEmpty());
    }


}
