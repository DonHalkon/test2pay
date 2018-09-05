package com.naiden.controller;

import com.naiden.model.EOrder;
import com.naiden.dto.OrderDTO;
import com.naiden.service.OrderService;
import org.jboss.logging.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jms.JMSException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "order")
public class OrderController {

	private final static Logger log = Logger.getLogger(OrderController.class.getName());
	private final String MESSAGE_COLOR_SUCCESS = "alert-success";
	private final String MESSAGE_COLOR_ALERT = "alert-danger";
	private final String CREATE_VIEW = "create";

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(path = "create", method = RequestMethod.GET)
	public String create() {
		return CREATE_VIEW;
	}

	@RequestMapping(path = "create", method = RequestMethod.POST)
	public String create(@Valid OrderDTO orderDTO, BindingResult result, Model model) throws JMSException {
		log.info("creation POST request: started");
		String message = "SUCCESS";
		String messageColor = MESSAGE_COLOR_SUCCESS;

		if (result.hasErrors()) {
			message = result.getAllErrors()
					.stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.joining(", "));
			messageColor = MESSAGE_COLOR_ALERT;
		    log.info("creation POST request: verification failed");
		} else {
		    log.info("creation POST request: verification passed");
			orderService.addOrder(orderDTO.toEOrder());
			model.addAttribute(new OrderDTO());
		}
		model.addAttribute("message", message);
		model.addAttribute("messageColor", messageColor);
		return CREATE_VIEW;
	}

	@RequestMapping(path = "list", method = RequestMethod.GET)
	public String list(Model model) {
		List<EOrder> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		return "list";
	}
}
