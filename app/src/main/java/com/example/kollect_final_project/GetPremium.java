package com.example.kollect_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.annotations.NotNull;
import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.approve.Approval;
import com.paypal.checkout.approve.OnApprove;
import com.paypal.checkout.cancel.OnCancel;
import com.paypal.checkout.config.CheckoutConfig;
import com.paypal.checkout.config.Environment;
import com.paypal.checkout.createorder.CreateOrder;
import com.paypal.checkout.createorder.CreateOrderActions;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.OrderIntent;
import com.paypal.checkout.createorder.UserAction;
import com.paypal.checkout.error.ErrorInfo;
import com.paypal.checkout.error.OnError;
import com.paypal.checkout.order.Amount;
import com.paypal.checkout.order.AppContext;
import com.paypal.checkout.order.CaptureOrderResult;
import com.paypal.checkout.order.OnCaptureComplete;
import com.paypal.checkout.order.Order;
import com.paypal.checkout.order.PurchaseUnit;
import com.paypal.checkout.paymentbutton.PayPalButton;
import com.paypal.checkout.shipping.OnShippingChange;
import com.paypal.checkout.shipping.ShippingChangeActions;
import com.paypal.checkout.shipping.ShippingChangeData;

import java.util.ArrayList;

public class GetPremium extends AppCompatActivity {

    private static final String YOUR_CLIENT_ID = "Aetfa-NyKGfIWgKNR9PA3DeBxPKvSct9-tD392aBVw-S0Frr8kAjiyobjnEfkOMehPF8w1hAS_uo3iLf";
    PayPalButton payPalButton;
    Button btn_back;
    //PaymentButtonContainer paymentButtonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PayPalCheckout.setConfig(new CheckoutConfig(
                getApplication(),
                YOUR_CLIENT_ID,
                Environment.SANDBOX,
                CurrencyCode.USD,
                UserAction.PAY_NOW

        ));
        setContentView(R.layout.activity_get_premium);

        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetPremium.this, Profile.class);
                startActivity(intent);
            }
        });

        payPalButton = findViewById(R.id.payPalButton);
        //paymentButtonContainer = findViewById(R.id.payment_button_container);
        payPalButton.setup(
                new CreateOrder() {
                    @Override
                    public void create(@NotNull CreateOrderActions createOrderActions) {

                        ArrayList<PurchaseUnit> purchaseUnits = new ArrayList<>();

                        purchaseUnits.add(
                                new PurchaseUnit.Builder()
                                        .amount(
                                                new Amount.Builder()
                                                        .currencyCode(CurrencyCode.USD)
                                                        .value("10.00")
                                                        .build()
                                        )
                                        .build()
                        );

                        Order order = new Order(
                                OrderIntent.CAPTURE,
                                new AppContext.Builder()
                                        .userAction(UserAction.PAY_NOW)
                                        .build(),
                                purchaseUnits
                        );
                        createOrderActions.create(order, (CreateOrderActions.OnOrderCreated) null);
                    }
                },

                new OnApprove() {
                    @Override
                    public void onApprove(@NotNull Approval approval) {
                        approval.getOrderActions().capture(new OnCaptureComplete() {
                            @Override
                            public void onCaptureComplete(@NotNull CaptureOrderResult result) {
                                Log.i("CaptureOrder", String.format("CaptureOrderResult: %s", result));
                                Toast.makeText(GetPremium.this, "Thank you for your purchase!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(GetPremium.this, Profile.class);
                                startActivity(intent);
                            }
                        });
                    }
                },
                new OnShippingChange() {
                    @Override
                    public void onShippingChanged(@NonNull ShippingChangeData shippingChangeData, @NonNull ShippingChangeActions shippingChangeActions) {
                    }
                },

                new OnCancel() {
                    @Override
                    public void onCancel() {

                        Log.d("OnCancel", "Buyer cancelled the PayPal experience.");
                        Toast.makeText(GetPremium.this, "You have canceled payment", Toast.LENGTH_SHORT).show();

                    }

                },
                new OnError() {
                    @Override
                    public void onError(@NotNull ErrorInfo errorInfo) {
                        Log.d("OnError", String.format("Error: %s", errorInfo));
                        Toast.makeText(GetPremium.this, "Error occurred!", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }
}