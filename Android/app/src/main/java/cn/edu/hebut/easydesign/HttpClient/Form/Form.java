package cn.edu.hebut.easydesign.HttpClient.Form;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Form {
    private List<FormField> fields = new ArrayList<>();
    private boolean multiPart = false;

    public RequestBody parse() throws Exception {
        if (multiPart) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            for (FormField f : fields) {
                f.addToFormBuilder(builder);
            }
            return builder.build();
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            for (FormField f : fields) {
                f.addToFormBuilder(builder);
            }
            Log.i("ED", "parse: success");
            return builder.build();
        }
    }

    // it will not check if a field which now exists has the same name with the new one.
    public Form AddFields(FormField field) {
        multiPart = field.ifMultiPart();
        fields.add(field);
        return this;
    }

    public void Replace(FormField oldField, FormField newField) {
        fields.remove(oldField);
        fields.add(newField);
    }
}
