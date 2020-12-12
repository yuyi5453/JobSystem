package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import pojo.AdminInfo;
import pojo.CompanyInfo;
import pojo.StudentInfo;
import service.AdminInfoService;
import service.CompanyInfoService;
import service.StudentInfoService;
import util.json.RestResult;
import util.json.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.CompanyInfo;
import pojo.StudentInfo;
import service.AdminInfoService;
import service.CompanyInfoService;
import service.StudentInfoService;
import util.json.RestResult;
import util.json.ResultCode;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 560寝室
 * @since 2020-12-06
 */

@Controller

public class AdminInfoController {
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private CompanyInfoService companyInfoService;
    @Autowired
    private AdminInfoService adminInfoService;


    @RequestMapping("/showUnsolved")
    @ResponseBody
    public String getUnsolvedCompany() {
        List<CompanyInfo> list = adminInfoService.selectUnsolvedCompany(CompanyInfo.CHECKING);
        return new RestResult().setCode(ResultCode.SUCCESS).setMessage("成功").setData(list).toString();
    }

    @RequestMapping("/showAll")
    @ResponseBody
    public String getAllStudent() {
        List<StudentInfo> list = adminInfoService.selectAllStudent();
        return new RestResult().setCode(ResultCode.SUCCESS).setMessage("成功").setData(list).toString();
    }

    @RequestMapping("/addStu")
    @ResponseBody
    public String addStudent(HttpServletRequest request){
        StudentInfo student=new StudentInfo();
        String studentId=request.getParameter("studentId");
        student.setStudentId(studentId);
        String password=request.getParameter("password");
        student.setPassword(password);
        String studentName=request.getParameter("studentName");
        student.setStudentName(studentName);
        int sex=Integer.parseInt(request.getParameter("sex"));
        student.setSex(sex);
        String politicalStatus=request.getParameter("politicalStatus");
        student.setPoliticalStatus(politicalStatus);
        String hometown=request.getParameter("hometown");
        student.setHometown(hometown);
        if(adminInfoService.insertStudent(student)){
            return new RestResult().setCode(ResultCode.SUCCESS).setMessage("成功").setData(student).toString();
        }
        return new RestResult().setCode(ResultCode.FAIL).setMessage("失败").toString();
    }

    @RequestMapping("/company/{id}/{flag}")
    @ResponseBody
    public String checkCompany(@PathVariable("id") String companyId, @PathVariable("flag") boolean flag){
        if(flag){
            CompanyInfo companyInfo=adminInfoService.updateCompany(companyId);
            return new RestResult().setCode(ResultCode.SUCCESS).setMessage("已通过").setData(companyInfo).toString();
        }
        return new RestResult().setCode(ResultCode.SUCCESS).setMessage("未通过").toString();
    }
}

