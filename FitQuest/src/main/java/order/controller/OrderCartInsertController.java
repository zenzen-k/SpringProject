package order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import member.model.MemberBean;
import product.model.MyCartList;
import product.model.ProductDao;
import usage.model.UsageDao;

@Controller
public class OrderCartInsertController {
	private final String command = "cartInsert.od";
	private final String getPage = "redirect:/cartList.od";
	@Autowired 
	UsageDao usageDao;
	@Autowired
	ProductDao productDao;

	@RequestMapping(value = command, method = RequestMethod.GET)
	public String doAction(@RequestParam("pnum") int pnum,
			@RequestParam("id") String tid,
			Model model,
			HttpSession session,
			HttpServletResponse response) throws IOException {
		MemberBean memberBean = (MemberBean)session.getAttribute("loginInfo");
		boolean flag1 = (Boolean)session.getAttribute("flag1");
		MyCartList cartList = (MyCartList)session.getAttribute("cartList");
		response.setContentType("text/html; charset=utf-8");
		if(flag1 == true) {
			if(memberBean == null) { // 로그인 정보 없을 때
				session.setAttribute("destination", "redirect:/cartInsert.od?pnum=" + pnum + "&id=" + tid);
				response.getWriter().print("<script>alert('로그인이 필요합니다.');</script>");
				response.getWriter().flush();
				return "forward:/login.mb";
			} else { //로그인된 상태.
				if(memberBean.getMtype().equals("generic")) { //일반 회원만 장바구니 추가 가능.
					model.addAttribute("memberBean", memberBean);
					if(cartList == null) { //카트 생성된거 없으면 생성 시킴.
						cartList = new MyCartList();
					}
					Map<String, String> map = new HashMap<String, String>(); //회원 아이디, 트레이너 아이디를 담을 맵.
					map.put("tid", tid);
					map.put("mid", memberBean.getId());
					int cnt = usageDao.getUsageCount(map);
					System.out.println("cnt: " + cnt);//해당 트레이너의 회원권이 있는지 확인해주는 
					if(cnt == 0) {
						List<Integer> oList = cartList.getOrderList(); //장바구니에 있는 상품번호 전체 호출
						for(int prodNum : oList) { //장바구니의 상품의 해당 트레이너 아이디가 추가 할려고 하는 상품의 트레이너 아이디랑 일치 하는지 보는 것.
							String cartTid = productDao.getIdByPnum(prodNum);
							if(tid.equals(cartTid)) {
								try {
									response.getWriter().print("<script>alert('장바구니에 해당 트레이너의 회원권이 이미 있어서 추가 불가능합니다.');</script>");
									response.getWriter().flush();
									return "forward:/cartList.od";
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
						cartList.addOrder(pnum);
						session.setAttribute("flag1", false);
					} else {
						try {
							response.getWriter().print("<script>alert('이미 해당 트레이너의 회원권이 있어서 구매 불가능합니다.');</script>");
							response.getWriter().flush();
							return "forward:/trainerList.pd";
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

			}
		}
		session.setAttribute("cartList", cartList);
		return getPage;	
	}
}
