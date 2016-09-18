package plz.com.singbar.view.info;

import java.util.List;

/**
 * Created by Administrator on 2016/9/18 0018.
 */
public class NewSongInfo {

    /**
     * song : [{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"Beyond","control":"0000000000","songid":"877578","has_mv":"1","encrypted_songid":"4205d640a09579ac78dL"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"黄家驹","control":"0000000000","songid":"14795583","has_mv":"1","encrypted_songid":"3306e1c33f0956b2ababL"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"欢子","control":"0000000000","songid":"109915269","has_mv":"0","encrypted_songid":"240768d2c85095795bb9eL"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"叶倩文","control":"0000000000","songid":"23276935","has_mv":"0","encrypted_songid":"55071632d870957a04312L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"1","songname":"海阔天空 beyond  干声","artistname":"陈奕迅~安子","control":"0000000000","songid":"73924588","has_mv":"0","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"T榜","control":"0000000000","songid":"266907369","has_mv":"0","encrypted_songid":"0307fe8aee90957726fa9L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"1","songname":"海阔天空-电吉他版","artistname":"MC雪殇","control":"0100000000","songid":"73984962","has_mv":"0","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"1","songname":"海阔天空翻唱","artistname":"西单男孩","control":"0000000000","songid":"73870587","has_mv":"0","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"1","songname":"海阔天空主题歌(我们的天空)","artistname":"杨成成","control":"0000000000","songid":"73899356","has_mv":"0","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"1","songname":"海阔天空","artistname":"无尘无埃","control":"0000000000","songid":"73931185","has_mv":"0","encrypted_songid":""}]
     * order : song,album
     * error_code : 22000
     * album : [{"albumname":"单曲 - 海阔天空","artistpic":"http://qukufile2.qianqian.com/data2/pic/43514877/43514877.jpg","albumid":"22164314","artistname":"亮亮"}]
     */

    private String order;
    private int error_code;
    /**
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * yyr_artist : 0
     * songname : 海阔天空
     * artistname : Beyond
     * control : 0000000000
     * songid : 877578
     * has_mv : 1
     * encrypted_songid : 4205d640a09579ac78dL
     */

    private List<SongBean> song;
    /**
     * albumname : 单曲 - 海阔天空
     * artistpic : http://qukufile2.qianqian.com/data2/pic/43514877/43514877.jpg
     * albumid : 22164314
     * artistname : 亮亮
     */

    private List<AlbumBean> album;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<SongBean> getSong() {
        return song;
    }

    public void setSong(List<SongBean> song) {
        this.song = song;
    }

    public List<AlbumBean> getAlbum() {
        return album;
    }

    public void setAlbum(List<AlbumBean> album) {
        this.album = album;
    }

    public static class SongBean {
        private String bitrate_fee;
        private String yyr_artist;
        private String songname;
        private String artistname;
        private String control;
        private String songid;
        private String has_mv;
        private String encrypted_songid;
        private String url;
        private String ima;
        private String geci;

        public String getSinger() {
            return singer;
        }

        public void setSinger(String singer) {
            this.singer = singer;
        }

        private String singer;

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getYyr_artist() {
            return yyr_artist;
        }

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public String getSongname() {
            return songname;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIma() {
            return ima;
        }

        public void setIma(String ima) {
            this.ima = ima;
        }

        public String getGeci() {
            return geci;
        }

        public void setGeci(String geci) {
            this.geci = geci;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getControl() {
            return control;
        }

        public void setControl(String control) {
            this.control = control;
        }

        public String getSongid() {
            return songid;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public String getHas_mv() {
            return has_mv;
        }

        public void setHas_mv(String has_mv) {
            this.has_mv = has_mv;
        }

        public String getEncrypted_songid() {
            return encrypted_songid;
        }

        public void setEncrypted_songid(String encrypted_songid) {
            this.encrypted_songid = encrypted_songid;
        }
    }

    public static class AlbumBean {
        private String albumname;
        private String artistpic;
        private String albumid;
        private String artistname;

        public String getAlbumname() {
            return albumname;
        }

        public void setAlbumname(String albumname) {
            this.albumname = albumname;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public String getAlbumid() {
            return albumid;
        }

        public void setAlbumid(String albumid) {
            this.albumid = albumid;
        }

        public String getArtistname() {
            return artistname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }
    }
}
