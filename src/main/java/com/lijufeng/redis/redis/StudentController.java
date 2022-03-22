package com.lijufeng.redis.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.Oneway;


@Controller
public class StudentController {

  @Autowired
    private StudentService studentService;
    /*-------------------------------字符串操作--------------------------------------------------*/
    //http://localhost:8080/put?key=data&value=sql        String 增
    @RequestMapping(value = "/put")
    public @ResponseBody  Object put(String key,String value){
        studentService.put(key,value);
        return key+"和"+value+"值已成功放入Redis数据库";
    }



    //http://localhost:8080/get?key=data   String  查
    @RequestMapping("/get")
    public @ResponseBody Object getData(String key){
       return studentService.get(key);
    }



    //http://localhost:8080/update?key=lunch&newValue=elephant   String 更
    @RequestMapping(value = "update")
    public @ResponseBody Object update(String key,String newValue){
        studentService.update(key,newValue);
        return key+"值已更新";
    }
    //http://localhost:8080/del?key=break    String 删
    @RequestMapping(value = "del")
    public @ResponseBody Object del(String key){
        studentService.del(key);
        return key+"键值 已删除 ";
    }

    /*设置字符串的有效时长*/
    @RequestMapping(value = "strExpire")
    public @ResponseBody Object strExpire(String key,Long time){
        studentService.strExpire(key,time);
        return key + "时间已设置为"+time+"秒";
    }
    /*查询字符串的有效时长*/
    @RequestMapping(value="getExpire")
    public @ResponseBody Object getExpire(String key){
        Object expire = studentService.getExpire(key);
        if(expire.toString().equals("-1")){
            expire = "无穷";
        }
        return key+"的剩余时间为"+expire+"秒";
    }


    /*-------------------------------HASH操作--------------------------------------------------*/
    //http://localhost:8080/hashSet?key=week       Hash   增
    @RequestMapping(value = "hashSet")
    public @ResponseBody  Object hashSet(String key){
         studentService.hashSet(key);
        return "星期的数据已存入数据库----";
    }

    //http://localhost:8080/hashGetKeys?key=week    Hash  查 keys
    @RequestMapping(value = "hashGetKeys")
    public @ResponseBody Object hashGetKeys(String key){
        return studentService.hashGetKeys(key);
    }


    //http://localhost:8080/hashGetValues?key=week     Hash  查 values
    @RequestMapping(value = "hashGetValues")
    public @ResponseBody Object hashGetValues(String key){
        return studentService.hashGetValues(key);
    }



    //http://localhost:8080/hashGetResults?key=week    Hash 查  key和values一起
    @RequestMapping(value = "hashGetResults")
    public @ResponseBody Object hashGetResults(String key){
        return studentService.hashGetResults(key);
    }


    //改put 3个参数     删delete
    // http://localhost:8080/hashUpdate?key=week&field=BA&newValue=星期八
    // http://localhost:8080/hashUpdate?key=week&field=SUN&newValue=星期日     HASH  更新  可以用来增加
    @RequestMapping(value = "hashUpdate")
    public @ResponseBody Object hashUpdate(String key,String field,String newValue){
        studentService.hashUpdate(key,field,newValue);
        return key+"中"+field+"值已修改";
    }

    //http://localhost:8080/hashDelete?key=week&field=JIU    HASH  删除
    @RequestMapping(value = "hashDelete")
    public @ResponseBody Object hashDelete(String key,String field){
        studentService.hashDelete(key,field);
        return key+"中"+field+"值已删除";
    }
/*----------------------------------列表数据的查询-----------------------------------------------------*/
    //http://localhost:8080/getList?key=mylist
    @RequestMapping(value="getList")
    public @ResponseBody Object getList(String key){

        return key+"列表中有如下数据："+studentService.getList(key);
    }
    /*----------------------------------有序集合数据的查询-----------------------------------------------------*/
    @RequestMapping(value = "getZset")
    public @ResponseBody Object getZset(String key){
        return  key+"有序集合中有以下数据："+studentService.getZset(key);
    }
    /*----------------------------------Set集合数据的查询-----------------------------------------------------*/
    @RequestMapping(value = "getSet")
    public @ResponseBody Object getSet(String key){
        return key+"集合中有以下数据："+studentService.getSet(key);
    }
}
