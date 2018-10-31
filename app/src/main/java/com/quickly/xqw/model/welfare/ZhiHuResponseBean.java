package com.quickly.xqw.model.welfare;

import com.google.gson.Gson;

import java.util.List;

public class ZhiHuResponseBean {


    /**
     * date : 20181021
     * stories : [{"images":["https://pic1.zhimg.com/v2-8766a5fbc2bc0c111fbc34f8056add74.jpg"],"type":0,"id":9697842,"ga_prefix":"102122","title":"小事 · 我为什么如此努力"},{"images":["https://pic2.zhimg.com/v2-59f7e83f940871b991aaa28a7bd8c361.jpg"],"type":0,"id":9696413,"ga_prefix":"102121","title":"这两个不穿衣服的主角，伴我走过了最科学的童年"},{"images":["https://pic4.zhimg.com/v2-0635717544655da68a415318c1ca03ef.jpg"],"type":0,"id":9698861,"ga_prefix":"102119","title":"为什么沙僧从来不喊孙悟空「猴哥」？"},{"title":"- 没有资格认证就为人父母，我好慌\r\n- 那就接好这些操作指南（多图）","ga_prefix":"102117","images":["https://pic2.zhimg.com/v2-f8ee9acd69ec4d441ee094f2e41ca22d.jpg"],"multipic":true,"type":0,"id":9698993},{"images":["https://pic2.zhimg.com/v2-4c0630799e2ac1e368bed7b18de38a95.jpg"],"type":0,"id":9698924,"ga_prefix":"102115","title":"概率没有什么决定不决定的，事情发生了就是发生了"},{"title":"每周一吸 · 家有萌狗子（多图）","ga_prefix":"102113","images":["https://pic3.zhimg.com/v2-8483f2662f21e15ea7b197cb005c1c66.jpg"],"multipic":true,"type":0,"id":9698955},{"images":["https://pic1.zhimg.com/v2-f05b17549f9b0946048201a4c056330c.jpg"],"type":0,"id":9699092,"ga_prefix":"102112","title":"大误 · 朱元璋：我不是，我没有，别瞎说啊"},{"images":["https://pic4.zhimg.com/v2-0eb2889f79d1c95116325505ffbd569b.jpg"],"type":0,"id":9698511,"ga_prefix":"102110","title":"想要桌面看得过去，就得玻璃下面压点绿"},{"images":["https://pic2.zhimg.com/v2-c3db20321987a643cee708ac996f243d.jpg"],"type":0,"id":9699004,"ga_prefix":"102109","title":"「阳光下的泡沫，是彩色的」，可我却不知道，这是为何"},{"images":["https://pic1.zhimg.com/v2-8e0b9d8769633ec7a01993ee135a7c58.jpg"],"type":0,"id":9699014,"ga_prefix":"102108","title":"大家喜爱（吃）的蛙儿子，都是从这里来的"},{"images":["https://pic2.zhimg.com/v2-6811e08dec530c4d8792a73396832a45.jpg"],"type":0,"id":9699018,"ga_prefix":"102107","title":"本周热门精选 · 惊变 2018"},{"images":["https://pic2.zhimg.com/v2-a8318ebd7783fdb388f7701290583281.jpg"],"type":0,"id":9699072,"ga_prefix":"102106","title":"瞎扯 · 如何正确地吐槽"},{"images":["https://pic4.zhimg.com/v2-fd9bff336c03610e3291224cd618cd3f.jpg"],"type":0,"id":9699090,"ga_prefix":"102101","title":"S8 意外爆冷的 RNG，这次你不是虽败犹荣"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-185e619b049730a5dda3ee99968c8244.jpg","type":0,"id":9699090,"ga_prefix":"102101","title":"S8 意外爆冷的 RNG，这次你不是虽败犹荣"},{"image":"https://pic1.zhimg.com/v2-1d24e63b806cd755123e6f9279e54ed0.jpg","type":0,"id":9698861,"ga_prefix":"102119","title":"为什么沙僧从来不喊孙悟空「猴哥」？"},{"image":"https://pic3.zhimg.com/v2-4d585b8be2eff720a391133bc799f10e.jpg","type":0,"id":9699067,"ga_prefix":"102021","title":"如果那个时候我们再加把劲，中国科幻电影真的会很好看"},{"image":"https://pic1.zhimg.com/v2-7a03092b6d7f5835ac22212874852ecc.jpg","type":0,"id":9698747,"ga_prefix":"102018","title":"「蒜蓉扇贝」的蒜蓉真香，可自己就是做不出来这个味儿"},{"image":"https://pic4.zhimg.com/v2-e00826d9019b6e41cf09992f0c656ee3.jpg","type":0,"id":9698885,"ga_prefix":"102016","title":"看完 2 元和 100 元维生素 C 的差别，我落泪了"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public static ZhiHuResponseBean objectFromData(String str) {

        return new Gson().fromJson(str, ZhiHuResponseBean.class);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-8766a5fbc2bc0c111fbc34f8056add74.jpg"]
         * type : 0
         * id : 9697842
         * ga_prefix : 102122
         * title : 小事 · 我为什么如此努力
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public static StoriesBean objectFromData(String str) {

            return new Gson().fromJson(str, StoriesBean.class);
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-185e619b049730a5dda3ee99968c8244.jpg
         * type : 0
         * id : 9699090
         * ga_prefix : 102101
         * title : S8 意外爆冷的 RNG，这次你不是虽败犹荣
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public static TopStoriesBean objectFromData(String str) {

            return new Gson().fromJson(str, TopStoriesBean.class);
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
