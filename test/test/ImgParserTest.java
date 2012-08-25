package test;

/**
 *
 * @author daoyu
 */
public class ImgParserTest extends Thread {
    int id;
    int sec;

    public ImgParserTest(int _sec, int _id) {
        this.sec = _sec;
        this.id = _id;
    }

    public void run() {
        try {
            sleep(sec * 1000);
        } catch (InterruptedException ex) {
        }
    }

//    @Test
//    public void imgTest() throws MalformedURLException, IOException {
//        String url ="http://mobile.zol.com.cn/229/2295060.html";
//        ImgParser imgP = new ImgParser(url, "2","1","1");
//
//
//        Iterator it = imgP.getExtImg();
//        while(it.hasNext()){
//            System.out.println(it.next().toString());
//        }
//    }
//    @Test
//    public void regExTest() {
//        String oldName = "http://p4.yokacdn.com/pic/fun/women/2011/600U213P41T8D31664F231DT20110512144156_maxh550.jpg";
//        Pattern p = Pattern.compile("//p4");
//        Matcher m = p.matcher(oldName);
//
//        System.out.println(m.replaceAll("//p1"));
//    }
    public static void main(String[] args) throws InterruptedException {
//        ImgParserTest ts[] = new ImgParserTest[5];
//        for (int i = 0; i < 5; i++) {
//            ts[i] = new ImgParserTest(5-i,i);
//            ts[i].start();
//        }
//
//        while (true) {
//            for (int i=0;i<5;i++) {
//                if (ts[i] != null && !ts[i].isAlive()) {
//                    System.out.println(ts[i].id);
//                    ts[i] = null;
//                }
//            }
//            sleep(200);
//        }
        char s = '3';
        System.out.println(s);
    }
}
