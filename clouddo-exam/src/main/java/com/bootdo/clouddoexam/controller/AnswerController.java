package com.bootdo.clouddoexam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.bootdo.clouddoexam.domain.PaperDO;
import com.bootdo.clouddoexam.domain.QuestionDO;
import com.bootdo.clouddoexam.service.PaperService;
import com.bootdo.clouddoexam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoexam.domain.AnswerDO;
import com.bootdo.clouddoexam.service.AnswerService;

import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-07-14 14:12:54
 */

@RestController
@RequestMapping("/answer")
public class AnswerController {
	@Autowired
	private PaperService paperService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@GetMapping
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AnswerDO> answerList = answerService.list(query);
		int total = answerService.count(query);
		PageUtils pageUtils = new PageUtils(answerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")

	String add(){
	    return "clouddoexam/answer/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Integer id,Model model){
		AnswerDO answer = answerService.get(id);
		model.addAttribute("answer", answer);
	    return "clouddoexam/answer/edit";
	}
	/*查询题目组装试卷*/
	@GetMapping("/paperAnswerQuestion")
	R paperQuestion(@RequestParam Map<String, Object> params){
		AnswerDO answer= answerService.get(Integer.parseInt(params.get("id").toString()));
		Integer s= Integer.parseInt(answer.getTestPaperid());
		PaperDO paper = paperService.get(s);
		String[] aa=paper.getQuestionallId().split(",");

		Integer[] ids = new Integer[aa.length];

		for(int i=0;i<aa.length;i++){

			ids[i] = Integer.parseInt(aa[i]);

		}
		List<QuestionDO> list = questionService.findlist(ids);
		List<QuestionDO> examitim=new ArrayList<QuestionDO>();
		List<QuestionDO> examitimtwo=new ArrayList<QuestionDO>();
		List<QuestionDO> examitimthree=new ArrayList<QuestionDO>();
		String[] choose_answer= answer.getSingleChoiceAnswer().split(",");
		String[] multiple_choice_answer=answer.getMultipleChoiceAnswer().split(";");
		String [] short_answer={answer.getShortAnswer1(),answer.getShortAnswer2(),answer.getShortAnswer3(),answer.getShortAnswer4(),answer.getShortAnswer5()}  ;

		String [] short_answerYorN={answer.getAnswer1yorn(),answer.getAnswer2yorn(),answer.getAnswer3yorn(),answer.getAnswer4yorn(),answer.getAnswer5yorn()}  ;

		String [] short_answerScore={answer.getAnswer1score(),answer.getAnswer2score(),answer.getAnswer3score(),answer.getAnswer4score(),answer.getAnswer5score()}  ;
		int X=0;
		int Y=0;
		int Z=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getType().equals("0")){/*单选题*/
				if(X<choose_answer.length) {
					list.get(i).setModeanswer(list.get(i).getAnswer());
					if(choose_answer[X].equals(list.get(i).getAnswer())){
						list.get(i).setWinscore(list.get(i).getScore());
						list.get(i).setYorN("1");
					}else{
						list.get(i).setWinscore(0);
						list.get(i).setYorN("0");
					}
					list.get(i).setAnswer(choose_answer[X]);
					X++;
				}else{
					list.get(i).setYorN("0");
					list.get(i).setWinscore(0);
					list.get(i).setModeanswer(list.get(i).getAnswer());
					list.get(i).setAnswer("");
				}
				examitim.add(list.get(i));

			}
			else if(list.get(i).getType().equals("1")){/*多选题*/
				if(Y<choose_answer.length) {
					list.get(i).setModeanswer(list.get(i).getAnswer());
					if(multiple_choice_answer[Y].equals(list.get(i).getAnswer())){
						list.get(i).setWinscore(list.get(i).getScore());
						list.get(i).setYorN("1");
					}else{
						list.get(i).setWinscore(0);
						list.get(i).setYorN("0");
					}
					String[] ary = multiple_choice_answer[Y].split(",");
					list.get(i).setAnswerduo(ary);
					Y++;
				}else{
					list.get(i).setYorN("0");
					list.get(i).setWinscore(0);
					list.get(i).setModeanswer(list.get(i).getAnswer());
					String[] ary={};
					list.get(i).setAnswerduo(ary);
				}
				examitimtwo.add(list.get(i));

			}
			else if(list.get(i).getType().equals("2")){/*简答题*/
				list.get(i).setModeanswer(list.get(i).getAnswer());
				list.get(i).setAnswer(short_answer[Z]);
				list.get(i).setYorN(short_answerYorN[Z]);
				if(short_answerScore[Z]==null||short_answerScore[Z]==""){

				}else{
					list.get(i).setWinscore(Integer.valueOf(short_answerScore[Z]));
				}
				Z++;
				examitimthree.add(list.get(i));
			}
		}
		System.out.println(">>>>>>>>>>"+questionService.findlist(ids));
		return R.ok().put("examitim",examitim).put("examitimtwo",examitimtwo).put("examitimthree",examitimthree);
	}
	/**
	 * 保存
	 */

	@PostMapping("/save")
	public R save(@RequestBody  AnswerDO answer){
		if(answerService.save(answer)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */

	@PutMapping("/update")
	public R update(@RequestBody  AnswerDO answer){
		answerService.update(answer);
		return R.ok();
	}
	@PostMapping("/correct")//在线批改
	public R correct(@RequestBody  Map<String, Object> map){
		AnswerDO answer = new AnswerDO();
		answer.setId((Integer) map.get("id"));
		String   totalScore=map.get("Score1").toString()+map.get("Score2").toString()+map.get("Score3").toString();
		totalScore=totalScore.substring(0,totalScore.length()-1);
		System.out.println(totalScore);
		String[] sumlist = totalScore.split(",");
		int sum=0;
		for(int n=0;n<sumlist.length;n++){
			sum= sum+Integer.parseInt(sumlist[n]);
		}
		answer.setSingleyorn( map.get("YorN1").toString().substring(0,map.get("YorN1").toString().length()-1));
		answer.setSinglescore( map.get("Score1").toString().substring(0,map.get("Score1").toString().length()-1));
		answer.setMultipleyorn(map.get("YorN2").toString().substring(0,map.get("YorN2").toString().length()-1));
		answer.setMultiplescore(map.get("Score2").toString().substring(0,map.get("Score2").toString().length()-1));
		String[] answeryorn={"","","","",""};
		String[] answerscore={"","","","",""};
		String answeryorn3=map.get("YorN3").toString().substring(0,map.get("YorN3").toString().length()-1);
		String answerscore3=map.get("Score3").toString().substring(0,map.get("Score3").toString().length()-1);
		String[] answeryornlist = answeryorn3.split(",");
		String[] answerscorelist = answerscore3.split(",");
		for(int i=0;i<answeryornlist.length;i++){
			answeryorn[i]=answeryornlist[i];
		}
		for(int j=0;j<answeryornlist.length;j++){
			answerscore[j]=answerscorelist[j];
		}
		answer.setAnswer1yorn(answeryorn[0]);
		answer.setAnswer2yorn(answeryorn[1]);
		answer.setAnswer3yorn(answeryorn[2]);
		answer.setAnswer3yorn(answeryorn[3]);
		answer.setAnswer4yorn(answeryorn[4]);

		answer.setAnswer1score(answerscore[0]);
		answer.setAnswer2score(answerscore[1]);
		answer.setAnswer3score(answerscore[2]);
		answer.setAnswer4score(answerscore[3]);
		answer.setAnswer5score(answerscore[4]);
		answer.setTotalscore(sum+"");
		answer.setState(1);
		answerService.update(answer);
		return R.ok();
	}
	/**
	 * 删除
	 */
	@DeleteMapping
	public R remove( Integer id){
		if(answerService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] ids){
		answerService.batchRemove(ids);
		return R.ok();
	}
	
}
