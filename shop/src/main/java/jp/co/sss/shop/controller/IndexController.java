package jp.co.sss.shop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
//	
//	//@RequestMapping("/ここにコンテキストパス以後の名前") 
//	public String メソッド名() { 
//		 return　"表示したいhtmlファイルの名前；
//		}
	@RequestMapping("/before") 
	public String before() { 
	 return "before"; 
	}
	@RequestMapping("/after") 
	public String after() { 
	 return "after"; 
	} 
	@RequestMapping("/transition") 
	public String sampleTransition() { 
	 return "sample_transition"; 
	} 
	 
	@RequestMapping("/index_f") 
	public String indexForward() { 
	return "forward:/"; 
	}
	@RequestMapping("/index_r") 
	public String indexRedirect() { 
	 return "redirect:/"; 
	}


}
