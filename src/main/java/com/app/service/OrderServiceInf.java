package com.app.service;

import java.util.List;
import com.app.exception.BookException;
import com.app.model.BookingDetails;
import com.app.model.OrderDetails;

public interface OrderServiceInf {
	
	/******************* Create ******************/
	public double placeOrder(int userId,List<BookingDetails> bookingDetails) throws BookException; 
	
	/******************* Read ********************/
	public OrderDetails findOrder(int orderId)throws BookException;
	public List<BookingDetails> getAllProductOfAnOrder(int orderId) throws BookException;
	public List<List<BookingDetails>> getAllProductOfAnUser(int userId) throws BookException;

	/******************* Update ********************/
	public double addProductsInOrder(int orderId,List<BookingDetails> bookingDetails) throws BookException;
	public double removeProductsInOrder(int orderId,List<Integer> bookInventoryId) throws BookException;
	/******************* Remove ********************/
	public void removeAllOrderOfAnUser(int userId) throws BookException;
	public void removeOrder(int orderId) throws BookException;
	
	}
