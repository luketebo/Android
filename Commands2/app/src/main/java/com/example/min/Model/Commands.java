package com.example.min.Model;

public class Commands {
    private int id;
    private String name;     //名字
    private String details;     //解释
    private String grammar;     //语法
    private String param;     //参数
    private String example;     //示例
    private  int collect;    //是否收藏

    public Commands(String name, String details, String grammar, String param, String example,int collect) {
        super();
        this.name = name;
        this.details = details;
        this.grammar = grammar;
        this.param = param;
        this.example = example;
        this.collect = collect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getGrammar() {
        return grammar;
    }

    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    @Override
    public String toString() {
        return "Commands{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", grammar='" + grammar + '\'' +
                ", param='" + param + '\'' +
                ", example='" + example + '\'' +
                ", collect='" + collect + '\'' +
                '}';
    }
}
