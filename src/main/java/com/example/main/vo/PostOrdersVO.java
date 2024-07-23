package com.example.main.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostOrdersVO {

    /*
     * market ID
     * 필수
     */
    private String market;

    /*
     * side
     * - bid : 매수
     * - ask : 매도
     * 필수
     */
    private String side;

    /*
     * 주문량 (지정가, 시장가 매도 시 필수)
     */
    private String volume;

    /*
     * 조회용 사용자 지정값 (선택), String (Uniq 값 사용)
     */
    private String identifier;

    /*
     * 주문 가격. (지정가, 시장가 매수 시 필수)
     * ex) KRW-BTC 마켓에서 1BTC당 1,000 KRW로 거래할 경우, 값은 1000 이 된다.
     * ex) KRW-BTC 마켓에서 1BTC당 매도 1호가가 500 KRW 인 경우, 시장가 매수 시 값을 1000으로 세팅하면 2BTC가
     * 매수된다.
     * (수수료가 존재하거나 매도 1호가의 수량에 따라 상이할 수 있음)
     */
    private String price;

    /*
     * 주문 타입 (필수)
     * - limit : 지정가 주문
     * - price : 시장가 주문(매수)
     * - market : 시장가 주문(매도)
     * - best : 최유리 주문 (time_in_force 설정 필수)
     */
    private String ord_type;

    /*
     * IOC, FOK 주문 설정*
     * - ioc : Immediate or Cancel
     * - fok : Fill or Kill
     * ord_type이 best 혹은 limit 일때만 지원됩니다.
     */
    private String time_in_force;

    public PostOrdersVO(String market, String side, String volume, String price, String ord_type) {
        this.market = market;
        this.side = side;
        this.volume = volume;
        this.price = price;
        this.ord_type = ord_type;
    }
}
