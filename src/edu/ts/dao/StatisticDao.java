package edu.ts.dao;
/**
 * @ClassName:     StatisticDao
 * @Description:   图表统计
 *
 * @author          tengyihao
 * @version         V1.0
 * @Date
 */
import java.util.Map;

/**
 * Created by tengyihao on 2017/3/24.
 */
public interface StatisticDao {
    /**
     * @Description: 按月份统计会员
     * @param:
     * @return: Map<String,String> 月份-会员数映射
     * @throws
     *
     */
    Map<String,String> CustomerStatisticByTime();
    /**
     * @Description: 按月份统计订单
     * @param:
     * @return: Map<String,String> 月份-订单数映射
     * @throws
     *
     */
    Map<String,Integer> OrdersByMonth();
    /**
     * @Description: 按性别统计会员
     * @param:
     * @return: Map<String, Integer> 性别-人数映射
     * @throws
     *
     */
    Map<String, Integer> MemberGenderPie();
    /**
     * @Description: 按类型统计会员
     * @param:
     * @return: Map<String, Integer> 类型-会员人数映射
     * @throws
     *
     */
    Map<String, Integer> MemberTypePie();
    /**
     * @Description: 按月份统计销售
     * @param:
     * @return: Map<String, Integer> 月份-销售额映射
     * @throws
     *
     */
    Map<String, Integer> SalesByMonth();
    /**
     * @Description: 按月份统计个人消费
     * @param:
     * @return: Map<String, Integer> 月份-消费额映射
     * @throws
     *
     */
    Map<String, Integer> ConsumeByMonth(int cid);
    /**
     * @Description: 按月份统计反馈
     * @param:
     * @return: Map<String, String> 月份-反馈映射
     * @throws
     *
     */
    Map<String, String> FeedbackByMonth();
}
