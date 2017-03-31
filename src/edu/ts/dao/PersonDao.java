package edu.ts.dao;

/**
 * Created by jose on 2017/3/2.
 */
public interface PersonDao {
	public static final String PERSON_GROUP_ID = "1"; 
    /**
     * 创建一个person
     *
     * @param personGroupId personGroupId
     * @param name person name
     * @param userId person database id
     * @return personId
     */
    public String createPerson(String personGroupId, String name, String userId);

    /**
     *为person的facelist添加face
     *
     * @param personGroupId person group id
     * @param personId person id
     * @param userId person database id
     * @param url picture url
     */
    public void addFaceToPerson(String personGroupId, String personId, String userId, String url);
}
