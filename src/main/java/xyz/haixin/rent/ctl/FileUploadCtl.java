package xyz.haixin.rent.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.haixin.rent.mapper.FileRentMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileUploadCtl {
    @Autowired
    FileRentMapper fileRentMapper;
    @RequestMapping("/imageUpload")
    public Map<String,Object> imageUpload(@RequestParam("file") MultipartFile multipartFile)  {
        String fileSavePath="/img/";
        if (null == multipartFile || multipartFile.getSize() <= 0) {
            return new HashMap<String,Object>(){{put("code",400);put("msg","请选择上传文件。");}};
        }
        //文件名
        String originalName = multipartFile.getOriginalFilename();
        String fileName= UUID.randomUUID().toString().replace("-", "");
        String picNewName = fileName + originalName.substring(originalName.lastIndexOf("."));
        String imgRealPath = fileSavePath + picNewName;
        try {
            //保存图片-将multipartFile对象装入image文件中
            File imageFile=new File(imgRealPath);
            multipartFile.transferTo(imageFile);
        } catch (Exception e) {
            return new HashMap<String,Object>(){{put("code",400);put("msg","图片保存异常:"+e);}};
        }
        return new HashMap<String,Object>(){{put("code",200);put("msg",picNewName);}};
    }
}
