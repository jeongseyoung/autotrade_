package com.example.main.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrentPriceVO {

    private String market;
    private String trade_date;
    private String trade_time;
    private String trade_date_kst;
    private String trade_time_kst;

    private Long trade_timestamp;

    private Double opening_price;
    private Double high_price;
    private Double low_price;
    private Double trade_price;
    private Double prev_closing_price;

    private String change; // even:보합, rise:상승, fall:하락
    private Double change_price;
    private Double change_rate;
    private Double signed_change_price;
    private Double signed_change_rate;
    private Double trade_volume;
    private Double acc_trade_price;
    private Double acc_trade_price_24h;
    private Double acc_trade_volume;
    private Double acc_trade_volume_24h;

    private Double highest_52_week_price;
    private String highest_52_week_date;
    private Double lowest_52_week_price;
    private String lowest_52_week_date;
    private Long timestamp;
}
