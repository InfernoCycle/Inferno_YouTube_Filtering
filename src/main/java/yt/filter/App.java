package yt.filter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private final String dateFormats_patterns="(\\d{4}[-/]\\d{2}[-/]\\d{0,2}|\\d{1,2}[-/]\\d{1,2}[-/]\\d{2,4}|\\d{1,2}[-/]\\d{1,2}[-/]\\d{4,4})";
    public String formatDates(String val, String form, boolean check){
        Pattern pattern = null;
        boolean [] marks = {false,false,false};
        if(form.equals("MM{-/}DD{-/}YYYY")){
            marks[0] = true;
            pattern = Pattern.compile("(\\d{1,2}[-/]\\d{1,2}[-/]\\d{4}|\\d{1,2}[-/]\\d{1,2}[-/]\\d{2})");
        }else if(form.equals("DD{-/}MM{-/}YYYY")){
            marks[1] = true;
            pattern = Pattern.compile("(\\d{1,2}[-/]\\d{1,2}[-/]\\d{4}|\\d{1,2}[-/]\\d{1,2}[-/]\\d{2})");
        }else if(form.equals("YYYY{-/}MM{-/}DD")){
            marks[2] = true;
            pattern = Pattern.compile("(\\d{4}[-/]\\d{1,2}[-/]\\d{1,2}|\\d{2}[-/]\\d{1,2}[-/]\\d{1,2})");
        }
        Matcher match = pattern.matcher(val);

        if(check){
            if(match.matches()){
                return "true";
            }else{
                return "false";
            }
        }

        if(match.matches()){
            String newVal = "";
            for(short i = 0; i < marks.length; i++){
                String [] strs = val.split("(-|/)");
                format_date_str(strs);
                if(marks[i] && i==0){
                    newVal = revertYear(strs[2])+"-"+strs[0]+"-"+strs[1];
                }
                else if(marks[i] && i==1){
                    newVal = revertYear(strs[2])+"-"+strs[1]+"-"+strs[0];
                }
                else if(marks[i] && i==2){
                    newVal = revertYear(strs[0])+"-"+strs[1]+"-"+strs[2];
                }
            }
            return newVal;
        }
        return "";
    }

    public String format_date_str(String val){
        if(val.length() == 1){
            return "0"+val;
        }
        return val;
    }

    public void format_date_str(String [] val){
        String [] list = new String[3];
        for(int i = 0; i < val.length;i++){
            if(val[i].length() == 1){
                val[i] = "0"+val[i];
            }
        }
    }

    public String revertYear(String val){
        if(val.length() == 1 && val.charAt(0) >= '5' && val.charAt(0) <= '9'){
            return "200"+val;
        }
        if(val.length() == 2){
            return "20" + val;
        }
        return val;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Inferno YouTube Filtering");
        String css = this.getClass().getResource("index.css").toExternalForm();
        BorderPane mainPane = new BorderPane();
        
//-----------Set Title (top)-----------------------------------------------------------------------------------------------------------------------------------------------------------------
        HBox titleBox = new HBox();
        Label title = new Label("YouTube Filtering-Ext"); title.setId("title1");
        titleBox.setAlignment(Pos.CENTER);;titleBox.getChildren().add(title);

//-----------main area (center)-----------------------------------------------------------------------------------------------------------------------------------------------------------------
        Insets sInsets = new Insets(30, 0, 0, 0);
        GridPane centerPane = new GridPane(); centerPane.setPadding(sInsets);
        centerPane.setId("centerP");
        centerPane.setAlignment(Pos.TOP_CENTER);

        Label valueLabel = new Label("Enter what you're searching:");
        TextField valueField = new TextField();

        //Checkboxes
        HBox checkBoxes = new HBox();
        ToggleGroup asp = new ToggleGroup();
        RadioButton exactValue = new RadioButton("Exact Value"); exactValue.getStyleClass().add("checks");
        RadioButton inTitle = new RadioButton("In-title"); inTitle.getStyleClass().add("checks");
        RadioButton description = new RadioButton("In-description"); description.getStyleClass().add("checks");
        RadioButton tag = new RadioButton("In-Tag"); tag.getStyleClass().add("checks");
        //RadioButton specifierP = new RadioButton("Public"); specifierP.getStyleClass().add("checks");
        //RadioButton specifierPr = new RadioButton("Private"); specifierPr.getStyleClass().add("checks");
        RadioButton none = new RadioButton("None"); none.setSelected(true);
        asp.getToggles().addAll(exactValue, inTitle, description, tag, none);
        checkBoxes.getChildren().addAll(exactValue, inTitle, description, tag, none);
        checkBoxes.setPadding(new Insets(30,0,0,0));

        VBox resultsBox = new VBox();
        resultsBox.setPadding(new Insets(30,0,0,0));
        HBox printBtnCont = new HBox();
        Button print_result = new Button("Print"); print_result.setId("resBtn"); print_result.setMinWidth(100);
        printBtnCont.setAlignment(Pos.BASELINE_CENTER); printBtnCont.getChildren().add(print_result);
        printBtnCont.setPadding(new Insets(0,0,10,0));

        HBox resultsCont = new HBox();
        Label resultsLabel = new Label("Result: ");resultsLabel.setId("resultLabel");
        TextField results = new TextField();resultsCont.setHgrow(results, Priority.ALWAYS);
        results.setEditable(false);
        resultsCont.getChildren().addAll(resultsLabel, results);
        resultsBox.getChildren().addAll(printBtnCont, resultsCont);

        ColumnConstraints col4 = new ColumnConstraints(); col4.setPercentWidth(68);

        centerPane.setConstraints(valueLabel, 0, 0);
        centerPane.setConstraints(valueField, 0, 1);
        centerPane.setConstraints(checkBoxes, 0, 2);
        centerPane.setConstraints(resultsBox, 0, 3);

        centerPane.getChildren().addAll(valueLabel,valueField, checkBoxes, resultsBox);
        
//-----------date area (right)-----------------------------------------------------------------------------------------------------------------------------------------------------------------
        HBox rightTitle = new HBox();
        Label datePicks = new Label("Date");datePicks.setId("rightTitle");
        rightTitle.setAlignment(Pos.BASELINE_CENTER);

        GridPane rightArea = new GridPane();
        rightArea.setMinWidth(200);
        rightArea.setAlignment(Pos.BASELINE_LEFT);
        
        Label startLabel = new Label("Start Date:");
        DatePicker start = new DatePicker();

        start.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0){
                arg0.consume();
            }
        });

        Label endLabel = new Label("End Date:"); endLabel.setPadding(new Insets(10, 0,0,0));
        DatePicker end = new DatePicker();

        end.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0){
                System.out.println(arg0);
            }
        });
        start.setDisable(true);
        end.setDisable(true);

        //activate check buttons
        HBox dateCheckBox = new HBox(); dateCheckBox.setPadding(new Insets(10, 0, 0, 0));
        RadioButton dateActive = new RadioButton("Enable"); dateActive.setPadding(new Insets(0,0,0,10));
        RadioButton dateDisable = new RadioButton("Disable"); dateDisable.setSelected(true);
        ToggleGroup btGroup = new ToggleGroup();
        btGroup.getToggles().addAll(dateDisable, dateActive);
        dateCheckBox.getChildren().addAll(dateDisable, dateActive);

        dateActive.addEventFilter(ActionEvent.ACTION, (e)->{
            start.setDisable(false);
            end.setDisable(false);
        });
        dateDisable.addEventFilter(ActionEvent.ACTION, (e)->{
            start.setDisable(true);
            end.setDisable(true);
        });

        GridPane format = new GridPane();
        VBox rowFormat = new VBox(); rowFormat.setPadding(new Insets(10,0,0,0));
        Label formatLabel = new Label("Choose a valid date format: ");formatLabel.getStyleClass().add("fLabels");
        ToggleGroup formatGroup = new ToggleGroup();
        RadioButton format1 = new RadioButton("YYYY{-/}MM{-/}DD");format1.getStyleClass().add("fRadios");//format1.setPadding(new Insets(0,0,0,10));
        RadioButton format2 = new RadioButton("DD{-/}MM{-/}YYYY");format2.getStyleClass().add("fRadios");
        RadioButton format3 = new RadioButton("MM{-/}DD{-/}YYYY");format3.getStyleClass().add("fRadios");format3.setSelected(true);
        formatGroup.getToggles().addAll(format1, format2, format3);
        //Label formNote = new Label("Note:The default option is for if you use the date picker.");formNote.getStyleClass().add("fLabels");
        //formNote.setWrapText(true);formNote.setPrefWidth(180);
        rowFormat.getChildren().addAll(formatLabel, format3, format2, format1);

        format.setConstraints(rowFormat, 0, 0);
        format.getChildren().addAll(rowFormat);

        rightTitle.getChildren().add(datePicks);
        rightArea.setConstraints(rightTitle, 0, 0);
        rightArea.setConstraints(startLabel, 0, 1);
        rightArea.setConstraints(start, 0, 2);
        rightArea.setConstraints(endLabel, 0, 3);
        rightArea.setConstraints(end, 0, 4);
        rightArea.setConstraints(dateCheckBox, 0, 5);
        rightArea.setConstraints(format, 0, 6);

        rightArea.getChildren().addAll(rightTitle, startLabel, start, endLabel, end, dateCheckBox, format);
        rightArea.setPadding(new Insets(10,0,0,0));

