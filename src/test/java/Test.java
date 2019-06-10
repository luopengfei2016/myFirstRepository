import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int i=0;
        Test a = new Test();
        List<String> b = new ArrayList<>();
        b.add("222");
        a.method2(b);

        a.method(i);
        System.out.println(i);
        System.out.println(b.size());
    }
    void method(int i){
        i =3;
    }
    void method2(List<String> a){
        a.add("111");
    }
}

