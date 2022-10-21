package run;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Element;
import utils.List;

public class Main {

	public static String convertExpression(String expression) {
		String[] expressionSplit = expression.split(" ");
		
		List list = new List();
		List resultsStack = new List();
		List operationsStack = new List();
		
		// Adding vector elements to an Array list
		for (String character : expressionSplit) {
			list.add(character);
		}
		
		for (int index = 0; index < list.getSize(); index++) {
			String currentValue = list.get(index).getValue();
			
			if (isNumeric(currentValue)) {
				resultsStack.add(currentValue);
				continue;
			}
			
			if(currentValue.equals(")")) {
				do {
					if(!operationsStack.get(operationsStack.getSize() - 1).getValue().equals("(")) {
						resultsStack.add(operationsStack.get(operationsStack.getSize() - 1).getValue());
						operationsStack.removeByIndex(operationsStack.getSize() - 1);
					}
					
				} while(!operationsStack.get(operationsStack.getSize() - 1).getValue().equals("("));
			
				operationsStack.removeByIndex(operationsStack.getSize() - 1);
				continue;
			}
									
			if(currentValue.equals("(")) {
				operationsStack.add(currentValue);
				continue;
			}
			
			if(currentValue.equals("+") || currentValue.equals("-")) {
				if(operationsStack.getSize() == 0 || operationsStack.get(operationsStack.getSize() - 1).getValue().equals("(")) {
					operationsStack.add(currentValue);
					continue;
				}
				
				resultsStack.add(operationsStack.get(operationsStack.getSize() - 1).getValue());
				operationsStack.removeByIndex(operationsStack.getSize() - 1);		
				operationsStack.add(currentValue);
				continue;
			}
			
			if(currentValue.equals("*") || currentValue.equals("/")) {
				if(operationsStack.getSize() == 0) {
					operationsStack.add(currentValue);
					continue;
				}
				
				if(operationsStack.get(operationsStack.getSize() - 1).getValue().equals("*") || operationsStack.get(operationsStack.getSize() - 1).getValue().equals("/"))  {
					resultsStack.add(operationsStack.get(operationsStack.getSize() - 1).getValue());
					operationsStack.removeByIndex(operationsStack.getSize() - 1);
				}
				operationsStack.add(currentValue);
				continue;
			}
		}
		
		while(operationsStack.getSize() != 0) {
			resultsStack.add(operationsStack.get(operationsStack.getSize() - 1).getValue());
			operationsStack.removeByIndex(operationsStack.getSize() - 1);
		}
				
		return resultsStack.convertListInString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite a expressão separando os números e as operações com espaços: ");

		String expression = convertExpression(sc.nextLine());
		sc.close();
		
		String[] expressionSplit = expression.split(" ");

		List list = new List();
		
		ArrayList<String> operators = new ArrayList<String>();
		
		operators.add("+");
		operators.add("-");
		operators.add("*");
		operators.add("/");
		
		// Adding vector elements to an Array list
		for (String x : expressionSplit) {
			list.add(x);
		}
		
		Element lastElement = list.getLast();

		List finalList = new List();

		try {
			// If the last element of the list is a number there will be an error in the equation, so it's need to notify the user
			if (isNumeric(lastElement.getValue())) {
				System.out.println("o último elemento da equação não pode ser um número ");
				throw new Exception();
			}
			// If the first element of the list is an operator there will be an error in the equation, so it is need to notify the user
			if (operators.contains(list.getFirst().getValue())) {
				System.out.println("o primeiro  elemento da equação não pode ser uma operação ");
				throw new Exception();
			}

			// Go through the equation list and do your operations
			for (int i = 0; i < list.getSize(); i++) {
				
				int last = finalList.getSize() - 1;
				String currentValue = list.get(i).getValue();
				
				if(finalList.getSize() < 2 && operators.contains(currentValue)) {
					System.out.println("Erro na formatação dos dados!");
					throw new Exception();
				}

				if (isNumeric(currentValue)) {
					finalList.add(currentValue);
					continue;
				} 
				
				if(!operators.contains(currentValue)) {
					break;
				}
				
				Double result = null;
				Double finalListLastValue = Double.parseDouble(finalList.get(last).getValue());
				Double finalListPrevValue = Double.parseDouble(finalList.get(last - 1).getValue());
				
				if(currentValue.equals("+")) 
					result = finalListLastValue + finalListPrevValue;
				
				if (currentValue.equals("-")) 
					result = finalListLastValue - finalListPrevValue;	
				
				if (currentValue.equals("*")) 
					result = finalListLastValue * finalListPrevValue;
				
				if (currentValue.equals("/")) 
					result = finalListLastValue / finalListPrevValue;
				
				finalList.removeByIndex(last);
				finalList.removeByIndex(last - 1);
				finalList.add(result.toString());

			}
			if (finalList.getSize() == 1) {
				// Print of the final result of the equation
				System.out.println(finalList.get(0).getValue());
			} else {
				System.out.println("Erro de sintaxe, verifique a equação");
				throw new Exception();
			}
		} catch (Exception error) {
			
		}
 
	}
	
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