//-----------results (center)-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    print_result.setOnAction((e)->{
        String [] temp = valueField.getText().split("==");
        if(temp.length > 0){
            final String pers = " OR ";
            String change = "";
            if(none.isSelected()){
                for(int a = 0; a < temp.length; a++){
                    change += "("+temp[a].strip()+")";
                    if(a != temp.length-1){
                        change+=pers;
                    }
                }
            }else{
                for(int i = 0; i < temp.length; i++){
                    //if(exactValue.isSelected()){temp = "\""+temp+"\"";}
                    //if(inTitle.isSelected()){temp="intitle:\""+temp+"\"";}
                    System.out.println(exactValue.isSelected());
                    if(i==0){
                        change = "(";
                    }
                    if(exactValue.isSelected()){
                        if(i!=temp.length-1){change=change+"\""+temp[i].strip()+"\""+pers;}
                        else{change=change+"\""+temp[i].strip()+"\"";}
                    }
                    else if(inTitle.isSelected()){
                        //if(i==0){change="(\"intitle:\""+temp[i].strip()+"\"";}
                        if(i<=temp.length-1){change=change+"intitle:\""+temp[i].strip()+"\"";}
                        if(i != temp.length-1){change+=pers;}
                        //else{change=change+")";}
                    }
                    else if(description.isSelected()){
                        if(i<=temp.length-1){change=change+"description:\""+temp[i].strip()+"\"";}
                        if(i != temp.length-1){change+=pers;}
                    }
                    else if(tag.isSelected()){
                        if(i<=temp.length-1){change=change+"#\""+temp[i].strip()+"\"";}
                        if(i != temp.length-1){change+=pers;}
                    }
                    /*else if(specifierP.isSelected()){
                        
                    }
                    else if(specifierPr.isSelected()){
                        
                    }*/
                    if(i == temp.length-1){
                        change = change + ")";
                    }
                    //System.out.println(temp[i].strip());
                }
            }
            if(!start.isDisabled()){
                Object [] formatsBtns = formatGroup.getToggles().toArray();

                for(int k = 0; k < formatsBtns.length; k++){
                    RadioButton val = (RadioButton)formatsBtns[k];
                    if(val.isSelected()){
                        if(start.getEditor().getText() != ""){
                            Boolean check = Boolean.valueOf(this.formatDates(start.getEditor().getText(), val.getText(), true));
                            if(check && !temp[0].equals("")){
                                String dateVal = this.formatDates(start.getEditor().getText(), val.getText(), false);
                                change += " after:"+String.valueOf(dateVal);
                            }
                        }
                        if(end.getEditor().getText() != ""){
                            Boolean check = Boolean.valueOf(this.formatDates(end.getEditor().getText(), val.getText(), true));
                            if(check && !temp[0].equals("")){
                                String dateVal = this.formatDates(end.getEditor().getText(), val.getText(), false);
                                change += " before:"+String.valueOf(dateVal);
                            }
                        }
                        System.out.println(this.formatDates(start.getEditor().getText(), val.getText(), true));
                    }
                }
                System.out.println(start.getValue());
                System.out.println(end.getValue());
            }
            results.setEditable(true);
            results.setText(change);
            results.setEditable(false);
            System.out.println(change);
        }
    });

/*-------//posting area where you put things on screen---and---set column constraints//-------------------------------------------------------------------------------------------------------------------------------------------------------------*/
        mainPane.setTop(titleBox);
        mainPane.setCenter(centerPane);
        mainPane.setRight(rightArea);
        scene = new Scene(mainPane, 680, 300);
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}