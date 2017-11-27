package cn.xyz.chaos.guava.common.eventbus.event;

import java.util.Date;

import cn.xyz.chaos.guava.common.eventbus.TradeType;
import cn.xyz.chaos.guava.common.model.TradeAccount;

/**
 *
 * @author lvchenggang
 *
 */
public class SellEvent extends TradeAccountEvent {

    public SellEvent(TradeAccount tradeAccount, double amount, Date tradExecutionTime) {
        super(tradeAccount, amount, tradExecutionTime, TradeType.SELL);
    }

    public static class Builder {
        private TradeAccount tradeAccount = new TradeAccount.Builder().build();
        private double amount = 500.75;
        private Date executionTime = new Date();

        public Builder tradeAccount(TradeAccount tradeAccount) {
            this.tradeAccount = tradeAccount;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder executionTime(Date executionTime) {
            this.executionTime = executionTime;
            return this;
        }

        public SellEvent build() {
            return new SellEvent(this.tradeAccount, this.amount, this.executionTime);
        }
    }
}
