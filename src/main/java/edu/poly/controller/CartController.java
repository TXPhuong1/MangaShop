package edu.poly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.repository.AccountRepository;
import edu.poly.repository.CartRepository;
import edu.poly.repository.OrderRepository;
import edu.poly.repository.OrderDetailRepository;
import edu.poly.repository.ProductRepository;
import edu.poly.entity.Account;
import edu.poly.entity.Cart;
import edu.poly.entity.Order;
import edu.poly.entity.OrderDetail;
import edu.poly.entity.Product;
import edu.poly.key.CartKey;
import edu.poly.service.SessionService;

@Controller
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	AccountRepository acccountRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailRepository detailRepository;
	
	@Autowired
	SessionService session;
	
	@GetMapping("/shop/cart")
	public String cart(Model model) {
		Account user = session.get("user");
		List<Cart> cart = cartRepository.findByAccountCart(user);
		Map<CartKey, Object[]> map = new HashMap<>();
		double totalCart = 0.0;
		for(Cart ca:cart) {
			totalCart += ca.getQuantity()*ca.getProductCart().getPrice();
			map.put(ca.getId(), new Object[] {
				ca.getAccountCart(),
				new Object[] {
					ca.getProductCart().getId(),
					ca.getProductCart().getName(),
					ca.getProductCart().getPrice(),
					ca.getProductCart().getImage().split(",", 0)
				},
				ca.getQuantity(),
				ca.getProductCart()
			});
		}
		model.addAttribute("products", map);
		model.addAttribute("numberCart", cart.size());
		model.addAttribute("totalCart", totalCart);
		return "shopping-cart";
	}
	
	@GetMapping("/shop/cart/add")
	public String cart_add(Model model, @RequestParam("id") Integer id, @RequestParam("qty") Integer qty) {
		Account user = session.get("user");
		if(productRepository.findById(id).get().getQuantity() > qty) {
			if(cartRepository.findById(new CartKey(user.getUsername(), id)).isEmpty())
				cartRepository.save(new Cart(new CartKey(user.getUsername(), id), user, productRepository.findById(id).get(), qty));
			else 
				cartRepository.save(new Cart(new CartKey(user.getUsername(), id), user, productRepository.findById(id).get(), cartRepository.findById(new CartKey(user.getUsername(), id)).get().getQuantity()+1));
		}
		return "shopping-cart";
	}
	
	@GetMapping("/shop/cart/update")
	public String cart_update(Model model, @RequestParam("id") Integer id, @RequestParam("qty") Integer qty) {
		Account user = session.get("user");
		if(productRepository.findById(id).get().getQuantity() > qty)
			cartRepository.save(new Cart(new CartKey(user.getUsername(), id), user, productRepository.findById(id).get(), qty));
		List<Cart> cart = cartRepository.findByAccountCart(user);
		Map<CartKey, Object[]> map = new HashMap<>();
		double totalCart = 0.0;
		for(Cart ca:cart) {
			totalCart += ca.getQuantity()*ca.getProductCart().getPrice();
			map.put(ca.getId(), new Object[] {
				ca.getAccountCart(),
				new Object[] {
					ca.getProductCart().getId(),
					ca.getProductCart().getName(),
					ca.getProductCart().getPrice(),
					ca.getProductCart().getImage().split(",", 0)
				},
				ca.getQuantity(),
				ca.getProductCart()
			});
		}
		model.addAttribute("products", map);
		model.addAttribute("numberCart", cart.size());
		model.addAttribute("totalCart", totalCart);
		return "shopping-cart";
	}
	
	@GetMapping("/shop/cart/delete")
	public String cart_delete(Model model, @RequestParam("id") Integer id) {
		Account user = session.get("user");
		cartRepository.deleteById(new CartKey(user.getUsername(), id));
		List<Cart> cart = cartRepository.findByAccountCart(user);
		Map<CartKey, Object[]> map = new HashMap<>();
		double totalCart = 0.0;
		for(Cart ca:cart) {
			totalCart += ca.getQuantity()*ca.getProductCart().getPrice();
			map.put(ca.getId(), new Object[] {
				ca.getAccountCart(),
				new Object[] {
					ca.getProductCart().getId(),
					ca.getProductCart().getName(),
					ca.getProductCart().getPrice(),
					ca.getProductCart().getImage().split(",", 0)
				},
				ca.getQuantity(),
				ca.getProductCart()
			});
		}
		model.addAttribute("products", map);
		model.addAttribute("numberCart", cart.size());
		model.addAttribute("totalCart", totalCart);
		return "shopping-cart";
	}
	
	@PostMapping("/shop/order")
	public String shopping_order(Model model, @RequestParam("address") String address){
		Account user = session.get("user");
		List<Cart> cart = cartRepository.findByAccountCart(user);
		List<OrderDetail> list = new ArrayList<>();
		double totalCart = 0.0;
		for(Cart ca:cart) {
			OrderDetail orDe = new OrderDetail();
			orDe.setPrice(ca.getProductCart().getPrice());
			orDe.setProduct(ca.getProductCart());
			orDe.setQuantity(ca.getQuantity());
			list.add(orDe);
			totalCart += ca.getQuantity()*ca.getProductCart().getPrice();
		}
		Order order = new Order();
		order.setAddress(address);
		order.setAccount(user);
		order.setOrderDetails(list);
		order.setTotal(totalCart);
		Order or = orderRepository.saveAndFlush(order);
		for(Cart ca:cart) {
			OrderDetail orDe = new OrderDetail();
			orDe.setOrder(or);
			orDe.setPrice(ca.getProductCart().getPrice());
			orDe.setProduct(ca.getProductCart());
			orDe.setQuantity(ca.getQuantity());
			Product p = productRepository.findById(ca.getProductCart().getId()).get();
			p.setQuantity(p.getQuantity() - ca.getQuantity());
			productRepository.save(p);
			detailRepository.save(orDe);
			cartRepository.delete(ca);
		}
		model.addAttribute("order_success", true);
		model.addAttribute("totalCart", 0);
		model.addAttribute("numberCart", 0);
		return "shopping-cart";
	}
}
