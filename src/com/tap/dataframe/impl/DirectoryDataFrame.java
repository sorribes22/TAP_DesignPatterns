package com.tap.dataframe.impl;

import com.tap.dataframe.DataFrame;
import com.tap.dataframe.ImplResolver;
import com.tap.dataframe.exception.InvalidFileFormatException;
import com.tap.dataframe.query.Query;
import com.tap.dataframe.visitor.DataFrameVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.*;

public class DirectoryDataFrame extends DataFrame {

    private ArrayList<DataFrame> childrens = new ArrayList<>();

    private ImplResolver implResolver = new ImplResolver();

    public DirectoryDataFrame() {

    }

    public void loadContent(File file) {
        loadDirectory(file, this);
    }

    /**
     * Expected to be called on a directory, not on a file
     */
    public void loadDirectory(File pointer, DirectoryDataFrame root) {
        for (File item : pointer.listFiles()) {
            if (item.isDirectory()) {
                DirectoryDataFrame directory = new DirectoryDataFrame();
                loadDirectory(item, directory);
                root.add(directory);
            } else {
                String extension = fileExtension(item.getName());
                try {
                    String factoryNamespace = implResolver.factoryFromExtension(extension);
                    if (factoryNamespace == null) continue;

                    Class<?> factoryImpl = Class.forName(factoryNamespace);
                    Method method = factoryImpl.getDeclaredMethod("makeDataFrame");
                    Object object = method.invoke(factoryImpl.getDeclaredConstructor().newInstance());
                    DataFrame dataFrame = (DataFrame) object;

                    dataFrame.loadContent(item);

                    root.add(dataFrame);
                } catch (ClassNotFoundException e) {
                    System.out.format("Could not resolve file extension: '%s'\n", extension);
                } catch (InvalidFileFormatException e) {
                    System.out.println(e.getMessage());
                } catch (FileNotFoundException e) {
                    // todo enhancement
                    e.printStackTrace();
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void add(DataFrame children) {
        childrens.add(children);
    }

    /**
     * @param filename Name of the file
     * @return File extension
     * @author https://frontbackend.com/java/how-to-get-extension-of-a-file-in-java
     */
    private static String fileExtension(String filename) {
        return Optional.of(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse("");
    }

    public void accept(DataFrameVisitor v) {
        v.visitDirectory(this);

    }

    public ArrayList<DataFrame> getChildrens() {
        return childrens;
    }


    public String at(int row, String label) {
        int size;

        for (DataFrame children : this.getChildrens()) {
            size = children.size();
            if (row > size) row -= size;
            else return children.at(row, label);
        }

        return null;
    }


    @Override
    public List<Map<String, String>> query(Query<Map<String, String>> condition) {
        Map<String, List<String>> result = new HashMap<>();
        Map<String, List<String>> result2 = new HashMap<>();

        for (DataFrame df : this.childrens) {
            result2 = df.query(condition);
            if (result.isEmpty() && !(df instanceof DirectoryDataFrame)) {
                for (String label : df.getLabels())
                    result.put(label, new ArrayList<>());
            }
            for (String label : result2.keySet()) {
                result.get(label).addAll(result2.get(label));
            }
        }
        return result;
    }


    public int size() {
        int totalSize = 0;
        for(DataFrame df : childrens){
            totalSize = totalSize + df.size();
        }
        return totalSize;
    }

    public Iterator<Map<String, String>> iterator() {
        return new Iterator<>() {

            private Iterator<> currentIndex = 0;
            // https://stackoverflow.com/questions/3610261/is-it-possible-to-merge-iterators-in-java/3610310
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Map<String, String> next() {
                Map<String, String> row = new HashMap<>();

                for (String label : labels) {
                    row.put(label, getContent().get(label).get(currentIndex));
                }

                currentIndex++;

                return row;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}