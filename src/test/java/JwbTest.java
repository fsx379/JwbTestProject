import java.net.URLEncoder;
import org.junit.Test;
import cn.suxin.util.HttpUtil;

/**
 * 使用寿命
 * @author bjsxfeng
 * 步骤
 * 1. 跑列表 testStartTask()：
 *      需要指定日期
 *      
 * 2. 跑具体的文章内容 testStartTaskSpiderActicleList()
 *      需要指定文章id
 *      
 * 3. 打印到word中 testStartTaskForPrint() 
 *      需要配置文章id和输出路径
 *      
 * 此外提供了：
 * （1）任务进度查询
 * （2）文章列表查询
 * （3）文章内容查询等功能
 * 
 */
public class JwbTest {
    
    public static final String URL_TASK_QUERY = "http://127.0.0.1:9999/query/queryTask?taskId=%s";

    public static final String URL_TASK_START = "http://127.0.0.1:9999/query/startTask?startDate=%s&endDate=%s&taskDesc=%s";

    public static final String URL_TASK_DEL = "http://127.0.0.1:9999/query/delTask?taskId=%s";
    
    
    public static final String URL_ARTICLELIST_QUERY = "http://127.0.0.1:9999/query/queryAllArticleListByDate?startDate=%s&endDate=%s";


    public static final String URL_ARTICLELISTBYID_QUERY = "http://127.0.0.1:9999/query/queryArticleListByArtIds?artIds=%s";

    public static final String URL_TASK_STRT_SPIDER_ARTICLELIS = "http://127.0.0.1:9999/query/startSpiderActicleDetailByArtIds?artIds=%s";
    
    public static final String URL_TASK_STRT_PRINT = "http://127.0.0.1:9999/query/startTaskForPrintWord?artIds=%s&path=%s";
    
    
    /**
     * 参数
     */
    String startDateStr = "2018-06-01";
    String endDateStr = "2018-06-30";
    String artIds = "1532691790200285,1532691790506302,1532691790506303,1532691790921328,1532691791338352,1532691791739367,1532691792149381,1532691792565409,1532691792565410,1532691792565414,1532691792565417,1532691792956425,1532691793363441,1532691793772467,1532691793772468,1532691794168483,1532691794572509,1532691794572509,1532691794993545";
    
    
    @Test
    public void testStartTask() {
        try {

            String taskDesc = "test";
            
            String url = String.format(URL_TASK_START, startDateStr,endDateStr,URLEncoder.encode(taskDesc, "UTF-8"));
            
            System.out.println(url);
            
            String ret = HttpUtil.sendRequest(url, false);
            System.out.println("[testTaskStart] ret: " + ret );
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testBatchStartTask() {
        try {

            String dateStr = "2018-06-01,2018-06-04,2018-06-06,2018-06-09,2018-06-10,2018-06-12,2018-06-15,2018-06-16,2018-06-20,2018-06-24,2018-06-25,2018-06-27,2018-06-30";
            String[] dates  = dateStr.split(",");
            for(String d : dates) {
                startDateStr = d;
                endDateStr = d;
                testStartTask();
                Thread.sleep(400);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testStartTaskSpiderActicleList() {
        try {
            
             
            String url = String.format(URL_TASK_STRT_SPIDER_ARTICLELIS, artIds);
            System.out.println(url);
            String ret = HttpUtil.sendRequest(url, false);
            System.out.println("[testStartTaskSpiderActicleList] ret: " + ret );
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @Test
    public void testStartTaskForPrint() {
        try {
            
            //String path = "E:\\jwb_20180727165601.doc";
            String path = "";
            
            String url = String.format(URL_TASK_STRT_PRINT, artIds,URLEncoder.encode(path, "UTF-8"));
            System.out.println(url);
            String ret = HttpUtil.sendRequest(url, false);
            System.out.println("[testStartTaskForPrint] ret: " + ret );
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testQueryTask() {
        try {
            String taskId = "1532677025139";
            
            String url = String.format(URL_TASK_QUERY, taskId );
            System.out.println(url);
            String ret = HttpUtil.sendRequest(url, false);
            System.out.println("[testQueryTask] ret: " + ret );
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testDelTask() {
        try {
            String taskId = "1532676385320";
            
            String url = String.format(URL_TASK_DEL, taskId );
            System.out.println(url);
            String ret = HttpUtil.sendRequest(url, false);
            System.out.println("[testDelTask] ret: " + ret );
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void testQueryActicleList() {
        try {
            
            String url = String.format(URL_ARTICLELIST_QUERY, startDateStr , endDateStr );
            System.out.println(url);
            String ret = HttpUtil.sendRequest(url, false);
            System.out.println("[testQueryActicleList] ret: " + ret );
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    @Test
    public void testQueryActicleListByIds() {
        try {
            
            String url = String.format(URL_ARTICLELISTBYID_QUERY, artIds);
            System.out.println(url);
            String ret = HttpUtil.sendRequest(url, false);
            System.out.println("[testQueryActicleListByIds] ret: " + ret );
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
