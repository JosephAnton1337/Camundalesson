package com.example.workflow;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named

public class ReserveSeatOnBoat implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String ticketType = "Coach";

       String money = (String) delegateExecution.getVariable("money");
        double moneyDouble = Double.parseDouble(money);

        if (moneyDouble >= 10000) {
            ticketType = "First Class";
        } else if (moneyDouble >= 5000) {
            ticketType = "Business Class";
        }
        else if (moneyDouble <= 10 ){//обработка ошибки, если у польщователя <10 валюты появляется данная ошибка
            throw  new BpmnError("Fall_Overboard","A cheap ticket has led a small wawe throwing you overboard ");
        }

        delegateExecution.setVariable("ticketType", ticketType);
    }
}
