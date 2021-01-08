package com.bootdo.clouddoexam.dto.convert;


import com.bootdo.clouddoexam.domain.FileDO;
import com.bootdo.clouddoexam.dto.FileDTO;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper
public interface FileConvert {
    FileConvert MAPPER = Mappers.getMapper(FileConvert.class);

    public FileDTO do2dto(FileDO person);

    public List<FileDTO> dos2dtos(List<FileDO> list);
}