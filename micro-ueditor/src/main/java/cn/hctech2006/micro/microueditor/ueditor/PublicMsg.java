package cn.hctech2006.micro.microueditor.ueditor;

public class PublicMsg {
    public final static String UEDITOR_CONFIG = "{\n" +
            "    \"imageActionName\": \"uploadimage\",\n" +
            "    \"imageFieldName\": \"upfile\",\n" +
            "    \"imageMaxSize\": 2048000,\n" +
            "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
            "    \"imageCompressEnable\": true,\n" +
            "    \"imageCompressBorder\": 1600,\n" +
            "    \"imageInsertAlign\": \"none\",\n" +
            "    \"imageUrlPrefix\": \"\",\n" +
            "    \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
            "\n" +
            "    \"scrawlActionName\": \"uploadscrawl\",\n" +
            "    \"scrawlFieldName\": \"upfile\",\n" +
            "    \"scrawlPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
            "    \"scrawlMaxSize\": 2048000,\n" +
            "    \"scrawlUrlPrefix\": \"\",\n" +
            "    \"scrawlInsertAlign\": \"none\",\n" +
            "\n" +
            "    \"snapscreenActionName\": \"uploadimage\",\n" +
            "    \"snapscreenPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
            "    \"snapscreenUrlPrefix\": \"\",\n" +
            "    \"snapscreenInsertAlign\": \"none\",\n" +
            "\n" +
            "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"],\n" +
            "    \"catcherActionName\": \"catchimage\",\n" +
            "    \"catcherFieldName\": \"source\",\n" +
            "    \"catcherPathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
            "    \"catcherUrlPrefix\": \"\",\n" +
            "    \"catcherMaxSize\": 2048000,\n" +
            "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
            "\n" +
            "    \"videoActionName\": \"uploadvideo\",\n" +
            "    \"videoFieldName\": \"upfile\",\n" +
            "    \"videoPathFormat\": \"/ueditor/jsp/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
            "    \"videoUrlPrefix\": \"\",\n" +
            "    \"videoMaxSize\": 102400000,\n" +
            "    \"videoAllowFiles\": [\n" +
            "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
            "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"],\n" +
            "\n" +
            "    \"fileActionName\": \"uploadfile\",\n" +
            "    \"fileFieldName\": \"upfile\",\n" +
            "    \"filePathFormat\": \"/ueditor/jsp/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}\",\n" +
            "    \"fileUrlPrefix\": \"\",\n" +
            "    \"fileMaxSize\": 51200000,\n" +
            "    \"fileAllowFiles\": [\n" +
            "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
            "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
            "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
            "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
            "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
            "    ],\n" +
            "\n" +
            "    \"imageManagerActionName\": \"listimage\",\n" +
            "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\",\n" +
            "    \"imageManagerListSize\": 20,\n" +
            "    \"imageManagerUrlPrefix\": \"\",\n" +
            "    \"imageManagerInsertAlign\": \"none\",\n" +
            "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
            "\n" +
            "    \"fileManagerActionName\": \"listfile\",\n" +
            "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\",\n" +
            "    \"fileManagerUrlPrefix\": \"\",\n" +
            "    \"fileManagerListSize\": 20,\n" +
            "    \"fileManagerAllowFiles\": [\n" +
            "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
            "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
            "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
            "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
            "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
            "    ] \n" +
            "\n" +
            "}";
    /**
     * Ueditor的返回状态类型
     */
    public enum UeditorMsg{
        SUCCESS("SUCCESS"),ERROR("上传失败");
        private String v;
        UeditorMsg(String v){
            this.v =v;
        }
        public String get(){
            return this.v;
        }
    }
}