package com.Polizas.Polizas.ControllerPay;

import com.Polizas.Polizas.ModelEntity.UserBuyer;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MercadoPagoController {

//
    @Value("${codigo.mercadoLibre}")
    private String mercadoLibreToken;

    @RequestMapping(value = "api/mp", method = RequestMethod.POST)
    public String getList(@RequestBody UserBuyer userBuyer){
        if(userBuyer==null){return "error jsons :/";}
        String title = userBuyer.getTitle();
        int quantity = userBuyer.getQuantity();
        int price = userBuyer.getUnit_price();
        try{
            MercadoPagoConfig.setAccessToken(mercadoLibreToken);
          PreferenceItemRequest itemRequest = PreferenceItemRequest
                  .builder()
                  .title(title)
                  .quantity(quantity)
                  .unitPrice(new BigDecimal(price))
                  .currencyId("ARS")
                  .build();
          List<PreferenceItemRequest> items = new ArrayList<>();
          items.add(itemRequest);

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest
                    .builder().success("https://www.youtube.com/")
                    .pending("https://www.youtube.com/")
                    .failure("https://www.youtube.com/")
                    .build();
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .build();

            PreferenceClient client= new PreferenceClient();

            Preference preference=client.create(preferenceRequest);

            return preference.getId();

        }catch (MPException | MPApiException e){return e.toString();}

    }
}
