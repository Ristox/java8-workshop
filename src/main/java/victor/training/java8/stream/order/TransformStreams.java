package victor.training.java8.stream.order;

import victor.training.java8.stream.order.dto.OrderDto;
import victor.training.java8.stream.order.entity.Customer;
import victor.training.java8.stream.order.entity.Order;
import victor.training.java8.stream.order.entity.Order.PaymentMethod;
import victor.training.java8.stream.order.entity.OrderLine;
import victor.training.java8.stream.order.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class TransformStreams {

	/**
	 * Transform all entities to DTOs.
	 * Discussion:.. Make it cleanest!
	 */
	public List<OrderDto> p01_toDtos(List<Order> orders) {
		return orders.stream().map(OrderDto::new).collect(toList());
	}
	
	/**
	 * Note: Order.getPaymentMethod()
	 */
	public Set<PaymentMethod> p02_getUsedPaymentMethods(Customer customer) {
		return customer.getOrders().stream()
			.map(Order::getPaymentMethod)
			.collect(toSet());
	}
	
	/**
	 * When did the customer created orders ?
	 * Note: Order.getCreationDate()
	 */
	public SortedSet<LocalDate> p03_getOrderDatesAscending(Customer customer) {
		return customer.getOrders().stream()
				.map(Order::getCreationDate)
				.collect(toCollection(TreeSet::new));
	}
	
	
	/**
	 * @return a map order.id -> order
	 */
	public Map<Long, Order> p04_mapOrdersById(Customer customer) {
		return customer.getOrders().stream()
				.collect(toMap(Order::getId, identity()));
	}
	
	/** 
	 * Orders grouped by Order.paymentMethod
	 */
	public Map<PaymentMethod, List<Order>> p05_getProductsByPaymentMethod(Customer customer) {
		return customer.getOrders().stream()
				.collect(groupingBy(Order::getPaymentMethod));
	}
	
	// -------------- MOVIE BREAK :p --------------------
	
	/** 
	 * A hard one !
	 * Get total number of products bought by a customer, across all her orders.
	 * Customer --->* Order --->* OrderLines(.count .product)
	 * The sum of all counts for the same product.
	 * i.e. SELECT PROD_ID, SUM(COUNT) FROM PROD GROUPING BY PROD_ID
	 */
	public Map<Product, Long> p06_getProductCount(Customer customer) {
		return streamLinesOf(customer.getOrders())
				.collect(
					groupingBy(OrderLine::getProduct, summingLong(OrderLine::getCount))
				);
	}

	private Stream<OrderLine> streamLinesOf(List<Order> orders) {
		return orders.stream().flatMap(order -> order.getOrderLines().stream());
	}

	/**
	 * All the unique products bought by the customer, 
	 * sorted by Product.name.
	 */
	public List<Product> p07_getAllOrderedProducts(Customer customer) {
		return streamLinesOf(customer.getOrders())
				.map(OrderLine::getProduct)
				.distinct()
				.sorted(comparing(Product::getName))
				.collect(toList());
	}
	
	
	/**
	 * The names of all the products bought by Customer,
	 * sorted and then concatenated by ",".
	 * Example: "Armchair,Chair,Table".
	 * Hint: Reuse the previous function.
	 */
	public String p08_getProductsJoined(Customer customer) {
		return p07_getAllOrderedProducts(customer)
				.stream()
				.map(Product::getName)
				.collect(joining(","));
	}
	
	/**
	 * Sum of all Order.getTotalPrice(), truncated to Long.
	 */
	public Long p09_getApproximateTotalOrdersPrice(Customer customer) {
		// TODO +, longValue(), reduce()
		return null;
	}
}
