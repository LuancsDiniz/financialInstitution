package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the contract details: ");
		System.out.print("Number: ");
		int number = sc.nextInt();		
		System.out.print("Date (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.print("Contract value: ");
		double contractValue = sc.nextDouble();
		
		Contract obj = new Contract(number, date, contractValue);
		
		System.out.print("Enter the number of installments: ");
		int installmentNumber = sc.nextInt();
		
		ContractService pay = new ContractService(new PaypalService());
		pay.processContract(obj, installmentNumber);
		System.out.println("Installments:");
		
		for(Installment installment : obj.getInstallment()) {
			System.out.println(installment);
		}

		
		sc.close();
	}

}
