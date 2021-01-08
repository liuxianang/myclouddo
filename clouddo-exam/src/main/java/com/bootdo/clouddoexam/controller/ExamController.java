package com.bootdo.clouddoexam.controller;


import com.alibaba.fastjson.JSON;
import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.utils.FileUtils;
import com.bootdo.clouddoexam.domain.AnswerDO;
import com.bootdo.clouddoexam.domain.PaperDO;
import com.bootdo.clouddoexam.domain.QuestionDO;
import com.bootdo.clouddoexam.domain.ScoreDO;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;
import com.bootdo.clouddoexam.service.AnswerService;
import com.bootdo.clouddoexam.service.PaperService;
import com.bootdo.clouddoexam.service.QuestionService;
import com.bootdo.clouddoexam.service.ScoreService;
import com.bootdo.clouddoexam.util.POIUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-12 12:17:36
 */

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Value("${app.filePath}")
    String filePath;

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private PaperService paperService;

    @GetMapping("{id}")
    public R get(@PathVariable String id) {
        ScoreDO blog = scoreService.get(id);
        return R.data(blog);
    }

    @GetMapping("examonline")
    public R examonline(@RequestParam Map<String, Object> params) {
        PaperDO paperinfo = JSON.parseObject(params.get("paperinfo").toString(),PaperDO.class);//将获得的json转换实体类
        if (params.get("choose_answer").equals("") && params.get("short_answer").equals("")) {
            return R.error();
        } else {
            AnswerDO answer = new AnswerDO();
            answer.setTestPaperid(paperinfo.getId()+"");
            answer.setTestPapername(paperinfo.getName());
            answer.setStudentid("100");
            answer.setStudentname("刘献盎");
            String choose_answer = params.get("choose_answer").toString();
            answer.setSingleChoiceAnswer(choose_answer.substring(0, choose_answer.length() - 1));
            String multiple_choice_answer = params.get("multiple_choice_answer").toString();
            answer.setMultipleChoiceAnswer(multiple_choice_answer.substring(0, multiple_choice_answer.length() - 1));
            String short_answer = params.get("short_answer").toString();
            short_answer = short_answer.substring(0, short_answer.length() - 1);
            String[] arr = short_answer.split("@"); // 用,分割
            for (int i = 0; i < arr.length; i++) {
                switch (i) {
                    case 0:
                        answer.setShortAnswer1(arr[i]);    //语句
                        break; //可选
                    case 1:
                        answer.setShortAnswer2(arr[i]);   //语句
                        break; //可选
                    case 2:
                        answer.setShortAnswer3(arr[i]);   //语句
                        break; //可选
                    case 3:
                        answer.setShortAnswer4(arr[i]);   //语句
                        break; //可选
                    case 4:
                        answer.setShortAnswer5(arr[i]);  //语句
                        break; //可选
                    default:
                        break; //可选
                }
            }
            answerService.save(answer);
            return R.ok();
        }

    }

    /*查询题目组装试卷*/
    @GetMapping("/paperQuestion")
    R paperQuestion(@RequestParam Map<String, Object> params) {
        Integer s = Integer.parseInt(params.get("id").toString());
        PaperDO paper = paperService.get(s);
        String[] aa = paper.getQuestionallId().split(",");

        Integer[] ids = new Integer[aa.length];

        for (int i = 0; i < aa.length; i++) {

            ids[i] = Integer.parseInt(aa[i]);

        }
        List<QuestionDO> list = questionService.findlist(ids);
        List<QuestionDO> examitim = new ArrayList<QuestionDO>();
        List<QuestionDO> examitimtwo = new ArrayList<QuestionDO>();
        List<QuestionDO> examitimthree = new ArrayList<QuestionDO>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals("0")) {/*单选题*/
                list.get(i).setAnswer("");
                examitim.add(list.get(i));
            } else if (list.get(i).getType().equals("1")) {/*多选题*/
                String[] ary = {};
                list.get(i).setAnswerduo(ary);
                examitimtwo.add(list.get(i));
            } else if (list.get(i).getType().equals("2")) {/*简答题*/
                list.get(i).setAnswer("");
                examitimthree.add(list.get(i));
            }
        }
        System.out.println(">>>>>>>>>>" + questionService.findlist(ids));
        return R.ok().put("examitim", examitim).put("examitimtwo", examitimtwo).put("examitimthree", examitimthree).put("paperinfo", paper);
    }

    @GetMapping("/paperall")
    R paperall(@RequestParam Map<String, Object> params) {
        System.out.println("wo>>>>>>>>>>");
        List<PaperDO> list = paperService.list(params);
        return R.ok().put("data", list);
    }

    @GetMapping("/paperallonline")
    R paperallonline(@RequestParam Map<String, Object> params) {
        System.out.println("wo>>>>>>>>>>");
        List<PaperDO> list = paperService.list(params);
        return R.ok().put("data", list);
    }

    /**
     * 分页查询
     */
    @GetMapping
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ScoreDO> fileList = scoreService.list(query);
//        List<FileDTO> fileDTOS = FileConvert.MAPPER.dos2dtos(fileList);
        System.out.println("wo>>>>>>>>>>" + fileList);
        System.out.println("wo2222>>>>>>>>>>" + params);
        int total = scoreService.count(query);
