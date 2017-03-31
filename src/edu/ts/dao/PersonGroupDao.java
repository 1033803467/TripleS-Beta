package edu.ts.dao;

/**
 * Created by jose on 2017/3/2.
 */
public interface PersonGroupDao {
    /**
     *创建一个personGroup
     *
     * @param groupName person group name
     * @param personGroupId person group Id
     */
    public void createPersonGroup(String groupName, String personGroupId);

    /**
     * 查询persongroup list
     *
     */
    public void  getPersonGroupList();

    /**
     * 训练persongroup
     *
     * @param personGroupId
     */
    public void trainPersongroup(String personGroupId);
}
