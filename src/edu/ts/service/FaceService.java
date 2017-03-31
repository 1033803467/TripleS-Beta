package edu.ts.service;

public interface FaceService {
	/**
     * 获取url图片的faceId
     *
     * @param url 图片地址
     * @return faceId
     */
    public String getFaceId(String url);

    /**
     * 识别人脸
     *
     * @param personGroupId person group id
     * @param faceId
     * @return personId
     */
    public String identifyFace(String personGroupId, String faceId);
}
