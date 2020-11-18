package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Tasks;
public class TasksValidator {
public static List<String> validate(Tasks ta){
    List<String> errors = new ArrayList<String>();



    String content_error = _validateContent(ta.getContent());
    if(!content_error.equals("")) {
        errors.add(content_error);
    }

    return errors;
}





private static String _validateContent(String content) {
    if(content == null || content.trim().equals("")) {
        return "メッセージを入力してください。";
    }

    return "";
}
}
