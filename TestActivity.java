package com.test2.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by shiyawei on 17/1/14.
 */

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String string = test("", "\\|", "&&");
        Log.i("Test", string);
    }

    /**
     * @param string         源数据
     * @param srcDivideSign  数据中的切割符号 比如一个竖线"\\|"
     * @param showDivideSign 需要显示的数据分割符号
     * @return
     */
    private String test(String string, String srcDivideSign, String showDivideSign) {
        int row = 0;//行数
        int column = 0;//列数

        String[] stArray = string.split(srcDivideSign);

        if (stArray == null || "".equals(stArray[0]))
            return "数据为空";
        //获取列数
        column = stArray.length;
        if (column == 0)
            return "没有数据";

        for (int i = 0; i < stArray.length; i++) {
            String[] dateArray = stArray[i].split(",");
            //获取最大行数
            row = dateArray.length > row ? dateArray.length : row;
        }

        if (row <= 0)
            return "没有数据";

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                //获取每个"|"中间包含的数据集合
                String[] dateArray = stArray[j].split(",");
                //获取"|"中包含的数据的个数
                int dateLength = dateArray.length;

                if (j == column - 1) {
                    //取每行中的最后数据
                    if (i + 1 > dateLength) {
                        //当每个分割号包含的数据个数小于当前行数时候 ,取数据中的最后一个
                        stringBuilder.append(dateArray[dateLength - 1]);
                    } else {
                        //取对应的行号的数据
                        stringBuilder.append(dateArray[i]);
                    }

                } else if (i + 1 > dateLength) {
                    //当每个分割号包含的数据个数小于当前行数时候 ,取数据中的最后一个
                    stringBuilder.append(dateArray[dateLength - 1] + showDivideSign);
                } else {
                    //取对应的行号的数据
                    stringBuilder.append(dateArray[i] + showDivideSign);
                }

            }
            //换行
            stringBuilder.append("\n\r");
        }
        return stringBuilder.toString();
    }

}
