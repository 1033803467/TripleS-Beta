package edu.ts.service;
/*
 * @interfaceName:     RecommendService
 * @Description:   推荐商品
 * @author          tengyihao
 * @version         V1.0
 * @Date
 */
import java.util.List;

import edu.ts.entity.Good;

public interface RecommendService {
	/**
	 * 推荐商品
	 * 
	 * @param  id 对特定id会员进行推荐
	 * @return List<RecommendedItem> 推荐商品list集
	 */
	public List<Good> recommendBycId(int id);
}
