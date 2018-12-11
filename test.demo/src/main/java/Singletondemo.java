import javax.swing.*;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/12/8$ 10:48$
 */
public class Singletondemo {
    //恶汉
    private static Singletondemo instance=new Singletondemo();
    static Singletondemo getInstance(){
        return  instance;
    }

}
class Singleton{
    //懒汉
    private static Singleton instance=null;

    static Singleton getInstance() {
        if(instance==null){
            instance=new Singleton();
        }
        return  instance;
    }
}