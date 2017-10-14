/*
 * FINAL EXAM CPT-287 ADVANCED JAVA
 * JASON STRATMAN
 * 
 * 
 * I chose the SortedList as the data structure. I think this is 
 * correct because you can add code to sort the OrderList by Model 
 * Number when the number of orders gets very high. That will make 
 * searching for Orders matching the Model Number much faster. 
 */
//-------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;
//-------------------------------------------------------------------

class Product {
	public int modelNumber;
	public String description;
	public double cost;
	public double price;
	public int quantity;
	public Product next;
	
	public Product(int newModelNumber,
				   String newDescription,
				   double newCost,
				   double newPrice,
				   int newQuantity) {
		modelNumber = newModelNumber;
		description = newDescription;
		cost = newCost;
		price = newPrice;
		quantity = newQuantity;
	}
	
	public void display() {
		System.out.println("*****************************************");
		System.out.println("Model Number: " + modelNumber);
		System.out.println("Description: " + description);
		System.out.println("Cost: $" + cost);
		System.out.println("Price: $" + price);
		System.out.println("Quanity On Hand: " + quantity);
		System.out.println("*****************************************");
	}
}
//-------------------------------------------------------------------

class Order {
	public int customerID;
	public int modelNumber;
	public int quantity;
	public double total;
	public Order next;

	public Order(int newCustomerID,
				 int newModelNumber,
			     int newQuantity,
			     double newTotal) {
		customerID = newCustomerID;
		modelNumber = newModelNumber;
		quantity = newQuantity;
		total = newTotal;
	}

	public void display() {
		System.out.println("*****************************************");
		System.out.println("Customer ID: " + customerID);
		System.out.println("Model Number: " + modelNumber);
		System.out.println("Quanity Ordered: " + quantity);
		System.out.println("Order Total: $" + total);
		System.out.println("*****************************************");
	}
}
//-------------------------------------------------------------------

class ProductList {
    private Product first;

    public ProductList() {
	    first = null;
    }

    public boolean isEmpty() {
	    return (first == null);
    }

    public void insert(int inputModelNumber,
		   			   String inputDescription,
		   			   double inputCost,
		   			   double inputPrice,
		   			   int inputQuantity) {
    	Product newLink = new Product(inputModelNumber, inputDescription, inputCost, inputPrice, inputQuantity);
	    Product previous = null;
	    Product current = first;
      
	    while(current != null && inputModelNumber > current.modelNumber) {
	    	previous = current;
	    	current = current.next;
	    }
	    
	    if (previous == null)
	    	first = newLink;
	    else
	    	previous.next = newLink;
	    
	    newLink.next = current;
    }
    
    public double getPrice(int inputModelNumber) {
    	Product current = first;
    	double price = 0;
    	
    	while (current.modelNumber != inputModelNumber) {
    		current = current.next;
    	}
    	
    	if (current.modelNumber == inputModelNumber) {
    		price = current.price;
    	}
    	
    	return price;
    }
    
    public void search(int inputModelNumber) {
    	Product current = first;
    	
    	while (current.modelNumber != inputModelNumber) {
    		current = current.next;
    	}
    	
    	if (current.modelNumber == inputModelNumber) {
    		current.display();
    	}
    	else {
    		System.out.println("Model Number not found!\n");
    	}
    }
}
//-------------------------------------------------------------------

class OrderList {
    private Order first;

    public OrderList() {
	    first = null;
    }

    public boolean isEmpty() {
	    return (first == null);
    }

    public void insert(int inputCustomerID,
    				   int inputModelNumber,
    				   int inputQuantity,
		   			   double inputTotal) {
    	Order newLink = new Order(inputCustomerID, inputModelNumber, inputQuantity, inputTotal);
	    Order previous = null;
	    Order current = first;
      
	    while(current != null && inputCustomerID > current.customerID) {
	    	previous = current;
	    	current = current.next;
	    }
	    
	    if (previous == null)
	    	first = newLink;
	    else
	    	previous.next = newLink;
	    
	    newLink.next = current;
    }
    
    public void search(int inputModelNumber) {
    	Order current = first;
    	int counter = 0;

		System.out.println("ORDERS:");

    	while (current != null) {
        	if (current.modelNumber == inputModelNumber) {
        		current.display();
        		counter++;
        	}
        	
    		current = current.next;
    	}
    	
    	if (counter == 0) {
    		System.out.println("No orders found for that Model Number!\n");
    	}
    }
}
//-------------------------------------------------------------------

public class FinalExam {

	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		int inputModelNumber;
		String inputDescription;
		double inputCost;
		double inputPrice;
		int inputQuantity;
		int inputCustomerID;
		
		ProductList products = new ProductList();
		OrderList orders = new OrderList();
		
		products.insert(33, "Patrick Roy jersey", 65.00, 199.99, 200);
		products.insert(1, "Brian Elliott jersey", 75.00, 229.99, 50);
		
		orders.insert(99, 33, 1, 199.99);

		while(true) {
			System.out.print("Enter first letter of: (I)nsert or (S)earch: ");
			char choice = getChar();
			
			switch(choice) {
				case 'i':
					System.out.print("Enter first letter of: (P)roduct or (O)rder: ");
					choice = getChar();
					
					switch(choice) {
						case 'p':
							System.out.print("Enter the Model Number: ");
							inputModelNumber = reader.nextInt();
							System.out.print("Enter the Description: ");
							inputDescription = getString();
							System.out.print("Enter the Cost: ");
							inputCost = reader.nextDouble();
							System.out.print("Enter the Price: ");
							inputPrice = reader.nextDouble();
							System.out.print("Enter the Quantity On Hand: ");
							inputQuantity = reader.nextInt();
							products.insert(inputModelNumber, inputDescription, inputCost, inputPrice, inputQuantity);
							break;
							
						case 'o':
							System.out.print("Enter the Customer ID: ");
							inputCustomerID = reader.nextInt();
							System.out.print("Enter the Model Number: ");
							inputModelNumber = reader.nextInt();
							System.out.print("Enter the Quantity Ordered: ");
							inputQuantity = reader.nextInt();
							
							inputPrice = products.getPrice(inputModelNumber) * inputQuantity;
							
							orders.insert(inputCustomerID, inputModelNumber, inputQuantity, inputPrice);
							break;
							
						default:
							System.out.println("Invalid entry!\n");
							break;
					}
					break;
					
				case 's':
					System.out.print("Search for Model Number: ");
					inputModelNumber = reader.nextInt();
					
					products.search(inputModelNumber);
					orders.search(inputModelNumber);
					break;
					
				default:
					System.out.println("Invalid entry!\n");
					break;
			}
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.toLowerCase().charAt(0);
	}

}
