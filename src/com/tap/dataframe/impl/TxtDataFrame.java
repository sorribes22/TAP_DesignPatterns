package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.exception.InvalidFileFormatException;
import com.tap.dataframe.exception.ItemWithIncorrectNumberOfAttributesException;
import com.tap.dataframe.exception.InvalidFormatException;

import java.util.ArrayList;
import java.util.Scanner;

public class TxtDataFrame extends DataFrame {
    int line = 0;
    int labelIndex = 0;
    int items=0;
    final String REGEX = "([A-z-0-9])*:((([A-z-0-9])*,)*||([A-z-0-9])*)*";

    public TxtDataFrame() {

    }

    public void loadContent(Scanner file) throws InvalidFileFormatException {
        String splitRow1[];
        String splitRow2[];
        String row ;
        while (file.hasNextLine()) {
                 row = file.nextLine();
                if(row.matches(REGEX)){
                    splitRow1 = row.split(":");
                    labels.add(splitRow1[0]);
                    labelIndex++;
                    content.put(splitRow1[0],new ArrayList<>());
                    splitRow2=splitRow1[1].split(",");

                    if(line==0)items=splitRow2.length; //First time save the number of elements, to validate others columns
                    else if(splitRow2.length!=items) throw new ItemWithIncorrectNumberOfAttributesException(labelIndex,items, splitRow2.length);

                    for (String data:splitRow2) {
                        System.out.println(data);
                        content.get(splitRow1[0]).add(data);
                    }

                }else throw new InvalidFormatException(labelIndex+1);

                line++;
                }


            }


        }




