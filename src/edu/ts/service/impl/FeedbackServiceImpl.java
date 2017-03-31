package edu.ts.service.impl;

import java.util.List;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.FeedbackDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.dao.impl.FeedbackDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.entity.Feedback;
import edu.ts.service.FeedbackService;


public class FeedbackServiceImpl implements FeedbackService{
	FeedbackDao fd = new FeedbackDaoImpl();
	@Override
	public boolean process(int fId) {
		return fd.process(fId);
	}

	@Override
	public List<Feedback> getAllUnprocessd() {
		return fd.getAllUnprocessed();
	}

	@Override
    public boolean addFeedBack(String openid, String fMessage) {
        CustomerDao customerDao = new CustomerDaoImpl();
        List<Customer> lists = customerDao.getCustomerByOpenid(openid);
        int cId  = lists.get(0).getcId();
        Feedback feedback = new Feedback(cId, fMessage);
        FeedbackDao feedbackDao = new FeedbackDaoImpl();
        return feedbackDao.add(feedback);
    }

	@Override
	public Feedback getByFid(int fId) {
		return fd.getByFid(fId).get(0);
	}

	
}
