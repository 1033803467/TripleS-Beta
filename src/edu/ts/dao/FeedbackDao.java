package edu.ts.dao;

import java.util.List;

import edu.ts.entity.Feedback;
/**
 * 
 * @author zmnerd
 *
 */
public interface FeedbackDao {
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	public boolean add(Feedback f);
	
	/**
	 * 
	 * @param fId
	 * @return
	 */
	public boolean process(int fId);
	
	/**
	 * 
	 * @return
	 */
	public List<Feedback> getAllUnprocessed();
	
	/**
	 * 
	 * @param cId
	 * @return
	 */
	public List<Feedback> getByCustomerId(int cId);
	
	/**
	 * 
	 * @param state
	 * @return
	 */
	public List<Feedback> getByState(int state);
	
	/**
	 * 
	 * @param fId
	 * @return
	 */
	public List<Feedback> getByFid(int fId);
		
}
