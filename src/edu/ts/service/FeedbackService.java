package edu.ts.service;

import java.util.List;

import edu.ts.entity.Feedback;
/**
 * 反馈管理service,
 * 支持更改反馈状态，获取未处理反馈，按时间段查询反馈数量
 * @author zmnerd
 *
 */
public interface FeedbackService {
    
    /**
     * 处理订单后修改反馈状态
     *
     * @param int 反馈id
     * @return 成功返回true，失败返回false
     */
    public boolean process(int fId);
    
    /**
     * 
     * 查找所有未处理的反馈信息
     * 
     * @return 反馈的集合
     */
    public List<Feedback> getAllUnprocessd() ;
    
    /**
    *
    * @param openid
    * @param fMessage
    * @return
    */
   public boolean addFeedBack(String openid, String fMessage);
   
   /**
    * 
    * @param fId
    * @return feedback
    */
   public Feedback getByFid(int fId);
}
