package com.lzx.onematerial.utils;

/**
 * Created by lizhenxin on 17-12-1.
 */

public class ApiUtil {

    public static final String BANNER = "3";
    public static final String BANNER_NORMAL = "4";
    public static final String BANNER_QA = "5";


    public static final String KUAN_LINK = "https://www.coolapk.com/apk/com.lzx.onematerial";

    /**
     * 专题封面的链接
     * @param id    专题id
     * @return      链接
     */
    public static String getTopicCoverUrl(String id) {
        return "http://m.wufazhuce.com/topic/" + id + "?channel=qq";
    }


    /**
     * 搜索链接
     * @param category  分类
     * @param content   搜索的内容
     * @param page      页数
     * @return
     */
    public static String getSearchUrl(String category, String content, int page){
        return "http://v3.wufazhuce.com:8000/api/search/" + category + "/" + content + "/" + page +"?version=4.3.4&platform=android";
    }


    /**
     * 获取搜索的内容的链接
     * @param category  分类
     * @param id        内容id
     * @return
     */
    public static String getSearchContentUrl(String category, String id) {
        switch (category) {
            case "0":
                return "http://v3.wufazhuce.com:8000/api/hp/feeds/" + id + "/0?version=4.3.4&platform=android";
            case "1":
                return "http://v3.wufazhuce.com:8000/api/essay/htmlcontent/" + id + "?version=4.3.4&platform=android";
            case "4":
                return "http://v3.wufazhuce.com:8000/api/music/htmlcontent/" + id + "?version=4.3.4&platform=android";
            case "5":
                return "http://v3.wufazhuce.com:8000/api/movie/htmlcontent/" + id + "?version=4.3.4&platform=android";
            case "8":
                return "http://v3.wufazhuce.com:8000/api/radio/htmlcontent/" + id + "?version=4.3.4&platform=android";
        }
        return "";
    }


    /**
     *
     * 获取作者列表
     * @return
     */
    public static String getAuthorsUrl() {
        return "http://v3.wufazhuce.com:8000/api/author/hot?&version=4.3.4&&platform=android";
    }


    /**
     * 获取作者详细信息
     * @param userId    作者id
     * @return
     */
    public static String getAuthorDetailUrl(String userId) {
        return "http://v3.wufazhuce.com:8000/api/author/info?author_id=" + userId + "&version=4.3.4&platform=android";
    }

    /**
     * 获取作者作品列表
     * @param pageNum   页数
     * @param userId    作者id
     * @return
     */
    public static String getAuthorWorkUrl(String pageNum, String userId) {
        return "http://v3.wufazhuce.com:8000/api/author/works?&page_num=" + pageNum + "&author_id=" + userId + "&version=4.3.4&platform=android";
    }


    /**
     * 获取每日内容的id列表
     * @return
     */
    public static String getDayIdListUrl() {
        return "http://v3.wufazhuce.com:8000/api/onelist/idlist";
    }


    /**
     * 每日内容
     * @param aimId     内容id
     * @return
     */
    public static String getDayContentUrl(String aimId) {
        return "http://v3.wufazhuce.com:8000/api/onelist/" + aimId + "/0?version=4.3.0";
    }


    public static String getBannerListUrl(String category) {
        return "http://v3.wufazhuce.com:8000/api/banner/list/" + category + "?version=4.3.4&platform=android";
    }
}
