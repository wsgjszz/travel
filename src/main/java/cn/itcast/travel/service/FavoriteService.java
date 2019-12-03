package cn.itcast.travel.service;

public interface FavoriteService {

    /**
     * 根据线路rid和用户uid判断当前用户是否收藏该线路
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(int rid,int uid);

    /**
     * 查询当前线路的收藏次数
     * @param rid
     * @return
     */
    public int findTimes(int rid);

    /**
     * 根据用户uid和线路rid完成收藏
     * @param uid
     * @param rid
     */
    public void favorite(int uid, int rid);
}
