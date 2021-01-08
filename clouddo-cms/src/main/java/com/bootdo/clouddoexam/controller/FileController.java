package com.bootdo.clouddoexam.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.clouddocommon.context.FilterContextHandler;
import com.bootdo.clouddocommon.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.bootdo.clouddoexam.domain.FileDO;
import com.bootdo.clouddoexam.dto.FileDTO;
import com.bootdo.clouddoexam.dto.convert.FileConvert;
import com.bootdo.clouddoexam.service.FileService;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-12 12:17:36
 */

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${app.filePath}")
    String filePath;

    @Value("${app.pre}")
    String filePre;

    @Autowired
    private FileService fileService;

    @GetMapping("{id}")
    public R get(@PathVariable Long id) {
        FileDTO fileDTO = FileConvert.MAPPER.do2dto(fileService.get(id));
        return R.data(fileDTO);
    }

    @GetMapping("getToken")
    public R getToken() {
        return R.ok().put("token", FilterContextHandler.getToken()).put("url", "http://114.115.178.160:8002/api-cms/file/upload")
                .put("key", UUID.randomUUID().toString());
    }

    @PostMapping("upload")
    public R upload(MultipartFile file, String key) {
        try {
            String resPath = FileUtils.saveFile(file.getBytes(), filePath, key);
          /*  fileService.save(new FileDO() {{
                setCreateDate(new Date());
                setUrl("http://localhost:8004" + filePre + "/"+resPath);
                setType(1);
            }});*/
          //插入图片数据
            return R.ok().put("resPath", resPath);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error("文件上传失败");
        }
    }

    /**
     * 分页查询
     */
    @GetMapping
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<FileDO> fileList = fileService.list(query);
//        List<FileDTO> fileDTOS = FileConvert.MAPPER.dos2dtos(fileList);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        int total = fileService.count(query);
//        PageUtils pageUtils = new PageUtils(fileDTOS, total);
        PageUtils pageUtils = new PageUtils(fileList, total);
        return R.page(pageUtils);
    }

    /**
     * 保存
     */
    @PostMapping
    public R save(FileDO file) {
        return R.operate(fileService.save(file) > 0);
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public R update(@RequestBody FileDO file) {
        System.out.println(">>>>>>>>>AAAAA"+file.getCreateDate());
        return R.operate(fileService.update(file) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping
    public R remove(Long id) {
        return R.operate(fileService.remove(id) > 0);
    }

    /**
     * 删除
     */
    @DeleteMapping("/batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        return R.operate(fileService.batchRemove(ids) > 0);
    }
}
