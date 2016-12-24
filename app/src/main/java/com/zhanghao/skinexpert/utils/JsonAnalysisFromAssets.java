package com.zhanghao.skinexpert.utils;

import android.content.Context;

import com.zhanghao.skinexpert.beans.QuestionBean;
import com.zhanghao.skinexpert.beans.TestResultBean;
import com.zhanghao.skinexpert.beans.TotalQuestionBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RockGao on 2016/12/23.
 */

public class JsonAnalysisFromAssets {
      //json解析，得到数据集合
      public static List<TotalQuestionBean> analysisJson(Context context) {
        String json = getJsonData(context);
        List<TotalQuestionBean> totalQuestions = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonArray = jsonObjectData.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectType = jsonArray.getJSONObject(i);
                //
                TotalQuestionBean totalquestion = new TotalQuestionBean();
                //
                List<TestResultBean> testResults = new ArrayList<>();
                JSONArray jsonArrayResult = jsonObjectType.getJSONArray("result");
                for (int n = 0; n <jsonArrayResult.length() ; n++) {
                    JSONObject jsonObjectResult = jsonArrayResult.getJSONObject(n);
                    //测试结果信息
                    TestResultBean resultbean = new TestResultBean();
                    resultbean.setTitle(jsonObjectResult.getString("title"));
                    resultbean.setContent(jsonObjectResult.getString("content"));
                    resultbean.setMaxScore(jsonObjectResult.getInt("maxScore"));
                    resultbean.setMinScore(jsonObjectResult.getInt("minScore"));
                    resultbean.setTestType(jsonObjectResult.getInt("testType"));
                    testResults.add(resultbean);
                }

                totalquestion.setTitle(jsonObjectType.get("title").toString());
                totalquestion.setCount(jsonObjectType.getInt("count"));
                totalquestion.setId(jsonObjectType.getInt("id"));
                totalquestion.setResults(testResults);
                List<List<Object>> questions = new ArrayList<>();
                JSONArray jsonArrayItem = jsonObjectType.getJSONArray("item");
                for (int j = 0; j <jsonArrayItem.length() ; j++) {
                    JSONObject jsonObjectItem = jsonArrayItem.getJSONObject(j);
                    List<Object> list = new ArrayList<>();
                    String questionTitle = jsonObjectItem.get("questionTitle").toString();
                    list.add(questionTitle);
                    JSONArray jsonArrayanswer = jsonObjectItem.getJSONArray("answer");
                    for (int k = 0; k <jsonArrayanswer.length() ; k++) {
                        JSONObject jsonObjectanswer = jsonArrayanswer.getJSONObject(k);

                        String answerTitle = jsonObjectanswer.get("answerTitle").toString();
                        String score = jsonObjectanswer.get("score").toString();
                        QuestionBean questBean = new QuestionBean(answerTitle,score);
                        char c = (char) (k+65);
                        questBean.setTitle(c+". "+answerTitle);
                        questBean.setScore(score);
                        list.add(questBean);
                    }
                    questions.add(list);
                }
                totalquestion.setQuestions(questions);
                totalQuestions.add(totalquestion);
            }
            return totalQuestions;
        } catch (JSONException e) {
            e.printStackTrace();
        }
          return null;
    }

    //从assets文件夹中读取.json文件
    public static String getJsonData(Context context) {
        StringBuffer sb = new StringBuffer();
        InputStream in=null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            in = context.getAssets().open("questions.json");
            int len =0;
            byte[] buffer = new byte[1024];
            while ((len=in.read(buffer,0,buffer.length))!=-1){
                baos.write(buffer,0,len);
                baos.flush();
            }
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in==null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos==null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