//        PageUtils pageUtils = new PageUtils(fileDTOS, total);
        PageUtils pageUtils = new PageUtils(fileList, total);
        return R.page(pageUtils);
    }

    @GetMapping("getToken")
    public R getToken() {
        return R.ok().put("token", FilterContextHandler.getToken()).put("url", "http://localhost:8002/api-exam/exam/upload")
                .put("key", UUID.randomUUID().toString());
    }

    @GetMapping("getTokenscore")
    public R getTokenscore(String id) {
        System.out.println(">>>>>>" + id);
        return R.ok().put("token", FilterContextHandler.getToken()).put("url", "http://localhost:8002/api-exam/exam/uploadscore")
                .put("key", id);
    }

    @PostMapping("upload")
    public R upload(MultipartFile file, String key) throws IOException {
        System.out.println(">>>>>>" + key);
        try {
            List<String[]> result = POIUtil.readExcel(file);
            ScoreDO score = new ScoreDO();
            for (int i = 1; i < result.size(); i++) {
                score.setStudentId(result.get(i)[0]);
                score.setStudentName(result.get(i)[1]);
                score.setStydentGrade(result.get(i)[2]);
                scoreService.save(score);
            }
            return R.ok().put("resPath", "导入成功");
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("文件导入失败");
        }
    }

    @PostMapping("uploadscore")
    public R uploadscore(MultipartFile file, String key) throws IOException {
        System.out.println(">>>>>>>>>" + key);
        try {
            List<String[]> result = POIUtil.readExcel(file);
            ScoreDO score = new ScoreDO();
            for (int i = 1; i < result.size(); i++) {
                score.setStudentId(result.get(i)[0]);
                int ch = Integer.parseInt(key);
                switch (ch) {
                    case 0:
                        score.setGeography(Integer.parseInt(result.get(i)[3]));
                        break;
                    case 1:
                        score.setChemistry(Integer.parseInt(result.get(i)[3]));
                        break;
                    case 2:
                        score.setHistory(Integer.parseInt(result.get(i)[3]));
                        break;
                    case 3:
                        score.setBiology(Integer.parseInt(result.get(i)[3]));
                        break;
                    case 4:
                        score.setMathematics(Integer.parseInt(result.get(i)[3]));
                        break;
                    case 5:
                        score.setPhysical(Integer.parseInt(result.get(i)[3]));
                        break;
                    case 6:
                        score.setEnglish(Double.parseDouble(result.get(i)[3]));
                        break;
                    case 7:
                        score.setChinese(Double.parseDouble(result.get(i)[3]));
                        break;
                    case 8:
                        score.setGovernment(Integer.parseInt(result.get(i)[3]));
                        break;
                    default:

                        break;
                }
                scoreService.update(score);
            }
            return R.ok().put("resPath", "导入成功");
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("文件导入失败");
        }
    }

    @GetMapping("getTokenout")
    public R getTokenout(String id) {
        System.out.println(">>>>>>" + id);
        return R.ok().put("token", FilterContextHandler.getToken()).put("url", "http://localhost:8002/api-exam/exam/export")
                .put("key", id);
    }

    @PostMapping("/export")
    public void exportExecl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("aaaaaaaaaaaaaaaaa");
        List<ScoreDO> list = scoreService.listall("二", "02", "Chinese");

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + "test" + ".xlsx");
        OutputStream ouputStream = response.getOutputStream();

        ouputStream.flush();
        ouputStream.close();


    }

    /**
     * 保存
     */
    @PostMapping
    public R save(ScoreDO file) {
        return R.operate(scoreService.save(file) > 0);
    }

    /**
     * 修改
     */
    @PutMapping
    public R update(ScoreDO file) {
        return R.operate(scoreService.update(file) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping
    public R remove(String id) {
        System.out.println("传入的删除id" + id);
        System.out.println("dddddddddddddddddddddd");
        return R.operate(scoreService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping("/batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        return R.operate(scoreService.batchRemove(ids) > 0);
    }
}
