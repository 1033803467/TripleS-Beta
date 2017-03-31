package edu.ts.service.impl;

import edu.ts.entity.Good;
import edu.ts.service.GoodService;
import edu.ts.service.RecommendService;
import edu.ts.util.DBUtil;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.util.ArrayList;
import java.util.List;

public class RecommendServiceImpl implements RecommendService{

	@Override
	public List<Good> recommendBycId(int id) {
			//推荐结果集
			List<RecommendedItem> recommendItems = null;
			List<Good> list = new ArrayList<Good>();
			//相近用户范围
			int kNeighbors = 5;

				try {
					//获取数据源
					ConnectionPoolDataSource ds = DBUtil.getDataSource();
					//用数据源构建模型
					//movie_preferences 表名
					//userID，movieID，preference 字段名
					JDBCDataModel dataModel=new MySQLJDBCDataModel(ds,"preferences","c_id","g_id","p_score");  

					//计算用户相似度
				    UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
				    UserNeighborhood neighborhood=new NearestNUserNeighborhood(kNeighbors,similarity,dataModel); 
				    //构造推荐引擎	
				    Recommender recommender=new GenericUserBasedRecommender(dataModel,neighborhood,similarity);
				    System.out.println("processing........");
				    recommendItems=recommender.recommend(id, 4); 
				    System.out.println("done........"); 
				    
				}
				catch (TasteException e) {
					e.printStackTrace();
				}
				GoodService gs = new GoodServiceImpl();
				for(RecommendedItem i :recommendItems){
					Good g = gs.getById((int) i.getItemID()).get(0);
					list.add(g);
				}
				
				return list;

	}

}
