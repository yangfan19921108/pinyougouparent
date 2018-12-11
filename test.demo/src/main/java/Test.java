import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: yf_mood
 * @CreateDate: 2018/12/6$ 15:13$
 */
public class Test {
    public static void main(String[] args) throws IOException, TemplateException {
        //1.创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //2.设置模板所在目录
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\32743\\IdeaProjects\\pinyougouparent\\test.demo\\src\\main\\resources"));
        //3.设置字符集
        configuration.setDefaultEncoding("utf-8");
        //4.加载模板
        Template template = configuration.getTemplate("test.ftl");
        //5.创建数据模型
        Map dataModel=new HashMap();
        dataModel.put("name","杨帆");
        dataModel.put("msg","你是个好人");
        Writer out=new FileWriter(new File("c:\\test.html"));
        template.process(dataModel,out);
        out.close();
    }
}
