package cn.edu.scau.cmi.colorCheck.activity.check;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.edu.scau.cmi.colorCheck.R;
import cn.edu.scau.cmi.colorCheck.asyncTask.FeatureAsyncTask;
import cn.edu.scau.cmi.colorCheck.asyncTask.PhotoAsyncTask;
import cn.edu.scau.cmi.colorCheck.asyncTask.ProjectAsyncTask;
import cn.edu.scau.cmi.colorCheck.asyncTask.RuleAsyncTask;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Project;
import cn.edu.scau.cmi.colorCheck.domain.mysql.Rule;
import cn.edu.scau.cmi.colorCheck.listener.TouchListenerAdapter;
import cn.edu.scau.cmi.colorCheck.util.FileUtil;
import cn.edu.scau.cmi.colorCheck.view.CameraPictureSurfaceView;

public class PictureCheckActivity extends AppCompatActivity {
    CameraPictureSurfaceView cameraPictureSurfaceView;
    Spinner projectSpinner;
    Spinner ruleSpinner;
    File currentFile;//当前的检测文件
    private static  List<Project> projectList;
    private static List<Rule> ruleList;
    private SharedPreferences sharePreferencesEditor;


    public void setProjectList(List<Project> projectList){
        this.projectList=projectList;
    }
    private ArrayAdapter<Project> projectAdapter;
    private ArrayAdapter<Rule> ruleAdapter;
    public ArrayAdapter getProjectAdapter(){
        return this.projectAdapter;
    }
    public Spinner getProjectSpinner(){
        return projectSpinner;
    }
    public Spinner getRuleSpinner(){
        return ruleSpinner;
    }
    Bundle bundle=new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        界面绘制
        setContentView(R.layout.activity_picture_check);
        initCameraView();
        initProjectSpinner();
        initRuleSinner();
        //       获取sharedPreferences
        sharePreferencesEditor=getSharedPreferences("colorCheckBitmaps",MODE_PRIVATE);
    }

    private void initRuleSinner() {
        //        TODO  待完善，是否需要添加监听器呢？
        ruleSpinner=findViewById(R.id.picture_check_rule_spinner);

       /* ruleSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ruleList =RuleAsyncTask.getAllRules();
                ArrayAdapter<Rule> ruleAdapter = new ArrayAdapter<Rule>(PictureCheckActivity.this, android.R.layout.simple_list_item_1, ruleList);
                ruleSpinner.setAdapter(ruleAdapter);

//              得到选中的项目，测试一下看能否得到Project对象。
                Project selectedProject = (Project) parent.getItemAtPosition(position);
                System.out.println(selectedProject);

                bundle.putString("selectedPorject",selectedProject.toString());
            }

        });*/
    }

    private void initProjectSpinner() {
        projectSpinner=findViewById(R.id.picture_check_project_spinner);
//TODO        选择了project后应该在选择项目对应的规则，
        projectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ruleList =RuleAsyncTask.getAllRules();
                ArrayAdapter<Rule> ruleAdapter = new ArrayAdapter<Rule>(PictureCheckActivity.this, android.R.layout.simple_list_item_1, ruleList);
                ruleSpinner.setAdapter(ruleAdapter);

//              得到选中的项目，测试一下看能否得到Project对象。
                Project selectedProject = (Project) parent.getItemAtPosition(position);
                System.out.println(selectedProject);

                bundle.putString("selectedPorject",selectedProject.toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initCameraView() {
        //        （1）摄像头处理
        cameraPictureSurfaceView =findViewById(R.id.picture_check_surface);
        //                在这里添加图片的显示部分，在结果界面中显示这个图表。
//                保持图片文件，如过不能保存图片，需要在手机中设置读取权限
        cameraPictureSurfaceView.setTouchListener(new TouchListenerAdapter(){
            @Override
            public void showPictureCheckResult(Bitmap bitmap) {
//              (1)点击图片，等待聚焦后，将保存图片到本地,文件名称是yyyyMMddhhmmss，OK。
                currentFile =FileUtil.getCurrentFile("PH","Check","");
                try {
                    FileUtil.saveColorCheckBitmap(bitmap, currentFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                （2）启动结果页面
                Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
                getAndTransationFeatureToPictureCheckResultActivity(currentFile, intent);
                startActivity(intent);
            }
        });
    }

    //获取图片的特征值并将这些特征值传递到结果页面
    private void getAndTransationFeatureToPictureCheckResultActivity(File file, Intent intent) {


//TODO       拍 照，特征抽取，结果显示，并显示成功。要不使用Service？？？先暂时用测试数据
        float[] redFeatureArrayList={1,140,2,140,3,140,4,140,5,140,6,141,7,141,8,141,9,141,10,141,11,141,12,141,13,142,14,142,15,142,16,142,17,142,18,142,19,142,20,142,21,142,22,142,23,142,24,142,25,142,26,142,27,142,28,142,29,142,30,142,31,142,32,142,33,142,34,142,35,142,36,142,37,142,38,144,39,144,40,144,41,144,42,144,43,144,44,144,45,144,46,144,47,144,48,144,49,144,50,144,51,144,52,144,53,144,54,144,55,145,56,145,57,145,58,145,59,144,60,144,61,144,62,144,63,143,64,142,65,141,66,141,67,140,68,141,69,142,70,143,71,144,72,144,73,144,74,145,75,145,76,145,77,147,78,147,79,147,80,146,81,146,82,146,83,146,84,147,85,147,86,147,87,147,88,147,89,147,90,147,91,147,92,147,93,147,94,147,95,147,96,147,97,147,98,147,99,147,100,147,101,147,102,147,103,147,104,147,105,147,106,147,107,148,108,148,109,148,110,148,111,147,112,147,113,147,114,147,115,149,116,149,117,149,118,149,119,149,120,149,121,149,122,149,123,149,124,149,125,149,126,149,127,149,128,149,129,149,130,149,131,149,132,149,133,149,134,149,135,149,136,149,137,149,138,149,139,149,140,149,141,149,142,149,143,149,144,149,145,149,146,149,147,149,148,149,149,149,150,149,151,149,152,149,153,150,154,151,155,150,156,150,157,150,158,150,159,150,160,150,161,150,162,150,163,150,164,150,165,150,166,150,167,150,168,150,169,150,170,150,171,151,172,150,173,150,174,150,175,150,176,150,177,150,178,150,179,150,180,150,181,150,182,150,183,150,184,150,185,150,186,150,187,150,188,150,189,150,190,150,191,152,192,151,193,151,194,151,195,151,196,151,197,151,198,151,199,151,200,151,201,151,202,151,203,151,204,151,205,151,206,151,207,151,208,151,209,151,210,151,211,151,212,151,213,151,214,151,215,151,216,151,217,151,218,151,219,151,220,151,221,151,222,151,223,150,224,150,225,150,226,150,227,150,228,150,229,151,230,151,231,150,232,150,233,150,234,150,235,150,236,149,237,149,238,148,239,146,240,146,241,144,242,143,243,141,244,139,245,137,246,134,247,130,248,128,249,125,250,121,251,121,252,120,253,118,254,117,255,116,256,115,257,115,258,114,259,112,260,111,261,110,262,109,263,107,264,106,265,105,266,104,267,104,268,103,269,104,270,106,271,110,272,113,273,117,274,119,275,122,276,128,277,136,278,142,279,147,280,148,281,148,282,148,283,149,284,151,285,152,286,153,287,153,288,153,289,153,290,153,291,152,292,152,293,152,294,152,295,152,296,152,297,152,298,152,299,152,300,152,301,152,302,152,303,152,304,152,305,152,306,153,307,153,308,153,309,153,310,153,311,154,312,154,313,154,314,153,315,154,316,154,317,154,318,154,319,154,320,154,321,153,322,153,323,153,324,153,325,153,326,153,327,153,328,153,329,153,330,153,331,153,332,153,333,153,334,153,335,153,336,153,337,153,338,153,339,153,340,153,341,153,342,153,343,153,344,153,345,151,346,151,347,151,348,151,349,150,350,150,351,151,352,151,353,151,354,151,355,151,356,151,357,151,358,151,359,151,360,151,361,151,362,151,363,150,364,150,365,150,366,150,367,150,368,150,369,150,370,150,371,150,372,150,373,150,374,150,375,150,376,150,377,150,378,150,379,150,380,150,381,150,382,150,383,148,384,148,385,148,386,147,387,147,388,147,389,147,390,147,391,147,392,147,393,147,394,147,395,147,396,147,397,147,398,147,399,147,400,147,401,147,402,147,403,147,404,147,405,147,406,146,407,146,408,146,409,146,410,146,411,146,412,146,413,146,414,146,415,146,416,146,417,146,418,146,419,145,420,145,421,145,422,144,423,144,424,144,425,144,426,144,427,143,428,143,429,143,430,143,431,143,432,143,433,143,434,143,435,143,436,143,437,143,438,143,439,143,440,143,441,143,442,143,443,143,444,143,445,142,446,142,447,142,448,142,449,142,450,142,451,142,452,142,453,142,454,142,455,142,456,142,457,142,458,142,459,142,460,140,461,140,462,140,463,140,464,140,465,140,466,140,467,140,468,140,469,140,470,140,471,140,472,139,473,139,474,139,475,139,476,139,477,139,478,139,479,139,480,139,481,139,482,139,483,139,484,139,485,139,486,139,487,139,488,139,489,139,490,139,491,139,492,138,493,138,494,138,495,138,496,138,497,138,498,137,499,137,500,136,501,136,502,136,503,136,504,136,505,136,506,136,507,136,508,136,509,136,510,136,511,136,512,136,513,136,514,136,515,135,516,135,517,135,518,135,519,135,520,135,521,135,522,135,523,135,524,135,525,135,526,135,527,135,528,135,529,135,530,134,531,134,532,134,533,135,534,134,535,135,536,133,537,133,538,133,539,133,540,133,541,132,542,133,543,132,544,132,545,132,546,132,547,132,548,132,549,132,550,132,551,132,552,132,553,132,554,132,555,132,556,132,557,132,558,132,559,132,560,132,561,132,562,132,563,132,564,131,565,131,566,131,567,131,568,131,569,131,570,131,571,131,572,131,573,131,574,129,575,129,576,129,577,129,578,129,579,129,580,129,581,129,582,129,583,129,584,128,585,128,586,128,587,128,588,128,589,128,590,128,591,128,592,128,593,128,594,128,595,128,596,128,597,128,598,128,599,128,600,128,601,128,602,128,603,128,604,128,605,128,606,128,607,128,608,128,609,128,610,128,611,128,612,126,613,126,614,126,615,126,616,126,617,126,618,126,619,126,620,126,621,126,622,126,623,125,624,125,625,125,626,125,627,125,628,125,629,125,630,125,631,125,632,125,633,125,634,125,635,125,636,125,637,125,638,125,639,125,640,125,641,124,642,124,643,124,644,124,645,124,646,124,647,124,648,124,649,124,650,124,651,122,652,122,653,122,654,122,655,122,656,122,657,122,658,122,659,122,660,121,661,121,662,121,663,120,664,120,665,120,666,119,667,119,668,119,669,118,670,86};
        float[] greenFeatureArrayList={1,142,2,143,3,143,4,143,5,143,6,143,7,143,8,143,9,143,10,143,11,143,12,143,13,143,14,143,15,143,16,143,17,143,18,143,19,144,20,144,21,144,22,144,23,144,24,144,25,144,26,144,27,144,28,144,29,144,30,144,31,144,32,144,33,144,34,144,35,144,36,144,37,144,38,146,39,146,40,146,41,146,42,146,43,146,44,146,45,146,46,146,47,146,48,146,49,146,50,146,51,146,52,146,53,146,54,146,55,146,56,146,57,146,58,146,59,146,60,146,61,146,62,146,63,146,64,146,65,146,66,145,67,144,68,143,69,142,70,140,71,140,72,140,73,141,74,144,75,144,76,145,77,147,78,147,79,147,80,147,81,147,82,147,83,147,84,147,85,147,86,147,87,147,88,147,89,147,90,147,91,147,92,147,93,147,94,147,95,147,96,147,97,147,98,147,99,147,100,147,101,147,102,147,103,147,104,147,105,147,106,147,107,147,108,147,109,147,110,147,111,147,112,147,113,147,114,147,115,149,116,149,117,149,118,149,119,149,120,149,121,149,122,149,123,149,124,149,125,149,126,149,127,149,128,149,129,149,130,149,131,149,132,149,133,148,134,148,135,148,136,148,137,149,138,149,139,149,140,149,141,148,142,148,143,148,144,148,145,148,146,148,147,148,148,148,149,148,150,148,151,148,152,148,153,150,154,150,155,150,156,150,157,150,158,150,159,150,160,150,161,150,162,150,163,150,164,150,165,150,166,150,167,150,168,150,169,150,170,150,171,150,172,150,173,150,174,150,175,150,176,150,177,150,178,150,179,150,180,150,181,150,182,150,183,150,184,150,185,150,186,150,187,150,188,150,189,150,190,150,191,151,192,151,193,151,194,151,195,151,196,151,197,151,198,151,199,151,200,151,201,151,202,151,203,151,204,151,205,151,206,151,207,151,208,151,209,150,210,150,211,150,212,150,213,150,214,150,215,151,216,151,217,151,218,151,219,150,220,150,221,150,222,150,223,150,224,150,225,150,226,150,227,151,228,150,229,152,230,152,231,152,232,152,233,152,234,152,235,151,236,150,237,150,238,150,239,149,240,149,241,148,242,146,243,144,244,141,245,137,246,134,247,129,248,125,249,121,250,117,251,113,252,110,253,107,254,105,255,103,256,101,257,100,258,98,259,97,260,96,261,94,262,93,263,91,264,90,265,88,266,87,267,86,268,85,269,83,270,81,271,80,272,80,273,80,274,83,275,87,276,93,277,101,278,109,279,118,280,127,281,135,282,141,283,144,284,147,285,148,286,149,287,150,288,151,289,151,290,152,291,152,292,152,293,152,294,152,295,152,296,152,297,152,298,152,299,152,300,152,301,151,302,151,303,151,304,151,305,151,306,153,307,153,308,153,309,153,310,153,311,153,312,153,313,153,314,153,315,153,316,153,317,153,318,153,319,153,320,153,321,153,322,153,323,153,324,153,325,153,326,153,327,153,328,153,329,153,330,153,331,153,332,153,333,153,334,153,335,153,336,153,337,153,338,153,339,153,340,153,341,152,342,152,343,152,344,152,345,150,346,150,347,151,348,151,349,150,350,150,351,150,352,150,353,150,354,150,355,150,356,150,357,150,358,150,359,150,360,150,361,150,362,150,363,150,364,150,365,150,366,150,367,150,368,150,369,150,370,150,371,150,372,150,373,150,374,150,375,150,376,150,377,150,378,150,379,150,380,150,381,149,382,149,383,148,384,148,385,148,386,148,387,147,388,147,389,147,390,147,391,147,392,147,393,147,394,147,395,147,396,147,397,147,398,147,399,147,400,147,401,147,402,147,403,147,404,147,405,147,406,146,407,146,408,146,409,146,410,146,411,146,412,146,413,146,414,146,415,146,416,146,417,146,418,146,419,146,420,146,421,146,422,144,423,144,424,144,425,144,426,144,427,144,428,144,429,144,430,144,431,144,432,144,433,144,434,144,435,144,436,144,437,144,438,144,439,144,440,144,441,144,442,144,443,144,444,144,445,144,446,144,447,144,448,144,449,144,450,144,451,143,452,143,453,143,454,143,455,143,456,143,457,143,458,143,459,143,460,141,461,141,462,141,463,141,464,141,465,141,466,141,467,141,468,141,469,141,470,141,471,141,472,141,473,141,474,141,475,141,476,141,477,140,478,140,479,140,480,140,481,140,482,140,483,140,484,140,485,140,486,140,487,140,488,140,489,140,490,140,491,140,492,140,493,140,494,140,495,140,496,139,497,140,498,138,499,138,500,138,501,138,502,138,503,138,504,138,505,138,506,138,507,137,508,137,509,137,510,137,511,137,512,137,513,137,514,137,515,137,516,137,517,137,518,137,519,137,520,137,521,137,522,137,523,137,524,137,525,137,526,137,527,137,528,137,529,137,530,137,531,137,532,137,533,137,534,136,535,136,536,135,537,135,538,135,539,135,540,135,541,135,542,135,543,135,544,135,545,135,546,134,547,134,548,134,549,134,550,134,551,134,552,134,553,134,554,134,555,134,556,134,557,134,558,134,559,134,560,134,561,134,562,134,563,134,564,134,565,134,566,134,567,134,568,134,569,134,570,133,571,133,572,133,573,133,574,132,575,132,576,132,577,132,578,132,579,131,580,132,581,132,582,132,583,131,584,131,585,131,586,131,587,131,588,131,589,131,590,131,591,131,592,131,593,131,594,131,595,131,596,131,597,131,598,131,599,131,600,131,601,131,602,131,603,131,604,130,605,130,606,130,607,130,608,130,609,130,610,130,611,130,612,128,613,128,614,128,615,128,616,128,617,128,618,128,619,128,620,128,621,128,622,128,623,128,624,128,625,128,626,128,627,128,628,128,629,128,630,128,631,127,632,127,633,127,634,127,635,127,636,127,637,127,638,127,639,127,640,127,641,127,642,127,643,127,644,127,645,127,646,127,647,127,648,127,649,126,650,126,651,125,652,125,653,125,654,124,655,124,656,124,657,124,658,124,659,123,660,123,661,123,662,122,663,122,664,122,665,121,666,121,667,120,668,120,669,119,670,87};
        float[] blueFeatureArrayList={1,144,2,144,3,144,4,144,5,144,6,144,7,144,8,144,9,144,10,144,11,144,12,144,13,144,14,144,15,144,16,144,17,145,18,145,19,145,20,145,21,145,22,145,23,145,24,145,25,145,26,145,27,145,28,145,29,145,30,145,31,145,32,145,33,145,34,145,35,145,36,145,37,145,38,147,39,147,40,147,41,147,42,147,43,147,44,147,45,147,46,147,47,147,48,147,49,147,50,147,51,147,52,147,53,147,54,147,55,147,56,147,57,147,58,147,59,147,60,147,61,147,62,147,63,147,64,147,65,147,66,147,67,147,68,147,69,148,70,146,71,144,72,141,73,138,74,138,75,144,76,145,77,147,78,148,79,148,80,149,81,149,82,149,83,148,84,148,85,149,86,149,87,149,88,149,89,149,90,149,91,149,92,149,93,149,94,149,95,149,96,149,97,149,98,149,99,149,100,149,101,149,102,149,103,149,104,149,105,149,106,149,107,149,108,149,109,149,110,149,111,149,112,149,113,149,114,149,115,150,116,150,117,150,118,150,119,150,120,150,121,150,122,150,123,151,124,151,125,150,126,150,127,150,128,150,129,150,130,150,131,150,132,150,133,150,134,150,135,150,136,150,137,150,138,150,139,150,140,150,141,150,142,150,143,150,144,150,145,150,146,150,147,150,148,150,149,150,150,150,151,150,152,150,153,151,154,152,155,152,156,152,157,151,158,151,159,152,160,152,161,152,162,152,163,151,164,151,165,151,166,151,167,152,168,151,169,151,170,151,171,151,172,151,173,151,174,151,175,151,176,151,177,151,178,151,179,151,180,151,181,151,182,151,183,151,184,151,185,151,186,150,187,150,188,150,189,150,190,150,191,152,192,152,193,152,194,152,195,152,196,152,197,152,198,152,199,152,200,152,201,152,202,152,203,152,204,152,205,152,206,152,207,152,208,152,209,152,210,152,211,152,212,152,213,152,214,152,215,152,216,152,217,152,218,152,219,152,220,152,221,152,222,152,223,152,224,152,225,152,226,152,227,152,228,152,229,153,230,153,231,153,232,153,233,153,234,153,235,154,236,154,237,154,238,154,239,153,240,153,241,153,242,152,243,152,244,150,245,149,246,147,247,144,248,142,249,139,250,136,251,133,252,131,253,128,254,126,255,125,256,123,257,122,258,121,259,120,260,119,261,117,262,116,263,114,264,113,265,112,266,110,267,108,268,107,269,107,270,105,271,105,272,102,273,101,274,99,275,99,276,102,277,106,278,112,279,120,280,127,281,134,282,139,283,144,284,146,285,148,286,149,287,151,288,151,289,151,290,152,291,152,292,152,293,153,294,153,295,153,296,153,297,153,298,152,299,152,300,152,301,152,302,152,303,153,304,153,305,153,306,154,307,154,308,154,309,155,310,155,311,155,312,155,313,155,314,155,315,155,316,155,317,155,318,155,319,155,320,155,321,155,322,155,323,155,324,155,325,155,326,155,327,155,328,155,329,155,330,155,331,154,332,155,333,155,334,154,335,154,336,154,337,154,338,154,339,154,340,154,341,154,342,154,343,154,344,154,345,152,346,152,347,152,348,152,349,152,350,152,351,152,352,152,353,152,354,152,355,152,356,152,357,152,358,152,359,152,360,152,361,152,362,152,363,152,364,152,365,152,366,152,367,152,368,152,369,152,370,152,371,152,372,152,373,151,374,151,375,151,376,151,377,151,378,151,379,151,380,151,381,151,382,151,383,150,384,150,385,149,386,149,387,149,388,149,389,149,390,149,391,149,392,149,393,149,394,149,395,149,396,149,397,149,398,149,399,149,400,149,401,149,402,149,403,149,404,149,405,149,406,149,407,149,408,149,409,149,410,149,411,149,412,149,413,149,414,149,415,148,416,148,417,148,418,148,419,148,420,148,421,148,422,146,423,146,424,147,425,147,426,146,427,147,428,147,429,147,430,147,431,147,432,147,433,147,434,147,435,146,436,147,437,147,438,147,439,147,440,147,441,147,442,147,443,146,444,146,445,146,446,146,447,146,448,146,449,146,450,146,451,146,452,146,453,146,454,146,455,146,456,146,457,146,458,146,459,146,460,144,461,144,462,144,463,144,464,144,465,144,466,144,467,144,468,144,469,144,470,144,471,144,472,144,473,144,474,144,475,144,476,144,477,144,478,144,479,144,480,144,481,144,482,144,483,144,484,144,485,144,486,144,487,144,488,144,489,143,490,143,491,143,492,143,493,143,494,143,495,143,496,143,497,143,498,142,499,141,500,141,501,141,502,141,503,141,504,141,505,141,506,141,507,141,508,141,509,141,510,141,511,141,512,141,513,141,514,141,515,141,516,141,517,141,518,141,519,141,520,141,521,141,522,141,523,141,524,141,525,140,526,140,527,140,528,140,529,140,530,140,531,140,532,140,533,140,534,140,535,140,536,138,537,138,538,138,539,138,540,138,541,138,542,138,543,138,544,138,545,138,546,138,547,138,548,138,549,138,550,138,551,138,552,138,553,138,554,138,555,138,556,138,557,138,558,138,559,138,560,138,561,138,562,138,563,138,564,137,565,137,566,137,567,137,568,137,569,137,570,137,571,137,572,137,573,137,574,136,575,136,576,136,577,136,578,135,579,135,580,135,581,135,582,135,583,135,584,135,585,135,586,135,587,135,588,135,589,135,590,135,591,135,592,135,593,135,594,135,595,135,596,135,597,135,598,135,599,135,600,135,601,135,602,135,603,135,604,135,605,134,606,134,607,134,608,134,609,134,610,134,611,134,612,133,613,133,614,133,615,133,616,133,617,133,618,133,619,133,620,133,621,133,622,133,623,132,624,132,625,132,626,132,627,132,628,132,629,132,630,132,631,132,632,132,633,132,634,132,635,132,636,132,637,132,638,132,639,132,640,132,641,132,642,132,643,132,644,131,645,132,646,131,647,131,648,131,649,131,650,131,651,129,652,129,653,129,654,129,655,129,656,129,657,129,658,129,659,130,660,129,661,129,662,129,663,129,664,129,665,128,666,128,667,127,668,127,669,126,670,93};
        float[] grayFeatureArrayList={1,103,2,140,3,140,4,140,5,140,6,140,7,140,8,140,9,140,10,140,11,140,12,140,13,140,14,140,15,140,16,140,17,140,18,140,19,140,20,140,21,140,22,142,23,142,24,143,25,143,26,143,27,143,28,143,29,143,30,143,31,143,32,143,33,143,34,143,35,143,36,143,37,143,38,143,39,143,40,143,41,144,42,144,43,144,44,144,45,144,46,144,47,144,48,144,49,144,50,144,51,144,52,144,53,144,54,144,55,144,56,144,57,144,58,144,59,144,60,146,61,146,62,146,63,146,64,146,65,146,66,146,67,146,68,146,69,146,70,146,71,146,72,146,73,146,74,146,75,146,76,146,77,146,78,146,79,146,80,146,81,146,82,146,83,146,84,146,85,146,86,146,87,146,88,145,89,144,90,144,91,144,92,142,93,142,94,141,95,140,96,142,97,144,98,145,99,147,100,147,101,147,102,147,103,147,104,147,105,147,106,147,107,148,108,148,109,148,110,148,111,148,112,148,113,148,114,148,115,148,116,148,117,148,118,148,119,148,120,148,121,148,122,148,123,148,124,148,125,148,126,148,127,148,128,148,129,148,130,148,131,148,132,148,133,148,134,148,135,148,136,148,137,149,138,149,139,149,140,149,141,149,142,149,143,149,144,149,145,150,146,150,147,149,148,149,149,149,150,149,151,149,152,149,153,149,154,149,155,149,156,149,157,149,158,149,159,149,160,149,161,149,162,149,163,149,164,149,165,149,166,149,167,149,168,149,169,149,170,149,171,149,172,149,173,149,174,149,175,150,176,151,177,151,178,151,179,150,180,150,181,151,182,151,183,151,184,151,185,150,186,150,187,150,188,150,189,151,190,150,191,150,192,150,193,150,194,150,195,150,196,150,197,150,198,150,199,150,200,150,201,150,202,150,203,150,204,150,205,150,206,150,207,150,208,150,209,150,210,150,211,150,212,150,213,151,214,151,215,151,216,151,217,151,218,151,219,151,220,151,221,151,222,151,223,151,224,151,225,151,226,151,227,151,228,151,229,151,230,151,231,151,232,151,233,151,234,151,235,151,236,151,237,151,238,151,239,151,240,151,241,151,242,151,243,151,244,151,245,151,246,151,247,151,248,151,249,151,250,151,251,152,252,152,253,152,254,152,255,152,256,152,257,152,258,151,259,151,260,151,261,150,262,150,263,149,264,147,265,146,266,143,267,141,268,138,269,134,270,130,271,127,272,123,273,120,274,117,275,115,276,113,277,111,278,109,279,108,280,107,281,106,282,105,283,103,284,102,285,100,286,99,287,97,288,96,289,95,290,94,291,93,292,91,293,91,294,90,295,90,296,92,297,95,298,100,299,106,300,114,301,122,302,129,303,136,304,141,305,145,306,147,307,148,308,149,309,151,310,151,311,151,312,152,313,152,314,152,315,152,316,152,317,152,318,152,319,152,320,152,321,152,322,152,323,151,324,151,325,152,326,152,327,152,328,153,329,153,330,153,331,154,332,154,333,154,334,154,335,154,336,154,337,154,338,154,339,154,340,154,341,154,342,154,343,154,344,154,345,154,346,154,347,154,348,154,349,154,350,154,351,154,352,154,353,153,354,154,355,154,356,153,357,153,358,153,359,153,360,153,361,153,362,153,363,153,364,153,365,153,366,153,367,151,368,151,369,151,370,151,371,151,372,151,373,151,374,151,375,151,376,151,377,151,378,151,379,151,380,151,381,151,382,151,383,151,384,151,385,151,386,151,387,151,388,151,389,151,390,151,391,151,392,151,393,151,394,151,395,150,396,150,397,150,398,150,399,150,400,150,401,150,402,150,403,150,404,150,405,149,406,149,407,148,408,148,409,148,410,148,411,148,412,148,413,148,414,148,415,148,416,148,417,148,418,148,419,148,420,148,421,148,422,148,423,148,424,148,425,148,426,148,427,148,428,147,429,147,430,147,431,147,432,147,433,147,434,147,435,147,436,147,437,147,438,147,439,147,440,147,441,146,442,146,443,146,444,145,445,145,446,145,447,145,448,145,449,145,450,145,451,145,452,145,453,145,454,145,455,145,456,145,457,144,458,145,459,145,460,145,461,145,462,145,463,145,464,145,465,144,466,144,467,144,468,144,469,144,470,144,471,144,472,144,473,144,474,144,475,144,476,144,477,144,478,144,479,144,480,144,481,144,482,142,483,142,484,142,485,142,486,142,487,142,488,142,489,142,490,142,491,142,492,142,493,142,494,142,495,142,496,142,497,142,498,142,499,141,500,141,501,141,502,141,503,141,504,141,505,141,506,141,507,141,508,141,509,141,510,141,511,141,512,141,513,141,514,141,515,141,516,141,517,141,518,140,519,141,520,139,521,139,522,139,523,139,524,139,525,139,526,139,527,139,528,139,529,138,530,138,531,138,532,138,533,138,534,138,535,138,536,138,537,138,538,138,539,138,540,138,541,138,542,138,543,138,544,138,545,138,546,138,547,138,548,138,549,138,550,138,551,138,552,138,553,138,554,138,555,138,556,137,557,137,558,136,559,136,560,136,561,136,562,136,563,136,564,136,565,136,566,136,567,136,568,135,569,135,570,135,571,135,572,135,573,135,574,135,575,135,576,135,577,135,578,135,579,135,580,135,581,135,582,135,583,135,584,135,585,135,586,135,587,135,588,135,589,135,590,135,591,135,592,134,593,134,594,134,595,134,596,133,597,133,598,133,599,133,600,133,601,132,602,133,603,133,604,133,605,132,606,132,607,132,608,132,609,132,610,132,611,132,612,132,613,132,614,132,615,132,616,132,617,132,618,132,619,132,620,132,621,132,622,132,623,132,624,132,625,132,626,131,627,131,628,131,629,131,630,131,631,131,632,131,633,131,634,129,635,129,636,129,637,129,638,129,639,129,640,129,641,129,642,129,643,129,644,129,645,129,646,129,647,129,648,129,649,129,650,129,651,129,652,129,653,128,654,128,655,128,656,128,657,128,658,128,659,128,660,128,661,128,662,128,663,128,664,128,665,128,666,128,667,128,668,128,669,128,670,128,671,127,672,127,673,126,674,126,675,126,676,125,677,125,678,125,679,125,680,125,681,125,682,125,683,125,684,124,685,124,686,124,687,123,688,123,689,122,690,122,691,121,692,89};

        bundle.putSerializable("checkBitmap",file);
        bundle.putSerializable("red",redFeatureArrayList);//也可以使用可序列化的对象！！！
        bundle.putFloatArray("redFeature",redFeatureArrayList);
        bundle.putFloatArray("greenFeature",greenFeatureArrayList);
        bundle.putFloatArray("blueFeature",blueFeatureArrayList);
        bundle.putFloatArray("grayFeature",grayFeatureArrayList);
        intent.putExtras(bundle);
    }

    //点击初始化按钮，Spinner设置初始值
    public void onPictureCheckGainData(View view){
        projectList=ProjectAsyncTask.getAllProject();
        projectAdapter=new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1,projectList);
        projectSpinner.setAdapter(projectAdapter);

        ruleList=RuleAsyncTask.getAllRules();
        ruleAdapter=new ArrayAdapter<Rule>(this, android.R.layout.simple_list_item_1,ruleList);
        ruleSpinner.setAdapter(ruleAdapter);
    }
//TODO 上传并在结果页面中显示检测结果
    public void onUploadPictureCheckBitMapAndCheck(View view){
//        上传文件
        PhotoAsyncTask photoAsyncTask=new PhotoAsyncTask(PictureCheckActivity.this,sharePreferencesEditor, currentFile);
        photoAsyncTask.execute();
//        获取特征值
        FeatureAsyncTask featureAsyncTask=new FeatureAsyncTask(this, currentFile);
        featureAsyncTask.execute();

//        TODO 显示结果,to be tested
        Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
        getAndTransationFeatureToPictureCheckResultActivity(currentFile, intent);
        startActivity(intent);
    }

    //上传所有的没上传的图片文件，调用PhotoAsyncTask，PhotoAsyncTask调用HttpUtil完成图像上传工作。OK
    public void onUploadAllPictureCheckBitMap(View view){
        PhotoAsyncTask photoAsyncTask=new PhotoAsyncTask(PictureCheckActivity.this,sharePreferencesEditor,null);
        photoAsyncTask.execute();
    }

//点击图片后，显示结果界面，
//    public void onPictureCheck(View view){
////      在这里将图片的检测结果作为参数在结果界面中显示这个图表。
////        获取图片！！！
//        Intent intent=new Intent(PictureCheckActivity.this, PictureCheckResultActivity.class);
////        将选中的项目和规则以及图片传到后台
//
//        startActivity(intent);
//    }
}