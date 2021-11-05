package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ItemWithIncorrectNumberOfAttributesException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//public class TxtDataFrame extends DataFrame {
//    int line = 0;
//    int labelIndex = 0;
//
//    final String REGEX = "#COLUMNS,(([A-z]|[0-9])+:(String|int|bool|double|char),)*([A-z]|[0-9])+:(String|int|bool|double|char)";
//
//    public TxtDataFrame() {
//
//    }
//
//    public void loadContent(Scanner file) throws ItemWithIncorrectNumberOfAttributesException {
//        boolean correct = true;
//        while (file.hasNextLine() && correct) {
//            String row = file.nextLine();
//            if (line==0 ){
//                if(!row.matches(REGEX)) correct=false;
//                else{
//                    String splitRow[] = row.split(",");
//
//                    for (int i=1;i<splitRow.length;i++){
//                        labels.add(splitRow[i].split(":")[0]);
//                        labelIndex++;
//
//                    }
//                } else if (line == 2 && !row.equals("#ROWS")) correct = false;
//                else {
//                    if (splitRow.length != labelIndex)
//                        throw new ItemWithIncorrectNumberOfAttributesException(items, labelIndex, row.length);
//
//
//                }
//
//
//            }
//
//
//        }
//
//    }
//
//    public void detectType(String[] splitColumn){
//        switch( splitColumn[1]){
//
//            case "String": content.put(splitColumn[0],new ArrayList<StringValue>());
//
//            case "int":
//
//            case "bool":
//
//            case "char":
//
//            case "double":
//        }
//    }
//}

