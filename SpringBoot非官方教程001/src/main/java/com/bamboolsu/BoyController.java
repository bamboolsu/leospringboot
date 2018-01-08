package com.bamboolsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoyController {
    @Autowired
    private BoyRep boyRep;

    /**
     * 查询所有列表
     * @return
     */
    @RequestMapping(value = "/boys",method = RequestMethod.GET)
    public List<Boy> getBoyList(){
        return boyRep.findAll();
    }

    // http://localhost:8080/springboot/boy
    @RequestMapping(value = "/boy",method = RequestMethod.GET)
    public String  getBoy(){
        String result;
        try {
            List<Boy> boys = boyRep.findAll();
            System.out.println("here will call boy! boys.size is: " + boys.size());
            result = "boy id is: " +  boys.get(0).getId() + " boy age is: " + boys.get(0).getAge();
        } catch (Exception e) {
            System.out.println("here exception, ex: " + e.getMessage());
            result = "here exception, ex: " + e.getMessage();
        }
        return result;
    }

    /**
     * 添加一个boy
     * @param age
     * @return
     */
    @RequestMapping(value = "/boys",method = RequestMethod.POST)
    public Boy addBoy(@RequestParam("age") Integer age){
        Boy boy = new Boy();
        boy.setAge(age);
        return boyRep.save(boy);
    }
}