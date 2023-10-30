package model.service;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {
          double instParcial = contract.getTotalValue() / months;
          for(int i = 1; i <= months; i++) {
        	  LocalDate dateInst = contract.getDate().plusMonths(i);
        	  double interest = onlinePaymentService.interest(instParcial, i);
        	  double paymentFee = onlinePaymentService.paymentFee(instParcial + interest);
        	  double instComplete = instParcial + interest + paymentFee;
        	  contract.getInstallment().add(new Installment(dateInst, instComplete));
          }
	}
}
