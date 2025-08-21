package jp.co.sss.shop.controller;

import java.sql.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.shop.bean.ItemBean;
import jp.co.sss.shop.entity.Item;
import jp.co.sss.shop.form.ItemForm;
import jp.co.sss.shop.repository.ItemRepository;

@Controller
@RequestMapping("/items")

public class ItemController {
	@Autowired
	ItemRepository repository;
	//	@RequestMapping("/findItemAndCategory")
	//    public String list(Model model) {
	//        List<ItemWithCategory> list = service.findAll(); // 取得済み想定
	//        model.addAttribute("items", list);
	//        return "items/item_category_list"; // ここがテンプレ名
	//    }

	@RequestMapping("/findAll")
	public String showItemList(Model model) {
		model.addAttribute("items", repository.findAll());
		return "items/item_list";
	}

	@RequestMapping("/findAllByOrderByPriceDesc")
	public String showItemListByOrderByPriceDesc(Model model) {
		model.addAttribute("items", repository.findAllByOrderByPriceDesc());
		return "items/item_list";
	}

	@RequestMapping("/getReferenceById/{id}")
	public String showItem(@PathVariable int id, Model model) {
		Item item = repository.getReferenceById(id);
		ItemBean itemBean = new ItemBean();
		itemBean.setId(item.getId());
		itemBean.setName(item.getName());
		itemBean.setPrice(item.getPrice());
		model.addAttribute("item", itemBean);
		return "items/item";
	}

	@RequestMapping("/create/input")
	public String createInput() {
		return "items/create_input";
	}

	@RequestMapping(path = "/create/complete", method = RequestMethod.POST)
	public String createComplete(ItemForm form, Model model) {
		Item item = new Item();
		BeanUtils.copyProperties(form, item, "id");
		item = repository.save(item);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item, itemBean);
		model.addAttribute("item", itemBean);
		return "items/item";
	}

	@RequestMapping("/update/input/{id}")
	public String updateInput(@PathVariable Integer id, Model model) {
		Item item = repository.getReferenceById(id);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item, itemBean);
		model.addAttribute("item", itemBean);
		return "items/update_input";
	}

	@RequestMapping(path = "/update/complete/{id}", method = RequestMethod.POST)
	public String updateComplete(@PathVariable Integer id, ItemForm form, Model model) {
		Item item = repository.getReferenceById(id);
		BeanUtils.copyProperties(form, item, "id");
		item = repository.save(item);
		ItemBean itemBean = new ItemBean();
		BeanUtils.copyProperties(item, itemBean);
		model.addAttribute("item", itemBean);
		return "items/item";
	}

	@RequestMapping("/findByPrice/{price}")
	public String showItemListByPrice(@PathVariable Integer price, Model model) {
		model.addAttribute("items", repository.findByPrice(price));
		return "items/item_list";
	}

	@GetMapping("/findByNameAndPrice/{name}/{price}")
	public String showItemListByNameAndPrice(
			@PathVariable String name, @PathVariable Integer price, Model model) {
		model.addAttribute("items", repository.findByNameAndPrice(name, price));
		return "items/item_list";
	}

	@GetMapping("/findByNameLike/{name}")
	public String showItemListByNameLike(@PathVariable String name, Model model) {
		model.addAttribute("items", repository.findByNameContaining(name));
		return "items/item_list";
	}

	@RequestMapping("/findAllAndSetDropdown")
	public String itemListSetDropdown(Model model) {
		model.addAttribute("items", repository.findAll());
		return "items/item_list_dropdown";
	}

	@RequestMapping("/delete/input")
	public String deleteInput(Model model) {
		model.addAttribute("items", repository.findAll());
		return "items/delete_input";
	}

	@RequestMapping(path = "/delete/complete", method = RequestMethod.POST)
	public String deleteComplete(ItemForm form) {
		repository.deleteById(form.getId());
		return "redirect:/items/findAll";
	}

	@GetMapping("/findAllJs")
	public String showItemListJs(Model model) {
		model.addAttribute("items", repository.findAll());
		// 実行時の日付を取得してリクエスト属性に保存する 
		model.addAttribute("now", new Date(0));
		return "items/item_list_js";
	}

	@RequestMapping("/layout_view")
	public String layoutView() {
		return "layout_view";
	}

}